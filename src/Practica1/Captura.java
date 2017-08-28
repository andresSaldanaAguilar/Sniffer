package Practica1;

import java.util.ArrayList;//hola 
import java.util.Date;//holis:P
import java.util.List;
import java.io.*;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapIf;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.PcapPacketHandler;
import org.jnetpcap.PcapBpfProgram;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.tcpip.*;
import org.jnetpcap.protocol.network.*;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.protocol.network.Arp;
import org.jnetpcap.protocol.lan.IEEE802dot2;
import org.jnetpcap.protocol.lan.IEEE802dot3;


public class Captura{

	/**
	 * Main startup method
	 *
	 * @param args
	 *          ignored
	 */
   private static String asString(final byte[] mac) {
    final StringBuilder buf = new StringBuilder();
    for (byte b : mac) {
      if (buf.length() != 0) {
        buf.append(':');
      }
      if (b >= 0 && b < 16) {
        buf.append('0');
      }
      buf.append(Integer.toHexString((b < 0) ? b + 256 : b).toUpperCase());
    }

    return buf.toString();
  }

	public static void main(String[] args) {
		List<PcapIf> alldevs = new ArrayList<PcapIf>(); // Will be filled with NICs
		StringBuilder errbuf = new StringBuilder(); // For any error msgs

		/***************************************************************************
		 * First get a list of devices on this system
		 **************************************************************************/
		int r = Pcap.findAllDevs(alldevs, errbuf);
		if (r == Pcap.NOT_OK || alldevs.isEmpty()) {
			System.err.printf("Can't read list of devices, error is %s", errbuf
			    .toString());
			return;
		}

		System.out.println("Network devices found:");

		int i = 0;
                try{
		for (PcapIf device : alldevs) {
			String description =
			    (device.getDescription() != null) ? device.getDescription()
			        : "No description available";
                        final byte[] mac = device.getHardwareAddress();
			String dir_mac = (mac==null)?"No tiene direccion MAC":asString(mac);
                        System.out.printf("#%d: %s [%s] MAC:[%s]\n", i++, device.getName(), description, dir_mac);

		}//for

		PcapIf device = alldevs.get(2); // We know we have atleast 1 device
		System.out
		    .printf("\nChoosing '%s' on your behalf:\n",
		        (device.getDescription() != null) ? device.getDescription()
		            : device.getName());

		/***************************************************************************
		 * Second we open up the selected device
		 **************************************************************************/
                /*"snaplen" is short for 'snapshot length', as it refers to the amount of actual data captured from each packet passing through the specified network interface.
                64*1024 = 65536 bytes; campo len en Ethernet(16 bits) tam mÃ¡x de trama */

		int snaplen = 64 * 1024;           // Capture all packets, no trucation
		int flags = Pcap.MODE_PROMISCUOUS; // capture all packets
		int timeout = 10 * 1000;           // 10 seconds in millis
                Pcap pcap =
		    Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);

		if (pcap == null) {
			System.err.printf("Error while opening device for capture: "
			    + errbuf.toString());
			return;
		}//if

                       /********F I L T R O********/
            PcapBpfProgram filter = new PcapBpfProgram();
            String expression =""; // "port 80";
            int optimize = 0; // 1 means true, 0 means false
            int netmask = 0;
            int r2 = pcap.compile(filter, expression, optimize, netmask);
            if (r2 != Pcap.OK) {
                System.out.println("Filter error: " + pcap.getErr());
            }//if
            pcap.setFilter(filter);
                /****************/


		/***************************************************************************
		 * Third we create a packet handler which will receive packets from the
		 * libpcap loop.
		 **********************************************************************/
		PcapPacketHandler<String> jpacketHandler = new PcapPacketHandler<String>() {

			public void nextPacket(PcapPacket packet, String user) {

				System.out.printf("Received packet at %s caplen=%-4d len=%-4d %s\n",
				    new Date(packet.getCaptureHeader().timestampInMillis()),
				    packet.getCaptureHeader().caplen(),  // Length actually captured
				    packet.getCaptureHeader().wirelen(), // Original length
				    user                                 // User supplied object
				    );//
                                /******Desencapsulado********/
                                for(int i=0;i<packet.size();i++){
                                System.out.printf("%02X ",packet.getUByte(i));
                                if(i%16==15)
                                    System.out.println("");
                                }
                                
                                System.out.println("\n\nEncabezado: \n"+ packet.toHexdump());
                                
                                /*********** Verificando checksum de TCP ************/
                                
                                
                                /* aqui capturo desde el byte 14 al 34, ya que son los 20
                                    bytes de longitud de una trama TCP*/
<<<<<<< HEAD
                                byte[] paquete1=new byte[20],paquete2=new byte[20];
                                int j=0,k=0;

                                
                                /******* Excluyendo los bytes del checksum, para saber si
                                 esta bien su valor ************/
                                for(int i=14;i<34;i++){
                                    paquete1[j]=(byte)packet.getUByte(i);
=======
                                byte[] paquete=new byte[50];
                                int j=0;
                                int tipo;
                                tipo=packet.getUByte(12)<<8 | packet.getUByte(13);
                                    if(tipo==0x86DD){
                                 System.out.println(" Es tipo IPV6");
                                    }
                                    if(tipo==0x0800){
                                 System.out.println(" Es tipo IPV4");
                                  for(int i=14;i<34;i++){
                                    paquete[j]=(byte)packet.getUByte(i);
>>>>>>> origin/master
                                    if(i==24||i==25){
                                        paquete1[j]=(byte) 0x00;
                                    }
                                    j++;
<<<<<<< HEAD
                                }
                                
                                 /*  Mandamos la cadena de bytes al metodo checksum y debe
                                    dar como resultado el checksum en la trama*/            
                                long resultado1 = Checksum.calculateChecksum(paquete1);
                                System.out.printf("Valor del checksum: %02X\n",resultado1);
                                
                                
                                /******* Excluyendo los bytes del checksum, para saber si
                                 esta bien su valor ************/
                                for(int i=14;i<34;i++){
                                    paquete2[k]=(byte)packet.getUByte(i);
                                    k++;
                                }
                                
                                long resultado2 = Checksum.calculateChecksum(paquete2);
                                System.out.printf("Resultado considerando al checksum: %02X\n",resultado2);
                                
                                
=======
                                }/*  Mandamos la cadena de bytes al metodo checksum y debe
                                    dar como resultado FFFF*/            
                                long resultado = Checksum.calculateChecksum(paquete);
                                System.out.printf("Valor del checksum: %02X\n",resultado);
                                    }
                                    if(tipo==0x876B){//tcp
                                 System.out.println(" Es tipo tcp/ip");
                                  for(int i=14;i<34;i++){
                                    paquete[j]=(byte)packet.getUByte(i);
                                    if(i==30||i==31){
                                        paquete[j]=(byte) 0x00;
                                    }
                                    j++;
                                }/*  Mandamos la cadena de bytes al metodo checksum y debe
                                    dar como resultado FFFF*/            
                                long resultado = Checksum.calculateChecksum(paquete);
                                System.out.printf("Valor del checksum: %02X\n",resultado);
                                    }
                                    if(tipo==0x876B){//UDP Cambiar 
                                 System.out.println(" Es tipo UDP");
                                  for(int i=14;i<22;i++){
                                    paquete[j]=(byte)packet.getUByte(i);
                                    if(i==7||i==8){
                                        paquete[j]=(byte) 0x00;
                                    }
                                    j++;
                                }/*  Mandamos la cadena de bytes al metodo checksum y debe
                                    dar como resultado FFFF*/            
                                long resultado = Checksum.calculateChecksum(paquete);
                                System.out.printf("Valor del checksum: %02X\n",resultado);
                                    }
                                        
>>>>>>> origin/master
			}
		};


		/***************************************************************************
		 * Fourth we enter the loop and tell it to capture 10 packets. The loop
		 * method does a mapping of pcap.datalink() DLT value to JProtocol ID, which
		 * is needed by JScanner. The scanner scans the packet buffer and decodes
		 * the headers. The mapping is done automatically, although a variation on
		 * the loop method exists that allows the programmer to sepecify exactly
		 * which protocol ID to use as the data link type for this pcap interface.
		 **************************************************************************/
		pcap.loop(10, jpacketHandler, "jNetPcap rocks!");

		/***************************************************************************
		 * Last thing to do is close the pcap handle
		 **************************************************************************/
		pcap.close();
                }catch(IOException e){e.printStackTrace();}
	}
}