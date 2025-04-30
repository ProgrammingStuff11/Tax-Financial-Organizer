package financeproject;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class PIDGUI {
	
	private JFrame f;
	private JPanel p;
	private JPanel p2;
	private JPanel p3;
	private JButton b1;
	private JButton b2;
	private JLabel tinL;
	private JTextField tinT;
	private JLabel nameL;
	private JTextField firstNameT;
	private JLabel middleIL;
	private JTextField middleITF;
	private JLabel lastL;
	private JTextField lastNameT;
	private JLabel dobL;
	private JTextField dobT;
	private JLabel phoneL;
	private JTextField phoneT;
	private JLabel occupationL;
	private JTextField occupationT;
	private JLabel routingNumberLT;
	private JTextField routingNumberFT;
	private JLabel accountNumberLT;
	private JTextField accountNumberFT;
	private JLabel typeOfAccountLT;
	private JTextField typeOfAccountFT;

	private JCheckBox spouseJCB;
	private JPanel spousePanel;
	private JLabel sTinL;
	private JTextField sTinT;
	private JLabel sNameL;
	private JTextField sFirstNameT;
	private JLabel sMiddleIL;
	private JTextField sMiddleITF;
	private JLabel sLastL;
	private JTextField sLastNameT;
	private JLabel sDobL;
	private JTextField sDobT;
	private JLabel sPhoneL;
	private JTextField sPhoneT;
	private JLabel sOccupationL;
	private JTextField sOccupationT;
	private JPanel southPanel;
	
	public PIDGUI() {
		f = new JFrame("PID");
		p = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		b1 = new JButton();
		b2 = new JButton();
		tinL = new JLabel("TIN");
		tinT = new JTextField(16);
		nameL = new JLabel("First Name");
		firstNameT = new JTextField(16);
		middleIL = new JLabel("Middle Initial");
		middleITF = new JTextField(1);
		lastL = new JLabel("Last Name");
		lastNameT = new JTextField(16);
		dobL = new JLabel("Date of Birth");
		dobT = new JTextField(9);
		phoneL = new JLabel("Phone Number");
		phoneT = new JTextField(11);
		occupationL = new JLabel("Occupation");
		occupationT = new JTextField(20);
		routingNumberLT = new JLabel("Routing Number");
		routingNumberFT = new JTextField(9);
		accountNumberLT = new JLabel("Account Number");
		accountNumberFT = new JTextField(12);
		typeOfAccountLT = new JLabel("Type of Account");
		typeOfAccountFT = new JTextField(1);

		spouseJCB = new JCheckBox("Spouse");
		spousePanel = new JPanel();
		sTinL = new JLabel("Spouse TIN");
		sTinT = new JTextField(16);
		sNameL = new JLabel("Spouse First Name");
		sFirstNameT = new JTextField(16);
		sMiddleIL = new JLabel("Spouse Middle Initial");
		sMiddleITF = new JTextField(1);
		sLastL = new JLabel("Spouse Last Name");
		sLastNameT = new JTextField(16);
		sDobL = new JLabel("Spouse Date of Birth");
		sDobT = new JTextField(9);
		sPhoneL = new JLabel("Spouse Phone Number");
		sPhoneT = new JTextField(11);
		sOccupationL = new JLabel("Spouse Occupation");
		sOccupationT = new JTextField(20);
		southPanel = new JPanel();
		
		PIDGUISetup();
	}

	private void PIDGUISetup() {
		p.setLayout(new GridLayout(14,2));
		
		// Button that takes user back to main GUI
		b1.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainUserGUI mainuserGUI = new MainUserGUI();
				f.dispose();
			}
			
		});
		b1.setText("back to main menu");
		
		// Button that submits, displays a message of data saved, and tell users they can return to the main menu
		b2.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PIDInfo pid = new PIDInfo(Integer.parseInt(tinT.getText()), firstNameT.getText(), middleITF.getText(), lastNameT.getText(), dobT.getText(), Long.parseLong(phoneT.getText()), occupationT.getText(), Long.parseLong(routingNumberFT.getText()), Long.parseLong(accountNumberFT.getText()), typeOfAccountFT.getText().charAt(0),
						Integer.parseInt(sTinT.getText()), sFirstNameT.getText(), sMiddleITF.getText(), sLastNameT.getText(), sDobT.getText(), Long.parseLong(sPhoneT.getText()), sOccupationT.getText());
				pid.pidInfo();
			}
			
		});
		b2.setText("Submit");
		
		spousePanel.setLayout(new GridLayout(8,2));
		spousePanel.setVisible(false);
		spouseJCB.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isSelected = spouseJCB.isSelected();
				spousePanel.setVisible(isSelected);
				if(isSelected) f.setSize(new Dimension(500, 500));
				else f.setSize(new Dimension(500, 400));
				f.repaint();
				f.revalidate();
			}
		});
		spouseJCB.setText("Spouse");
		
		p2.add(b1);
		p.add(tinL);
		p.add(tinT);
		p.add(nameL);
		p.add(firstNameT);
		p.add(middleIL);
		p.add(middleITF);
		p.add(lastL);
		p.add(lastNameT);
		p.add(dobL);
		p.add(dobT);
		p.add(phoneL);
		p.add(phoneT);
		p.add(occupationL);
		p.add(occupationT);
		p.add(routingNumberLT);
		p.add(routingNumberFT);
		p.add(accountNumberLT);
		p.add(accountNumberFT);
		p.add(typeOfAccountLT);
		p.add(typeOfAccountFT);

		p.add(spouseJCB);
		
		spousePanel.add(sTinL);
		spousePanel.add(sTinT);
		spousePanel.add(sNameL);
		spousePanel.add(sFirstNameT);
		spousePanel.add(sMiddleIL);
		spousePanel.add(sMiddleITF);
		spousePanel.add(sLastL);
		spousePanel.add(sLastNameT);
		spousePanel.add(sDobL);
		spousePanel.add(sDobT);
		spousePanel.add(sPhoneL);
		spousePanel.add(sPhoneT);
		spousePanel.add(sOccupationL);
		spousePanel.add(sOccupationT);
		
		southPanel.setLayout(new BorderLayout());
		southPanel.add(spousePanel, "North");
		
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.add(b2);
		
		southPanel.add(p3, "South");
		
		f.add(p2, "North");
		f.add(p);
		f.add(southPanel, "South");
		
		f.setSize(new Dimension(500, 500));
		f.pack();
		f.setResizable(true);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class PIDInfo {
	
	private int tin;
	private String firstName;
	private String mInitial;
	private String lastName;
	private String dob;
	private long phone;
	private String occupation;
	
	private int sTin;
	private String sFirstName;
	private String sMInitial;
	private String sLastName;
	private String sDob;
	private long sPhone;
	private String sOccupation;
	private long routingNumber;
	private long accountNumber;
	private char typeOfAccount;
	
	protected PIDInfo(int tin, String firstName, String mInitial, String lastName, String dob, long phone, String occupation, long routingNumber, long accountNumber, char typeOfAccount, int sTin, String sFirstName, String sMInitial, String sLastName, String sDob, long sPhone, String sOccupation) {
		this.tin = tin;
		this.firstName = firstName;
		this.mInitial = mInitial;
		this.lastName = lastName;
		this.dob = dob;
		this.phone = phone;
		this.occupation = occupation;
		this.routingNumber = routingNumber;
		this.accountNumber = accountNumber;
		this.typeOfAccount = typeOfAccount;
		
		this.sTin = sTin;
		this.sFirstName = sFirstName;
		this.sMInitial = sMInitial;
		this.sLastName = sLastName;
		this.sDob = sDob;
		this.sPhone = sPhone;
		this.sOccupation = sOccupation;
	}

	protected void pidInfo() {
		DatabaseConnection databaseconnection = new DatabaseConnection();
		databaseconnection.pidToDatabase(tin, firstName, mInitial, lastName, dob, phone, occupation, routingNumber, accountNumber, typeOfAccount);
		databaseconnection.pidToDatabase(sTin, tin, sFirstName, sMInitial, sLastName, sDob, sPhone, sOccupation);

		System.out.println(tin + " " + firstName + " " + mInitial + " " + lastName + " " + dob + " " + phone + " " + occupation);
	}
}
