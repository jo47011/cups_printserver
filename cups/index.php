<?php

# $Id: index.php,v 1.8 2011/09/26 18:01:04 zschach Exp $

// set the reporting level
error_reporting(E_ALL);

/** This function prints $msg to the caller/client as well as to the logfile
    defined in php.ini "error_log" directive */
function log_msg($msg) {
  error_log($msg);
  printf($msg."<br />\n");
}

// get details about ourself
$saddr= $_SERVER['SERVER_ADDR'];
$sport= $_SERVER['SERVER_PORT'];
$self= $_SERVER['PHP_SELF'];

// read the uploaded data to a variable
$fp= fopen("php://input", "r");
if (!$fp) { log_msg("ERROR: could not open input stream"); exit(-1); }
$data= stream_get_contents($fp);
fclose($fp);

if(trim(strlen($data)) == 0) {
  // nothing received ... give up and return
  log_msg("ERROR: received 0 bytes! giving up ....");
  exit(-1);
}

$descriptorspec= array(
	0=> array("pipe", "r"), // process STDIN
	1=> array("pipe", "w")  // process STDOUT
);

$opts= "";
foreach ($_GET as $key => $var) { $opts.= " ".$key."=".$var; }

if (strcasecmp(dirname($self), "/axt") == 0) {
  $cmd= "bash -c '/usr/local/cups/asctops blow title 1 \"".trim($opts)."\"'";
} else if(strcasecmp(dirname($self), "/wald") == 0) {
  $cmd= "bash -c '/usr/local/cups/asctopdf blow title 1 \"".trim($opts)."\"'";
} else {
  log_msg("ERROR: unknown conversion filter ".dirname($self));
  exit(-1);
}

$p= proc_open($cmd, $descriptorspec, $pipes);

if (!$p) {
  log_msg("ERROR: failed to open conversion process");
  log_msg("ERROR: command: " . $cmd);
  exit(-1);
}

if (!$pipes[0] || !$pipes[1]) {
  log_msg("ERROR: failed to open pipes");
  proc_close($p);
  exit(-1);
}

// send the header
if (strcasecmp(dirname($self), "/axt") == 0) {
  header('Content-type: application/postscript');
  header('Content-Disposition: attachment; filename="file.ps"');
} else if(strcasecmp(dirname($self), "/wald") == 0) {
  header('Content-type: application/pdf');
  header('Content-Disposition: attachment; filename="file.pdf"');
}

// send the POST data to the script
fwrite($pipes[0], $data);
fclose($pipes[0]);

// send the result
echo stream_get_contents($pipes[1]);
fclose($pipes[1]);
proc_close($p);

exit(0);
?>
