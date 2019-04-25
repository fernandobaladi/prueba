/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobaladibrito;

/**
 *
 * @author Fernando Baladi
 */
public class BarcoPequeño extends Barco {

    public BarcoPequeño(int tamañoDelBarco, Jugador j1) {
        super(tamañoDelBarco, j1);
    }
    
    public void RecuperarVida(){
        for (int i = 0; i <partes.length ; i++) {
            if ((partes[i].getVidaDeParte()<vida)&&(partes[i].isPedazoDañado())&&(!partes[i].isPedazoDestruido())) {
                partes[i].setVidaDeParte(partes[i].getVidaDeParte()+1);
                if (partes[i].getVidaDeParte()==vida){
                    partes[i].setPedazoDañado(false);
                    barcoDañado = false;
                }
            }
        }
    }
}
