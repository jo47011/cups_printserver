# $Id: Makefile,v 1.3 2011/08/22 20:37:10 ferber Exp $

# This file is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

JAVAC = javac -g -O -source 1.2 -target 1.2 -encoding ASCII

CLASSES = pdfagb2.class pdfmark.class

%.class: %.java
	$(JAVAC) -classpath "iText-2.1.5.jar:$(CLASSPATH)" $^

.PHONY: all

all: $(CLASSES)

.PHONY: clean

clean:
	$(RM) *~ *.o java.prof core
	$(RM) $(CLASSES)
