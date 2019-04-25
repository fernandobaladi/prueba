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
public class Jugador {
    private int vida=0;
    private int contAciertos=0;
    private int contFallidos=0;
    private int partidasGanadas=0;
    private Barco[] barcos= new Barco[6];
    private Mapa tablero;
    private int tamañoBarco;
    private boolean ganador;

    public Jugador(Mapa tablero, int tamañoBarco, int vida) {
        this.tablero = tablero;
        tablero.llenarTableroEnBlanco();
        this.tamañoBarco = tamañoBarco;
        this.vida = vida;
        this.ganador = false;
    }

    public int getContAciertos() {
        return contAciertos;
    }

    public void setContAciertos(int contAciertos) {
        this.contAciertos = contAciertos;
    }

    public int getContFallidos() {
        return contFallidos;
    }

    public void setContFallidos(int contFallidos) {
        this.contFallidos = contFallidos;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }
    
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getContaciertos() {
        return contAciertos;
    }

    public void setContaciertos(int contaciertos) {
        this.contAciertos = contaciertos;
    }

    public int getContfallidos() {
        return contFallidos;
    }

    public void setContfallidos(int contfallidos) {
        this.contFallidos = contfallidos;
    }

    public int getPartidasganadas() {
        return partidasGanadas;
    }

    public void setPartidasganadas(int partidasganadas) {
        this.partidasGanadas = partidasganadas;
    }

    public Barco[] getBarcos() {
        return barcos;
    }

    public void setBarcos(Barco[] barcos) {
        this.barcos = barcos;
    }

    public Mapa getTablero() {
        return tablero;
    }

    public void setTablero(Mapa tablero) {
        this.tablero = tablero;
    }

    public int getTamañoBarco() {
        return tamañoBarco;
    }

    public void setTamañoBarco(int tamañoBarco) {
        this.tamañoBarco = tamañoBarco;
    }
    
    
    
    
    
}
