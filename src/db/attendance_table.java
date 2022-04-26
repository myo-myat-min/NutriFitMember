package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class attendance_table {
    
     public static void create_table(){
         String sql="Create table attendance("
                 + "id int(100) primary key auto_increment ,"
                 + "attendance date not null  )";
         try (Connection con=ConnectDB.CreateConnection();
                 PreparedStatement ps=con.prepareStatement(sql)){
             
             ps.execute();
             System.out.println("attendance table created");
             
         } catch (Exception e) {
             e.printStackTrace();
         }
     
     }
     
     public static void insert_attendance(attendance at){
         String sql="INSERT INTO `attendance`( `attendance`) VALUES (?)";
         try(Connection con=ConnectDB.CreateConnection();
                 PreparedStatement ps=con.prepareStatement(sql)) {
             ps.setDate(1, Date.valueOf(at.getAttendance_date()));
             ps.execute();
             System.out.println("inserted");
         } catch (Exception e) {
             e.printStackTrace();
         }
         
     }
    
}

