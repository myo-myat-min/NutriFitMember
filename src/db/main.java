/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.SQLException;

/**
 *
 * @author Soe min hein
 */
public class main {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CreateDB.create_db();
        Membertable.CreateTable();
        membership_table.create_membership_table();
        admin_table.create_table();
        invoice_table.create_table();

        attendance_table.create_table();

        schedule_table.create_table();
        workout_table.create_table();
        monthly_progress_table.create_table();
 viewtable.createview();
        
        
    }
}