# $Id: Makefile,v 1.13 2011/09/20 05:53:18 gruhn Exp $

# This file is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

FILES = form brief liste test devel

EPS_FILES = $(addsuffix .eps, $(FILES))
PDF_FILES = $(addsuffix .pdf, $(FILES))

%.pdf: %.ps
	ps2pdf -sPAPERSIZE=a4 $^ $@

%.ps: %.fig
	fig2dev -L ps -b 0 $< $@

%.eps: %.fig
	fig2dev -L eps -D -99 $< $@

%.svg: %.fig
	fig2dev -L svg $< $@

all: $(EPS_FILES) $(PDF_FILES)

clean:
	rm -f $(EPS_FILES) $(PDF_FILES) *~ *.bak
	rm -f *.{emf,wmf,odg,svg,ps}

# eof
