package financeproject;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class FileImportGUI {
	private JFrame f;
	private JPanel p;
	private JPanel p2;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JLabel l;
	private JLabel tinL;
	private JTextField tinT;
	private JRadioButton rb1;
	private JRadioButton rb2;
	private JRadioButton rb3;
	private boolean is1099BSelected;
	private boolean is1099DIVSelected;
	private boolean is1099INTSelected;

	private int tin;
	private String transactions;
	private double quantity;
	private String description;
	private String cusip;
	private String owner;
	private char reportingCategory;
	private String dateOfAcquistion;
	private String dateOfSaleOrExchange;
	private double salesPrice;
	private double costBasis;
	private double accruedMarketDiscount;
	private double washSaleLossDisallowed;
	private double gainOrLoss;
	private String transactionDescription;
	private byte YTDAmortizationOrAccretion;
	private byte LTDAmortizationOrAccretion;
	private byte ProceedsFromCollectibles;
	private int SalesPrice1099B;
	private int CostBasis1099B;
	private int GainOrLoss1099B;
	
	private int accountNumber1099DIV;
	private double totalOrdinaryDividends;
	private double qualifiedDividends;
	private double totalCapitalGainDist;
	private double unrecapSec1250Gain;
	private int section1202Gain;
	private int collectiblesGain;
	private double section897OrdinaryDividends;
	private double section897CapitalGain;
	private double nondividendDistributions;
	private int federalIncomeTaxWithheld;
	private double section199ADividends;
	private int investmentExpenses;
	private double foreignTaxPaid;
	private String foreignCountryOrUSPossession;
	private int liquidationDistributionsCash;
	private int liquidationDistributionsNonCash;
	private double exemptInterestDividends;
	private double specifiedPrivateActivityBondInterestDividends;
	private String state;
	private int stateIdentificationNo;
	private int stateTaxWithheld;
	
	private int accountNumber1099INT;
	private double interestIncomeNotIncludedLine3;
	private int earlyWithdrawalPenalty;
	private int interestOnUsSavingsBondsAndTreasuryObligations;
	private int federalIncomeTaxWithheld1;
	private int investmentExpenses1;
	private int foreignTaxPaid1;
	private String foreignCountryOrUsPossession;
	private int taxExemptInterest;
	private int specifiedPrivateActivityBondInterest;
	private int marketDiscount;
	private int bondPremium;
	private int bondPremiumOnTreasuryObligations;
	private int bondPremiumOnTaxExemptBond;
	private int taxExemptTaxCreditBondCusipNo;
	private String state1;
	private int stateIdentificationNo1;
	private int stateTaxWithheld1;
	
	private JTextField accountNumber1099DIVField;
	private JTextField accountNumber1099INTField;


	public FileImportGUI() {
		f = new JFrame("File Import");
		p = new JPanel();
		p2 = new JPanel();
		b1 = new JButton();
		b2 = new JButton();
		b3 = new JButton();
		l = new JLabel("no file selected");
		tinL = new JLabel("TIN");
		tinT = new JTextField(10);
		rb1 = new JRadioButton();
		rb2 = new JRadioButton();
		rb3 = new JRadioButton();
		is1099BSelected = false;
		is1099DIVSelected = false;
		is1099INTSelected = false;
		
		tin = 0;
		transactions = "";
		quantity = 0;
		description = "";
		cusip = "";
		owner = ""; 
		reportingCategory = ' ';
		dateOfAcquistion = "";
		dateOfSaleOrExchange = "";
		salesPrice = 0;
		costBasis= 0;
		accruedMarketDiscount = 0;
		washSaleLossDisallowed = 0;
		gainOrLoss = 0;
		transactionDescription = ""; 
		YTDAmortizationOrAccretion = 0;
		LTDAmortizationOrAccretion = 0;
		ProceedsFromCollectibles = 0;
		SalesPrice1099B = 0;
		CostBasis1099B = 0;
		GainOrLoss1099B = 0; 
		
		accountNumber1099DIV = 0;
		totalOrdinaryDividends = 0;
		qualifiedDividends = 0;
		totalCapitalGainDist = 0;
		unrecapSec1250Gain = 0;
		section1202Gain = 0;
		collectiblesGain = 0;
		section897OrdinaryDividends = 0;
		section897CapitalGain = 0;
		nondividendDistributions = 0;
		federalIncomeTaxWithheld = 0;
		section199ADividends = 0;
		investmentExpenses = 0;
		foreignTaxPaid = 0;
		foreignCountryOrUSPossession = "";
		liquidationDistributionsCash = 0;
		liquidationDistributionsNonCash = 0;
		exemptInterestDividends = 0;
		specifiedPrivateActivityBondInterestDividends = 0;
		state = "";
		stateIdentificationNo = 0;
		stateTaxWithheld = 0;		
		
		accountNumber1099INT = 0;
		interestIncomeNotIncludedLine3 = 0;
		earlyWithdrawalPenalty = 0;
		interestOnUsSavingsBondsAndTreasuryObligations = 0;
		federalIncomeTaxWithheld1 = 0;
		investmentExpenses1 = 0;
		foreignTaxPaid1 = 0;
		foreignCountryOrUsPossession = "";
		taxExemptInterest = 0;
		specifiedPrivateActivityBondInterest = 0;
		marketDiscount = 0;
		bondPremium = 0;
		bondPremiumOnTreasuryObligations = 0;
		bondPremiumOnTaxExemptBond = 0;
		taxExemptTaxCreditBondCusipNo = 0;
		state1 = "";
		stateIdentificationNo1 = 0;
		stateTaxWithheld1 = 0;
		
		accountNumber1099DIVField = new JTextField(10);
		accountNumber1099DIVField.setVisible(false);
		accountNumber1099INTField = new JTextField(10);
		accountNumber1099INTField.setVisible(false);


		fileImportGUISetup();
	}

	public void fileImportGUISetup() {

		rb1.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				is1099BSelected = !is1099BSelected;
			}
		});
		rb1.setText("Form1099B");
		
		rb2.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				is1099DIVSelected = !is1099DIVSelected;
				accountNumber1099DIVField.setVisible(is1099DIVSelected);
				p.revalidate();
				p.repaint();
			}
		});
		rb2.setText("Form1099DIV");
		
		rb3.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				is1099INTSelected = !is1099INTSelected;
				accountNumber1099INTField.setVisible(is1099INTSelected);
				p.revalidate();
				p.repaint();
			}
		});
		rb3.setText("Form1099INT");
		
		// Button that selects file
		b1 = new JButton();
		b1.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnFileValue = j.showSaveDialog(null);
				if (returnFileValue == JFileChooser.APPROVE_OPTION) {
					l.setText(j.getSelectedFile().getAbsolutePath());
				}
			}
			
		});
		b1.setText("select file");
		b1.setPreferredSize(new Dimension(500, 100));
		
		// Button that saves file
		b2.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tin = Integer.parseInt(tinT.getText());
				
				// Reads info from file and adds it to the variables
				try {
					Scanner sc = new Scanner(new File(l.getText()));

					// Split the data with commas
					while(sc.hasNextLine()) {
						String[] splitData = sc.nextLine().split(",");

						if(is1099BSelected) {
							transactions = splitData[0];
							quantity = Double.parseDouble(splitData[1]);
							description = splitData[2];
							cusip = splitData[3];
							dateOfAcquistion = splitData[4];
							dateOfSaleOrExchange = splitData[5];
							salesPrice = Double.parseDouble(splitData[6]);
							costBasis = Double.parseDouble(splitData[7]);
							accruedMarketDiscount = Double.parseDouble(splitData[8]);
							washSaleLossDisallowed = Double.parseDouble(splitData[9]);
							gainOrLoss = Double.parseDouble(splitData[10]);
							transactionDescription = splitData[11];
							YTDAmortizationOrAccretion = Byte.parseByte(splitData[12]);
							LTDAmortizationOrAccretion = Byte.parseByte(splitData[13]);
	
							// Saves the file to the program.
							Form1099BInfo form1099BInfo = new Form1099BInfo(tin, transactions, quantity, description, cusip, owner, reportingCategory, dateOfAcquistion, dateOfSaleOrExchange, salesPrice, costBasis, accruedMarketDiscount, washSaleLossDisallowed, gainOrLoss, transactionDescription, YTDAmortizationOrAccretion, LTDAmortizationOrAccretion, ProceedsFromCollectibles, SalesPrice1099B, CostBasis1099B, GainOrLoss1099B);
							form1099BInfo.form1099BInfo();
						}
						else if(is1099DIVSelected) {
							accountNumber1099DIV = Integer.parseInt(accountNumber1099DIVField.getText());
							totalOrdinaryDividends = Double.parseDouble(splitData[0]);
							qualifiedDividends = Double.parseDouble(splitData[1]);
							totalCapitalGainDist = Double.parseDouble(splitData[2]);
							unrecapSec1250Gain = Double.parseDouble(splitData[3]);
							section1202Gain = Integer.parseInt(splitData[4]);
							collectiblesGain = Integer.parseInt(splitData[5]);
							section897OrdinaryDividends = Double.parseDouble(splitData[6]);
							section897CapitalGain = Double.parseDouble(splitData[7]);
							nondividendDistributions = Double.parseDouble(splitData[8]);
							federalIncomeTaxWithheld = Integer.parseInt(splitData[9]);
							section199ADividends = Double.parseDouble(splitData[10]);
							investmentExpenses = Integer.parseInt(splitData[11]);
							foreignTaxPaid = Double.parseDouble(splitData[12]);
							foreignCountryOrUSPossession = splitData[13];
							liquidationDistributionsCash = Integer.parseInt(splitData[14]);
							liquidationDistributionsNonCash = Integer.parseInt(splitData[15]);
							exemptInterestDividends = Double.parseDouble(splitData[16]);
							specifiedPrivateActivityBondInterestDividends = Double.parseDouble(splitData[17]);
							state = splitData[18];
							stateIdentificationNo = Integer.parseInt(splitData[19]);
							stateTaxWithheld = Integer.parseInt(splitData[20]);
							
							Form1099DIVInfo form1099DIVInfo = new Form1099DIVInfo(
									tin, accountNumber1099DIV, totalOrdinaryDividends, qualifiedDividends, totalCapitalGainDist,
									unrecapSec1250Gain, section1202Gain, collectiblesGain, section897OrdinaryDividends,
									section897CapitalGain, nondividendDistributions, federalIncomeTaxWithheld,
									section199ADividends, investmentExpenses, foreignTaxPaid,
									foreignCountryOrUSPossession, liquidationDistributionsCash, liquidationDistributionsNonCash,
									exemptInterestDividends, specifiedPrivateActivityBondInterestDividends,
									state, stateIdentificationNo, stateTaxWithheld
							);
							form1099DIVInfo.form1099DIVInfo();
						}
						
						else if(is1099INTSelected) {
							accountNumber1099INT = Integer.parseInt(accountNumber1099INTField.getText());
							interestIncomeNotIncludedLine3 = Double.parseDouble(splitData[0]);
							earlyWithdrawalPenalty = Integer.parseInt(splitData[1]);
							interestOnUsSavingsBondsAndTreasuryObligations = Integer.parseInt(splitData[2]);
							federalIncomeTaxWithheld1 = Integer.parseInt(splitData[3]);
							investmentExpenses1 = Integer.parseInt(splitData[4]);
							foreignTaxPaid1 = Integer.parseInt(splitData[5]);
							foreignCountryOrUsPossession = splitData[6];
							taxExemptInterest = Integer.parseInt(splitData[7]);
							specifiedPrivateActivityBondInterest = Integer.parseInt(splitData[8]);
							marketDiscount = Integer.parseInt(splitData[9]);
							bondPremium = Integer.parseInt(splitData[10]);
							bondPremiumOnTreasuryObligations = Integer.parseInt(splitData[11]);
							bondPremiumOnTaxExemptBond = Integer.parseInt(splitData[12]);
							taxExemptTaxCreditBondCusipNo = Integer.parseInt(splitData[13]);
							state1 = splitData[14];
							stateIdentificationNo1 = Integer.parseInt(splitData[15]);
							stateTaxWithheld1 = Integer.parseInt(splitData[16]);

							Form1099INTInfo form1099INTInfo = new Form1099INTInfo(
								tin, accountNumber1099INT, interestIncomeNotIncludedLine3, earlyWithdrawalPenalty,
								interestOnUsSavingsBondsAndTreasuryObligations, federalIncomeTaxWithheld1,
								investmentExpenses1, foreignTaxPaid1, foreignCountryOrUsPossession, taxExemptInterest,
								specifiedPrivateActivityBondInterest, marketDiscount, bondPremium,
								bondPremiumOnTreasuryObligations, bondPremiumOnTaxExemptBond,
								taxExemptTaxCreditBondCusipNo, state1, stateIdentificationNo1, stateTaxWithheld1
							);
							form1099INTInfo.form1099INTInfo();
							
						}

					}
					sc.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}


			}
			
		});
		b2.setText("save");
		b2.setPreferredSize(new Dimension(500, 100));
		
		// Button that takes user back to main GUI
		b3.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainUserGUI mainuserGUI = new MainUserGUI();
				f.dispose();
			}
			
		});
		b3.setText("back to main menu");
				
		p2.add(b3);
		p.add(tinL);
		p.add(tinT);
		p.add(rb1);
		p.add(rb2);
		p.add(rb3);
		p.add(new JLabel("1099 DIV Account Number:"));
		p.add(accountNumber1099DIVField);
		p.add(new JLabel("1099 INT Account Number:"));
		p.add(accountNumber1099INTField);
		p.add(b1);
		p.add(b2);
		p.add(l);
		
		f.add(p2, "North");
		f.add(p);
		
		f.setSize(new Dimension(500, 500));
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}

