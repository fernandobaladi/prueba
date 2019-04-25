/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobaladibrito;

import java.io.IOException;

/**
 *
 * @author nicolebrito
 */
public class ProyectoBaladiBrito {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        InicioJuego battleship = new  InicioJuego(); //Creación de objeto del juego battleship
            battleship.JuegoCompleto();  //Inicio del programa
    }
    
}
