# cups_printserver

A simple cups print server printing postscript and pdf.

To install later updates on an existing VMWare Image <ip>:

```bash
git clone git@github.com:jo47011/cups_printserver.git
rsync -av cups_printserver/ user@192.168.2.42:/usr/local/cups_printserver
ssh user@<ip>
cd /usr/local/cups_printserver && make clean && make
chmod 777 cups
```

-------------------------------------------------

To change the IP address of the image:

Wenn Ihr eine feste IP vergeben wollt, dann mußt Du das (als root) in /etc/network/interfaces eintragen.  Aus:

    auto eth0
    iface eth0 inet dhcp


wird dann

    auto eth0
    iface eth0 inet static
    address <IP>
    gateway <GW>
    netmask <NETMASK>


  
--------------------------------------------------

Kommando-Zeile mit curl:

curl -s --data-binary "@ASC-FILE" "http://192.168.2.42/axt/?agb=1&kopie=1&… " > PS-FILE