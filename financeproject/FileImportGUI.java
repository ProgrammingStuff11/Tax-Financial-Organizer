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
	private boolean is1099BSelected;
	
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
		is1099BSelected = false;
		
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
		databaseconnection.pidToDatabase(tin, transactions, quantity, description, cusip, owner, reportingCategory, dateOfAcquistion, dateOfSaleOrExchange, salesPrice, costBasis, accruedMarketDiscount, washSaleLossDisallowed, gainOrLoss, transactionDescription, YTDAmortizationOrAccretion, LTDAmortizationOrAccretion, ProceedsFromCollectibles, SalesPrice1099B, CostBasis1099B, GainOrLoss1099B);
		
		System.out.println(tin + " " + transactions  + " " + quantity  + " " + description  + " " + cusip  + " " + owner  + " " + reportingCategory  + " " + dateOfAcquistion  + " " + dateOfSaleOrExchange  + " " + salesPrice  + " " + costBasis + " " + accruedMarketDiscount  + " " + washSaleLossDisallowed  + " " + gainOrLoss  + " " + transactionDescription  + " " + YTDAmortizationOrAccretion  + " " + LTDAmortizationOrAccretion  + " " + ProceedsFromCollectibles + " " + SalesPrice1099B + " " + CostBasis1099B + " " + GainOrLoss1099B);

	}
}


