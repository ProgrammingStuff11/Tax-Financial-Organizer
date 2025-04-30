package financeproject;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class Form1040Generator {
	private JButton saveB;
	private JFrame frame;
	private JPanel tinPanel;
	private JLabel tinL;
	private JTextField tinF;
	private JLabel salariesL;
	private JTextField salariesF;
	private JLabel adjustmentsToIncomeL;
	private JTextField adjustmentsToIncomeF;
	private JLabel taxL;
	private JTextField taxF;
	private JLabel childTaxCreditL;
	private JTextField childTaxCreditF;
	private JLabel federalIncomeTaxWithheldInW2FromSalariesL;
	private JTextField federalIncomeTaxWithheldInW2FromSalariesF;
	private JLabel additionalTaxPaidL;
	private JTextField additionalTaxPaidF;

	private JFrame mainFrame;
	private JLabel tinLabel;
	private JLabel infoLabel;
	private JButton tinButton;
	private JButton Form1040InfoButton;

	private JFrame tinFrame;
	private JButton tinSaveButton;
	private JLabel tinL2;
	private JTextField tinF2;
	private JPanel tinPanel2;

	private JFileChooser fileChooser;
	private int inputResult;
	private File inputFile;
	private int outputResult;
	private File outputFile;
	private PdfDocument pdfDoc;
	private PdfAcroForm form;
	private PdfFont boldFont;

	private DatabaseConnection db;

	private String firstNameMiddleInitialField;
	private String lastNameField;
	private String firstNameMiddleInitial;
	private String lastName;
	private String tinField;
	private String tinFieldS;
	private String tinS;

	private String firstNameMiddleInitialFieldS;
	private String lastNameFieldS;
	private String salaries;
	private String salariesField;
	private String firstNameMiddleInitialS;
	private String lastNameS;
	private String adjustmentsToIncomeField;
	private String adjustmentsToIncome;
	private String taxField;
	private String tax;
	private String childTaxCreditField;
	private String childTaxCredit;
	private String federalIncomeTaxWithheldInW2FromSalariesField;
	private String federalIncomeTaxWithheldInW2FromSalaries;
	private String additionalTaxPaidField;
	private String additionalTaxPaid;
	private String line7Field;
	private String line7;
	private String line9Field;
	private String line9;
	private String line11Field;
	private String line11;
	private String standardDeduction;
	private String standardDeductionField;
	private String line14Field;
	private String line14;
	private String line15Field;
	private String line15;
	private String line18Field;
	private String line18;
	private String line2bField;
	private String line2b;
	private String line3aField;
	private String line3a;
	private String line3bField;
	private String line3b;
	private String line33Field;
	private String line33;
	private String mPhoneField;
	private String mPhone;
	private String occupationField;
	private String occupation;
	private String routingNumberField;
	private String routingNumber;
	private String accountNumberField;
	private String accountNumber;
	private String typeOfAccountField;
	private String typeOfAccount;

	public Form1040Generator() {

		saveB = new JButton();
		frame = new JFrame("1040 Form Info");
		tinPanel = new JPanel();
		tinPanel2 = new JPanel();
		tinL = new JLabel("Tin: ");
		tinF = new JTextField(10);
		salariesL = new JLabel("Salaries: ");
		salariesF = new JTextField(10);
		adjustmentsToIncomeL = new JLabel("Adjustments to Income: ");
		adjustmentsToIncomeF = new JTextField(10);
		taxL = new JLabel("Tax: ");
		taxF = new JTextField(10);
		childTaxCreditL = new JLabel("Child Tax Credit: ");
		childTaxCreditF = new JTextField(10);
		federalIncomeTaxWithheldInW2FromSalariesL = new JLabel("Federal Income Tax Withheld In W2 From Salaries: ");
		federalIncomeTaxWithheldInW2FromSalariesF = new JTextField(10);
		additionalTaxPaidL = new JLabel("Additional Tax Paid: ");
		additionalTaxPaidF = new JTextField(10);

		mainFrame = new JFrame("Form 1040 Menu");
		tinButton = new JButton();
		Form1040InfoButton = new JButton();
		tinLabel = new JLabel("Enter tin:");
		infoLabel = new JLabel("Enter info: (first time only)");

		tinFrame = new JFrame("Form 1040 Tin");
		tinSaveButton = new JButton();
		tinL2 = new JLabel("Tin: ");
		tinF2 = new JTextField(9);


		db = new DatabaseConnection();
		firstNameMiddleInitialField = "topmostSubform[0].Page1[0].f1_04[0]";
		lastNameField = "topmostSubform[0].Page1[0].f1_05[0]";
		tinField = "topmostSubform[0].Page1[0].f1_06[0]";

		firstNameMiddleInitialFieldS = "topmostSubform[0].Page1[0].f1_07[0]";
		lastNameFieldS = "topmostSubform[0].Page1[0].f1_08[0]";
		tinFieldS = "topmostSubform[0].Page1[0].f1_09[0]";

		salariesField = "topmostSubform[0].Page1[0].f1_32[0]";
		adjustmentsToIncomeField = "topmostSubform[0].Page1[0].Line4a-11_ReadOrder[0].f1_55[0]";
		taxField = "topmostSubform[0].Page2[0].f2_02[0]";
		childTaxCreditField = "topmostSubform[0].Page2[0].f2_05[0]";
		federalIncomeTaxWithheldInW2FromSalariesField = "topmostSubform[0].Page2[0].f2_11[0]";
		additionalTaxPaidField = "topmostSubform[0].Page2[0].f2_20[0]";
		line7Field = "topmostSubform[0].Page1[0].Line4a-11_ReadOrder[0].f1_52[0]";
		line9Field = "topmostSubform[0].Page1[0].Line4a-11_ReadOrder[0].f1_54[0]";
		line11Field = "topmostSubform[0].Page1[0].Line4a-11_ReadOrder[0].f1_56[0]";
		standardDeductionField = "topmostSubform[0].Page1[0].f1_57[0]";
		line14Field = "topmostSubform[0].Page1[0].f1_59[0]";
		line15Field = "topmostSubform[0].Page1[0].f1_60[0]";
		line18Field = "topmostSubform[0].Page2[0].f2_04[0]";
		line2bField = "topmostSubform[0].Page1[0].f1_43[0]";
		line3aField = "topmostSubform[0].Page1[0].f1_44[0]";
		line3bField = "topmostSubform[0].Page1[0].f1_45[0]";
		line33Field = "topmostSubform[0].Page2[0].f2_22[0]";
		mPhoneField = "topmostSubform[0].Page2[0].f2_37[0]";
		occupationField = "topmostSubform[0].Page2[0].f2_33[0]";
		routingNumberField = "topmostSubform[0].Page2[0].RoutingNo[0].f2_25[0]";
		accountNumberField = "topmostSubform[0].Page2[0].AccountNo[0].f2_26[0]";
		standardDeduction = "29200";

		fileChooser = new JFileChooser();

		mainFrame.setLayout(new GridLayout(2, 1));

		tinButton.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tinFrame.setVisible(true);
			}
		});
		tinButton.setText("Enter Tin");

		Form1040InfoButton.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
			}
		});
		Form1040InfoButton.setText("Enter Info");

		mainFrame.add(tinLabel);
		mainFrame.add(tinButton);
		mainFrame.add(infoLabel);
		mainFrame.add(Form1040InfoButton);
		mainFrame.pack();
		mainFrame.setSize(500, 500);
		mainFrame.setResizable(true);
		mainFrame.setVisible(true);

		tinFrame.setLayout(new GridLayout(2, 1));
		tinPanel2.setLayout(new GridLayout(1, 2));
		tinPanel2.add(tinL);
		tinPanel2.add(tinF);
		tinFrame.add(tinPanel2);
		tinFrame.add(tinSaveButton);
		tinFrame.pack();
		tinFrame.setResizable(true);

		frame.setLayout(new GridLayout(2, 1));
		tinPanel.setLayout(new GridLayout(7, 2));
		tinPanel.add(tinL2);
		tinPanel.add(tinF2);
		tinPanel.add(salariesL);
		tinPanel.add(salariesF);
		tinPanel.add(adjustmentsToIncomeL);
		tinPanel.add(adjustmentsToIncomeF);
		tinPanel.add(taxL);
		tinPanel.add(taxF);
		tinPanel.add(childTaxCreditL);
		tinPanel.add(childTaxCreditF);
		tinPanel.add(federalIncomeTaxWithheldInW2FromSalariesL);
		tinPanel.add(federalIncomeTaxWithheldInW2FromSalariesF);
		tinPanel.add(additionalTaxPaidL);
		tinPanel.add(additionalTaxPaidF);

		frame.add(tinPanel);
		frame.add(saveB);

		frame.pack();
		frame.setResizable(false);

		saveB.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				db.Form1040ToDatabase(Integer.parseInt(tinF2.getText()), Double.parseDouble(salariesF.getText()), Double.parseDouble(adjustmentsToIncomeF.getText()), Double.parseDouble(taxF.getText()), Double.parseDouble(childTaxCreditF.getText()), Double.parseDouble(federalIncomeTaxWithheldInW2FromSalariesF.getText()), Double.parseDouble(additionalTaxPaidF.getText()));
				frame.dispose();
			}
		});
		saveB.setText("Save");

		tinSaveButton.setAction(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tinFrame.dispose();

				try {

					// Input PDF chooser
					fileChooser.setDialogTitle("Input 1040 Form");
					inputResult = fileChooser.showOpenDialog(null);
					inputFile = null;
					if (inputResult == JFileChooser.APPROVE_OPTION) {
						inputFile = fileChooser.getSelectedFile();
					}

					// Output PDF chooser
					fileChooser.setDialogTitle("Save Filled 1040 Form");
					outputResult = fileChooser.showSaveDialog(null);
					outputFile = null;
					if (outputResult == JFileChooser.APPROVE_OPTION) {
						outputFile = fileChooser.getSelectedFile();
					}

					pdfDoc = new PdfDocument(new PdfReader(inputFile.getAbsolutePath()), new PdfWriter(outputFile.getAbsolutePath()));
					form = PdfAcroForm.getAcroForm(pdfDoc, true);

					ArrayList<String> info = db.InfoFor1040Form(Integer.parseInt(tinF.getText()));
					firstNameMiddleInitial = info.get(0) + " " + info.get(1) + ".";
					lastName = info.get(2);

					firstNameMiddleInitialS = info.get(3) + " " + info.get(4) + ".";
					lastNameS = info.get(5);
					tinS = info.get(6);
					salaries = info.get(7);
					adjustmentsToIncome = info.get(8);
					tax = info.get(9);
					childTaxCredit = info.get(10);
					federalIncomeTaxWithheldInW2FromSalaries = info.get(11);
					additionalTaxPaid = info.get(12);
					line7 = String.format("%.2f", (Double.parseDouble(info.get(13))));
					line9 = info.get(14);
					line11 = Double.toString(Double.parseDouble(line9) - Double.parseDouble(adjustmentsToIncome));
					line14 = standardDeduction;
					line15 = Double.toString(Double.parseDouble(line11) - Double.parseDouble(line14));
					line18 = tax;
					line2b = info.get(14);
					line3a = info.get(15);
					line3b = info.get(16);
					line33 = Double.toString(Double.parseDouble(federalIncomeTaxWithheldInW2FromSalaries) + Double.parseDouble(additionalTaxPaid));
					mPhone = info.get(17);
					occupation = info.get(18);
					routingNumber = info.get(19);
					accountNumber = info.get(20);
					typeOfAccount = info.get(21);
					if(typeOfAccount.equals("c")) {
						typeOfAccountField = "topmostSubform[0].Page2[0].c2_5[0]";
					}
					else if(typeOfAccount.equals("s")) {
						typeOfAccountField = "topmostSubform[0].Page2[0].c2_5[1]";
					}


					// Makes the Font bold
					boldFont = PdfFontFactory.createFont("Helvetica-Bold");

					// Sets Color, Font, and Value to the Fields
					form.getField(firstNameMiddleInitialField).setColor(ColorConstants.BLACK);
					form.getField(firstNameMiddleInitialField).setFont(boldFont);
					form.getField(firstNameMiddleInitialField).setValue(firstNameMiddleInitial);

					form.getField(lastNameField).setColor(ColorConstants.BLACK);
					form.getField(lastNameField).setFont(boldFont);
					form.getField(lastNameField).setValue(lastName);

					form.getField(tinField).setColor(ColorConstants.BLACK);
					form.getField(tinField).setFont(boldFont);
					form.getField(tinField).setValue(tinF.getText());

					form.getField(firstNameMiddleInitialFieldS).setColor(ColorConstants.BLACK);
					form.getField(firstNameMiddleInitialFieldS).setFont(boldFont);
					form.getField(firstNameMiddleInitialFieldS).setValue(firstNameMiddleInitialS);

					form.getField(lastNameFieldS).setColor(ColorConstants.BLACK);
					form.getField(lastNameFieldS).setFont(boldFont);
					form.getField(lastNameFieldS).setValue(lastNameS);

					form.getField(tinFieldS).setColor(ColorConstants.BLACK);
					form.getField(tinFieldS).setFont(boldFont);
					form.getField(tinFieldS).setValue(tinS);

					form.getField(salariesField).setColor(ColorConstants.BLACK);
					form.getField(salariesField).setFont(boldFont);
					form.getField(salariesField).setValue(salaries);

					form.getField(adjustmentsToIncomeField).setColor(ColorConstants.BLACK);
					form.getField(adjustmentsToIncomeField).setFont(boldFont);
					form.getField(adjustmentsToIncomeField).setValue(adjustmentsToIncome);

					form.getField(taxField).setColor(ColorConstants.BLACK);
					form.getField(taxField).setFont(boldFont);
					form.getField(taxField).setValue(tax);

					form.getField(childTaxCreditField).setColor(ColorConstants.BLACK);
					form.getField(childTaxCreditField).setFont(boldFont);
					form.getField(childTaxCreditField).setValue(childTaxCredit);

					form.getField(federalIncomeTaxWithheldInW2FromSalariesField).setColor(ColorConstants.BLACK);
					form.getField(federalIncomeTaxWithheldInW2FromSalariesField).setFont(boldFont);
					form.getField(federalIncomeTaxWithheldInW2FromSalariesField).setValue(federalIncomeTaxWithheldInW2FromSalaries);

					form.getField(additionalTaxPaidField).setColor(ColorConstants.BLACK);
					form.getField(additionalTaxPaidField).setFont(boldFont);
					form.getField(additionalTaxPaidField).setValue(additionalTaxPaid);

					form.getField(line7Field).setColor(ColorConstants.BLACK);
					form.getField(line7Field).setFont(boldFont);
					form.getField(line7Field).setValue(line7);

					form.getField(line9Field).setColor(ColorConstants.BLACK);
					form.getField(line9Field).setFont(boldFont);
					form.getField(line9Field).setValue(line9);

					form.getField(line11Field).setColor(ColorConstants.BLACK);
					form.getField(line11Field).setFont(boldFont);
					form.getField(line11Field).setValue(line11);

					form.getField(standardDeductionField).setColor(ColorConstants.BLACK);
					form.getField(standardDeductionField).setFont(boldFont);
					form.getField(standardDeductionField).setValue(standardDeduction);

					form.getField(line14Field).setColor(ColorConstants.BLACK);
					form.getField(line14Field).setFont(boldFont);
					form.getField(line14Field).setValue(line14);

					form.getField(line15Field).setColor(ColorConstants.BLACK);
					form.getField(line15Field).setFont(boldFont);
					form.getField(line15Field).setValue(line15);

					form.getField(line18Field).setColor(ColorConstants.BLACK);
					form.getField(line18Field).setFont(boldFont);
					form.getField(line18Field).setValue(line18);

					form.getField(line2bField).setColor(ColorConstants.BLACK);
					form.getField(line2bField).setFont(boldFont);
					form.getField(line2bField).setValue(line2b);

					form.getField(line3aField).setColor(ColorConstants.BLACK);
					form.getField(line3aField).setFont(boldFont);
					form.getField(line3aField).setValue(line3a);

					form.getField(line3bField).setColor(ColorConstants.BLACK);
					form.getField(line3bField).setFont(boldFont);
					form.getField(line3bField).setValue(line3b);

					form.getField(line33Field).setColor(ColorConstants.BLACK);
					form.getField(line33Field).setFont(boldFont);
					form.getField(line33Field).setValue(line33);

					form.getField(mPhoneField).setColor(ColorConstants.BLACK);
					form.getField(mPhoneField).setFont(boldFont);
					form.getField(mPhoneField).setValue(mPhone);

					form.getField(occupationField).setColor(ColorConstants.BLACK);
					form.getField(occupationField).setFont(boldFont);
					form.getField(occupationField).setValue(occupation);

					form.getField(routingNumberField).setColor(ColorConstants.BLACK);
					form.getField(routingNumberField).setFont(boldFont);
					form.getField(routingNumberField).setValue(routingNumber);

					form.getField(accountNumberField).setColor(ColorConstants.BLACK);
					form.getField(accountNumberField).setFont(boldFont);
					form.getField(accountNumberField).setValue(accountNumber);

					form.getField(typeOfAccountField).setColor(ColorConstants.BLACK);
					form.getField(typeOfAccountField).setFont(boldFont);
					form.getField(typeOfAccountField).setValue("Yes");

					// Removes blue box around fields to make the 1040 form look nicer
					form.flattenFields();

					pdfDoc.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		tinSaveButton.setText("Save");

	}
}
