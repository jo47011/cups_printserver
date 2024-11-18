# $Id: Makefile,v 1.3 2011/06/26 14:55:24 gruhn Exp $

# This file is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

subdirs = form agb pdfagb2 cups run

install_prefix=/cygdrive/c/MyProg/MIKI

.PHONY: all clean

all:
	for dir in $(subdirs) ; do \
	  ( cd $$dir && $(MAKE) $(MFLAGS) ) || exit 1 ; done

clean:
	for dir in $(subdirs) ; do \
	  ( cd $$dir && $(MAKE) $(MFLAGS) clean ) || exit 1 ; done

install:
	install -d $(install_prefix)/print
	cp -a $(subdirs) $(install_prefix)/print
	-$(RM) -rf $(install_prefix)/print/*/CVS
	-$(RM) -f $(install_prefix)/print/*/*akefile

# eof
