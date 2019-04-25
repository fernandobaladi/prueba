/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobaladibrito;

/**
 *
 * @author nicolebrito
 */


public class Mapa {
    PartesBarco[][] tablero;
    
    public Mapa(int tamaño) {
        this.tablero = new PartesBarco[tamaño][tamaño];
    }
    
    
    public void llenarTableroEnBlanco(){
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero.length; j++) {
                PartesBarco NuevaParte = new PartesBarco();
                this.tablero[i][j]=NuevaParte;
            }
        }
    }
    
    public void imprimirTablero(){
        
        for (int i = 0; i < 4; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < this.tablero.length; i++) {
            System.out.print(" ");
            System.out.print(i+1);
        }
        System.out.println("");
        for (int i = 0; i < 4; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < this.tablero.length; i++) {
            System.out.print(" -");
        }
        System.out.println("");
        for (int i = 0; i < this.tablero.length; i++) {
            
            System.out.print("");
            System.out.print(i+1);
            System.out.print(" | ");
            for (int j = 0; j < this.tablero.length; j++) {
                System.out.print(" ");
                System.out.print(this.tablero[i][j].getPedazo());
                
            }
            System.out.print(" | ");
            System.out.println("");
        }
        for (int i = 0; i < 4; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i < (this.tablero.length); i++) {
            System.out.print(" -");
        }
        System.out.println("");
        
    }
    
    
}