class Form1099BInfo {
	private int tin;
	private String transactions;
	private double quantity;
	private String description;
	private String cusip;
	private String owner;
	private char reportingCategory;
	private String dateOfAcquistion;
	private String dateOfSaleOrExchange;
	private double salesPrice;
	private double costBasis;
	private double accruedMarketDiscount;
	private double washSaleLossDisallowed;
	private double gainOrLoss;
	private String transactionDescription;
	private byte YTDAmortizationOrAccretion;
	private byte LTDAmortizationOrAccretion;
	private byte ProceedsFromCollectibles;
	private int SalesPrice1099B;
	private int CostBasis1099B;
	private int GainOrLoss1099B;
	
	protected Form1099BInfo(int tin, String transactions, double quantity, String description, String cusip, String owner, char reportingCategory, String dateOfAcquistion, String dateOfSaleOrExchange, double salesPrice, double costBasis, double accruedMarketDiscount, double washSaleLossDisallowed, double gainOrLoss, String transactionDescription, byte YTDAmortizationOrAccretion, byte LTDAmortizationOrAccretion, byte ProceedsFromCollectibles, int SalesPrice1099B, int CostBasis1099B, int GainOrLoss1099B) {
		this.tin= tin;
		this.transactions = transactions;
		this.quantity = quantity;
		this.description = description;
		this.cusip = cusip;
		this.owner = owner;
		this.reportingCategory = reportingCategory;
		this.dateOfAcquistion = dateOfAcquistion;
		this.dateOfSaleOrExchange = dateOfSaleOrExchange;
		this.salesPrice = salesPrice;
		this.costBasis = costBasis;
		this.accruedMarketDiscount = accruedMarketDiscount;
		this.washSaleLossDisallowed = washSaleLossDisallowed;
		this.gainOrLoss = gainOrLoss;
		this.transactionDescription = transactionDescription;
		this.YTDAmortizationOrAccretion = YTDAmortizationOrAccretion;
		this.LTDAmortizationOrAccretion = LTDAmortizationOrAccretion;
		this.ProceedsFromCollectibles = ProceedsFromCollectibles;
		this.SalesPrice1099B = SalesPrice1099B;
		this.CostBasis1099B = CostBasis1099B;
		this.GainOrLoss1099B = GainOrLoss1099B;
	}
	
