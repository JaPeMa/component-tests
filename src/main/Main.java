package main;

import java.util.List;

import logicClasses.ConnectMySQL;
import models.ConnectionData;
import models.QueryType;

public class Main {
	static ConnectMySQL databaseConnection = null;
	public static void main(String[] args) {
		connectDB();
		
		selectAndList();
		
		insert();
		
		update();
		
		delete();
		
		callProcedure();
		
		searchByDatabaseAndUser();
		
		searchByDatabaseAndUserAndType();
		
		searchByDatabaseAndType();
		
		disconnectDB();
	}
	
	public static void insert() {
		databaseConnection.executeQuery("INSERT INTO test (id, name) VALUES (1, 'Sean')");
	}
	
	public static void selectAndList() {
		List<List<String>> queryResult = databaseConnection.executeQuery("Select * from test");
		
		for (List<String> row : queryResult) {
			String rowToPrint = "";
			for (String column : row) {
				rowToPrint = rowToPrint + "-" + column;
			}
			System.out.println(rowToPrint.substring(1));
		}
	}
	
	public static void update() {
		databaseConnection.executeQuery("update test set name = 'Carlos' where name = 'Sean'");
	}
	
	public static void delete() {
		databaseConnection.executeQuery("delete from test where name = 'Carlos'");
	}
	
	public static void callProcedure() {
		databaseConnection.executeQuery("call insertAdmin()");
	}
	
	public static void searchByDatabaseAndUser() {
		databaseConnection.searchByDatabaseAndUser("component2", "root");
		System.out.println("\n\n\n");
	}
	
	public static void searchByDatabaseAndUserAndType() {
		databaseConnection.searchByDatabaseAndUserAndType("component2", "root", QueryType.SELECT);
		System.out.println("\n\n\n");
	}
	
	public static void searchByDatabaseAndType() {
		databaseConnection.searchByDatabaseAndType("component2", QueryType.INSERT);
		System.out.println("\n\n\n");
	}
	
	public static void connectDB() {
		databaseConnection = new ConnectMySQL(new ConnectionData("localhost:3306", "component2", "root", ""));
		databaseConnection.openConnection();
	}
	
	public static void disconnectDB() {
		databaseConnection.closeConnection();
	}
	
}
