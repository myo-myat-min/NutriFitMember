package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class monthly_progress_table {

    public static void insert_monthly_progress(monthly_progress mp, Member m, workout w) {
        String sql = "INSERT INTO `monthly_progress`( `progress_date`, `member_id`, `workout_id`)"
                + " VALUES (?,?,?)";

        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(mp.getProgress_date()));
            ps.setString(2, m.getId());
            ps.setInt(3, w.getId());
            ps.execute();
          
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static ArrayList<monthly_progress> showall(){
        ArrayList<monthly_progress> mp=new ArrayList<>();
        String sql="Select * from monthly_progress";
        
        try (Connection con = ConnectDB.CreateConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                mp.add(new monthly_progress(rs.getInt(1), rs.getDate(2).toLocalDate(), rs.getInt(3), rs.getInt(4)));
            }
          return mp;
        } catch (Exception e) {
            e.printStackTrace();
        }
return null;
        
    }

    public static void main(String[] args) {
        //create_table();
        System.out.println(showall());
    }

}
