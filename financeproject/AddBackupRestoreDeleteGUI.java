package financeproject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddBackupRestoreDeleteGUI {
	private JFrame f;
	private JButton b1;
	private JButton addB;
	private JButton updateB;
	private JButton backupB;
	private JButton restoreB;
	private JButton deleteB;
	
	private JFrame addDataFrame;
	private JFrame f3;
	private JFrame f4;
	
	private JLabel jl;
	private JRadioButton jrbadd1099b;
	private JRadioButton jrbadd1099div;
	private JRadioButton jrbadd1099int;
	private boolean addDataFormIsSelected[];
	private DatabaseConnection db;
	
	private JLabel jl2;
	private JPanel panelWithAddRadioButtons;
	private JTable jt;
	private JPanel panelWithAddJTable;
	private String[] columns1099B = {"tin", "transactions", "quantity", "description", "cusip", "owner", "reportingCategory", "dateOfAcquisition", "dateOfSaleOrExchange", "salesPrice", "costBasis", "accruedMarketDiscount", "washSaleLossDisallowed", "gainOrLoss", "transactionDescription", "YTDAmortizationOrAccretion", "LTDAmortizationOrAccretion", "ProceedsFromCollectibles", "SalesPrice1099B", "CostBasis1099B", "GainOrLoss1099B"};

	private JPanel panelWithAddRowButton;
	private JButton addRow;
	private JLabel colNames1099B[];
	private JTextField colFields1099B[];
	private String[] colFieldData1099B;
	private DatabaseConnection db2;
	private DefaultTableModel model1099B;
	private ResultSet rs;
	
	private JPanel panelWithRemoveRadioButtons;
	private JTable removeJt;
	private JPanel panelWithRemoveJTable;
	private JPanel panelWithRemoveRowButton;
	private JButton removeRow;
	private JRadioButton jrbRemove1099b;
	private JRadioButton jrbRemove1099div;
	private JRadioButton jrbRemove1099int;
	private boolean removeDataFormIsSelected[];
	private JTextField[] removeColFields1099B;
	private String[] removeColFieldData1099B;

	private JPanel panelWithUpdateRadioButtons;
	private JPanel panelWithUpdateRowButton;
	private JPanel panelWithUpdateJTable;
	private JRadioButton jrbUpdate1099b;
	private JRadioButton jrbUpdate1099div;
	private JRadioButton jrbUpdate1099int;
	private boolean updateDataFormIsSelected[];
	private JTable updateJt;
	private JButton updateRow;
	private String[] updateColFieldData1099B;
	private JTextField[] updateColFields1099B;
	private String[] rowBeingUpdated1099B;

	private JLabel tinL;
	private JTextField tinF;

	private String[] columns1099DIV = {"tin", "accountNumber", "totalOrdinaryDividends", "qualifiedDividends", "totalCapitalGainDist",
			"unrecapSec1250Gain", "section1202Gain", "collectiblesGain", "section897OrdinaryDividends",
			"section897CapitalGain", "nondividendDistributions", "federalIncomeTaxWithheld",
			"section199ADividends", "investmentExpenses", "foreignTaxPaid", "foreignCountryOrUSPossession",
			"liquidationDistributionsCash", "liquidationDistributionsNonCash", "exemptInterestDividends",
			"specifiedPrivateActivityBondInterestDividends", "state", "stateIdentificationNo", "stateTaxWithheld"};
	private JLabel colNames1099DIV[];
	private JTextField colFields1099DIV[];
	private String[] colFieldData1099DIV;
	private JTextField[] removeColFields1099DIV;
	private String[] removeColFieldData1099DIV;
	private String[] updateColFieldData1099DIV;
	private JTextField[] updateColFields1099DIV;
	private String[] rowBeingUpdated1099DIV;
	private DefaultTableModel model1099DIV;

	
	private String[] columns1099INT = {"tin", "accountNumber", "interestIncome", "earlyWithdrawalPenalty", "usSavingsBondInterest",
			"interestOnUSObligations", "foreignTaxPaid", "foreignCountryOrUSPossession", "taxExemptInterest",
			"specifiedPrivateActivityBondInterest", "marketDiscount", "bondPremium", "bondPremiumTreasury",
			"bondPremiumTaxExemptBond", "federalIncomeTaxWithheld", "investmentExpenses", "state",
			"stateIdentificationNo", "stateTaxWithheld"};
	private JLabel colNames1099INT[];
	private JTextField colFields1099INT[];
	private String[] colFieldData1099INT;
	private JTextField[] removeColFields1099INT;
	private String[] removeColFieldData1099INT;
	private String[] updateColFieldData1099INT;
	private JTextField[] updateColFields1099INT;
	private String[] rowBeingUpdated1099INT;
	private DefaultTableModel model1099INT;

	private JPanel tinPanel;
	
	public AddBackupRestoreDeleteGUI() {
		tinL = new JLabel("Tin:");
		tinF = new JTextField(10);

		f = new JFrame("Add/Update/Backup/Restore/Delete Data");
		addB = new JButton();
		updateB = new JButton();
		backupB = new JButton();
		restoreB = new JButton();
		deleteB = new JButton();
		b1 = new JButton();
		
		addDataFrame = new JFrame("Add Data");
		f3 = new JFrame("Update Data");
		f4 = new JFrame("Delete Data");
		
		jl = new JLabel("Forms");
		jrbadd1099b = new JRadioButton();
		jrbadd1099div = new JRadioButton();
		jrbadd1099int = new JRadioButton();
		addDataFormIsSelected = new boolean[3];
		db = new DatabaseConnection();
		jl2 = new JLabel("Columns");
		panelWithAddRadioButtons = new JPanel();
		panelWithAddJTable = new JPanel();
		
		panelWithAddRowButton = new JPanel();
		addRow = new JButton("Add Row");
		colNames1099B = new JLabel[21];
		colFields1099B = new JTextField[21];
		removeColFields1099B = new JTextField[21];
		updateColFields1099B = new JTextField[21];
		for(int i = 0; i<21; i++) {
			colNames1099B[i] = new JLabel();
			colFields1099B[i] = new JTextField();
			removeColFields1099B[i] = new JTextField();
			updateColFields1099B[i] = new JTextField();
		}
		colFieldData1099B = new String[21];
		updateColFieldData1099B = new String[21];
		db2 = new DatabaseConnection();
		model1099B = new DefaultTableModel(columns1099B, 0);
		model1099DIV = new DefaultTableModel(columns1099DIV, 0);
		model1099INT = new DefaultTableModel(columns1099INT, 0);
		
		panelWithRemoveRadioButtons = new JPanel();
		panelWithRemoveJTable = new JPanel();
		panelWithRemoveRowButton = new JPanel();
		removeRow = new JButton("Remove Row");
		jrbRemove1099b = new JRadioButton();
		jrbRemove1099div = new JRadioButton();
		jrbRemove1099int = new JRadioButton();
		removeDataFormIsSelected = new boolean[3];
		removeColFieldData1099B = new String[21];
		updateDataFormIsSelected = new boolean[3];
		panelWithUpdateRadioButtons = new JPanel();
		panelWithUpdateRowButton = new JPanel();
		panelWithUpdateJTable  = new JPanel();
		jrbUpdate1099b = new JRadioButton();
		jrbUpdate1099div = new JRadioButton();
		jrbUpdate1099int = new JRadioButton();
		
		updateJt = new JTable();
		updateRow = new JButton("Update Row");
		rowBeingUpdated1099B = new String[21];
		
		colNames1099DIV = new JLabel[23];
		colFields1099DIV = new JTextField[23];
		removeColFields1099DIV = new JTextField[23];
		updateColFields1099DIV = new JTextField[23];
		for(int i = 0; i<23; i++) {
			colNames1099DIV[i] = new JLabel();
			colFields1099DIV[i] = new JTextField();
			removeColFields1099DIV[i] = new JTextField();
			updateColFields1099DIV[i] = new JTextField();
		}
		colFieldData1099DIV = new String[23];
		updateColFieldData1099DIV = new String[23];
		removeColFieldData1099DIV = new String[23];
		rowBeingUpdated1099DIV = new String[23];
		
		colNames1099INT = new JLabel[19];
		colFields1099INT = new JTextField[19];
		removeColFields1099INT = new JTextField[19];
		updateColFields1099INT = new JTextField[19];
		for(int i = 0; i<19; i++) {
			colNames1099INT[i] = new JLabel();
			colFields1099INT[i] = new JTextField();
			removeColFields1099INT[i] = new JTextField();
			updateColFields1099INT[i] = new JTextField();
		}
		colFieldData1099DIV = new String[19];
		updateColFieldData1099DIV = new String[19];
		removeColFieldData1099DIV = new String[19];
		rowBeingUpdated1099DIV = new String[19];

		tinPanel = new JPanel();
		
		GUISetup();
	}

	private void GUISetup() {
		// GridLayout: 6 Rows and 1 Column
		f.setLayout(new GridLayout(8, 1));

		// Button that takes user back to main GUI
		b1.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainUserGUI mainuserGUI = new MainUserGUI();
				f.dispose();
			}
			
		});
		b1.setText("back to main menu");
		f.add(b1);

		tinPanel.setLayout(new GridLayout(1, 2));
		tinPanel.add(tinL);
		tinPanel.add(tinF);
		f.add(tinPanel);

		// Button that adds info
		addB.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addData();
			}
			
		});
		
		addB.setText("Add Info");
		addB.setPreferredSize(new Dimension(500, 100));
		f.add(addB);
		
		// Button that updates info
		updateB.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateData();
			}
			
		});
		updateB.setText("Update Info");
		updateB.setPreferredSize(new Dimension(500, 100));
		f.add(updateB);
		
		// Button that backups info
		backupB.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				backupData();
			}
			
		});
		backupB.setText("Backup Info");
		backupB.setPreferredSize(new Dimension(500, 100));
		f.add(backupB);
		
		// Button that restores info
		restoreB.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				restoreData();
			}
			
		});
		restoreB.setText("Restore Info");
		restoreB.setPreferredSize(new Dimension(500, 100));
		f.add(restoreB);
		
		// Button that deletes info
		deleteB.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteData();
			}
			
		});
		deleteB.setText("Delete Info");
		deleteB.setPreferredSize(new Dimension(500, 100));
		f.add(deleteB);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(new Dimension(500, 500));
		f.setResizable(true);
		f.setVisible(true);
	}
	
	private void addData() {
		
		panelWithAddRadioButtons.setLayout(new GridLayout(6,2));

		addDataFrame.setSize(new Dimension(800, 200));

		jrbadd1099b.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					// Clear all panels first
					panelWithAddRowButton.removeAll();
					panelWithAddJTable.removeAll();

					if(jrbadd1099b.isSelected()) {
						addDataFormIsSelected[0] = true;
						addDataFormIsSelected[1] = false;
						addDataFormIsSelected[2] = false;

						panelWithAddRowButton.setLayout(new GridLayout(21,2));

						rs = db.readFromForm1099B(tinF.getText());
						model1099B.setRowCount(0);

						for(int i = 0; i < 21; i++) {
							colNames1099B[i].setText(columns1099B[i]);
							panelWithAddRowButton.add(colNames1099B[i]);
							panelWithAddRowButton.add(colFields1099B[i]);
						}

						jt = new JTable(model1099B);
						previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);

						jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

						panelWithAddJTable.add(new JScrollPane(jt));
						jt.setPreferredScrollableViewportSize(new Dimension(650, 150));
						panelWithAddJTable.setPreferredSize(new Dimension(500, 150));
						addDataFrame.add(panelWithAddJTable, BorderLayout.CENTER);
						panelWithAddRowButton.setPreferredSize(new Dimension(250, 350));
						addDataFrame.add(panelWithAddRowButton, BorderLayout.SOUTH);
						addDataFrame.setSize(new Dimension(800, 740));
					}
					else {
						addDataFormIsSelected[0] = false;
						model1099B.setRowCount(0);

						addDataFrame.setSize(new Dimension(800, 200));
					}

					panelWithAddRowButton.revalidate();
					panelWithAddRowButton.repaint();
					panelWithAddJTable.revalidate();
					panelWithAddJTable.repaint();
					addDataFrame.revalidate();
					addDataFrame.repaint();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jrbadd1099b.setText("Form 1099B");

		jrbadd1099div.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					// Clear all panels first
					panelWithAddRowButton.removeAll();
					panelWithAddJTable.removeAll();

					if(jrbadd1099div.isSelected()) {
						addDataFormIsSelected[0] = false;
						addDataFormIsSelected[1] = true;
						addDataFormIsSelected[2] = false;

						panelWithAddRowButton.setLayout(new GridLayout(23,2));

						rs = db.readFromForm1099DIV(tinF.getText());
						model1099DIV.setRowCount(0);

						for(int i = 0; i < 23; i++) {
							colNames1099DIV[i].setText(columns1099DIV[i]);
							panelWithAddRowButton.add(colNames1099DIV[i]);
							panelWithAddRowButton.add(colFields1099DIV[i]);
						}

						jt = new JTable(model1099DIV);
						previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);

						jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

						panelWithAddJTable.add(new JScrollPane(jt));
						jt.setPreferredScrollableViewportSize(new Dimension(650, 150));
						panelWithAddJTable.setPreferredSize(new Dimension(500, 150));
						addDataFrame.add(panelWithAddJTable, BorderLayout.CENTER);
						panelWithAddRowButton.setPreferredSize(new Dimension(250, 350));
						addDataFrame.add(panelWithAddRowButton, BorderLayout.SOUTH);
						addDataFrame.setSize(new Dimension(800, 1000));
					}
					else {
						addDataFormIsSelected[1] = false;
						model1099B.setRowCount(0);

						addDataFrame.setSize(new Dimension(800, 200));
					}

					panelWithAddRowButton.revalidate();
					panelWithAddRowButton.repaint();
					panelWithAddJTable.revalidate();
					panelWithAddJTable.repaint();
					addDataFrame.revalidate();
					addDataFrame.repaint();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jrbadd1099div.setText("Form 1099DIV");

		jrbadd1099int.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					// Clear all panels first
					panelWithAddRowButton.removeAll();
					panelWithAddJTable.removeAll();

					if(jrbadd1099int.isSelected()) {
						addDataFormIsSelected[0] = false;
						addDataFormIsSelected[1] = false;
						addDataFormIsSelected[2] = true;

						panelWithAddRowButton.setLayout(new GridLayout(19,2));

						rs = db.readFromForm1099INT(tinF.getText());
						model1099INT.setRowCount(0);

						for(int i = 0; i < 19; i++) {
							colNames1099INT[i].setText(columns1099INT[i]);
							panelWithAddRowButton.add(colNames1099INT[i]);
							panelWithAddRowButton.add(colFields1099INT[i]);
						}

						jt = new JTable(model1099INT);
						previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);

						jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

						panelWithAddJTable.add(new JScrollPane(jt));
						jt.setPreferredScrollableViewportSize(new Dimension(650, 150));
						panelWithAddJTable.setPreferredSize(new Dimension(500, 150));
						addDataFrame.add(panelWithAddJTable, BorderLayout.CENTER);
						panelWithAddRowButton.setPreferredSize(new Dimension(250, 350));
						addDataFrame.add(panelWithAddRowButton, BorderLayout.SOUTH);
						addDataFrame.setSize(new Dimension(800, 740));
					}
					else {
						addDataFormIsSelected[2] = false;
						model1099B.setRowCount(0);

						addDataFrame.setSize(new Dimension(800, 200));
					}

					panelWithAddRowButton.revalidate();
					panelWithAddRowButton.repaint();
					panelWithAddJTable.revalidate();
					panelWithAddJTable.repaint();
					addDataFrame.revalidate();
					addDataFrame.repaint();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jrbadd1099int.setText("Form 1099INT");

		addRow.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					if(jrbadd1099b.isSelected()) {
						for(int i = 0; i < 21; i++) {
							colFieldData1099B[i] = colFields1099B[i].getText();
						}
						db2.addDB(colFieldData1099B);
						rs = db.readFromForm1099B(tinF.getText());
					}
					else if(jrbadd1099div.isSelected()) {
						for(int i = 0; i < 23; i++) {
							colFieldData1099DIV[i] = colFields1099DIV[i].getText();
						}
						db2.addDB(colFieldData1099DIV);
						rs = db.readFromForm1099DIV(tinF.getText());
					}
					else if(jrbadd1099int.isSelected()) {
						for(int i = 0; i < 19; i++) {
							colFieldData1099INT[i] = colFields1099INT[i].getText();
						}
						db2.addDB(colFieldData1099INT);
						rs = db.readFromForm1099INT(tinF.getText());
					}
					JOptionPane.showMessageDialog(addRow, "Row Added!");

					model1099B.setRowCount(0);
					model1099DIV.setRowCount(0);
					model1099INT.setRowCount(0);
					previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);
					model1099B.fireTableDataChanged();
					model1099DIV.fireTableDataChanged();
					model1099INT.fireTableDataChanged();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		addRow.setText("Add Row");
			
		panelWithAddRadioButtons.add(jl);
		panelWithAddRadioButtons.add(jrbadd1099b);
		panelWithAddRadioButtons.add(jrbadd1099div);
		panelWithAddRadioButtons.add(jrbadd1099int);
		panelWithAddRadioButtons.add(jl2);
		panelWithAddRadioButtons.add(addRow);
		addDataFrame.add(panelWithAddRadioButtons, BorderLayout.NORTH);

		addDataFrame.setResizable(true);
		addDataFrame.setVisible(true);
	}
	
	private void updateData() {
		
		panelWithUpdateRadioButtons.setLayout(new GridLayout(6,2));

		f3.setSize(new Dimension(800, 200));
		
		jrbUpdate1099b.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					// Clear all panels first
					panelWithUpdateRowButton.removeAll();
					panelWithUpdateJTable.removeAll();

					if(jrbUpdate1099b.isSelected()) {
						updateDataFormIsSelected[0] = true;
						updateDataFormIsSelected[1] = false;
						updateDataFormIsSelected[2] = false;

						panelWithUpdateRowButton.setLayout(new GridLayout(21,2));

						rs = db.readFromForm1099B(tinF.getText());
						model1099B.setRowCount(0);

						for(int i = 0; i < 21; i++) {
							colNames1099B[i].setText(columns1099B[i]);
							panelWithUpdateRowButton.add(colNames1099B[i]);
							panelWithUpdateRowButton.add(updateColFields1099B[i]);
						}

						updateJt = new JTable(model1099B);
						previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);

						updateJt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						panelWithUpdateJTable.add(new JScrollPane(updateJt));
						updateJt.setPreferredScrollableViewportSize(new Dimension(650, 150));
						panelWithUpdateJTable.setPreferredSize(new Dimension(500, 150));
						f3.add(panelWithUpdateJTable, BorderLayout.CENTER);
						panelWithUpdateRowButton.setPreferredSize(new Dimension(250, 350));
						f3.add(panelWithUpdateRowButton, BorderLayout.SOUTH);
						f3.setSize(new Dimension(800, 740));
					}
					else {
						updateDataFormIsSelected[0] = false;
						model1099B.setRowCount(0);

						f3.setSize(new Dimension(800, 200));
					}

					panelWithUpdateRowButton.revalidate();
					panelWithUpdateRowButton.repaint();
					panelWithUpdateJTable.revalidate();
					panelWithUpdateJTable.repaint();
					f3.revalidate();
					f3.repaint();

				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jrbUpdate1099b.setText("Form 1099B");
		
		jrbUpdate1099div.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					// Clear all panels first
					panelWithUpdateRowButton.removeAll();
					panelWithUpdateJTable.removeAll();

					if(jrbUpdate1099div.isSelected()) {
						updateDataFormIsSelected[0] = false;
						updateDataFormIsSelected[1] = true;
						updateDataFormIsSelected[2] = false;

						panelWithUpdateRowButton.setLayout(new GridLayout(23,2));

						rs = db.readFromForm1099DIV(tinF.getText());
						model1099DIV.setRowCount(0);

						for(int i = 0; i < 23; i++) {
							colNames1099DIV[i].setText(columns1099DIV[i]);
							panelWithUpdateRowButton.add(colNames1099DIV[i]);
							panelWithUpdateRowButton.add(updateColFields1099DIV[i]);
						}

						updateJt = new JTable(model1099DIV);
						previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);

						updateJt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						panelWithUpdateJTable.add(new JScrollPane(updateJt));
						updateJt.setPreferredScrollableViewportSize(new Dimension(650, 150));
						panelWithUpdateJTable.setPreferredSize(new Dimension(500, 150));
						f3.add(panelWithUpdateJTable, BorderLayout.CENTER);
						panelWithUpdateRowButton.setPreferredSize(new Dimension(250, 350));
						f3.add(panelWithUpdateRowButton, BorderLayout.SOUTH);
						f3.setSize(new Dimension(800, 740));

					}
					else {
						updateDataFormIsSelected[1] = false;
						model1099B.setRowCount(0);

						f3.setSize(new Dimension(800, 200));
					}

					panelWithUpdateRowButton.revalidate();
					panelWithUpdateRowButton.repaint();
					panelWithUpdateJTable.revalidate();
					panelWithUpdateJTable.repaint();
					f3.revalidate();
					f3.repaint();

				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jrbUpdate1099div.setText("Form 1099DIV");
		
		jrbUpdate1099int.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// Clear all panels first
					panelWithUpdateRowButton.removeAll();
					panelWithUpdateJTable.removeAll();


					if(jrbUpdate1099int.isSelected()) {
						updateDataFormIsSelected[0] = false;
						updateDataFormIsSelected[1] = false;
						updateDataFormIsSelected[2] = true;

						panelWithUpdateRowButton.setLayout(new GridLayout(19,2));

						rs = db.readFromForm1099INT(tinF.getText());
						model1099INT.setRowCount(0);

						for(int i = 0; i < 19; i++) {
							colNames1099INT[i].setText(columns1099INT[i]);
							panelWithUpdateRowButton.add(colNames1099INT[i]);
							panelWithUpdateRowButton.add(updateColFields1099INT[i]);
						}

						updateJt = new JTable(model1099INT);
						previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);

						updateJt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						panelWithUpdateJTable.add(new JScrollPane(updateJt));
						updateJt.setPreferredScrollableViewportSize(new Dimension(650, 150));
						panelWithUpdateJTable.setPreferredSize(new Dimension(500, 150));
						f3.add(panelWithUpdateJTable, BorderLayout.CENTER);
						panelWithUpdateRowButton.setPreferredSize(new Dimension(250, 350));
						f3.add(panelWithUpdateRowButton, BorderLayout.SOUTH);
						f3.setSize(new Dimension(800, 740));

					}
					else {
						updateDataFormIsSelected[2] = false;
						model1099B.setRowCount(0);

						f3.setSize(new Dimension(800, 200));
					}

					} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jrbUpdate1099int.setText("Form 1099INT");
		
		updateRow.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {					
					
					if(jrbUpdate1099b.isSelected()) {
						for(int i = 0; i < 21; i++) {
							updateColFieldData1099B[i] = updateColFields1099B[i].getText();
						}
						for (int i = 0; i < 21; i++) {
			                rowBeingUpdated1099B[i] = updateJt.getValueAt(updateJt.getSelectedRow(), i).toString();
			            }
						db2.updateDB(columns1099B, rowBeingUpdated1099B, updateColFieldData1099B);
						rs = db.readFromForm1099B(tinF.getText());
					}
					else if(jrbUpdate1099div.isSelected()) {
						for(int i = 0; i < 23; i++) {
							updateColFieldData1099DIV[i] = updateColFields1099DIV[i].getText();
						}
						for (int i = 0; i < 23; i++) {
			                rowBeingUpdated1099DIV[i] = updateJt.getValueAt(updateJt.getSelectedRow(), i).toString();
			            }
						db2.updateDB(columns1099DIV, rowBeingUpdated1099DIV, updateColFieldData1099DIV);
						rs = db.readFromForm1099DIV(tinF.getText());
					}
					else if(jrbUpdate1099int.isSelected()) {
						for(int i = 0; i < 19; i++) {
							updateColFieldData1099INT[i] = updateColFields1099INT[i].getText();
						}
						for (int i = 0; i < 19; i++) {
			                rowBeingUpdated1099INT[i] = updateJt.getValueAt(updateJt.getSelectedRow(), i).toString();
			            }
						db2.updateDB(columns1099INT, rowBeingUpdated1099INT, updateColFieldData1099INT);
						rs = db.readFromForm1099INT(tinF.getText());
					}
					JOptionPane.showMessageDialog(updateRow, "Row Updated!");
					
					model1099B.setRowCount(0);
					model1099DIV.setRowCount(0);
					model1099INT.setRowCount(0);
					previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);
					model1099B.fireTableDataChanged();
					model1099DIV.fireTableDataChanged();
					model1099INT.fireTableDataChanged();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		updateRow.setText("Update Row");
		
		panelWithUpdateRadioButtons.add(jl);
		panelWithUpdateRadioButtons.add(jrbUpdate1099b);
		panelWithUpdateRadioButtons.add(jrbUpdate1099div);
		panelWithUpdateRadioButtons.add(jrbUpdate1099int);
		panelWithUpdateRadioButtons.add(jl2);

		panelWithUpdateRadioButtons.add(updateRow);
		f3.add(panelWithUpdateRadioButtons, BorderLayout.NORTH);
		
		f3.setSize(new Dimension(500, 500));
		f3.setResizable(true);
		f3.setVisible(true);
	}
	
	private void backupData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Backup File");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String fileLocation = fileChooser.getSelectedFile().getAbsolutePath() + ".bak";
            db.backupDB(fileLocation);
        }
		
	}
	
	private void restoreData() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Restore File");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String fileLocation = fileChooser.getSelectedFile().getAbsolutePath();
            db.restoreDB(fileLocation);
        }
	}
	
	private void deleteData() {
		
		panelWithRemoveRadioButtons.setLayout(new GridLayout(6,2));

		f4.setSize(new Dimension(800, 200));

		jrbRemove1099b.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					// Clear all panels first
					panelWithRemoveRowButton.removeAll();
					panelWithRemoveJTable.removeAll();

					if(jrbRemove1099b.isSelected()) {
						removeDataFormIsSelected[0] = true;
						removeDataFormIsSelected[1] = false;
						removeDataFormIsSelected[2] = false;

						panelWithRemoveRowButton.setLayout(new GridLayout(21,2));

						rs = db.readFromForm1099B(tinF.getText());
						model1099B.setRowCount(0);

						for(int i = 0; i < 21; i++) {
							colNames1099B[i].setText(columns1099B[i]);
							panelWithRemoveRowButton.add(colNames1099B[i]);
							panelWithRemoveRowButton.add(removeColFields1099B[i]);
						}

						removeJt = new JTable(model1099B);
						previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);

						removeJt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						panelWithRemoveJTable.add(new JScrollPane(removeJt));
						removeJt.setPreferredScrollableViewportSize(new Dimension(650, 150));
						panelWithRemoveJTable.setPreferredSize(new Dimension(500, 150));
						f4.add(panelWithRemoveJTable, BorderLayout.CENTER);
						panelWithRemoveRowButton.setPreferredSize(new Dimension(250, 350));
						f4.add(panelWithRemoveRowButton, BorderLayout.SOUTH);
						f4.setSize(new Dimension(800, 740));
					}
					else {
						removeDataFormIsSelected[0] = false;
						model1099B.setRowCount(0);

						f4.setSize(new Dimension(800, 200));
					}

					panelWithRemoveRowButton.revalidate();
					panelWithRemoveRowButton.repaint();
					panelWithRemoveJTable.revalidate();
					panelWithRemoveJTable.repaint();
					f4.revalidate();
					f4.repaint();

				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		});
		jrbRemove1099b.setText("Form 1099B");

		jrbRemove1099div.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					// Clear all panels first
					panelWithRemoveRowButton.removeAll();
					panelWithRemoveJTable.removeAll();

					if(jrbRemove1099div.isSelected()) {
						removeDataFormIsSelected[0] = false;
						removeDataFormIsSelected[1] = true;
						removeDataFormIsSelected[2] = false;

						panelWithRemoveRowButton.setLayout(new GridLayout(23,2));

						rs = db.readFromForm1099DIV(tinF.getText());
						model1099DIV.setRowCount(0);

						for(int i = 0; i < 23; i++) {
							colNames1099DIV[i].setText(columns1099DIV[i]);
							panelWithRemoveRowButton.add(colNames1099DIV[i]);
							panelWithRemoveRowButton.add(removeColFields1099DIV[i]);
						}

						removeJt = new JTable(model1099DIV);
						previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);

						removeJt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						panelWithRemoveJTable.add(new JScrollPane(removeJt));
						removeJt.setPreferredScrollableViewportSize(new Dimension(650, 150));
						panelWithRemoveJTable.setPreferredSize(new Dimension(500, 150));
						f4.add(panelWithRemoveJTable, BorderLayout.CENTER);
						panelWithRemoveRowButton.setPreferredSize(new Dimension(250, 350));
						f4.add(panelWithRemoveRowButton, BorderLayout.SOUTH);
						f4.setSize(new Dimension(800, 1000));
					}
					else {
						removeDataFormIsSelected[1] = false;
						model1099DIV.setRowCount(0);

						f4.setSize(new Dimension(800, 200));
					}

					panelWithRemoveRowButton.revalidate();
					panelWithRemoveRowButton.repaint();
					panelWithRemoveJTable.revalidate();
					panelWithRemoveJTable.repaint();
					f4.revalidate();
					f4.repaint();

				} catch (SQLException ex) {
					ex.printStackTrace();
				}

			}
		});
		jrbRemove1099div.setText("Form 1099DIV");
		
		jrbRemove1099int.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {

                try {
					panelWithRemoveRowButton.removeAll();
					panelWithRemoveJTable.removeAll();

					if(jrbRemove1099int.isSelected()) {
						removeDataFormIsSelected[0] = false;
						removeDataFormIsSelected[1] = false;
						removeDataFormIsSelected[2] = true;

						panelWithRemoveRowButton.setLayout(new GridLayout(19,2));

						rs = db.readFromForm1099INT(tinF.getText());
						model1099INT.setRowCount(0);

						for(int i = 0; i < 19; i++) {
							colNames1099INT[i].setText(columns1099INT[i]);
							panelWithRemoveRowButton.add(colNames1099INT[i]);
							panelWithRemoveRowButton.add(removeColFields1099INT[i]);
						}

						removeJt = new JTable(model1099INT);
						previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);

						removeJt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
						panelWithRemoveJTable.add(new JScrollPane(removeJt));
						removeJt.setPreferredScrollableViewportSize(new Dimension(650, 150));
						panelWithRemoveJTable.setPreferredSize(new Dimension(500, 150));
						f4.add(panelWithRemoveJTable, BorderLayout.CENTER);
						panelWithRemoveRowButton.setPreferredSize(new Dimension(250, 350));
						f4.add(panelWithRemoveRowButton, BorderLayout.SOUTH);
						f4.setSize(new Dimension(800, 740));

					}
					else {
						removeDataFormIsSelected[2] = false;
						model1099B.setRowCount(0);

						f4.setSize(new Dimension(800, 200));
					}

					panelWithRemoveRowButton.revalidate();
					panelWithRemoveRowButton.repaint();
					panelWithRemoveJTable.revalidate();
					panelWithRemoveJTable.repaint();
					f4.revalidate();
					f4.repaint();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

			}
		});
		jrbRemove1099int.setText("Form 1099INT");
		
		removeRow.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					if(jrbRemove1099b.isSelected()) {
						for(int i = 0; i < 21; i++) {
							removeColFieldData1099B[i] = removeColFields1099B[i].getText();
						}
						db2.removeDB(removeColFieldData1099B, columns1099B);
						rs = db.readFromForm1099B(tinF.getText());
					}
					else if(jrbRemove1099div.isSelected()) {
						for(int i = 0; i < 23; i++) {
							removeColFieldData1099DIV[i] = removeColFields1099DIV[i].getText();
						}
						db2.removeDB(removeColFieldData1099DIV, columns1099DIV);
						rs = db.readFromForm1099DIV(tinF.getText());
					}
					else if(jrbRemove1099int.isSelected()) {
						for(int i = 0; i < 19; i++) {
							removeColFieldData1099INT[i] = removeColFields1099INT[i].getText();
						}
						db2.removeDB(removeColFieldData1099INT, columns1099INT);
						rs = db.readFromForm1099INT(tinF.getText());
					}
					JOptionPane.showMessageDialog(removeRow, "Row Removed!");
					
					model1099B.setRowCount(0);
					model1099DIV.setRowCount(0);
					model1099INT.setRowCount(0);
					previewJTable(rs, addDataFormIsSelected, updateDataFormIsSelected, removeDataFormIsSelected);
					model1099B.fireTableDataChanged();
					model1099DIV.fireTableDataChanged();
					model1099INT.fireTableDataChanged();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		removeRow.setText("Remove Row");
		
		panelWithRemoveRadioButtons.add(jl);
		panelWithRemoveRadioButtons.add(jrbRemove1099b);
		panelWithRemoveRadioButtons.add(jrbRemove1099div);
		panelWithRemoveRadioButtons.add(jrbRemove1099int);
		panelWithRemoveRadioButtons.add(jl2);
		panelWithRemoveRadioButtons.add(removeRow);
		f4.add(panelWithRemoveRadioButtons, BorderLayout.NORTH);
		
		f4.setSize(new Dimension(500, 500));
		f4.setResizable(true);
		f4.setVisible(true);
	}
		
	private void previewJTable(ResultSet rs, boolean addDataFormIsSelected[], boolean updateDataFormIsSelected[], boolean removeDataFormIsSelected[]) {
		try {
			while(rs.next()) {
				if(addDataFormIsSelected[0] || updateDataFormIsSelected[0] || removeDataFormIsSelected[0]) {
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
					model1099B.addRow(data);
				}
				else if(addDataFormIsSelected[1] || updateDataFormIsSelected[1] || removeDataFormIsSelected[1]) {
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
					model1099DIV.addRow(data);
				}
				else if(addDataFormIsSelected[2] || updateDataFormIsSelected[2] || removeDataFormIsSelected[2]) {
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
					model1099INT.addRow(data);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}