	public void form1099BInfo() {
		DatabaseConnection databaseconnection = new DatabaseConnection();
		databaseconnection.pidForm1099BToDatabase(tin, transactions, quantity, description, cusip, owner, reportingCategory, dateOfAcquistion, dateOfSaleOrExchange, salesPrice, costBasis, accruedMarketDiscount, washSaleLossDisallowed, gainOrLoss, transactionDescription, YTDAmortizationOrAccretion, LTDAmortizationOrAccretion, ProceedsFromCollectibles, SalesPrice1099B, CostBasis1099B, GainOrLoss1099B);
		
		System.out.println(tin + " " + transactions  + " " + quantity  + " " + description  + " " + cusip  + " " + owner  + " " + reportingCategory  + " " + dateOfAcquistion  + " " + dateOfSaleOrExchange  + " " + salesPrice  + " " + costBasis + " " + accruedMarketDiscount  + " " + washSaleLossDisallowed  + " " + gainOrLoss  + " " + transactionDescription  + " " + YTDAmortizationOrAccretion  + " " + LTDAmortizationOrAccretion  + " " + ProceedsFromCollectibles + " " + SalesPrice1099B + " " + CostBasis1099B + " " + GainOrLoss1099B);

	}
}

class Form1099DIVInfo {
	private int tin;
	private int accountNumber;
	private double totalOrdinaryDividends;
	private double qualifiedDividends;
	private double totalCapitalGainDist;
	private double unrecapSec1250Gain;
	private int section1202Gain;
	private int collectiblesGain;
	private double section897OrdinaryDividends;
	private double section897CapitalGain;
	private double nondividendDistributions;
	private int federalIncomeTaxWithheld;
	private double section199ADividends;
	private int investmentExpenses;
	private double foreignTaxPaid;
	private String foreignCountryOrUSPossession;
	private int liquidationDistributionsCash;
	private int liquidationDistributionsNonCash;
	private double exemptInterestDividends;
	private double specifiedPrivateActivityBondInterestDividends;
	private String state;
	private int stateIdentificationNo;
	private int stateTaxWithheld;

