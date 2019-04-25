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
public class PartesBarco {
    private int vidaDeParte;
    private boolean pedazoDañado=false, pedazoDestruido=false, pedazoMar=true, pedazoBarco=true, haSidoDisparado=false;
    private char pedazo;

    public PartesBarco(int vida) {
        this.vidaDeParte= vida;
        this.pedazoDañado = false;
        this.pedazoDestruido = false;
        this.pedazoMar = false;
        this.pedazoBarco = true;
        this.haSidoDisparado = false;
        this.pedazo = '±';
    }
    
    public PartesBarco() {
        this.vidaDeParte= 1;
        this.pedazoDañado = false;
        this.pedazoDestruido = false;
        this.pedazoMar = true;
        this.pedazoBarco = false;
        this.haSidoDisparado = false;
        this.pedazo = '=';
    }
    
    //Parte dañada:
    public void estáDañadaLaParte(){
            this.pedazoDañado= true; 
            this.pedazo='*';
            this.vidaDeParte= this.vidaDeParte-1;
    }
    
    //Parte destruída:
    public void estáDestruidaLaParte(){
            this.vidaDeParte=0;
            this.pedazoDañado=false;
            this.pedazoDestruido = true; 
            this.haSidoDisparado = true;
            this.pedazo='#';
    }

    public PartesBarco(int vidaDeParte, char pedazo) {
        this.vidaDeParte = vidaDeParte;
        this.pedazo = pedazo;
    }
    
    public int getVidaDeParte() {
        return vidaDeParte;
    }

    public void setVidaDeParte(int vidaDeParte) {
        this.vidaDeParte = vidaDeParte;
    }

    public boolean isPedazoDañado() {
        return pedazoDañado;
    }

    public void setPedazoDañado(boolean pedazoDañado) {
        this.pedazoDañado = pedazoDañado;
    }

    public boolean isPedazoDestruido() {
        return pedazoDestruido;
    }

    public void setPedazoDestruido(boolean pedazoDestruido) {
        this.pedazoDestruido = pedazoDestruido;
    }

    public char getPedazo() {
        return pedazo;
    }

    public void setPedazo(char pedazo) {
        this.pedazo = pedazo;
    }

    public boolean isPedazoMar() {
        return pedazoMar;
    }

    public void setPedazoMar(boolean pedazoMar) {
        this.pedazoMar = pedazoMar;
    }

    public boolean isPedazoBarco() {
        return pedazoBarco;
    }

    public void setPedazoBarco(boolean pedazoBarco) {
        this.pedazoBarco = pedazoBarco;
    }

    public boolean isHaSidoDisparado() {
        return haSidoDisparado;
    }

    public void setHaSidoDisparado(boolean haSidoDisparado) {
        this.haSidoDisparado = haSidoDisparado;
    }
    
   
    
    
    
    
}
