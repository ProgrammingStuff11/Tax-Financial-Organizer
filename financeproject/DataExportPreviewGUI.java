package financeproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.sql.*;
import java.text.MessageFormat;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataExportPreviewGUI {
	// Swing GUI for selecting which forms to export
	private JFrame f;
	private JButton b1;
	private JButton b2;
	private JCheckBox cb1;
	private JCheckBox cb2;
	private JCheckBox cb3;
	private JLabel l;
	private boolean[] isChecked;
	private JLabel tinL;
	private JTextField tinF;
	private JPanel tinPanel;

	// Swing GUI menu for sorting/exporting GUI
	private JFrame f2;
	private JButton b3;
	private JButton b4;
	private JButton b6;
	private JButton b7;
	private JLabel l1;
	
	// Swing GUI for the sorting GUI
	private JFrame f3;
	private JComboBox<JCheckBox> c1;
	private JComboBox<JCheckBox> c2;
	private JComboBox<JCheckBox> c3;
	private JButton updateButton;
	
	// Swing GUI for the exporting GUI
	private JTable jt1;
	private JCheckBox[] jcb = {new JCheckBox("Form 1099B"), new JCheckBox("Form 1099DIV"), new JCheckBox("Form 1099INT")};
	private JCheckBox[] jcb2 = {new JCheckBox("Ascending"), new JCheckBox("Descending")};
	private JCheckBox[] jcb3 = {
			new JCheckBox("tin"),
			new JCheckBox("transactions"),
			new JCheckBox("quantity"),
			new JCheckBox("description"),
			new JCheckBox("cusip"),
			new JCheckBox("owner"),
			new JCheckBox("reportingCategory"),
			new JCheckBox("dateOfAcquisition"),
			new JCheckBox("dateOfSaleOrExchange"),
			new JCheckBox("salesPrice"),
			new JCheckBox("costBasis"),
			new JCheckBox("accruedMarketDiscount"),
			new JCheckBox("washSaleLossDisallowed"),
			new JCheckBox("gainOrLoss"),
			new JCheckBox("transactionDescription"),
			new JCheckBox("YTDAmortizationOrAccretion"),
			new JCheckBox("LTDAmortizationOrAccretion"),
			new JCheckBox("ProceedsFromCollectibles"),
			new JCheckBox("SalesPrice1099B"),
			new JCheckBox("CostBasis1099B"),
			new JCheckBox("GainOrLoss1099B")
	};
	
	// JTable
	private DefaultTableModel model;
	
	private ResultSet rs;
	private DatabaseConnection dbconnection;
	
	public DataExportPreviewGUI() {
		f = new JFrame("Export");
		b1 = new JButton();
		b2 = new JButton();
		cb1 = new JCheckBox();
		cb2 = new JCheckBox();
		cb3 = new JCheckBox();
		l = new JLabel("Pick which forms you would like to export");
		isChecked = new boolean[5];
		tinL = new JLabel("Tin:");
		tinF = new JTextField(10);
		tinPanel = new JPanel();

		f2 = new JFrame("Sort/Preview/Export");
		b3 = new JButton();
		b4 = new JButton();
		b6 = new JButton();
		b7 = new JButton();
		l1 = new JLabel("Note: The 1040 form will always be exported as an PDF.");

		f3 = new JFrame("Sort");
		updateButton = new JButton("Update");
		
		rs = null;
		dbconnection = new DatabaseConnection();
		dataExportGUISetup();
	}

	private void dataExportGUISetup() {
		
		f.setLayout(new GridLayout(8, 1));
		
		l.setHorizontalAlignment(JLabel.CENTER);
		l.setVerticalAlignment(JLabel.TOP);
		
		// Determines whether checkboxes are checked or not
		cb1.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cb1.isSelected()) isChecked[0] = true;
				else isChecked[0] = false;
			}
			
		});
		cb1.setText("Form 1099B");
		
		cb2.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cb2.isSelected()) isChecked[1] = true;
				else isChecked[1] = false;
			}
			
		});
		cb2.setText("Form 1099DIV");
		
		cb3.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(cb3.isSelected()) isChecked[2] = true;
				else isChecked[2] = false;
			}
			
		});
		cb3.setText("Form 1099INT");
		
		// Button that takes user back to main GUI
		b1.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainUserGUI mainuserGUI = new MainUserGUI();
				f.dispose();
			}
			
		});
		b1.setText("back to main menu");
		
		// Button that saves selected information
		b2.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				try {
					sortAndPreviewGUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		b2.setText("save");
		
		f.add(b1);
		f.add(l);
		tinPanel.setLayout(new GridLayout(1, 2));
		tinPanel.add(tinL);
		tinPanel.add(tinF);
		f.add(tinPanel);
		f.add(cb1);
		f.add(cb2);
		f.add(cb3);
		f.add(b2);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(new Dimension(500, 500));
		f.setResizable(true);
		f.pack();
		f.setVisible(true);
	}
	
	private void sortAndPreviewGUI() throws SQLException {
		
		
		JPanel mainManuPanel = new JPanel();
		mainManuPanel.setLayout(new GridLayout(1, 1));
		mainManuPanel.add(b3);
		
		// Button that takes user back to main GUI
		b3.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainUserGUI mainuserGUI = new MainUserGUI();
				f2.dispose();
			}
			
		});
		b3.setText("back to main menu");
		
		// Button that takes user back to sort GUI
		b4.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sortGUI();
			}
			
		});
		b4.setText("Sort");

		if(isChecked[0]) {
			rs = dbconnection.readFromForm1099B(tinF.getText());
			
			String[] columns = {"tin", "transactions", "quantity", "description", "cusip", "owner", "reportingCategory", "dateOfAcquisition", "dateOfSaleOrExchange", "salesPrice", "costBasis", "accruedMarketDiscount", "washSaleLossDisallowed", "gainOrLoss", "transactionDescription", "YTDAmortizationOrAccretion", "LTDAmortizationOrAccretion", "ProceedsFromCollectibles", "SalesPrice1099B", "CostBasis1099B", "GainOrLoss1099B"};
			model = new DefaultTableModel(columns, 0);
			jt1 = new JTable(model);
			while(rs.next()) {
				String[] data = {
				String.valueOf(rs.getInt("tin")),
				rs.getString("transactions"),
				String.valueOf(rs.getDouble("quantity")),
				rs.getString("description"),
				rs.getString("cusip"),
				rs.getString("owner"),
				String.valueOf(rs.getString("reportingCategory").charAt(0)),
				rs.getString("dateOfAcquisition"),
				rs.getString("dateOfSaleOrExchange"),
				String.valueOf(rs.getDouble("salesPrice")),
				String.valueOf(rs.getDouble("costBasis")),
				String.valueOf(rs.getDouble("accruedMarketDiscount")),
				String.valueOf(rs.getDouble("washSaleLossDisallowed")),
				String.valueOf(rs.getDouble("washSaleLossDisallowed")),
				rs.getString("transactionDescription"),
				String.valueOf(rs.getByte("YTDAmortizationOrAccretion")),
				String.valueOf(rs.getByte("LTDAmortizationOrAccretion")),
				String.valueOf(rs.getByte("ProceedsFromCollectibles")),
				String.valueOf(rs.getInt("SalesPrice1099B")),
				String.valueOf(rs.getInt("CostBasis1099B")),
				String.valueOf(rs.getInt("GainOrLoss1099B"))
				};
				model.addRow(data);
			}
		}
		else if (isChecked[1]) {
			rs = dbconnection.readFromForm1099DIV(tinF.getText());

			String[] columns = {
				"tin", "accountNumber", "totalOrdinaryDividends", "qualifiedDividends", "totalCapitalGainDist",
				"unrecapSec1250Gain", "section1202Gain", "collectiblesGain", "section897OrdinaryDividends",
				"section897CapitalGain", "nondividendDistributions", "federalIncomeTaxWithheld",
				"section199ADividends", "investmentExpenses", "foreignTaxPaid", "foreignCountryOrUSPossession",
				"liquidationDistributionsCash", "liquidationDistributionsNonCash", "exemptInterestDividends",
				"specifiedPrivateActivityBondInterestDividends", "state", "stateIdentificationNo", "stateTaxWithheld"
			};
			model = new DefaultTableModel(columns, 0);
			jt1 = new JTable(model);

			while (rs.next()) {
				String[] data = {
					String.valueOf(rs.getInt("tin")),
					String.valueOf(rs.getDouble("accountNumber")),
					String.valueOf(rs.getDouble("totalOrdinaryDividends")),
					String.valueOf(rs.getDouble("qualifiedDividends")),
					String.valueOf(rs.getDouble("totalCapitalGainDist")),
					String.valueOf(rs.getDouble("unrecapSec1250Gain")),
					String.valueOf(rs.getInt("section1202Gain")),
					String.valueOf(rs.getDouble("collectiblesGain")),
					String.valueOf(rs.getDouble("section897OrdinaryDividends")),
					String.valueOf(rs.getDouble("section897CapitalGain")),
					String.valueOf(rs.getDouble("nondividendDistributions")),
					String.valueOf(rs.getDouble("federalIncomeTaxWithheld")),
					String.valueOf(rs.getDouble("section199ADividends")),
					String.valueOf(rs.getInt("investmentExpenses")),
					String.valueOf(rs.getDouble("foreignTaxPaid")),
					rs.getString("foreignCountryOrUSPossession"),
					String.valueOf(rs.getDouble("liquidationDistributionsCash")),
					String.valueOf(rs.getDouble("liquidationDistributionsNonCash")),
					String.valueOf(rs.getDouble("exemptInterestDividends")),
					String.valueOf(rs.getDouble("specifiedPrivateActivityBondInterestDividends")),
					rs.getString("state"),
					String.valueOf(rs.getInt("stateIdentificationNo")),
					String.valueOf(rs.getInt("stateTaxWithheld"))
				};
				model.addRow(data);
			}
		}
		else if (isChecked[2]) {
			rs = dbconnection.readFromForm1099INT(tinF.getText());

			String[] columns = {
				"tin", "accountNumber", "interestIncome", "earlyWithdrawalPenalty", "usSavingsBondInterest",
				"interestOnUSObligations", "foreignTaxPaid", "foreignCountryOrUSPossession", "taxExemptInterest",
				"specifiedPrivateActivityBondInterest", "marketDiscount", "bondPremium", "bondPremiumTreasury",
				"bondPremiumTaxExemptBond", "federalIncomeTaxWithheld", "investmentExpenses", "state",
				"stateIdentificationNo", "stateTaxWithheld"
			};
			model = new DefaultTableModel(columns, 0);
			jt1 = new JTable(model);

			while (rs.next()) {
				String[] data = {
					String.valueOf(rs.getInt("tin")),
					String.valueOf(rs.getInt("accountNumber")),
					String.valueOf(rs.getDouble("interestIncome")),
					String.valueOf(rs.getInt("earlyWithdrawalPenalty")),
					String.valueOf(rs.getInt("usSavingsBondInterest")),
					String.valueOf(rs.getInt("interestOnUSObligations")),
					String.valueOf(rs.getInt("foreignTaxPaid")),
					rs.getString("foreignCountryOrUSPossession"),
					String.valueOf(rs.getInt("taxExemptInterest")),
					String.valueOf(rs.getInt("specifiedPrivateActivityBondInterest")),
					String.valueOf(rs.getInt("marketDiscount")),
					String.valueOf(rs.getInt("bondPremium")),
					String.valueOf(rs.getInt("bondPremiumTreasury")),
					String.valueOf(rs.getInt("bondPremiumTaxExemptBond")),
					String.valueOf(rs.getInt("federalIncomeTaxWithheld")),
					String.valueOf(rs.getInt("investmentExpenses")),
					rs.getString("state"),
					String.valueOf(rs.getInt("stateIdentificationNo")),
					String.valueOf(rs.getInt("stateTaxWithheld"))
				};
				model.addRow(data);
			}
		}

		
		JPanel otherButtonsPanel = new JPanel();
		otherButtonsPanel.setLayout(new GridLayout(7, 1));
				
		// Button that exports data as an PDF
		b6.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String headerText = "";

				for (int i = 0; i < jcb.length; i++) {
					if (jcb[i].isSelected()) {
						if (headerText.equals("")) {
							headerText += jcb[i].getText();
						} else {
							headerText += ", " + jcb[i].getText();
						}
					}
				}
				
				MessageFormat header = new MessageFormat(headerText);
				MessageFormat footer = new MessageFormat("");

				try{
					PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
					set.add(OrientationRequested.LANDSCAPE);
					jt1.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, true);
					JOptionPane.showMessageDialog(null, "\n" + "Printed Successfully");
				} catch (java.awt.print.PrinterException e1) {
					JOptionPane.showMessageDialog(null, "\n" + "Failed" + "\n" + e1);
				}
			}
			
		});
		b6.setText("Export as a PDF");
		
		// Button that exports data as an spreadsheet
		b7.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportToExcel(jt1);
			}
			
		});
		b7.setText("Export as a spreadsheet");
				
		f2.add(mainManuPanel, BorderLayout.NORTH);
		jt1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt1.setPreferredScrollableViewportSize(new Dimension(500, 500));
		f2.add(new JScrollPane(jt1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		otherButtonsPanel.add(b4);
		otherButtonsPanel.add(b6);
		otherButtonsPanel.add(b7);
		otherButtonsPanel.add(l1);
		f2.add(otherButtonsPanel, BorderLayout.SOUTH);
		
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setSize(new Dimension(700, 500));
		f2.setResizable(true);
		f2.setVisible(true);
	}
	
	private void sortGUI() {
		
		f3.setLayout(new GridLayout(4,1));
		
		c1 = new JComboBox<>(jcb);
		c2 = new JComboBox<>(jcb2);
		c3 = new JComboBox<>(jcb3);

		c1.setRenderer((list, value, index, isSelected, cellHasFocus) -> value);
		c2.setRenderer((list, value, index, isSelected, cellHasFocus) -> value);
		c3.setRenderer((list, value, index, isSelected, cellHasFocus) -> value);

		c1.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox selected = (JCheckBox) c1.getSelectedItem();
                if (selected != null) {
                    selected.setSelected(!selected.isSelected());
                }
			}
		});

		c2.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox selected = (JCheckBox) c2.getSelectedItem();
				if (selected != null) {
					selected.setSelected(!selected.isSelected());
				}

            }
		});

		c3.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox selected = (JCheckBox) c3.getSelectedItem();
				if (selected != null) {
					selected.setSelected(!selected.isSelected());
				}

			}
		});
		
		updateButton.setAction(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rs = dbconnection.orderForm1099B(tinF.getText(), jcb[0].isSelected(), jcb[1].isSelected(), jcb[2].isSelected(), jcb2[0].isSelected(), jcb2[1].isSelected(), jcb3);
					
					model.setRowCount(0);
					
					if(isChecked[0]) {
						while(rs.next()) {
							String[] data = {
							String.valueOf(rs.getInt("tin")),
							rs.getString("transactions"),
							String.valueOf(rs.getDouble("quantity")),
							rs.getString("description"),
							rs.getString("cusip"),
							rs.getString("owner"),
							String.valueOf(rs.getString("reportingCategory").charAt(0)),
							rs.getString("dateOfAcquisition"),
							rs.getString("dateOfSaleOrExchange"),
							String.valueOf(rs.getDouble("salesPrice")),
							String.valueOf(rs.getDouble("costBasis")),
							String.valueOf(rs.getDouble("accruedMarketDiscount")),
							String.valueOf(rs.getDouble("washSaleLossDisallowed")),
							String.valueOf(rs.getDouble("washSaleLossDisallowed")),
							rs.getString("transactionDescription"),
							String.valueOf(rs.getByte("YTDAmortizationOrAccretion")),
							String.valueOf(rs.getByte("LTDAmortizationOrAccretion")),
							String.valueOf(rs.getByte("ProceedsFromCollectibles")),
							String.valueOf(rs.getInt("SalesPrice1099B")),
							String.valueOf(rs.getInt("CostBasis1099B")),
							String.valueOf(rs.getInt("GainOrLoss1099B"))
							};
							model.addRow(data);
						}
					}
					
					else if (isChecked[1]) {
						while (rs.next()) {
							String[] data = {
								String.valueOf(rs.getInt("tin")),
								String.valueOf(rs.getInt("accountNumber")),
								String.valueOf(rs.getInt("totalOrdinaryDividends")),
								String.valueOf(rs.getInt("qualifiedDividends")),
								String.valueOf(rs.getInt("totalCapitalGainDist")),
								String.valueOf(rs.getInt("unrecapSec1250Gain")),
								String.valueOf(rs.getInt("section1202Gain")),
								String.valueOf(rs.getInt("collectiblesGain")),
								String.valueOf(rs.getInt("section897OrdinaryDividends")),
								String.valueOf(rs.getInt("section897CapitalGain")),
								String.valueOf(rs.getInt("nondividendDistributions")),
								String.valueOf(rs.getInt("federalIncomeTaxWithheld")),
								String.valueOf(rs.getInt("section199ADividends")),
								String.valueOf(rs.getInt("investmentExpenses")),
								String.valueOf(rs.getInt("foreignTaxPaid")),
								rs.getString("foreignCountryOrUSPossession"),
								String.valueOf(rs.getInt("liquidationDistributionsCash")),
								String.valueOf(rs.getInt("liquidationDistributionsNonCash")),
								String.valueOf(rs.getInt("exemptInterestDividends")),
								String.valueOf(rs.getInt("specifiedPrivateActivityBondInterestDividends")),
								rs.getString("state"),
								String.valueOf(rs.getInt("stateIdentificationNo")),
								String.valueOf(rs.getInt("stateTaxWithheld"))
							};
							model.addRow(data);
						}
					}
					else if (isChecked[2]) {
						while (rs.next()) {
							String[] data = {
								String.valueOf(rs.getInt("tin")),
								String.valueOf(rs.getInt("accountNumber")),
								String.valueOf(rs.getDouble("interestIncome")),
								String.valueOf(rs.getInt("earlyWithdrawalPenalty")),
								String.valueOf(rs.getInt("usSavingsBondInterest")),
								String.valueOf(rs.getInt("interestOnUSObligations")),
								String.valueOf(rs.getInt("foreignTaxPaid")),
								rs.getString("foreignCountryOrUSPossession"),
								String.valueOf(rs.getInt("taxExemptInterest")),
								String.valueOf(rs.getInt("specifiedPrivateActivityBondInterest")),
								String.valueOf(rs.getInt("marketDiscount")),
								String.valueOf(rs.getInt("bondPremium")),
								String.valueOf(rs.getInt("bondPremiumTreasury")),
								String.valueOf(rs.getInt("bondPremiumTaxExemptBond")),
								String.valueOf(rs.getInt("federalIncomeTaxWithheld")),
								String.valueOf(rs.getInt("investmentExpenses")),
								rs.getString("state"),
								String.valueOf(rs.getInt("stateIdentificationNo")),
								String.valueOf(rs.getInt("stateTaxWithheld"))
							};
							model.addRow(data);
						}
					}
					
					model.fireTableDataChanged();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		updateButton.setText("Update");

		f3.add(c1);
		f3.add(c2);
		f3.add(c3);
		f3.add(updateButton);

		f3.setSize(new Dimension(250, 120));
		f3.setResizable(true);
		f3.setVisible(true);
	}
	
	private void exportToExcel(JTable table) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Excel File");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String fileLocation = fileChooser.getSelectedFile().getAbsolutePath() + ".xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(jcb[0].getText());

            TableModel model = table.getModel();
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < model.getColumnCount(); col++) {
                headerRow.createCell(col).setCellValue(model.getColumnName(col));
            }

            for (int row = 0; row < model.getRowCount(); row++) {
            	Row dataRow = sheet.createRow(row + 1);
                for (int col = 0; col < model.getColumnCount(); col++) {
                	Object value = model.getValueAt(row, col);
                    if (value != null) {
                    	dataRow.createCell(col).setCellValue(value.toString());
                    } else {
                    	dataRow.createCell(col).setCellValue("");
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(fileLocation)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "Excel file saved successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving file: " + e.getMessage());
            }
        }
	}

}