	protected Form1099DIVInfo(
		int tin, int accountNumber, double totalOrdinaryDividends, double qualifiedDividends,
		double totalCapitalGainDist, double unrecapSec1250Gain, int section1202Gain, int collectiblesGain,
		double section897OrdinaryDividends, double section897CapitalGain, double nondividendDistributions,
		int federalIncomeTaxWithheld, double section199ADividends, int investmentExpenses,
		double foreignTaxPaid, String foreignCountryOrUSPossession, int liquidationDistributionsCash,
		int liquidationDistributionsNonCash, double exemptInterestDividends,
		double specifiedPrivateActivityBondInterestDividends, String state,
		int stateIdentificationNo, int stateTaxWithheld
	) {
		this.tin = tin;
		this.accountNumber = accountNumber;
		this.totalOrdinaryDividends = totalOrdinaryDividends;
		this.qualifiedDividends = qualifiedDividends;
		this.totalCapitalGainDist = totalCapitalGainDist;
		this.unrecapSec1250Gain = unrecapSec1250Gain;
		this.section1202Gain = section1202Gain;
		this.collectiblesGain = collectiblesGain;
		this.section897OrdinaryDividends = section897OrdinaryDividends;
		this.section897CapitalGain = section897CapitalGain;
		this.nondividendDistributions = nondividendDistributions;
		this.federalIncomeTaxWithheld = federalIncomeTaxWithheld;
		this.section199ADividends = section199ADividends;
		this.investmentExpenses = investmentExpenses;
		this.foreignTaxPaid = foreignTaxPaid;
		this.foreignCountryOrUSPossession = foreignCountryOrUSPossession;
		this.liquidationDistributionsCash = liquidationDistributionsCash;
		this.liquidationDistributionsNonCash = liquidationDistributionsNonCash;
		this.exemptInterestDividends = exemptInterestDividends;
		this.specifiedPrivateActivityBondInterestDividends = specifiedPrivateActivityBondInterestDividends;
		this.state = state;
		this.stateIdentificationNo = stateIdentificationNo;
		this.stateTaxWithheld = stateTaxWithheld;
	}

