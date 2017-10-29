/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica3;

/**
 *
 * @author ANDRES
 */
public class LongToByte {
    

public static void main(String args[]){
    long l1 = 6100;                
    String cadena=Long.toBinaryString(l1);
    
    String str1="",str2="",aux="";
    if(cadena.length()<16){
        for(int j=0;j<16-cadena.length();j++){
            aux=aux+"0";
        }
            aux=aux+cadena;
    }
    System.out.println(aux);
    
//    int tam=cadena.length();
//    if(cadena.length()<16){
//        if(cadena.length()<8){
//            
//            str1+="00000000";
//            
//            for(int i=0;i<8-tam;i++){
//                str2+='0';
//            }        
//            for(int i=0; i<tam;i++){
//                str2+=cadena.charAt(i);
//            }   
//        }
//        else{
//            for(int i=0;i<8-tam;i++){
//                str1+='0';
//            }
//            int j=0;
//            for(int i=8-tam; i<8;i++){
//                str1+=cadena.charAt(j);
//                j++;
//            }
//            for(int i=8; i<16;i++){
//                str2+=cadena.charAt(j);
//                j++;
//            }
//        }
//        
//    } System.out.println(cadena);  
        
    for(int i=0;i<8;i++){
            str1+=aux.charAt(i);
        }System.out.print(str1);
        for(int i=8;i<16;i++){
            str2+=aux.charAt(i);
        }System.out.println(str2);    
    
    System.out.println("long value: " + l1);                 // prints 10
    int n1=Integer.parseInt(str1,2);
    byte b1 = (byte)(n1);
    int n2=Integer.parseInt(str2,2);
    byte b2 = (byte)n2;

    System.out.println(b1);
    System.out.println(b2);

}
}
