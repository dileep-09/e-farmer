package com.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DatabaseConnection {

	public static Connection connection;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eagriculture?useSSL=false", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (connection);
	}

	public static void CloseConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static ResultSet getResultFromSqlQuery(String SqlQueryString) {
		ResultSet rs = null;
		try {
			if (connection == null) {
				getConnection();
			}
			rs = connection.createStatement().executeQuery(SqlQueryString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static int insertUpdateFromSqlQuery(String SqlQueryString) {
		int i = 2;
		try {
			if (connection == null) {
				getConnection();
			}
			i = connection.createStatement().executeUpdate(SqlQueryString);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return i;
	}
	
	public static int generateCustomerId() {
		int cid = 0;
		Random random=new Random();
		cid = random.nextInt((9999 - 100) + 1) + 10;
		System.out.println("Customer Id " + cid);
		return cid;
	}
	
	public static int generateAdminId() {
		int aid = 0;
		Random random=new Random();
		aid = random.nextInt((9999 - 100) + 1) + 10;
		System.out.println("Admin Id " + aid);
		return aid;
	}
	
	public static int generateBuyerId() {
		int bid = 0;
		Random random=new Random();
		bid = random.nextInt((9999 - 100) + 1) + 10;
		System.out.println("Buyer Id " + bid);
		return bid;
	}
	
	public static int generateCropId() {
		int cropId = 0;
		Random random=new Random();
		cropId = random.nextInt((9999 - 100) + 1) + 10;
		System.out.println("Crop Id " + cropId);
		return cropId;
	}
	
	public static int generatePesticideId() {
		int psticideId = 0;
		Random random=new Random();
		psticideId = random.nextInt((9999 - 100) + 1) + 10;
		System.out.println("Pesticide Id " + psticideId);
		return psticideId;
	}
	
	public static int generateInsecticideId() {
		int insecticidesId = 0;
		Random random=new Random();
		insecticidesId = random.nextInt((9999 - 100) + 1) + 10;
		System.out.println("Insecticides Id " + insecticidesId);
		return insecticidesId;
	}
	
	public static int generateEquipmentId() {
		int equipmentId = 0;
		Random random=new Random();
		equipmentId = random.nextInt((9999 - 100) + 1) + 10;
		System.out.println("Equipment Id " + equipmentId);
		return equipmentId;
	}
	
	public static int generateSellId() {
		int sellId = 0;
		Random random=new Random();
		sellId = random.nextInt((9999 - 100) + 1) + 10;
		System.out.println("Sell Id " + sellId);
		return sellId;
	}
	
}