	public void form1099DIVInfo() {
		DatabaseConnection databaseconnection = new DatabaseConnection();
		databaseconnection.pidForm1099DIVToDatabase(
			tin, accountNumber, totalOrdinaryDividends, qualifiedDividends,
			totalCapitalGainDist, unrecapSec1250Gain, section1202Gain, collectiblesGain,
			section897OrdinaryDividends, section897CapitalGain, nondividendDistributions,
			federalIncomeTaxWithheld, section199ADividends, investmentExpenses,
			foreignTaxPaid, foreignCountryOrUSPossession, liquidationDistributionsCash,
			liquidationDistributionsNonCash, exemptInterestDividends,
			specifiedPrivateActivityBondInterestDividends, state,
			stateIdentificationNo, stateTaxWithheld
		);

		System.out.println(
			tin + " " + accountNumber + " " + totalOrdinaryDividends + " " + qualifiedDividends + " " +
			totalCapitalGainDist + " " + unrecapSec1250Gain + " " + section1202Gain + " " + collectiblesGain + " " +
			section897OrdinaryDividends + " " + section897CapitalGain + " " + nondividendDistributions + " " +
			federalIncomeTaxWithheld + " " + section199ADividends + " " + investmentExpenses + " " + foreignTaxPaid + " " +
			foreignCountryOrUSPossession + " " + liquidationDistributionsCash + " " + liquidationDistributionsNonCash + " " +
			exemptInterestDividends + " " + specifiedPrivateActivityBondInterestDividends + " " + state + " " +
			stateIdentificationNo + " " + stateTaxWithheld
		);
	}
}

