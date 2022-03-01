package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class invoice_table {

	public static void create_table() {

		String sql = "create table invoice(" + "id varchar(50) primary key ," + "invoice_date date  not null ,"
				+ "end_date date not null ," + "member_id  varchar(50) not null ,  "
				+ " membership_id  varchar(50) not null," + "admin_id  varchar(50)  not null ,"
				+ " FOREIGN KEY (`member_id`) REFERENCES `member`(`id`) ON UPDATE CASCADE ON DELETE CASCADE,"
				+ " FOREIGN KEY (`membership_id`) REFERENCES `membership`(`membership_id`) ON UPDATE CASCADE ON DELETE CASCADE,"
				+ "FOREIGN KEY (`admin_id`) REFERENCES `admin`(`id`) ON UPDATE CASCADE ON DELETE CASCADE);";

		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.execute();
			System.out.println("invoice table created");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void delete_table() {
		String sql = "Drop table invoice";
		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.execute();
			System.out.println("invoice table deleted");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void insert_invoice(Member m, Membership ms, String a, LocalDate enddate) {

		String id = "INV-000001";
		String sql = "INSERT INTO `invoice`( `id`,`invoice_date`,  `end_date`, `member_id`, `membership_id`, `admin_id`) "
				+ "VALUES (?,?,?,?,?,?)";

		String sql1 = "Select id from invoice order by id desc limit 1";

		try (Connection con = ConnectDB.CreateConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				PreparedStatement ps1 = con.prepareStatement(sql1)) {

			ResultSet rs = ps1.executeQuery();

			if (rs.next()) {
				String s = rs.getString(1);
				String i[] = s.split("-");
				int no = Integer.valueOf(i[1]);
				++no;
				id = String.format("INV-%06d", no);

			} else {
				id = "INV-000001";
			}

			ps.setString(1, id);
			ps.setDate(2, Date.valueOf(LocalDate.now()));
			ps.setDate(3, Date.valueOf(enddate));
			ps.setString(4, m.getId());
			ps.setString(5, ms.getId());
			ps.setString(6, a);
			ps.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String[] show_lastrow() {

		String sql = "SELECT  *  FROM invoice ORDER BY id DESC LIMIT 1;";
		String sql1 = "SELECT  *  FROM  membership where membership_id=?";
		String sql2 = "SELECT  name  FROM  member where id=?";
		String s[] = new String[2];
		try (Connection con = ConnectDB.CreateConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				PreparedStatement ps1 = con.prepareStatement(sql1);
				PreparedStatement ps2 = con.prepareStatement(sql2)) {

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String member_id = rs.getString(4);
				String membership = rs.getString(5);
				ps2.setString(1, member_id);
				ps1.setString(1, membership);

				ResultSet r1 = ps1.executeQuery();
				ResultSet r2 = ps2.executeQuery();
				if (r1.next()) {
					s[0] = r1.getString(2);
				}
				if (r2.next()) {
					s[1] = r2.getString(1);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static ArrayList<invoice> ShowInvoices() {
		ArrayList<invoice> i = new ArrayList<>();

		String sql = "SELECT * FROM `invoice` , member , membership  WHERE invoice.member_id=member.id and invoice.membership_id=membership.membership_id order by invoice.id desc ";
		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				i.add(new invoice(rs.getString("invoice.id"), rs.getDate("invoice.end_date").toLocalDate(),
						rs.getDate("invoice_date").toLocalDate(), rs.getString("membership.membership_name"),
						rs.getString("member.id"), rs.getString("member.name"), rs.getString("member.email")));
				System.err.println(rs.getString("member.id"));
			}

			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static ArrayList<invoice> ShowInvoices_ByNames(String name) {
		ArrayList<invoice> i = new ArrayList<>();

		String sql = "SELECT * FROM `invoice` , member , membership  WHERE invoice.member_id=member.id and invoice.membership_id=membership.membership_id and member.name like ? order by invoice.id desc ";
		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				i.add(new invoice(rs.getString("invoice.id"), rs.getDate("invoice.end_date").toLocalDate(),
						rs.getDate("invoice_date").toLocalDate(), rs.getString("membership.membership_name"),
						rs.getString("member.id"), rs.getString("member.name"), rs.getString("member.email")));
				System.err.println(rs.getString("member.id"));
			}

			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static LocalDate lastEndDate(String email) {
		LocalDate endDate = null;
		String sql = String.format(
				"SELECT invoice.end_date FROM invoice LEFT JOIN member ON member.id = invoice.member_id WHERE member.email = '%s' ORDER BY invoice.id DESC LIMIT 1;",
				email);
		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				endDate = rs.getDate("end_date").toLocalDate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return endDate;

	}

	public static ArrayList<invoice> ShowInvoices_ByDate(LocalDate start, LocalDate end) {
		ArrayList<invoice> i = new ArrayList<>();
		if (start == null) {
			start = LocalDate.now();
		}
		if (end == null) {
			end = LocalDate.now();
		}

		String sql = "SELECT * FROM `invoice` , member , membership  WHERE invoice.member_id=member.id and invoice.membership_id=membership.membership_id and invoice.invoice_date between ? and ?  order by invoice.id desc ";
		try (Connection con = ConnectDB.CreateConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setDate(1, Date.valueOf(start));
			ps.setDate(2, Date.valueOf(end));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				i.add(new invoice(rs.getString("invoice.id"), rs.getDate("invoice.end_date").toLocalDate(),
						rs.getDate("invoice_date").toLocalDate(), rs.getString("membership.membership_name"),
						rs.getString("member.id"), rs.getString("member.name"), rs.getString("member.email")));
				System.err.println(rs.getString("member.id"));
			}

			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
