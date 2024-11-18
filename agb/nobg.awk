#! /usr/bin/gawk -f

# $Id: nobg.awk,v 1.1 2011/08/23 10:47:03 ferber Exp $

# Remove the white background from the generated agb.ps file.

BEGIN { white=0; }

$4 == "setrgbcolor" {
  white= (int($1)==1 && int($2)==1 && int($3)==1) ? 1 : 0;
}

white == 1 && $1 == "fill" { next; }

{ print; }