class Form1099INTInfo {
	private int tin;
	private int accountNumber;
	private double interestIncomeNotIncludedLine3;
	private int earlyWithdrawalPenalty;
	private int interestOnUsSavingsBondsAndTreasuryObligations;
	private int federalIncomeTaxWithheld;
	private int investmentExpenses;
	private int foreignTaxPaid;
	private String foreignCountryOrUsPossession;
	private int taxExemptInterest;
	private int specifiedPrivateActivityBondInterest;
	private int marketDiscount;
	private int bondPremium;
	private int bondPremiumOnTreasuryObligations;
	private int bondPremiumOnTaxExemptBond;
	private int taxExemptTaxCreditBondCusipNo;
	private String state;
	private int stateIdentificationNo;
	private int stateTaxWithheld;

	protected Form1099INTInfo(
		int tin, int accountNumber, double interestIncomeNotIncludedLine3, int earlyWithdrawalPenalty,
		int interestOnUsSavingsBondsAndTreasuryObligations, int federalIncomeTaxWithheld,
		int investmentExpenses, int foreignTaxPaid, String foreignCountryOrUsPossession,
		int taxExemptInterest, int specifiedPrivateActivityBondInterest, int marketDiscount,
		int bondPremium, int bondPremiumOnTreasuryObligations, int bondPremiumOnTaxExemptBond,
		int taxExemptTaxCreditBondCusipNo, String state, int stateIdentificationNo, int stateTaxWithheld
	) {
		this.tin = tin;
		this.accountNumber = accountNumber;
		this.interestIncomeNotIncludedLine3 = interestIncomeNotIncludedLine3;
		this.earlyWithdrawalPenalty = earlyWithdrawalPenalty;
		this.interestOnUsSavingsBondsAndTreasuryObligations = interestOnUsSavingsBondsAndTreasuryObligations;
		this.federalIncomeTaxWithheld = federalIncomeTaxWithheld;
		this.investmentExpenses = investmentExpenses;
		this.foreignTaxPaid = foreignTaxPaid;
		this.foreignCountryOrUsPossession = foreignCountryOrUsPossession;
		this.taxExemptInterest = taxExemptInterest;
		this.specifiedPrivateActivityBondInterest = specifiedPrivateActivityBondInterest;
		this.marketDiscount = marketDiscount;
		this.bondPremium = bondPremium;
		this.bondPremiumOnTreasuryObligations = bondPremiumOnTreasuryObligations;
		this.bondPremiumOnTaxExemptBond = bondPremiumOnTaxExemptBond;
		this.taxExemptTaxCreditBondCusipNo = taxExemptTaxCreditBondCusipNo;
		this.state = state;
		this.stateIdentificationNo = stateIdentificationNo;
		this.stateTaxWithheld = stateTaxWithheld;
	}

	public void form1099INTInfo() {
		DatabaseConnection databaseconnection = new DatabaseConnection();
		databaseconnection.pidForm1099INTToDatabase(
			tin, accountNumber, interestIncomeNotIncludedLine3, earlyWithdrawalPenalty,
			interestOnUsSavingsBondsAndTreasuryObligations, federalIncomeTaxWithheld,
			investmentExpenses, foreignTaxPaid, foreignCountryOrUsPossession,
			taxExemptInterest, specifiedPrivateActivityBondInterest, marketDiscount,
			bondPremium, bondPremiumOnTreasuryObligations, bondPremiumOnTaxExemptBond,
			taxExemptTaxCreditBondCusipNo, state, stateIdentificationNo, stateTaxWithheld
		);

		System.out.println(
			tin + " " + accountNumber + " " + interestIncomeNotIncludedLine3 + " " + earlyWithdrawalPenalty + " " +
			interestOnUsSavingsBondsAndTreasuryObligations + " " + federalIncomeTaxWithheld + " " + investmentExpenses + " " +
			foreignTaxPaid + " " + foreignCountryOrUsPossession + " " + taxExemptInterest + " " +
			specifiedPrivateActivityBondInterest + " " + marketDiscount + " " + bondPremium + " " +
			bondPremiumOnTreasuryObligations + " " + bondPremiumOnTaxExemptBond + " " +
			taxExemptTaxCreditBondCusipNo + " " + state + " " + stateIdentificationNo + " " + stateTaxWithheld
		);
	}
}




