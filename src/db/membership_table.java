package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class membership_table {

    public static void delete_table() {
        String sql = "Drop table membership";
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {
            System.out.println(ps.execute());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void insert_membership(Membership ms) {
        String sql = "insert  into membership(membership_name,price) values (?,?) ";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ms.getName());
            ps.setInt(2, ms.getPrice());

            ps.execute();
            System.out.println("added  member ship");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
       public static void update_membership(ArrayList<Membership> ms){
        
        String sql="UPDATE `membership` SET  `price`=? WHERE membership_id=?";
        
        try(Connection con=ConnectDB.CreateConnection();
                PreparedStatement ps=con.prepareStatement(sql)) {
            
            ms.forEach((t) -> {
                try {
                    ps.setInt(1,t.getPrice());
                    ps.setString(2,t.getId());
                    ps.addBatch();
                } catch (SQLException ex) {
                    Logger.getLogger(invoice_table.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            ps.executeBatch();
        } catch (Exception e) {
        }
        
    }
    
    

    public static ArrayList<Membership> showall() {
        ArrayList<Membership> ms = new ArrayList<>();
        String sql = "select * from membership ";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ms.add(new Membership(rs.getString(1), rs.getString(2), rs.getInt(3)));
            }
            return ms;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
