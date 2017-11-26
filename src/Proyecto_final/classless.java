/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto_final;

/**
 *
 * @author ANDRES
 */
public class classless {
    public static void main(String args[]){
        
        PcapDumperExample dump= new PcapDumperExample();
        Captura cap=new Captura();
        cap.Captura();
        //dump.interfaz=2;
        //dump.start();
        cap.start(); 
       //dump.stop();
       //cap.stop();
    }
}
