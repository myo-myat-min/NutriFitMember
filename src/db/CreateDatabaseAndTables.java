package db;

public class CreateDatabaseAndTables {

	public static void main(String[] args) {
		try {
			CreateDB.create_db();
			Membertable.CreateTable();
			trainer_table.create_table();
			membership_table.create_membership_table();
			admin_table.create_table();
			invoice_table.create_table();
			attendance_table.create_table();
			schedule_table.create_table();
			workout_table.create_table();
			monthly_progress_table.create_table();
			viewtable.createview();
			
			System.out.println("Database and tables are created.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
