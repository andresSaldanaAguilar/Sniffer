package Practica3;

/**
 *
 * @author ANDRES
 */
public class Counter {
    int id, destinatario, emisor;
    public Counter(int inicio){
        id=inicio;
    } 
    int incrementa(int i){
        id+=i;
        return i;
    }
    
    
}
