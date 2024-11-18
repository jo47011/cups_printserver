/* $Id: pdfagb2.java,v 1.2 2009/11/19 07:24:02 tobi Exp $ */

/*
 * This file is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */

import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
//import com.lowagie.text.pdf.SequenceList;

/** Move the last page (AGB) between pages one and two. */
public class pdfagb2 {
  public static void main(String[] args) {
    int err= 0;
    try {
      final PdfReader in= new PdfReader(System.in);
      final int numPages= in.getNumberOfPages();
      if(numPages >= 1) {
        final StringBuffer range= new StringBuffer("1");
        if(numPages >= 2) {
          range.append(',').append(numPages);
          if(numPages >= 3) {
            range.append(',').append(2);
            if(numPages >= 4) {
              range.append('-').append(numPages-1);
            }
          }
          final String r= range.toString();
          System.err.println(r);  // debug info
          in.selectPages(r);
        }
        // FIXME: else error: no AGB page
        final PdfStamper out= new PdfStamper(in, System.out);
        out.setFullCompression();

        // NEW: insert blank pages on back side of sheets 2+
        final Rectangle pageSize= in.getPageSizeWithRotation(1);
        for(int p=4; p<=in.getNumberOfPages(); p+=2) {
          out.insertPage(p, pageSize);
        }

	out.close();
      }
      // FIXME: else error: no pages
    }
    catch(Exception ex) {
      ex.printStackTrace();
      err= 1;
    }
    System.exit(err);
  }
}
