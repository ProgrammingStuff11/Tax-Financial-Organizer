package financeproject;

import javax.swing.*;
import java.sql.*;

public class DatabaseConnection {
	public static void main(String[] args) {
		DatabaseConnection dc = new DatabaseConnection();
	}
	private String db;
	private Statement s;
	private ResultSet rs;
	
	public DatabaseConnection() {
		
		db = "jdbc:sqlserver://localhost;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true";
		
		try(Connection c = DriverManager.getConnection(db)) {
			s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery("SELECT DB_ID('financial_sorter');");
			
			rs.first();
			if(rs.getString(1) == null) s.execute("CREATE DATABASE financial_sorter;");
			s.execute("USE financial_sorter;");
			s.execute("IF OBJECT_ID('taxpayer') IS NULL CREATE TABLE taxpayer(tin INT PRIMARY KEY, fName VARCHAR(30), mInitial CHAR(1), lName VARCHAR(30), dob DATE, mPhone BIGINT, occupation VARCHAR(500));");
			s.execute("IF OBJECT_ID('spouse') IS NULL CREATE TABLE spouse(sTin INT PRIMARY KEY, tin INT FOREIGN KEY REFERENCES taxpayer, sFName VARCHAR(30), sMInitial CHAR(1), sLName VARCHAR(30), sDob DATE, sMPhone BIGINT, sOccupation VARCHAR(500));");
			s.execute("IF OBJECT_ID('Form1099B') IS NULL CREATE TABLE Form1099B(tin INT FOREIGN KEY REFERENCES taxpayer, transactions VARCHAR(500), quantity FLOAT, description VARCHAR(150), cusip CHAR(9), owner VARCHAR(15), reportingCategory CHAR(1), dateOfAcquisition DATE, dateOfSaleOrExchange DATE, salesPrice FLOAT, costBasis FLOAT, accruedMarketDiscount FLOAT, washSaleLossDisallowed FLOAT, gainOrLoss FLOAT, transactionDescription VARCHAR(50), YTDAmortizationOrAccretion TINYINT, LTDAmortizationOrAccretion TINYINT, ProceedsFromCollectibles TINYINT, SalesPrice1099B INT, CostBasis1099B INT, GainOrLoss1099B INT)");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	public void pidToDatabase(int tin, String firstName, String mInitial, String lName, String dob, long phone, String occupation) {
		
		try(Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT OBJECT_ID('taxpayer');");
			) {			
		
			s.execute("INSERT INTO taxpayer VALUES(" + tin + ",'" + firstName + "','" + mInitial + "','" + lName + "','" + dob + "'," + phone + ",'" + occupation + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void pidToDatabase(int sTin, int tin, String sFirstName, String sMInitial, String sLName, String sDob, long sPhone, String sOccupation) {
		
		try(Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT OBJECT_ID('spouse');");
			) {			
		
			s.execute("INSERT INTO spouse VALUES(" + sTin + "," + tin + ",'" + sFirstName + "','" + sMInitial + "','" + sLName + "','" + sDob + "'," + sPhone + ",'" + sOccupation + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void pidToDatabase(int tin, String transactions, double quantity, String description, String cusip, String owner, char reportingCategory, String dateOfAcquisition, String dateOfSaleOrExchange, double salesPrice, double costBasis, double accruedMarketDiscount, double washSaleLossDisallowed, double gainOrLoss, String transactionDescription, byte YTDAmortizationOrAccretion, byte LTDAmortizationOrAccretion, byte ProceedsFromCollectibles, int SalesPrice1099B, int CostBasis1099B, int GainOrLoss1099B) {
		try(Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT OBJECT_ID('Form1099B');");
			) {	
			
			s.execute("INSERT INTO Form1099B VALUES(" + tin + ",'" + transactions + "'," + quantity + ",'" + description +  "','" + cusip + "','" + owner + "','" + reportingCategory +  "','" + dateOfAcquisition +  "','" + dateOfSaleOrExchange +  "'," + salesPrice + "," + costBasis +  "," + accruedMarketDiscount +  "," + washSaleLossDisallowed +  "," + gainOrLoss +  ",'" + transactionDescription +  "'," + YTDAmortizationOrAccretion + "," + LTDAmortizationOrAccretion + "," + ProceedsFromCollectibles +  "," + SalesPrice1099B +  "," + CostBasis1099B +  "," + GainOrLoss1099B + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet readFromForm1099B() throws SQLException {
		
		Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
		Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		return s.executeQuery("SELECT * FROM Form1099B;");

	}

	public ResultSet orderForm1099B(boolean asc, boolean desc, JCheckBox[] jcb3) throws SQLException {

		int[] indexOfSelectedColumns = new int[jcb3.length];
		int counter = 0;

		Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
		Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		for(int i = 0; i < jcb3.length; i++) {
			if(jcb3[i].isSelected()) {
				indexOfSelectedColumns[counter] = i;
				counter++;
			}
		}

		// Create a for loop for indexOfSelectedColumns[] and a separate String that holds the query in order to implement this properly
		if(asc) {
			System.out.println(jcb3[indexOfSelectedColumns[0]].getText());
			return s.executeQuery("SELECT * FROM Form1099B ORDER BY " + jcb3[indexOfSelectedColumns[0]].getText() + " ASC;");
		}
		else if(desc) {
			return s.executeQuery("SELECT * FROM Form1099B ORDER BY " + jcb3[indexOfSelectedColumns[0]].getText() + " DESC;");
		}
        else return null;
    }
	
	public void addDB(String[] colFieldData) {
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
			
			// PreparedStatement is used to prevent SQL injection from user input.
		    PreparedStatement ps = c.prepareStatement("INSERT INTO Form1099B VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		    
		    datatypeOrder(1, ps, colFieldData);
		    
		    ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeDB(String[] removeColFieldData, String[] colNames) {
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
			
			// PreparedStatement is used to prevent SQL injection from user input.
			PreparedStatement ps = c.prepareStatement("DELETE FROM Form1099B WHERE " + 
				    colNames[0] + " = ? AND " + colNames[1] + " = ? AND " + colNames[2] + " = ? AND " + 
				    colNames[3] + " = ? AND " + colNames[4] + " = ? AND " + colNames[5] + " = ? AND " + 
				    colNames[6] + " = ? AND " + colNames[7] + " = ? AND " + colNames[8] + " = ? AND " + 
				    colNames[9] + " = ? AND " + colNames[10] + " = ? AND " + colNames[11] + " = ? AND " + 
				    colNames[12] + " = ? AND " + colNames[13] + " = ? AND " + colNames[14] + " = ? AND " + 
				    colNames[15] + " = ? AND " + colNames[16] + " = ? AND " + colNames[17] + " = ? AND " + 
				    colNames[18] + " = ? AND " + colNames[19] + " = ? AND " + colNames[20] + " = ?");
			
				datatypeOrder(1, ps, removeColFieldData);

				ps.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateDB(String[] columns, String[] rowBeingUpdated, String[] updateColDataField) {
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
			
			String query = "UPDATE Form1099B SET ";
			for (int i = 0; i < 21; i++) {
	            query += columns[i] + " = ?";
	            if (i < columns.length - 1) {
	                query += ", ";
	            }
	        }
			query += " WHERE ";
			for (int i = 0; i < columns.length; i++) {
	            query += columns[i] + " = ?";
	            if (i < columns.length - 1) {
	                query += " AND ";
	            }
	        }
			
			// PreparedStatement is used to prevent SQL injection from user input.
			PreparedStatement ps = c.prepareStatement(query);
			
			datatypeOrder(1, ps, updateColDataField);
			datatypeOrder(22, ps, rowBeingUpdated);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void backupDB() {
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			s.execute("BACKUP DATABASE financial_sorter TO DISK = 'C:\\Users\\student\\Downloads\\backup.bak';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void restoreDB() {
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			s.execute("RESTORE DATABASE financial_sorter FROM DISK = 'C:\\Users\\student\\Downloads\\backup.bak';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void datatypeOrder(int startVal, PreparedStatement ps, String[] data) {
		try {
			ps.setInt(startVal, Integer.parseInt(data[0]));
			ps.setString(startVal+1, data[1]);
			ps.setDouble(startVal+2, Double.parseDouble(data[2]));
			ps.setString(startVal+3, data[3]);
			ps.setString(startVal+4, data[4]);
			ps.setString(startVal+5, data[5]);
			ps.setString(startVal+6, String.valueOf(data[6].charAt(0)));
			ps.setString(startVal+7, data[7]);
			ps.setString(startVal+8, data[8]);
			ps.setDouble(startVal+9, Double.parseDouble(data[9]));
			ps.setDouble(startVal+10, Double.parseDouble(data[10]));
			ps.setDouble(startVal+11, Double.parseDouble(data[11]));
			ps.setDouble(startVal+12, Double.parseDouble(data[12]));
			ps.setDouble(startVal+13, Double.parseDouble(data[13]));
			ps.setString(startVal+14, data[14]);
			ps.setByte(startVal+15, Byte.parseByte(data[15]));
			ps.setByte(startVal+16, Byte.parseByte(data[16]));
			ps.setByte(startVal+17, Byte.parseByte(data[17]));
			ps.setInt(startVal+18, Integer.parseInt(data[18]));
			ps.setInt(startVal+19, Integer.parseInt(data[19]));
			ps.setInt(startVal+20, Integer.parseInt(data[20]));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
