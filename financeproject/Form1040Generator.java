package financeproject;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.IOException;

public class Form1040Generator {

	public Form1040Generator() {
		try {
			PdfDocument pdfDoc = new PdfDocument(new PdfReader("C:\\Users\\student\\Downloads\\f1040_unlocked.pdf"),
					new PdfWriter("C:\\Users\\student\\Downloads\\f1040_filled.pdf"));
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

			String firstNameMiddleInitialField = "topmostSubform[0].Page1[0].f1_04[0]";
			String lastNameField = "topmostSubform[0].Page1[0].f1_05[0]";

			String firstNameMiddleInitial = "John E";
			String lastName = "Doe";

			// Makes the Font bold
			PdfFont boldFont = PdfFontFactory.createFont("Helvetica-Bold");

			// Sets Color, Font, and Value to the Fields
			form.getField(firstNameMiddleInitialField).setColor(ColorConstants.BLACK);
			form.getField(firstNameMiddleInitialField).setFont(boldFont);
			form.getField(firstNameMiddleInitialField).setValue(firstNameMiddleInitial);

			form.getField(lastNameField).setColor(ColorConstants.BLACK);
			form.getField(lastNameField).setFont(boldFont);
			form.getField(lastNameField).setValue(lastName);

			// Removes blue box around fields to make the 1040 form look nicer
			form.flattenFields();

			pdfDoc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
