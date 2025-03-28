package financeproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MainUserGUI {
	
	private JFrame f;
	private JLabel lbl;
	private JLabel lbl2;
	private JButton pidB;
	private JButton importB;
	private JButton exportB;
	private JButton addBackupRestoreDelete;
	private JButton form1040Generator;
	
	public MainUserGUI() {
		f = new JFrame("Main Menu");
		lbl = new JLabel("Welcome to the financial organizer!");
		lbl2 = new JLabel("Notice: First-time users are required to enter PID before importing files.");
		pidB = new JButton();
		importB = new JButton();
		exportB = new JButton();
		addBackupRestoreDelete = new JButton();
		form1040Generator = new JButton();
		GUISetup();
	}

	private void GUISetup() {
		
		// GridLayout: 7 Rows and 1 Column
		f.setLayout(new GridLayout(7, 1));
		
		lbl.setHorizontalAlignment(JLabel.CENTER);
		lbl.setVerticalAlignment(JLabel.TOP);
		
		f.add(lbl);
		
		lbl2.setHorizontalAlignment(JLabel.CENTER);
		f.add(lbl2);
		
		buttons();
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(new Dimension(500, 500));
		f.setResizable(true);
		f.setVisible(true);
	}
	
	private void buttons() {
		
		// Button that takes user to enter PID
		pidB.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PIDGUI pidGUI = new PIDGUI();
				f.dispose();
			}
			
		});
		pidB.setText("Enter PID");
		pidB.setPreferredSize(new Dimension(500, 100));
		f.add(pidB);
		
		// Button that takes user to import data GUI
		importB.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileImportGUI fileimportGUI = new FileImportGUI();
				f.dispose();
			}
			
		});
		importB.setText("Import Data");
		importB.setPreferredSize(new Dimension(500, 100));
		f.add(importB);
		
		// Button that takes user to export data GUI
		exportB.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DataExportPreviewGUI dataexportpreviewGUI = new DataExportPreviewGUI();
				f.dispose();
			}
			
		});
		exportB.setText("Export Data");
		exportB.setPreferredSize(new Dimension(500, 100));
		f.add(exportB);
		
		// Button that takes user to add/delete/backup/restore info
		addBackupRestoreDelete.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddBackupRestoreDeleteGUI addBackupRestoreDelete = new AddBackupRestoreDeleteGUI();
				f.dispose();
			}
			
		});
		addBackupRestoreDelete.setText("Add/Backup/Restore/Delete Data");
		addBackupRestoreDelete.setPreferredSize(new Dimension(500, 100));
		f.add(addBackupRestoreDelete);
		

		// Button that exports the 1040 Form as a PDF
		form1040Generator.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Form1040Generator form1040 = new Form1040Generator();
			}
			
		});
		form1040Generator.setText("1040 Form Export");
		form1040Generator.setPreferredSize(new Dimension(500, 100));
		f.add(form1040Generator);		
	}
}