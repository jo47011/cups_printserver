#! /bin/bash

# $Id: mkpdf.sh,v 1.1 2011/06/26 14:57:06 jo47011 Exp $

case "$0" in
  /*) self=$0 ;;
   *) self=$(pwd)/$0 ;;
esac

selfdir=$( (cd "${self%/?*}" && pwd) )
[ "$selfdir" ] || exit 1

test ! -z "${PROFILEREAD}" || . /etc/profile </dev/null 1>/dev/null 2>&1

rawfile="${1}" ; shift
exec "${selfdir}/../cups/asctopdf" "$@" >"${rawfile}"
