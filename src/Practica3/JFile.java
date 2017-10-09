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



 byte[] openFile() throws IOException{
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
                        String path = selectedFile.getAbsolutePath();
                        long tamanio = selectedFile.length();
                        String nombre = selectedFile.getName();
                    try {
                        //DataInputStream dis = new DataInputStream(new FileInputStream(path));

                        Path fileLocation = Paths.get(path);
                        byte [] data = Files.readAllBytes(fileLocation);
                        return data;
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(JFile.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
                return null;
}


public static void main(String args[]) throws IOException{
    JFile file=new JFile(); 
    file.openFile();
}


}
