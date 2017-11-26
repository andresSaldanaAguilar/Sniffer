/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto_final;

import javax.swing.JOptionPane;

/**
 *
 * @author ANDRES
 */
public class classless {
    public static void main(String args[]){
        
        PcapDumperExample dump= new PcapDumperExample();
        Captura cap=new Captura();
        //preguntamos si sera captura (0) o lectura (1)
        cap.Captura();
        if(cap.modo==0){
          dump.interfaz=2;
          //preguntamos por el tiempo
          Object[] possibleValues1 = {"Por tiempo","Por numero de paquetes"};
          String opcion1 = (String)JOptionPane.showInputDialog(null,"Selecciona una forma de captura:", "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues1, possibleValues1[0]);
          //preguntamos por filtro
          Object[] possibleValues3 = {"","","","",""};
          String interfaz = (String)JOptionPane.showInputDialog(null,"Selecciona un temporizador en segundos:", "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues3, possibleValues3[0]);
          cap.filtro=interfaz;
          dump.filtro=interfaz;
          //configurando tiempo
          if(opcion1=="Por tiempo"){                
                Object[] possibleValues2 = {5,10,15,25,60};
                int tiempo = (int)JOptionPane.showInputDialog(null,"Selecciona un temporizador en segundos:", "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues2, possibleValues2[0]);
                dump.tiempo=tiempo;
                cap.tiempo=tiempo;
                dump.nopaquetes=100;
                cap.nopaquetes=100;
                dump.start();
                cap.start();
          }
          //configurando no. de paquetes
          else{
              Object[] possibleValues2 = {5,10,15,25,60};
              int paquetes = (int)JOptionPane.showInputDialog(null,"Selecciona numero de paquetes a recibir:", "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues2, possibleValues2[0]);
              dump.tiempo=10;
              cap.tiempo=10;
              dump.nopaquetes=paquetes;
              cap.nopaquetes=paquetes;
              dump.start();
              cap.start();
          }
        }
        else{
          cap.start(); 
        }
    }
}
