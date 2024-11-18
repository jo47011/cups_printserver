# $Id: Makefile,v 1.5 2011/08/23 10:47:15 ferber Exp $

# This file is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

fname = agb

%.ps: %.html
	html2ps -f ./html2psrc -o $@ $^
	psselect -q -p1 $@ ${fname}.$$$$ && mv ${fname}.$$$$ $@
	gawk -f nobg.awk <$@ >${fname}.$$$$ && mv ${fname}.$$$$ $@

%.pdf: %.ps
	ps2pdf -sPAPERSIZE=a4 $^ $@

.PHONY: all clean

all: $(fname).ps $(fname).pdf

clean:
	$(RM) $(fname).{ps,pdf} *~

# eof
