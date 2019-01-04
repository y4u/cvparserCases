package reader;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class Reader {

	/* public String getdata(Object str) {
		 return str.toString();
	 }
	 public String getdata(String str) {
		 return str.toString();
	 }
	 public void getdata(String str) {
		 return str;
	 }*/
	public static void main(String[] args) throws IOException {
		
		 System.out.println(Math.min(Double.MIN_VALUE,0.0d));
		 
		 final class Constants{
			 public String name="pi";
		 }
		
		 String one="hello";
		 String two="hello";
		 if(one==two) 
			 System.out.println("good");
		 else
			 System.out.println("bad");
		

	/*	try (PDDocument document = PDDocument.load(new File("/home/agile/Documents/sample.pdf"))) {

			document.getClass();

			if (!document.isEncrypted()) {

				PDFTextStripperByArea stripper = new PDFTextStripperByArea();
				stripper.setSortByPosition(true);

				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(document);
				// System.out.println("Text:" + st);

				// split by whitespace
				String lines[] = pdfFileInText.split("\r?\\n");
				for (String line : lines) {
					System.out.println(line);
				}

			}

		}
*/
	}
}