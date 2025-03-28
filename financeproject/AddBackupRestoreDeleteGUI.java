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
	private DatabaseConnection db;
	
	private JLabel jl2;
	private JPanel panelWithAddRadioButtons;
	private JTable jt;
	private JPanel panelWithAddJTable;
	private String[] columns = {"tin", "transactions", "quantity", "description", "cusip", "owner", "reportingCategory", "dateOfAcquisition", "dateOfSaleOrExchange", "salesPrice", "costBasis", "accruedMarketDiscount", "washSaleLossDisallowed", "gainOrLoss", "transactionDescription", "YTDAmortizationOrAccretion", "LTDAmortizationOrAccretion", "ProceedsFromCollectibles", "SalesPrice1099B", "CostBasis1099B", "GainOrLoss1099B"};

	private JPanel panelWithAddRowButton;
	private JButton addRow;
	private JLabel colNames[];
	private JTextField colFields[];
	private String[] colFieldData;
	private DatabaseConnection db2;
	private DefaultTableModel model;
	private ResultSet rs;
	
	private JLabel jl3;
	private JPanel panelWithRemoveRadioButtons;
	private JTable removeJt;
	private JPanel panelWithRemoveJTable;
	private JPanel panelWithRemoveRowButton;
	private JButton removeRow;
	private JRadioButton jrbRemove1099b;
	private JTextField[] removeColFields;
	private String[] removeColFieldData;

	private JPanel panelWithUpdateRadioButtons;
	private JPanel panelWithUpdateRowButton;
	private JPanel panelWithUpdateJTable;
	private JRadioButton jrbUpdate1099b;
	private JTable updateJt;
	private JButton updateRow;
	private String[] updateColFieldData;
	private JTextField[] updateColFields;
	private String[] rowBeingUpdated;

	public AddBackupRestoreDeleteGUI() {
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
		db = new DatabaseConnection();
		jl2 = new JLabel("Columns");
		panelWithAddRadioButtons = new JPanel();
		panelWithAddJTable = new JPanel();
		
		panelWithAddRowButton = new JPanel();
		addRow = new JButton("Add Row");
		colNames = new JLabel[21];
		colFields = new JTextField[21];
		removeColFields = new JTextField[21];
		updateColFields = new JTextField[21];
		for(int i = 0; i<21; i++) {
			colNames[i] = new JLabel();
			colFields[i] = new JTextField();
			removeColFields[i] = new JTextField();
			updateColFields[i] = new JTextField();
		}
		colFieldData = new String[21];
		updateColFieldData = new String[21];
		db2 = new DatabaseConnection();
		model = new DefaultTableModel(columns, 0);
		try {
			rs = db.readFromForm1099B();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		panelWithRemoveRadioButtons = new JPanel();
		panelWithRemoveJTable = new JPanel();
		panelWithRemoveRowButton = new JPanel();
		removeRow = new JButton("Remove Row");
		jrbRemove1099b = new JRadioButton();
		removeColFieldData = new String[21];
		
		panelWithUpdateRadioButtons = new JPanel();
		panelWithUpdateRowButton = new JPanel();
		panelWithUpdateJTable  = new JPanel();
		jrbUpdate1099b = new JRadioButton();
		updateJt = new JTable();
		updateRow = new JButton("Update Row");
		rowBeingUpdated = new String[21];
		
		GUISetup();
	}

	private void GUISetup() {
		// GridLayout: 6 Rows and 1 Column
		f.setLayout(new GridLayout(6, 1));
		
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
		panelWithAddRowButton.setLayout(new GridLayout(21,2));
		
		addDataFrame.setSize(new Dimension(800, 200));
		
		jrbadd1099b.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					rs = db.readFromForm1099B();
					
					model.setRowCount(0);
					
					jt = new JTable(model);
					previewJTable(rs);
										
					jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					panelWithAddJTable.add(new JScrollPane(jt));
					jt.setPreferredScrollableViewportSize(new Dimension(650, 150));
					panelWithAddJTable.setPreferredSize(new Dimension(500, 150));
					addDataFrame.add(panelWithAddJTable, BorderLayout.CENTER);
					panelWithAddRowButton.setPreferredSize(new Dimension(250, 350));
					addDataFrame.add(panelWithAddRowButton, BorderLayout.SOUTH);
					addDataFrame.setSize(new Dimension(800, 740));
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jrbadd1099b.setText("Form 1099B");
		
		addRow.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i = 0; i < 21; i++) {
						colFieldData[i] = colFields[i].getText();
					}
					db2.addDB(colFieldData);
					JOptionPane.showMessageDialog(addRow, "Row Added!");
					
					rs = db.readFromForm1099B();
					
					model.setRowCount(0);
					previewJTable(rs);
					model.fireTableDataChanged();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		addRow.setText("Add Row");
			
		panelWithAddRadioButtons.add(jl);
		panelWithAddRadioButtons.add(jrbadd1099b);
		panelWithAddRadioButtons.add(jl2);
		for(int i = 0; i < 21; i++) {
			colNames[i].setText(columns[i]);
			panelWithAddRowButton.add(colNames[i]);
			panelWithAddRowButton.add(colFields[i]);
		}
		panelWithAddRadioButtons.add(addRow);
		addDataFrame.add(panelWithAddRadioButtons, BorderLayout.NORTH);

		addDataFrame.setResizable(true);
		addDataFrame.setVisible(true);
	}
	
	private void updateData() {
		
		panelWithUpdateRadioButtons.setLayout(new GridLayout(6,2));
		panelWithUpdateRowButton.setLayout(new GridLayout(21,2));
		
		f3.setSize(new Dimension(800, 200));
		
		jrbUpdate1099b.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					rs = db.readFromForm1099B();
					
					model.setRowCount(0);
					updateJt = new JTable(model);
					previewJTable(rs);
					
					updateJt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					panelWithUpdateJTable.add(new JScrollPane(updateJt));
					updateJt.setPreferredScrollableViewportSize(new Dimension(650, 150));
					panelWithUpdateJTable.setPreferredSize(new Dimension(500, 150));
					f3.add(panelWithUpdateJTable, BorderLayout.CENTER);
					panelWithUpdateRowButton.setPreferredSize(new Dimension(250, 350));
					f3.add(panelWithUpdateRowButton, BorderLayout.SOUTH);
					f3.setSize(new Dimension(800, 740));
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		jrbUpdate1099b.setText("Form 1099B");
		
		updateRow.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i = 0; i < 21; i++) {
						updateColFieldData[i] = updateColFields[i].getText();
					}
					for (int i = 0; i < 21; i++) {
		                rowBeingUpdated[i] = updateJt.getValueAt(updateJt.getSelectedRow(), i).toString();
		            }
					db2.updateDB(columns, rowBeingUpdated, updateColFieldData);
					JOptionPane.showMessageDialog(updateRow, "Row Updated!");
					
					rs = db.readFromForm1099B();
					
					model.setRowCount(0);
					previewJTable(rs);
					model.fireTableDataChanged();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		updateRow.setText("Update Row");
		
		panelWithUpdateRadioButtons.add(jl);
		panelWithUpdateRadioButtons.add(jrbUpdate1099b);
		panelWithUpdateRadioButtons.add(jl2);
		for(int i = 0; i < 21; i++) {
			colNames[i].setText(columns[i]);
			panelWithUpdateRowButton.add(colNames[i]);
			panelWithUpdateRowButton.add(updateColFields[i]);
		}
		panelWithUpdateRadioButtons.add(updateRow);
		f3.add(panelWithUpdateRadioButtons, BorderLayout.NORTH);
		
		f3.setSize(new Dimension(500, 500));
		f3.setResizable(true);
		f3.setVisible(true);
	}
	
	private void backupData() {
		db.backupDB();
	}
	
	private void restoreData() {
		db.restoreDB();
	}
	
	private void deleteData() {
		
		panelWithRemoveRadioButtons.setLayout(new GridLayout(6,2));
		panelWithRemoveRowButton.setLayout(new GridLayout(21,2));
		
		f4.setSize(new Dimension(800, 200));
		
		jrbRemove1099b.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				
				removeJt = new JTable(model);
				previewJTable(rs);
				
				removeJt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				panelWithRemoveJTable.add(new JScrollPane(removeJt));
				removeJt.setPreferredScrollableViewportSize(new Dimension(650, 150));
				panelWithRemoveJTable.setPreferredSize(new Dimension(500, 150));
				f4.add(panelWithRemoveJTable, BorderLayout.CENTER);
				panelWithRemoveRowButton.setPreferredSize(new Dimension(250, 350));
				f4.add(panelWithRemoveRowButton, BorderLayout.SOUTH);
				f4.setSize(new Dimension(800, 740));
			}
		});
		jrbRemove1099b.setText("Form 1099B");
		
		removeRow.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i = 0; i < 21; i++) {
						removeColFieldData[i] = removeColFields[i].getText();
					}
					db2.removeDB(removeColFieldData, columns);
					JOptionPane.showMessageDialog(removeRow, "Row Removed!");
					
					rs = db.readFromForm1099B();
					
					model.setRowCount(0);
					previewJTable(rs);
					model.fireTableDataChanged();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		removeRow.setText("Remove Row");
		
		panelWithRemoveRadioButtons.add(jl);
		panelWithRemoveRadioButtons.add(jrbRemove1099b);
		panelWithRemoveRadioButtons.add(jl2);
		for(int i = 0; i < 21; i++) {
			colNames[i].setText(columns[i]);
			panelWithRemoveRowButton.add(colNames[i]);
			panelWithRemoveRowButton.add(removeColFields[i]);
		}
		panelWithRemoveRadioButtons.add(removeRow);
		f4.add(panelWithRemoveRadioButtons, BorderLayout.NORTH);

		f4.setResizable(true);
		f4.setVisible(true);
		
		f4.setSize(new Dimension(500, 500));
		f4.setResizable(true);
		f4.setVisible(true);
	}
		
	private void previewJTable(ResultSet rs) {
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
