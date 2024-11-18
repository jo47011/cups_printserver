@echo off
bash.exe ./print/run/mkps.sh %1 %2 %3 %4 %5 %6 %7
lpr -S %8 -P %9 -o l %1

