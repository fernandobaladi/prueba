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
public abstract class Barco {
    protected int tamañoDelBarco, vida;
    protected PartesBarco[] partes;
    protected boolean barcoDañado,  barcoDestruido, barcoGolpeado;
    protected Jugador j1;
    
    public Barco(int tamañoDelBarco, Jugador j1){
        this.tamañoDelBarco = tamañoDelBarco;
        this.j1 = j1;
        this.vida = j1.getVida();
        this.barcoDestruido = false;
        this.barcoDañado = false;
        this.barcoGolpeado = false;
        llenarVectorBarco(tamañoDelBarco);
    }
    public Barco(Jugador j1){
        this.j1=j1;
        this.vida = j1.getVida();
        this.barcoDestruido = false;
        this.barcoDañado = false;
        this.barcoGolpeado = false;
        this.tamañoDelBarco=j1.getTamañoBarco();
        llenarVectorBarco(tamañoDelBarco);
    }
    
    

    public void llenarVectorBarco(int tamaño){
        partes = new PartesBarco[tamaño];
        for (int i = 0; i < this.partes.length; i++) {
            PartesBarco ParteDeNuevoBarco = new PartesBarco(vida);
            partes[i] = ParteDeNuevoBarco;
        }
    }

    public int getTamañoDelBarco() {
        return tamañoDelBarco;
    }

    public void setTamañoDelBarco(int tamañoDelBarco) {
        this.tamañoDelBarco = tamañoDelBarco;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public PartesBarco[] getPartes() {
        return partes;
    }

    public void setPartes(PartesBarco[] partes) {
        this.partes = partes;
    }

    public boolean isBarcoDañado() {
        return barcoDañado;
    }

    public void setBarcoDañado(boolean barcoDañado) {
        this.barcoDañado = barcoDañado;
    }

    public boolean isBarcoDestruido() {
        return barcoDestruido;
    }

    public void setBarcoDestruido(boolean barcoDestruido) {
        this.barcoDestruido = barcoDestruido;
    }

    public boolean isBarcoGolpeado() {
        return barcoGolpeado;
    }

    public void setBarcoGolpeado(boolean barcoGolpeado) {
        this.barcoGolpeado = barcoGolpeado;
    }

    public Jugador getJ1() {
        return j1;
    }

    public void setJ1(Jugador j1) {
        this.j1 = j1;
    }
    
    
}
