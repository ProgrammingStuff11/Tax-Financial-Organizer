package financeproject;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
	public static void main(String[] args) {
		DatabaseConnection dc = new DatabaseConnection();
	}
	private String db;
	private Statement s;
	private ResultSet rs;
	
	public DatabaseConnection() {
		
		db = "jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;";
		
		try(Connection c = DriverManager.getConnection(db)) {
			s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery("SELECT DB_ID('financial_sorter');");
			
			rs.first();
			if(rs.getString(1) == null) s.execute("CREATE DATABASE financial_sorter;");
			s.execute("USE financial_sorter;");
			s.execute("IF OBJECT_ID('taxpayer') IS NULL CREATE TABLE taxpayer(tin INT PRIMARY KEY, routingNumber BIGINT, accountNumber BIGINT, typeOfAccount CHAR(1), fName VARCHAR(30), mInitial CHAR(1), lName VARCHAR(30), dob DATE, mPhone BIGINT, occupation VARCHAR(500));");
			s.execute("IF OBJECT_ID('spouse') IS NULL CREATE TABLE spouse(sTin INT PRIMARY KEY, tin INT FOREIGN KEY REFERENCES taxpayer, sFName VARCHAR(30), sMInitial CHAR(1), sLName VARCHAR(30), sDob DATE, sMPhone BIGINT, sOccupation VARCHAR(500));");
			s.execute("IF OBJECT_ID('Form1099B') IS NULL CREATE TABLE Form1099B(tin INT FOREIGN KEY REFERENCES taxpayer, transactions VARCHAR(500), quantity FLOAT, description VARCHAR(150), cusip CHAR(9), owner VARCHAR(15), reportingCategory CHAR(1), dateOfAcquisition DATE, dateOfSaleOrExchange DATE, salesPrice FLOAT, costBasis FLOAT, accruedMarketDiscount FLOAT, washSaleLossDisallowed FLOAT, gainOrLoss FLOAT, transactionDescription VARCHAR(50), YTDAmortizationOrAccretion TINYINT, LTDAmortizationOrAccretion TINYINT, ProceedsFromCollectibles TINYINT, SalesPrice1099B INT, CostBasis1099B INT, GainOrLoss1099B INT)");
			s.execute("IF OBJECT_ID('Form1099DIV') IS NULL CREATE TABLE Form1099DIV(tin INT FOREIGN KEY REFERENCES taxpayer, accountNumber INT, totalOrdinaryDividends FLOAT, qualifiedDividends FLOAT, totalCapitalGainDist FLOAT, unrecapSec1250Gain FLOAT, section1202Gain FLOAT, collectiblesGain FLOAT, section897OrdinaryDividends FLOAT, section897CapitalGain FLOAT, nondividendDistributions FLOAT, federalIncomeTaxWithheld FLOAT, section199ADividends FLOAT, investmentExpenses FLOAT, foreignTaxPaid FLOAT, ForeignCountryorUSPossession VARCHAR(50), LiquidationDistributionsCash FLOAT, LiquidationDistributionsNonCash FLOAT, ExemptInterestDividends FLOAT, SpecifiedPrivateActivityBondInterestDividends FLOAT, state VARCHAR(2), StateIdentificationNo FLOAT, StateTaxWithheld FLOAT)");
			s.execute("IF OBJECT_ID('Form1099INT') IS NULL CREATE TABLE Form1099INT(tin INT FOREIGN KEY REFERENCES taxpayer, accountNumber INT, interestIncome FLOAT, earlyWithdrawalPenalty INT, usSavingsBondInterest FLOAT, interestOnUSObligations FLOAT, foreignTaxPaid FLOAT, foreignCountryOrUSPossession VARCHAR(50), taxExemptInterest FLOAT, specifiedPrivateActivityBondInterest FLOAT, marketDiscount FLOAT, bondPremium FLOAT, bondPremiumTreasury FLOAT, bondPremiumTaxExemptBond FLOAT, federalIncomeTaxWithheld FLOAT, investmentExpenses FLOAT, state VARCHAR(2), stateIdentificationNo INT, stateTaxWithheld FLOAT)");
			s.execute("IF OBJECT_ID('Form1040') IS NULL CREATE TABLE Form1040(tin INT PRIMARY KEY, salaries FLOAT, adjustmentsToIncome FLOAT, tax FLOAT, childTaxCredit FLOAT, federalIncomeTaxWithheldInW2FromSalaries FLOAT, additionalTaxPaid FLOAT)");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		
	}

	public void pidToDatabase(int tin, String firstName, String mInitial, String lName, String dob, long phone, String occupation, long routingNumber, long accountNumber, char typeOfAccount) {
		
		try(Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT OBJECT_ID('taxpayer');");
			) {			
		
			s.execute("INSERT INTO taxpayer VALUES(" + tin + "," + routingNumber + "," + accountNumber + ",'" + typeOfAccount + "','" + firstName + "','" + mInitial + "','" + lName + "','" + dob + "'," + phone + ",'" + occupation + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void pidToDatabase(int sTin, int tin, String sFirstName, String sMInitial, String sLName, String sDob, long sPhone, String sOccupation) {
		
		try(Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT OBJECT_ID('spouse');");
			) {			
		
			s.execute("INSERT INTO spouse VALUES(" + sTin + "," + tin + ",'" + sFirstName + "','" + sMInitial + "','" + sLName + "','" + sDob + "'," + sPhone + ",'" + sOccupation + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void pidForm1099BToDatabase(int tin, String transactions, double quantity, String description, String cusip, String owner, char reportingCategory, String dateOfAcquisition, String dateOfSaleOrExchange, double salesPrice, double costBasis, double accruedMarketDiscount, double washSaleLossDisallowed, double gainOrLoss, String transactionDescription, byte YTDAmortizationOrAccretion, byte LTDAmortizationOrAccretion, byte ProceedsFromCollectibles, int SalesPrice1099B, int CostBasis1099B, int GainOrLoss1099B) {
		try(Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT OBJECT_ID('Form1099B');");
			) {	
			
			s.execute("INSERT INTO Form1099B VALUES(" + tin + ",'" + transactions + "'," + quantity + ",'" + description +  "','" + cusip + "','" + owner + "','" + reportingCategory +  "','" + dateOfAcquisition +  "','" + dateOfSaleOrExchange +  "'," + salesPrice + "," + costBasis +  "," + accruedMarketDiscount +  "," + washSaleLossDisallowed +  "," + gainOrLoss +  ",'" + transactionDescription +  "'," + YTDAmortizationOrAccretion + "," + LTDAmortizationOrAccretion + "," + ProceedsFromCollectibles +  "," + SalesPrice1099B +  "," + CostBasis1099B +  "," + GainOrLoss1099B + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void pidForm1099DIVToDatabase(int tin, int accountNumber, double totalOrdinaryDividends,
			double qualifiedDividends, double totalCapitalGainDist, double unrecapSec1250Gain, int section1202Gain,
			int collectiblesGain, double section897OrdinaryDividends, double section897CapitalGain,
			double nondividendDistributions, int federalIncomeTaxWithheld, double section199ADividends,
			int investmentExpenses, double foreignTaxPaid, String ForeignCountryorUSPossession,
			int LiquidationDistributionsCash, int LiquidationDistributionsNonCash, double ExemptInterestDividends,
			double specifiedPrivateActivityBondInterestDividends, String state, int stateIdentificationNo,
			int stateTaxWithheld) {
		try (
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT OBJECT_ID('Form1099DIV');")
		) {
			s.execute("INSERT INTO Form1099DIV VALUES(" + tin + "," + accountNumber + "," + totalOrdinaryDividends + "," + qualifiedDividends + "," + totalCapitalGainDist + "," + unrecapSec1250Gain + "," + section1202Gain + "," + collectiblesGain + "," + section897OrdinaryDividends + "," + section897CapitalGain + "," + nondividendDistributions + "," +	federalIncomeTaxWithheld + "," + section199ADividends + "," + investmentExpenses + "," + foreignTaxPaid + ",'" + ForeignCountryorUSPossession + "'," + LiquidationDistributionsCash + "," +	LiquidationDistributionsNonCash + "," +	ExemptInterestDividends + "," +	specifiedPrivateActivityBondInterestDividends + ",'" + state + "'," + stateIdentificationNo + "," +	stateTaxWithheld + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void pidForm1099INTToDatabase(int tin, int accountNumber, double interestIncomeNotIncludedLine3, int earlyWithdrawalPenalty, int interestOnUsSavingsBondsAndTreasuryObligations, int federalIncomeTaxWithheld, int investmentExpenses, int foreignTaxPaid, String foreignCountryOrUsPossession, int taxExemptInterest, int specifiedPrivateActivityBondInterest, int marketDiscount, int bondPremium, int bondPremiumOnTreasuryObligations, int bondPremiumOnTaxExemptBond, int taxExemptTaxCreditBondCusipNo, String state, int stateIdentificationNo, int stateTaxWithheld) {
		try (
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT OBJECT_ID('Form1099INT');")
		) {
			s.execute("INSERT INTO Form1099INT VALUES(" + tin + "," + accountNumber + "," + interestIncomeNotIncludedLine3 + "," + earlyWithdrawalPenalty + "," + interestOnUsSavingsBondsAndTreasuryObligations + "," + federalIncomeTaxWithheld + "," + investmentExpenses + "," + foreignTaxPaid + ",'" + foreignCountryOrUsPossession + "'," + taxExemptInterest + "," + specifiedPrivateActivityBondInterest + "," + marketDiscount + "," + bondPremium + "," + bondPremiumOnTreasuryObligations + "," + bondPremiumOnTaxExemptBond + "," + taxExemptTaxCreditBondCusipNo + ",'" + state + "'," + stateIdentificationNo + "," + stateTaxWithheld + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet readFromForm1099B(String tin) throws SQLException {
		
		Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
		Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		return s.executeQuery("SELECT * FROM Form1099B WHERE tin = " + tin + ";");

	}
	
	public ResultSet readFromForm1099DIV(String tin) throws SQLException {
		
		Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
		Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		return s.executeQuery("SELECT * FROM Form1099DIV WHERE tin = " + tin + ";");

	}
	
	public ResultSet readFromForm1099INT(String tin) throws SQLException {
		
		Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
		Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		return s.executeQuery("SELECT * FROM Form1099INT WHERE tin = " + tin + ";");

	}

	public ResultSet orderForm1099B(String tin, boolean Form1099B, boolean Form1099DIV, boolean Form1099INT, boolean asc, boolean desc, JCheckBox[] jcb3) throws SQLException {

		int[] indexOfSelectedColumns = new int[jcb3.length];
		int counter = 0;

		Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
		Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		// Finds the index of the columns selected
		for(int i = 0; i < jcb3.length; i++) {
			if(jcb3[i].isSelected()) {
				indexOfSelectedColumns[counter] = i;
				counter++;
			}
		}

		// Orders multiple columns
		String orderByCol = "";
		for (int i = 0; i < counter; i++) {
			orderByCol += jcb3[indexOfSelectedColumns[i]].getText();
			if (i < counter - 1) {
				orderByCol += ", ";
			}
		}
		
		// Finds the direction the user ordered the columns
		String orderDirection = "";
		if (asc) {
			orderDirection = "ASC";
		} else if (desc) {
			orderDirection = "DESC";
		}
		
		// Orders depending on the 1099 Form chosen
		if(Form1099B) {
			System.out.println(jcb3[indexOfSelectedColumns[0]].getText());
			return s.executeQuery("SELECT * FROM Form1099B WHERE tin = " + tin + " ORDER BY " + orderByCol + " " + orderDirection + ";");
		}
		else if(Form1099DIV) {
			return s.executeQuery("SELECT * FROM Form1099DIV WHERE tin = " + tin + " ORDER BY " + orderByCol + " " + orderDirection + ";");
		}
		else if(Form1099INT) {
			return s.executeQuery("SELECT * FROM Form1099INT WHERE tin = " + tin + " ORDER BY " + orderByCol + " " + orderDirection + ";");
		}
        else return null;
    }
	
	public void addDB(String[] colFieldData) {
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			
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
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			
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
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			
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
	
	public void backupDB(String fileLocation) {
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			s.execute("BACKUP DATABASE financial_sorter TO DISK = '" + fileLocation + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void restoreDB(String fileLocation) {
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=master;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			// ALTER DATABASE command disconnects the database so that the database can be restored
			s.execute("ALTER DATABASE financial_sorter SET SINGLE_USER WITH ROLLBACK IMMEDIATE;");
			// WITH REPLACE allows the database to be overwritten
			s.execute("RESTORE DATABASE financial_sorter FROM DISK = '" + fileLocation + "' WITH REPLACE;");
			s.execute("ALTER DATABASE financial_sorter SET MULTI_USER;");
			
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

	public void Form1040ToDatabase(int tin, double salaries, double adjustmentsToIncome, double tax, double childTaxCredit, double federalIncomeTaxWithheldInW2FromSalaries, double additionalTaxPaid) {
		try(Connection c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = s.executeQuery("SELECT OBJECT_ID('Form1040');");
		) {

			s.execute("INSERT INTO Form1040 VALUES(" + tin + "," + salaries + "," + adjustmentsToIncome + "," + tax + "," + childTaxCredit + "," + federalIncomeTaxWithheldInW2FromSalaries + "," + additionalTaxPaid + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> InfoFor1040Form(int tin) {
		ArrayList<String> info = new ArrayList<String>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=financial_sorter;user=sa;password=Pass4SQL!;encrypt=true;trustServerCertificate=true;");
			s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = s.executeQuery("SELECT OBJECT_ID('taxpayer');");
			rs.close();

			rs = s.executeQuery("SELECT fName, mInitial, lName FROM taxpayer WHERE tin = " + tin + ";");
			if (rs.next()) {
				String fName = rs.getString("fName");
				String mInitial = rs.getString("mInitial");
				String lName = rs.getString("lName");

				info.add(fName);
				info.add(mInitial);
				info.add(lName);

				System.out.println(fName);
			}
			rs.close();

			rs = s.executeQuery("SELECT OBJECT_ID('spouse');");
			rs.close();

			rs = s.executeQuery("SELECT sFName, sMInitial, sLName, sTin FROM spouse WHERE tin = " + tin + ";");
			if (rs.next()) {
				String fNameS = rs.getString("sFName");
				String mInitialS = rs.getString("sMInitial");
				String lNameS = rs.getString("sLName");
				String tinS = rs.getString("sTin");

				info.add(fNameS);
				info.add(mInitialS);
				info.add(lNameS);
				info.add(tinS);

				System.out.println(fNameS);
			}
			rs.close();

			rs = s.executeQuery("SELECT OBJECT_ID('Form1040');");
			rs.close();

			rs = s.executeQuery("SELECT salaries, adjustmentsToIncome, tax, childTaxCredit, federalIncomeTaxWithheldInW2FromSalaries, additionalTaxPaid FROM Form1040 WHERE tin = " + tin + ";");
			if (rs.next()) {
				String salaries = rs.getString("salaries");
				String adjustmentsToIncome = rs.getString("adjustmentsToIncome");
				String tax = rs.getString("tax");
				String childTaxCredit = rs.getString("childTaxCredit");
				String federalIncomeTaxWithheldInW2FromSalaries = rs.getString("federalIncomeTaxWithheldInW2FromSalaries");
				String additionalTaxPaid = rs.getString("additionalTaxPaid");

				info.add(salaries);
				info.add(adjustmentsToIncome);
				info.add(tax);
				info.add(childTaxCredit);
				info.add(federalIncomeTaxWithheldInW2FromSalaries);
				info.add(additionalTaxPaid);

				System.out.println(salaries);
			}
			rs.close();

			rs = s.executeQuery("SELECT OBJECT_ID('Form1099B');");
			rs.close();

			rs = s.executeQuery("SELECT SUM(gainOrLoss) AS sumGainOrLoss FROM Form1099B WHERE tin = " + tin + ";");
			if (rs.next()) {
				String gainOrLoss = rs.getString("sumGainOrLoss");

				info.add(gainOrLoss);

			}
			rs.close();

			rs = s.executeQuery("SELECT OBJECT_ID('Form1099INT');");
			rs.close();

			rs = s.executeQuery("SELECT SUM(interestIncome) AS sumInterestIncome FROM Form1099INT WHERE tin = " + tin + ";");
			if (rs.next()) {
				String interestIncome = rs.getString("sumInterestIncome");

				info.add(interestIncome);

			}
			rs.close();

			rs = s.executeQuery("SELECT OBJECT_ID('Form1099DIV');");
			rs.close();

			rs = s.executeQuery("SELECT SUM(qualifiedDividends) AS sumQualifiedDividends, SUM(totalOrdinaryDividends) AS sumOrdinaryDividends FROM Form1099DIV WHERE tin = " + tin + ";");
			if (rs.next()) {
				String sumQualifiedDividends = rs.getString("sumQualifiedDividends");
				String sumOrdinaryDividends = rs.getString("sumOrdinaryDividends");

				info.add(sumQualifiedDividends);
				info.add(sumOrdinaryDividends);

			}
			rs.close();

			rs = s.executeQuery("SELECT OBJECT_ID('taxpayer');");
			rs.close();

			rs = s.executeQuery("SELECT mPhone, occupation, routingNumber, accountNumber, typeOfAccount FROM taxpayer WHERE tin = " + tin + ";");
			if (rs.next()) {
				String mPhone = rs.getString("mPhone");
				String occupation = rs.getString("occupation");
				String routingNumber = rs.getString("routingNumber");
				String accountNumber = rs.getString("accountNumber");
				String typeOfAccount = rs.getString("typeOfAccount");

				info.add(mPhone);
				info.add(occupation);
				info.add(routingNumber);
				info.add(accountNumber);
				info.add(typeOfAccount);

				System.out.println(mPhone);
			}
			rs.close();


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return info;
	}
}
