/**
 * 
 */
package es.uned.lsi.pfg.service.common;

import java.io.ByteArrayOutputStream;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import es.uned.lsi.pfg.model.Payment;
import es.uned.lsi.pfg.model.Student;

/**
 * Implementacion de servicio PDF
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class PDFServiceImpl implements PDFService {

	private static final Logger logger = LoggerFactory.getLogger(PDFServiceImpl.class);
	
	private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLDITALIC);
	private static final Font TEXT_FONT = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
	private static final Font BOLD_FONT = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public byte[] generateReceipt(Payment payment, Student student) throws Exception {
		logger.debug("generateReceipt: " + payment);
		ByteArrayOutputStream pdf = new ByteArrayOutputStream();
		Locale locale = LocaleContextHolder.getLocale();
		Document doc = new Document();
		String docTitle = messageSource.getMessage("payments.receipt", null, locale) + "_" + payment.getId();
		
		initDocument(doc, pdf);
		getMetadata(doc, docTitle);
		
		//Titulo
		Paragraph title = new Paragraph(messageSource.getMessage("app.name", null, locale),TITLE_FONT);
		doc.add(title);
		doc.add(Chunk.NEWLINE);
		doc.add(Chunk.NEWLINE);
		doc.add(Chunk.NEWLINE);
		//Texto
		Paragraph text = new Paragraph(messageSource.getMessage("payments.receipt", null, locale) + ":",BOLD_FONT);
		doc.add(text);
		doc.add(Chunk.NEWLINE);
		//Tabla
		PdfPTable table = new PdfPTable(2);
		//Numero de recibo
		Phrase receiptNumberTitle = new Phrase(messageSource.getMessage("payments.receiptNumber", null, locale), TEXT_FONT);
		Phrase receiptNumberText = new Phrase(Integer.toString(payment.getId()), TEXT_FONT);
		table.addCell(receiptNumberTitle);
		table.addCell(receiptNumberText);
		//Alumno
		Phrase studentTitle = new Phrase(messageSource.getMessage("payments.student", null, locale), TEXT_FONT);
		Phrase studentText = new Phrase(student.getFullName(), TEXT_FONT);
		table.addCell(studentTitle);
		table.addCell(studentText);
		//Mes
		DateFormatSymbols dfs = new DateFormatSymbols(locale);
		Phrase monthTitle = new Phrase(messageSource.getMessage("payments.month", null, locale), TEXT_FONT);
		Phrase monthText = new Phrase(dfs.getMonths()[payment.getMonth()].toUpperCase(), TEXT_FONT);
		table.addCell(monthTitle);
		table.addCell(monthText);
		//Cantidad
		Phrase amountTitle = new Phrase(messageSource.getMessage("payments.amount", null, locale), TEXT_FONT);
		Phrase amountText = new Phrase(payment.getAmount() + " " + messageSource.getMessage("payments.unit", null, locale), TEXT_FONT);
		table.addCell(amountTitle);
		table.addCell(amountText);
		//Estado
		Phrase stateTitle = new Phrase(messageSource.getMessage("payments.state", null, locale), TEXT_FONT);
		Phrase stateText = new Phrase(messageSource.getMessage("payments.paid", null, locale), TEXT_FONT);
		table.addCell(stateTitle);
		table.addCell(stateText);
		//Estado
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Phrase dateTitle = new Phrase(messageSource.getMessage("payments.paymentDate", null, locale), TEXT_FONT);
		Phrase dateText = new Phrase(format.format(payment.getInsertDate()), TEXT_FONT);
		table.addCell(dateTitle);
		table.addCell(dateText);

		doc.add(table);
		
		doc.close();
		
		return pdf.toByteArray();
	}
	
	
	private void initDocument(Document doc, ByteArrayOutputStream pdf) throws DocumentException {
		PdfWriter.getInstance(doc,pdf).setInitialLeading(20);
		doc.open();
	}

	private void getMetadata(Document doc, String title) {
		doc.addTitle(title);
	}
	

}
