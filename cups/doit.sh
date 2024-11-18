# $Id: doit.sh,v 1.22 2011/11/06 11:05:38 ferber Exp $

#file=../test/liste-kurz.asc
#file=../test/FormularAGBs-3Seiten.ASC
#file=../test/RechnungAGBs-5Seiten.ASC
#file=../test/RechnKopie-ohneAGBs-5Seiten.ASC
#file=../test/landscape-liste.ASC
file=../test/liste.ASC
#file=../test/print07.asc

opt="job-originating-host-name=::ffff:192.168.1.60"
opt="${opt} job-uuid=urn:uuid:e23f813c-fde4-373d-7057-9026e3569494"
#opt="${opt} form=brief"
#opt="${opt} form=form"
opt="${opt} form=liste"
#opt="${opt} form=liste landscape=1"
opt="${opt} kopie=1 agb=1"
opt="${opt} watermark=devel"
#opt="${opt} font=Courier5/11.1"

./asctopdf blowjob user title 1 "${opt}" "${file}" > _user.pdf
./asctops  blowjob user title 3 "${opt}" "${file}" > _print.ps

unset opt

ps2pdf _print.ps _print.pdf
