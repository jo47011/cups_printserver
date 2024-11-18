/* $Id: pdfmark.java,v 1.5 2011/08/23 10:36:00 ferber Exp $ */

/*
 * This file is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfImportedPage;

/** Put the first page of a "watermark" PDF file into the background of
    each page in a PDF document.
    Losely based on an example from pdfhacks.com. */

public class pdfmark {
  public static void main(String[] args) {
    if(args.length != 1) {
      System.err.println("Usage: pdfmark WATERMARK <INFILE >OUTFILE");
      System.exit(1);
    }
    else {
      try {
        final PdfReader in= new PdfReader(System.in);
        final int numPages= in.getNumberOfPages();

        final PdfReader bg= new PdfReader(args[0]);
        final Rectangle bgPageSize= bg.getPageSizeWithRotation(1);
        final float bgWidth= bgPageSize.getWidth();
        final float bgHeight= bgPageSize.getHeight();

	in.removeUnusedObjects();
	bg.removeUnusedObjects();

        final PdfStamper out= new PdfStamper(in, System.out);
        out.setFullCompression();

        // PdfTemplate from the first page of our watermark
        final PdfImportedPage bgPage= out.getImportedPage(bg, 1);

        for(int page= 1; page <= numPages; page++) {
          final Rectangle pageSize= in.getPageSizeWithRotation(page);

          final float pageWidth= pageSize.getWidth();
          final float pageHeight= pageSize.getHeight();

          final float hScale=  pageWidth  / bgWidth;
          final float vScale= pageHeight / bgHeight;
          final float scaleFactor= (hScale < vScale) ? hScale : vScale;

          out.getUnderContent(page).addTemplate
	  //out.getOverContent(page).addTemplate
            (bgPage,
	     scaleFactor, 0,
	     0, scaleFactor,
             (float)((pageWidth - bgWidth*scaleFactor) / 2.0), // htrans
             (float)((pageHeight - bgHeight*scaleFactor) / 2.0)); // vtrans

        }
        out.close();
      }
      catch(Exception ex) {
        ex.printStackTrace();
        System.exit(1);
      }
    }
  }
}
