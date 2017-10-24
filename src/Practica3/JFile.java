/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author ANDRES
 */
public class JFile {

String pathO="";
String pathD="";

 byte[] readFile() throws IOException{
    InputStream is = null;
    int i;
    char c;
    byte[] b; 
      
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
                        String path = selectedFile.getAbsolutePath();
                        pathO = selectedFile.getName();
                        long tamanio = selectedFile.length();
                try {

                        // new input stream created
                        is = new FileInputStream(path);
                        b= new byte[(int)tamanio];

                        System.out.println("Characters printed:");
                        long env=0;
                        // reads till the end of the stream
                        while(env <tamanio) {
                            i = is.read(b); //copia al arreglo el contenido del archivo.
                            env++;
                                    //is.read(b, offset, len);
                                    //is.b[],off, len
                        }
                        for (int j=0;j<b.length;j++){
                            System.out.print((char)b[j]);
                        }                       
                        return b;

                    } catch(Exception e) {

                         // if any I/O error occurs
                         e.printStackTrace();
                      } finally {

                         // releases system resources associated with this stream
                         if(is!=null)
                            is.close();
                   }
		}
                return null;
}
 
 byte[] writeFile(int []b) throws IOException{
    OutputStream os = null;
    int i;
    char c;
     
      
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
                        String path = selectedFile.getAbsolutePath();
                        pathD=selectedFile.getName();
                        long tamanio = selectedFile.length();
                try {

                        // new input stream created
                        os = new FileOutputStream(path);                                              
                        //os.write(b); //copia al arreglo el contenido del archivo.
                        
                        for (int j=0;j<b.length;j++){
                            System.out.print((char)b[j]);
                        }                       
                        //return b;

                    } catch(Exception e) {

                         // if any I/O error occurs
                         e.printStackTrace();
                      } finally {

                         // releases system resources associated with this stream
                         if(os!=null)
                            os.close();
                   }
		}
                return null;
}

String getOriginPath(){
   return pathO;
}
String getDestinyPath(){
    return pathD;
}

}
