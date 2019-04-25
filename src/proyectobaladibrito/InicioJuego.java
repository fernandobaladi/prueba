/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobaladibrito;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

/**
 *
 * @author nicolebrito
 */
public class InicioJuego {
     int menu;
    Scanner sc = new Scanner(System.in);
    private boolean volverAJugar = true, ingresó=false, campañaGanadorJugador=false, campañaGanadorComputador=false;
    private int dificultad, tamañoMapa, tamañoBarco, volverAJugar2, vidaDelJugador, vidaDelComputador;
    private Jugador jugador,computador;
    private int contadorDisparoFallidoJugador=0, contadorDisparoAcertadoJugador=0;
    private int contadorDisparoFallidoComputador=0, contadorDisparoAcertadoComputador=0;
    private int contadorDisparoAcertadoJugadorCampaña=0;
    private int modoDeJuego,ganadasCampaña=0;
  
                                   //Corrida del juego completo:
  
    public void JuegoCompleto() throws IOException{
       
       //Se coloca do-while para iniciar nuevamente la partida si el usuario quiere volver a jugar
        do{
            System.out.println("┌------------------------┐");
            System.out.println("|       BATTLESHIP       |");
            System.out.println("|      El videojuego     |");
            System.out.println("└------------------------┘");
            System.out.println(" "); //Espacio
            System.out.println("         INICIO ");
            System.out.println(" "); //Espacio

            //Inicio de juego con pedido de "tipo de partida"
            this.modoDeJuego = ModoDeJuego();  //Modo de juego
            this.dificultad = Dificultad();   //Dificultad de partida
            this.tamañoMapa = TamañoMapa();   //Tamaño del mapa
            this.tamañoBarco = TamañoBarco6(); //Tamaño del barco número 6
            
                    
                    
            
            Mapa mapaJugador = new Mapa(this.tamañoMapa); //Se instancia el tablero
            Mapa mapaComputador = new Mapa(this.tamañoMapa);
            
            InsertarVida(this.dificultad);
            
            //Se instancia el jugador y la computadora
            Jugador jugador = new Jugador(mapaJugador, this.tamañoBarco, this.vidaDelJugador);
            Jugador computador = new Jugador(mapaComputador, TamañoBarcoRandom(this.tamañoBarco), this.vidaDelComputador);
            
            
            /*
            Notación para en nombre de los objetos barcos:
            Se empieza colocando el nombre del objeto (barco).
            Luego el número de partes que tendrá.
            (Para el barco que el jugador asigna el tamaño, se colocará una N).
            Luego se colocará una J si es un barco del jugador y una C si es del computador.
            Por último se coloca el número de barco instanciado (Si se tiene más de un barco del mismo tamaño).
            */
            
            //Barcos del usuario
            BarcoPequeño barco2J = new BarcoPequeño(2, jugador);
            BarcoMediano barco3J1 = new BarcoMediano(3, jugador);
            BarcoMediano2 barco3J2 = new BarcoMediano2(3, jugador);
            BarcoGrande barco4J = new BarcoGrande(4, jugador);
            BarcoGigante barco5J = new BarcoGigante(5, jugador);
            BarcoJugador barcoNJ = new BarcoJugador(jugador);
            PrimerPoder(barcoNJ);
            
            //Barcos del computador
            BarcoPequeño barco2C = new BarcoPequeño(2, computador);
            BarcoMediano barco3C1 = new BarcoMediano(3, computador);
            BarcoMediano2 barco3C2 = new BarcoMediano2(3, computador);
            BarcoGrande barco4C = new BarcoGrande(4, computador);
            BarcoGigante barco5C = new BarcoGigante(5, computador);
            BarcoJugador barcoNC = new BarcoJugador(computador);
            PrimerPoder(barcoNC);
            
            
            int[][] guardarPosicionesJugador = new int[6][3];
            Barco[] vectorBarcoJugador = new Barco[6];
            int[][] guardarPosicionesComputador = new int[6][3];
            int[] vectorAnteriorDisparoJugardor = new int[2];
            int[] vectorAnteriorDisparoComputador = new int[2];
            for (int i = 0; i < 2; i++) {
                vectorAnteriorDisparoComputador[i]=0;
                vectorAnteriorDisparoJugardor[i]=0;
            }
            
            IngresarTodosLosBarcosDelJugador(jugador.getTablero(), barco2J, barco3J1, barco3J2, barco4J, barco5J,  barcoNJ, guardarPosicionesJugador, vectorBarcoJugador);
            System.out.println("Antes de empezar:\nSi se dispara y no hay nada se marcará una (X).\nSi se dispara a una parte del tablero y dañas o te dañan una parte del barco, se colorá un asterisco(*).\nSi se dispara a una parte del tablero y destruyes o te destruyen una pieza se colorá un numeral (#).");
            System.out.println("");//Espacio
            System.out.println("");//Espacio
            System.out.println("Ahora empezará atacando la computadora.");
            
           
            
             //Inicio de temporizador para cualquier tipo de modo de juego tiempo completo
                 Instant before= Instant.now();
              
            if(this.modoDeJuego==1){
                DentroCadaBarcoComputador(computador.getTablero(), barco2C, barco3C1, barco3C2, barco4C, barco5C, barcoNC, guardarPosicionesComputador);
                setTableroQueParezcaMar(computador.getTablero());
                    do {
                        DispararComputador(jugador.getTablero(), vectorAnteriorDisparoComputador);
                        /*Se ejecuta el segundo poder del jugador*/

                        if (vectorBarcoJugador[0]!=barcoNJ) {
                            SegundoPoder(jugador.getTablero(), guardarPosicionesJugador[0][1], guardarPosicionesJugador[0][2], guardarPosicionesJugador[0][0], vectorBarcoJugador[0], vectorAnteriorDisparoComputador);
                        }else{
                            SegundoPoder(jugador.getTablero(), guardarPosicionesJugador[1][1], guardarPosicionesJugador[1][2], guardarPosicionesJugador[1][0], vectorBarcoJugador[1], vectorAnteriorDisparoComputador);
                        }
                        if (vectorBarcoJugador[1]!=barcoNJ&&vectorBarcoJugador[1]!=barco5J) {
                            TercerPoder(jugador.getTablero(), guardarPosicionesJugador[1][1], guardarPosicionesJugador[1][2], guardarPosicionesJugador[1][0], vectorBarcoJugador[1]);
                        }else{
                            TercerPoder(jugador.getTablero(), guardarPosicionesJugador[2][1], guardarPosicionesJugador[2][2], guardarPosicionesJugador[2][0], vectorBarcoJugador[2]);
                        }
                        if ((vectorBarcoJugador[2]!=barcoNJ)&&(vectorBarcoJugador[2]!=barco5J)&&(this.contadorDisparoFallidoJugador%5==0)&&(this.contadorDisparoFallidoJugador>0)) {
                            CuartoPoder(jugador.getTablero(), guardarPosicionesJugador[2][1], guardarPosicionesJugador[2][2], guardarPosicionesJugador[2][0], vectorBarcoJugador[2]);
                        }else{
                            CuartoPoder(jugador.getTablero(), guardarPosicionesJugador[3][1], guardarPosicionesJugador[3][2], guardarPosicionesJugador[3][0], vectorBarcoJugador[3]);
                        }
                        if ((this.contadorDisparoAcertadoJugador%3==0)&&(this.contadorDisparoAcertadoJugador>0)) {
                            QuintoPoder(computador.getTablero());
                        }

                        DispararJugador(computador.getTablero(), vectorAnteriorDisparoJugardor);

                        SegundoPoder(computador.getTablero(), guardarPosicionesComputador[1][1], guardarPosicionesComputador[1][2], guardarPosicionesComputador[1][0], barco5C, vectorAnteriorDisparoJugardor);
                        TercerPoder(computador.getTablero(), guardarPosicionesComputador[2][1], guardarPosicionesComputador[2][2], guardarPosicionesComputador[2][0], barco4C);
                        if ((this.contadorDisparoFallidoComputador%5==0)&&(this.contadorDisparoFallidoComputador>0)) {
                            CuartoPoder(computador.getTablero(), guardarPosicionesComputador[3][1], guardarPosicionesComputador[3][2], guardarPosicionesComputador[3][0], barco3C1);
                        }
                        if(Ganó(mapaJugador, barco2J, barco3J1, barco3J2, barco4J, barco5J, barcoNJ)){
                            jugador.setGanador(true);
                        }
                        if (Ganó(mapaComputador, barco2C, barco3C1, barco3C2, barco4C, barco5C, barcoNC)) {
                            computador.setGanador(true);
                        }
                        System.out.println("¿Desea ingresar al menú? 1-Sí   2-No");
                        menu=sc.nextInt();

                        while(menu<1|| menu>2){
                            System.out.println("Error en el número ingresado, inténtelo nuevamente");
                            System.out.println("¿Desea ingresar al menú? 1-Sí   2-No");
                            menu=sc.nextInt();
                        }
                        if(menu==1){
                            this.Menú();
                        }

                    }while(!jugador.isGanador() && !computador.isGanador());


                    //Finaliza temporizador
                    Instant after= Instant.now();


                        //Segundos de la partida
                        long militotal=Duration.between(before, after).toMillis();


                        //Llevar de segundos a minutos y milisegundos para hacer  MM:ss:mm
                        int minutosT=(int)((militotal/(1000*60))%60);
                        int segundosT=(int)(militotal/1000)%60;
                        int milisegundosT=(int)(militotal-((segundosT*1000)+(minutosT*1000*60)));


                    if (jugador.isGanador()) {
                        System.out.println("Has perdido, ganó la computadora. Inténtalo de nuevo");
                    }
                    if (computador.isGanador()) {
                        System.out.println("Has ganado. Felicitaciones!");
                    }


                    //Datos finales de la partida
                    System.out.println("\nDisparos acertados por el jugador: " + this.contadorDisparoAcertadoJugador);
                    System.out.println("Disparos fallados por el jugador: " +this.contadorDisparoFallidoJugador );
                    System.out.println("Porcentaje de disparos acertados por el jugador: " + (((this.contadorDisparoAcertadoJugador*100)/(this.contadorDisparoAcertadoJugador+this.contadorDisparoFallidoJugador))) + "%");
                    System.out.println("\nDisparos acertados por la computador: " + this.contadorDisparoAcertadoComputador);
                    System.out.println("Disparos fallados por la computadora: " +this.contadorDisparoFallidoComputador );
                    System.out.println("Porcentaje de disparos acertados por la computadora: " + (((this.contadorDisparoAcertadoComputador*100)/(this.contadorDisparoAcertadoComputador+this.contadorDisparoFallidoComputador))) + "%");
                    System.out.println("\nLa duración de la partida fue de: " +minutosT+":"+segundosT+":"+milisegundosT);

            }
            else{
                do{
                    Mapa mapaComputador2 = new Mapa(this.tamañoMapa);
                    Jugador computador2 = new Jugador(mapaComputador2, TamañoBarcoRandom(this.tamañoBarco), this.vidaDelComputador);
                    DentroCadaBarcoComputador(computador.getTablero(), barco2C, barco3C1, barco3C2, barco4C, barco5C, barcoNC, guardarPosicionesComputador);
                    DentroCadaBarcoComputador(mapaComputador2, barco2C, barco3C1, barco3C2, barco4C, barco5C, barcoNC, guardarPosicionesJugador);
                    
                    computador= computador2;
                    //setTableroQueParezcaMar(computador.getTablero());
                    SumaVidaCampaña(jugador.getTablero());
                    setContadorDisparoAcertadoJugadorCampaña(0);
                    this.campañaGanadorJugador=false;
                    if (this.ganadasCampaña>0) {
                        HacerMar(jugador.getTablero());
                    }
                    do {
                        
                            DispararComputador(jugador.getTablero(), vectorAnteriorDisparoComputador);
                            /*Se ejecuta el segundo poder del jugador*/

                            if (vectorBarcoJugador[0]!=barcoNJ) {
                                SegundoPoder(jugador.getTablero(), guardarPosicionesJugador[0][1], guardarPosicionesJugador[0][2], guardarPosicionesJugador[0][0], vectorBarcoJugador[0], vectorAnteriorDisparoComputador);
                            }else{
                                SegundoPoder(jugador.getTablero(), guardarPosicionesJugador[1][1], guardarPosicionesJugador[1][2], guardarPosicionesJugador[1][0], vectorBarcoJugador[1], vectorAnteriorDisparoComputador);
                            }
                            if (vectorBarcoJugador[1]!=barcoNJ&&vectorBarcoJugador[1]!=barco5J) {
                                TercerPoder(jugador.getTablero(), guardarPosicionesJugador[1][1], guardarPosicionesJugador[1][2], guardarPosicionesJugador[1][0], vectorBarcoJugador[1]);
                            }else{
                                TercerPoder(jugador.getTablero(), guardarPosicionesJugador[2][1], guardarPosicionesJugador[2][2], guardarPosicionesJugador[2][0], vectorBarcoJugador[2]);
                            }
                            if ((vectorBarcoJugador[2]!=barcoNJ)&&(vectorBarcoJugador[2]!=barco5J)&&(this.contadorDisparoFallidoJugador%5==0)&&(this.contadorDisparoFallidoJugador>0)) {
                                CuartoPoder(jugador.getTablero(), guardarPosicionesJugador[2][1], guardarPosicionesJugador[2][2], guardarPosicionesJugador[2][0], vectorBarcoJugador[2]);
                            }else{
                                CuartoPoder(jugador.getTablero(), guardarPosicionesJugador[3][1], guardarPosicionesJugador[3][2], guardarPosicionesJugador[3][0], vectorBarcoJugador[3]);
                            }
                            if ((this.contadorDisparoAcertadoJugador%3==0)&&(this.contadorDisparoAcertadoJugador>0)) {
                                QuintoPoder(computador.getTablero());
                            }

                            DispararJugador(computador.getTablero(), vectorAnteriorDisparoJugardor);

                            SegundoPoder(computador.getTablero(), guardarPosicionesComputador[1][1], guardarPosicionesComputador[1][2], guardarPosicionesComputador[1][0], barco5C, vectorAnteriorDisparoJugardor);
                            TercerPoder(computador.getTablero(), guardarPosicionesComputador[2][1], guardarPosicionesComputador[2][2], guardarPosicionesComputador[2][0], barco4C);
                            if ((this.contadorDisparoFallidoComputador%5==0)&&(this.contadorDisparoFallidoComputador>0)) {
                                CuartoPoder(computador.getTablero(), guardarPosicionesComputador[3][1], guardarPosicionesComputador[3][2], guardarPosicionesComputador[3][0], barco3C1);
                            }
                            int contadorJugador=0, contadorComputador=0;
                            for (int i = 0; i < computador.getTablero().tablero.length; i++) {
                                for (int j = 0; j < computador.getTablero().tablero[i].length; j++) {
                                    if (computador.getTablero().tablero[i][j].isPedazoDestruido()) {
                                        contadorJugador++;
                                    }
                                }
                            }
                            for (int i = 0; i < jugador.getTablero().tablero.length; i++) {
                                for (int j = 0; j < jugador.getTablero().tablero[i].length; j++) {
                                    if (jugador.getTablero().tablero[i][j].isPedazoDestruido()) {
                                        contadorComputador++;
                                    }
                                }
                            }
                            if (contadorJugador==NúmeroDePartesEnTablero(barco2C, barco3C1, barco3C2, barco4C, barco5C, barcoNC)) {
                                this.campañaGanadorJugador=true;
                            }
                            if (contadorComputador==NúmeroDePartesEnTablero(barco2J, barco3J1, barco3J2, barco4J, barco5J, barcoNJ)) {
                                this.campañaGanadorComputador=true;
                                this.campañaGanadorJugador=false;
                            }
                            System.out.println("¿Desea ingresar al menú? 1-Sí   2-No");
                            menu=sc.nextInt();

                            while(menu<1|| menu>2){
                                System.out.println("Error en el número ingresado, inténtelo nuevamente");
                                System.out.println("¿Desea ingresar al menú? 1-Sí   2-No");
                                menu=sc.nextInt();
                            }
                            if(menu==1){
                                this.Menú();
                            }
                        }while(!this.campañaGanadorJugador && !this.campañaGanadorComputador);
                    
                    this.ganadasCampaña++; //Contador de partidas ganadas por el usuario en la campaña
                    System.out.println("¡Iniciemos con el siguiente enfrentamiento!");
                    System.out.println("");//Espacio
                    System.out.println("");//Espacio
                    
                    
                    
                }while(this.campañaGanadorJugador);

                    //Finaliza temporizador de campaña completa 
                    Instant after= Instant.now();

                    
                        //Segundos de la campaña
                        long militotal=Duration.between(before, after).toMillis();


                        //Llevar de segundos a minutos y milisegundos para hacer  MM:ss:mm  CAMPAÑA
                        int minutosT=(int)((militotal/(1000*60))%60);
                        int segundosT=(int)(militotal/1000)%60;
                        int milisegundosT=(int)(militotal-((segundosT*1000)+(minutosT*1000*60)));
                       
                        
                        
                       long militotalpromedio=((Duration.between(before, after).toMillis())/ganadasCampaña);
                       
                        int minutosTpromedio=(int)((militotalpromedio/(1000*60))%60);
                        int segundosTpromedio=(int)(militotalpromedio/1000)%60;
                        int milisegundosTpromedio=(int)(militotalpromedio-((segundosT*1000)+(minutosT*1000*60)));
                        
                    //Tiempo de la campaña
                    System.out.println("\nLa duración del juego fue fue de: " +minutosT+":"+segundosT+":"+milisegundosT);  
                      //Promedio de tiempo por partida
                    System.out.println("El promedio de tiempo por partida es de: "+minutosTpromedio+":"+segundosTpromedio+":"+milisegundosTpromedio);
                        //INFORMACIÓN DE LA CAMPAÑA COMPLETA 
                    System.out.println("\nDisparos acertados por el jugador: " + this.contadorDisparoAcertadoJugador);
                    System.out.println("Disparos fallados por el jugador: " +this.contadorDisparoFallidoJugador );
                    System.out.println("Porcentaje de disparos acertados por el jugador: " + (((this.contadorDisparoAcertadoJugador*100)/(this.contadorDisparoAcertadoJugador+this.contadorDisparoFallidoJugador))) + "%");
                    System.out.println("\nDisparos acertados por la computador: " + this.contadorDisparoAcertadoComputador);
                    System.out.println("Disparos fallados por la computadora: " +this.contadorDisparoFallidoComputador );
                    System.out.println("Porcentaje de disparos acertados por la computadora: " + (((this.contadorDisparoAcertadoComputador*100)/(this.contadorDisparoAcertadoComputador+this.contadorDisparoFallidoComputador))) + "%");
                    System.out.println("El número de barcos destruidos en todas tus victorias fueron " + (this.ganadasCampaña*6));

            }
                
                
                
                
        
        VolverAJugar(); //Método que le pregunta al usuario si desea volver a jugar 
        
        }while(volverAJugar) ;
    }
   
    
    //MÉTODOS GENERALES DE TODO EL JUEGO
   
   //Tipo de juego:
    private int ModoDeJuego(){
        System.out.println("¿Qué modo de juego desea? \n1-Batalla     2-Campaña");
        this.modoDeJuego = sc.nextInt();
            
        while(!(this.modoDeJuego == 1 || this.modoDeJuego == 2)){
            System.out.println("\n¡El número ingresado no es correcto, inténtelo nuevamente!");
            System.out.println("¿Qué modo de juego desea? \n1-Batalla     2-Campaña");
            this.modoDeJuego = sc.nextInt();
        }
        return modoDeJuego;
    }
    
    //Selección de dificultad
    private int Dificultad(){
        System.out.println("\n¿En qué dificultad desea jugar? \n1-Muy fácil \n2-Fácil \n3-Normal \n4-Difícil \n5-Muy difícil");
            this.dificultad = sc.nextInt();
        
        while(this.dificultad < 1 ||this.dificultad > 5){
            System.out.println("\n¡El número ingresado no es correcto, inténtelo nuevamente!");
            System.out.println("\n¿En qué dificultad desea jugar? \n1-Muy fácil \n2-Fácil \n3-Normal \n4-Difícil \n5-Muy difícil");
            this.dificultad = sc.nextInt();
        }
        return dificultad;
    }
    
    //Selección de tamaño del mapa
    private int TamañoMapa(){
        System.out.println("\n¿En qué tamaño de mapa desea jugar?\n1- 7x7           2- 8x8            3- 9x9");
        this.tamañoMapa = sc.nextInt();
        
        while(this.tamañoMapa < 1 || this.tamañoMapa > 3){
            System.out.println("\n¡El número ingresado no es correcto, inténtelo nuevamente!");
            System.out.println("\n¿En qué tamaño de mapa desea jugar?\n1- 7x7           2- 8x8            3- 9x9");
            this.tamañoMapa = sc.nextInt();
        }
        this.tamañoMapa=this.tamañoMapa+6;
        return tamañoMapa;
    }
    
    //Selección de tamaño del barco número 6 elegido por el jugador
    private int TamañoBarco6(){
        System.out.println("\n¿De qué tamaño desea que sea su sexto barco? \n Ingrese un tamaño entre 2 y 7");
        this.tamañoBarco = sc.nextInt();
        
        while(this.tamañoBarco < 2 || this.tamañoBarco > 7){
            System.out.println("\n¡El número ingresado para el tamaño del barco no es correcto, ingrese uno nuevamente!");
            System.out.println("\n¿De qué tamaño desea que sea su sexto barco? \n Ingrese un tamaño entre 2 y 7");
            this.tamañoBarco= sc.nextInt();
        }
        return tamañoBarco;
    }
    
    //Selección de si el usuario volver a jugar
    private void VolverAJugar(){
        System.out.println("¿Desea volver a jugar? \n1-Sí      2-No");
        this.volverAJugar2 = sc.nextInt();
        
        while(!(this.volverAJugar2 == 1 || this.volverAJugar2 == 2)){
            System.out.println("\n¡El número ingresado no es correcto, inténtelo nuevamente!");
            System.out.println("¿Desea volver a jugar? \n1-Sí      2-No");
            this.volverAJugar2 = sc.nextInt();
        }
        //Para finalizar juego tranformamos el boolean en falso para que no entre más en el bucle do-while
        if(this.volverAJugar2 == 2){
            this.volverAJugar =! this.volverAJugar;
        }
    }
    
    //Decide el tamaño del barco de la computadora (Random)
    private int TamañoBarcoRandom(int tamañoBarco){
        int tamañoDeBarcoComputadora;
        do{
            tamañoDeBarcoComputadora = (int) ((Math.random()*7)+1);
        }while(tamañoDeBarcoComputadora<tamañoBarco);
        return tamañoDeBarcoComputadora;
    }
    
    //Traduce la posición de la ordenada indicada
    public int TraductorDePosiciónY(int número){
        int y;
        y = número/10;
        return y;
    }
    
    //Traduce la posición de la abscisas indicada
    public int TraductorDePosiciónX(int número){
        int x;
        x = número%10;
        return x;
    }
    
    //Valida si la posición introducida puede ser utilizada
    public boolean ValidarSiLaPosiciónEstáLibreJugador(Mapa mapa, int númeroY, int númeroX, Barco barco, int orientación){
        if (orientación==1) {  //orientación 1 es vertical. orientación 2 es horizontal
            for (int i = 0; i < barco.partes.length; i++) {
                if ((barco.partes.length+((númeroY)-1))>mapa.tablero.length) {
                    System.out.println("No se puede colocar aquí el barco, intenta en otra posición");
                    return false;
                }else{
                    if(!mapa.tablero[(númeroY+i)-1][(númeroX)-1].isPedazoMar()){
                        System.out.println("No se puede colocar aquí el barco, intenta en otra posición");
                        return false;
                    }
                }
            }  
            return true;
        }else{
            for (int i = 0; i < barco.partes.length; i++) {
                if ((barco.partes.length+((númeroX)-1))>mapa.tablero.length) {
                    System.out.println("No se puede colocar aquí el barco, intenta en otra posición");
                    return false;
                }
                if(!mapa.tablero[(númeroY)-1][(númeroX+i)-1].isPedazoMar()){
                    System.out.println("No se puede colocar aquí el barco, intenta en otra posición");
                    return false;
                }
            }  
            return true;
        }
    }
    
    //Coloca la pieza del barco en el tablero de forma vertical
    public void ColocarBarcoEnMapaVertical(Mapa mapa, Barco barco, int númeroY, int númeroX){
        for (int i = 0; i < barco.partes.length; i++) {
            mapa.tablero[(númeroY+i)-1][(númeroX)-1] = barco.partes[i];
        } 
    }
    
    //Coloca la pieza del barco en el tablero de forma horizontal
    public void ColocarBarcoEnMapaHorizontal(Mapa mapa, Barco barco, int númeroY, int númeroX){
        for (int i = 0; i < barco.partes.length; i++) {
            mapa.tablero[(númeroY)-1][(númeroX+i)-1] = barco.partes[i];
        } 
    }
    
    //Proceso de ingresar el barco
    public void ColocarBarco(Mapa mapa, Barco barco, int[][] guardarPosición, int contador){
        int orientación=0, posición=0, posiciónX=0, posiciónY=0;
        boolean validación=true;
        while(orientación!=1 && orientación!=2){
            System.out.println("El barco a ingresar tiene "+barco.partes.length+" partes");
            System.out.println("¿Quieres ingresarlo de forma vertical (1) u horizontal(2)?");
            orientación = sc.nextInt();
            if (orientación!=1 && orientación!=2) {
                System.out.println("Vuelve a ingresar la orientación");
                 orientación = sc.nextInt();
                         
            }
        }
        guardarPosición[contador][0]=orientación;
        switch (orientación) {
            case 1:
                do{
                    do {
                        System.out.println("El barco será ingresado de forma vertical hacia abajo iniciando por el número que indique.");
                        System.out.println("Indique en qué posición lo colocará");
                        System.out.println("Para indicar la posición coloque los dos números contiguos, empezando con el número en la altura, seguido de la posición en el espacio");
                        System.out.println("Ejemplo: 22");
                        posición = sc.nextInt();
                        if (((posición%10)==0)||(posición>this.tamañoMapa*11)||(posición<11)||((posición%10)>this.tamañoMapa)) {
                            System.out.println("Error, vuelve a ingresar las coordenadas.");
                                posición = sc.nextInt();
                        }else{
                            System.out.println("Puedes continuar");
                            validación = true;
                        }
                            
                    } while (!validación);
                    posiciónY = TraductorDePosiciónY(posición);
                    posiciónX = TraductorDePosiciónX(posición);
                    validación = ValidarSiLaPosiciónEstáLibreJugador(mapa, posiciónY, posiciónX, barco, orientación);
                }while(!validación);
                guardarPosición[contador][1]=posiciónY;
                guardarPosición[contador][2]=posiciónX;
                ColocarBarcoEnMapaVertical(mapa, barco, posiciónY, posiciónX);
                mapa.imprimirTablero();
                break;
            case 2:
                do{
                    do {
                        System.out.println("El barco será ingresado de forma vertical hacia abajo iniciando por el número que indique.");
                        System.out.println("Indique en qué posición lo colocará");
                        System.out.println("Para indicar la posición coloque los dos números contiguos, empezando con el número en la altura, seguido de la posición en el espacio");
                        System.out.println("Ejemplo: 22");
                        posición = sc.nextInt();
                        if (((posición%10)==0)||(posición>this.tamañoMapa*11)||(posición<11)||((posición%10)>this.tamañoMapa)) {
                            System.out.println("Error, vuelve a ingresar las coordenadas.");
                        }else{
                            System.out.println("Puedes continuar");
                            validación = true;
                        }
                    } while (!validación);
                    posiciónY = TraductorDePosiciónY(posición);
                    posiciónX = TraductorDePosiciónX(posición);
                    validación = ValidarSiLaPosiciónEstáLibreJugador(mapa, posiciónY, posiciónX, barco, orientación);
                }while(!validación);
                guardarPosición[contador][1]=posiciónY;
                guardarPosición[contador][2]=posiciónX;
                ColocarBarcoEnMapaHorizontal(mapa, barco, posiciónY, posiciónX);
                mapa.imprimirTablero();
                break;
            default:
                throw new AssertionError();
        }
    }
    
    
    //Pregunta de que si el jugador desea reiniciar el tablero
    public int ReiniciarTablero(){
        int reiniciarTableroM = 0;
        while(reiniciarTableroM!=1 && reiniciarTableroM!=2){
            System.out.println("¿Quieres reiniciar el tablero? Sí(1), No(2)");
            reiniciarTableroM = sc.nextInt();
            if (reiniciarTableroM!=1 && reiniciarTableroM!=2) {
                System.out.println("Escoge una opción válida.");
            }
        }
        return reiniciarTableroM;
    }
    
    //Ingreso de barcos del jugador
    public void IngresarTodosLosBarcosDelJugador(Mapa mapa, Barco barco2J, Barco barco3J1, Barco barco3J2, Barco barco4J, Barco barco5J, Barco barcoNJ, int[][] guardarPosición, Barco[] vectorBarcos){
        int reiniciarTablero=0, contador=0;
        Mapa mapaAuxiliar = new Mapa(this.tamañoMapa);
        int[][] guardarPosiciónAuxiliar = new int[6][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                guardarPosiciónAuxiliar[i][j]=0;
            }
        }
        Jugador jugadorAuxiliar = new Jugador(mapaAuxiliar, this.tamañoBarco, this.vidaDelJugador);
        do{
            jugadorAuxiliar.getTablero().imprimirTablero();
            
            if (reiniciarTablero==1) {
                System.out.println("Este es el tablero reiniciado, comenzaremos de nuevo con el proceso. \n");
                for (int i = 0; i < mapa.tablero.length; i++) {
                    for (int j = 0; j < mapa.tablero.length; j++) {
                        mapa.tablero[i][j] = jugadorAuxiliar.getTablero().tablero[i][j];
                    }
                }
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        guardarPosición[i][j] = guardarPosiciónAuxiliar[i][j];
                    }
                }
            }
            int[] vectorOrdenar = {2, 3, 3, 4, 5, barcoNJ.tamañoDelBarco};
            
            int aux;
            boolean cambio=false;
            while(true){
            cambio=false;
                for (int i = 6; i < vectorOrdenar.length; i--) {
                   if(vectorOrdenar [i]>vectorOrdenar [i-1]){
                        aux=vectorOrdenar[i];
                        vectorOrdenar[i]=vectorOrdenar [i-1];
                        vectorOrdenar [i-1]=aux;
                        cambio=true;
                    }
                           
                }
                    
            if (cambio==false)
                break;
            }
            invertir(vectorOrdenar);
            colocarlo(mapa, barco2J, barco3J1, barco3J2, barco4J, barco5J, barcoNJ, vectorOrdenar, contador, guardarPosición, vectorBarcos);
            contador++;
            reiniciarTablero = ReiniciarTablero();
            if(reiniciarTablero==2){
                colocarlo(mapa, barco2J, barco3J1, barco3J2, barco4J, barco5J, barcoNJ, vectorOrdenar, contador, guardarPosición, vectorBarcos);
                contador++;
                reiniciarTablero = ReiniciarTablero();
                
            }
            if(reiniciarTablero==2){
                colocarlo(mapa, barco2J, barco3J1, barco3J2, barco4J, barco5J, barcoNJ, vectorOrdenar, contador, guardarPosición, vectorBarcos);
                contador++;
                reiniciarTablero = ReiniciarTablero();
            }
            if(reiniciarTablero==2){
                colocarlo(mapa, barco2J, barco3J1, barco3J2, barco4J, barco5J, barcoNJ, vectorOrdenar, contador, guardarPosición, vectorBarcos);
                contador++;
                reiniciarTablero = ReiniciarTablero();
            }
            if(reiniciarTablero==2){
                colocarlo(mapa, barco2J, barco3J1, barco3J2, barco4J, barco5J, barcoNJ, vectorOrdenar, contador, guardarPosición, vectorBarcos);
                contador++;
                reiniciarTablero = ReiniciarTablero();
            }
            if(reiniciarTablero==2){
                colocarlo(mapa, barco2J, barco3J1, barco3J2, barco4J, barco5J, barcoNJ, vectorOrdenar, contador, guardarPosición, vectorBarcos);
                contador++;
                reiniciarTablero = ReiniciarTablero();
            }
            if (reiniciarTablero==1) {
                    contador=0;
            }
        }while(reiniciarTablero==1);
    }
    
    
    public int [] invertir (int [] vector){
        int aux;
        for (int i = 0; i <= (vector.length-1)/2; i++) {
            aux = vector [i];
            vector [i] = vector [(vector.length-1)-i];
            vector [(vector.length-1)-i] = aux;
        }
        return vector;
    }
    
    
    public void colocarlo(Mapa mapa, Barco barco2J, Barco barco3J1, Barco barco3J2, Barco barco4J, Barco barco5J, Barco barcoNJ, int[] vectorOrdenar, int contador, int[][] guardarPosición, Barco[] vectorBarcos){
        switch (vectorOrdenar[contador]) {
                    case 2:
                        ColocarBarco(mapa, barco2J, guardarPosición, contador);
                        vectorBarcos[contador]=barco2J;
                        break;
                    case 3:
                        if (contador==1) {
                            ColocarBarco(mapa, barco3J1,guardarPosición,contador); 
                            vectorBarcos[contador]=barco3J1;
                        }else{
                            ColocarBarco(mapa, barco3J2,guardarPosición,contador);
                            vectorBarcos[contador]=barco3J2;
                        }
                        break;
                    case 4:
                        ColocarBarco(mapa, barco4J,guardarPosición,contador);
                        vectorBarcos[contador]=barco4J;
                        break;
                    case 5:
                        ColocarBarco(mapa, barco5J,guardarPosición,contador);
                        vectorBarcos[contador]=barco5J;
                        break;
                    default:
                        ColocarBarco(mapa, barcoNJ,guardarPosición,contador);
                        vectorBarcos[contador]=barcoNJ;
                }
    }
           
    //Validación de posición del espacio de colocación del barco de la computadora
    public boolean ValidarSiLaPosiciónEstáLibreComputador(Mapa mapa, int númeroY, int númeroX, Barco barco, int orientación){
        if (orientación==1) { //orientación 1 es vertical. orientación 2 es horizontal
            for (int i = 0; i < barco.partes.length; i++) {
                if ((barco.partes.length+((númeroY)-1))>mapa.tablero.length) {
                    return false;
                }else{
                    if(!mapa.tablero[(númeroY+i)-1][(númeroX)-1].isPedazoMar()){
                        return false;
                    }
                }
            }  
            return true;
        }else{
            for (int i = 0; i < barco.partes.length; i++) {
                if ((barco.partes.length+((númeroX)-1))>mapa.tablero.length) {
                    return false;
                }
                if(!mapa.tablero[(númeroY)-1][(númeroX+i)-1].isPedazoMar()){
                    return false;
                }
            }  
            return true;
        }
        
    }
    
    //Ingreso aleatorio-barco
    public void IngresarBarcoAleatorio(Mapa mapa, Barco barco, int[][] guardarPosición, int contador){
        int orientación, númeroY, númeroX, aux=0;
        boolean validar;    
        do{
            orientación=(int)(Math.random()*1)+1;
            númeroY = (int)(Math.random()*(this.tamañoMapa))+1;
            númeroX = (int)(Math.random()*(this.tamañoMapa))+1;
            validar = ValidarSiLaPosiciónEstáLibreComputador(mapa, númeroY, númeroX, barco, orientación);
            if (!validar) {
                switch (orientación) {
                    case 1:
                        orientación = 2;
                        break;
                    case 2:
                        orientación = 1;
                        break;
                    default:
                        System.out.println("Hay un error");
                }
                validar = ValidarSiLaPosiciónEstáLibreComputador(mapa, númeroY, númeroX, barco, orientación);
            }
            if (validar) { //orientación 1 es vertical. orientación 2 es horizontal
                switch (orientación) {
                    case 1:
                        ColocarBarcoEnMapaVertical(mapa, barco, númeroY, númeroX);
                        break;
                    case 2:
                        ColocarBarcoEnMapaHorizontal(mapa, barco, númeroY, númeroX);
                        break;
                    default:
                        System.out.println("Hay un error");
                        break;
                }
            }
            aux++;
        }while(!validar && aux<30);
        guardarPosición[contador][0]=orientación;
        guardarPosición[contador][1]=númeroY;
        guardarPosición[contador][2]=númeroX;
        contador++;
    }
    
    public Mapa GuardarTableroDelComputador(Mapa mapa){
        Mapa mapaAuxiliar = new Mapa(this.tamañoMapa);
        Jugador computadorAuxiliar = new Jugador(mapaAuxiliar, this.tamañoBarco, this.vidaDelComputador);
        for (int i = 0; i < mapa.tablero.length; i++) {
            for (int j = 0; j < mapa.tablero.length; j++) {
                computadorAuxiliar.getTablero().tablero[i][j] = mapa.tablero[i][j];
            }
        }
        return computadorAuxiliar.getTablero();
        
    }
    
    public void DentroCadaBarcoComputador(Mapa mapa, Barco barco2C, Barco barco3C1, Barco barco3C2, Barco barco4C, Barco barco5C, Barco barcoNC, int[][] guardarPosición){
        int contador=0;
        IngresarBarcoAleatorio(mapa, barcoNC, guardarPosición, contador);
        IngresarBarcoAleatorio(mapa, barco5C, guardarPosición, contador);
        IngresarBarcoAleatorio(mapa, barco4C, guardarPosición, contador);
        IngresarBarcoAleatorio(mapa, barco3C1, guardarPosición, contador);
        IngresarBarcoAleatorio(mapa, barco3C2, guardarPosición, contador);
        IngresarBarcoAleatorio(mapa, barco2C, guardarPosición, contador);
        //setTableroQueParezcaMar(mapa);
    }
    
    //Colocación del agua en todo el tablero en forma de =
    public void setTableroQueParezcaMar(Mapa mapa){
        for (int i = 0; i < mapa.tablero.length; i++) {
            for (int j = 0; j < mapa.tablero.length; j++) {
                mapa.tablero[i][j].setPedazo('=');
            }
        }
    }
    
    public void InsertarVida(int dificultad){
        switch (dificultad) {
                case 1:
                    this.vidaDelJugador=3;
                    this.vidaDelComputador=1;
                    break;
                case 2:
                    this.vidaDelJugador=2;
                    this.vidaDelComputador=1;
                    break;
                case 3:
                    this.vidaDelJugador=1;
                    this.vidaDelComputador=1;
                    break;
                case 4:
                    this.vidaDelJugador=1;
                    this.vidaDelComputador=2;
                    break;
                case 5:
                    this.vidaDelJugador=1;
                    this.vidaDelComputador=3;
                    break;    
                default:
                    System.out.println("Hay un error.");
            }
    }

    //Método de disparo del jugador al mapa de la computadora
    public void DispararJugador(Mapa mapa, int[] vectorDisparo){
        
        int posición = 0, númeroY=0, númeroX=0;
        boolean validar=false, validar2=false;
        System.out.println("Tablero de tu contrincante: \n \n \n");
        mapa.imprimirTablero();
        while (!validar) {
            validar2=false;
            while (!validar2) {
                System.out.println("Coloca las coordenadas a donde quieras disparar. \n Hazlo como lo hiciste para colocar los barcos.");
                posición = sc.nextInt();
                if (((posición%10)==0)||(posición>this.tamañoMapa*11)||(posición<11)||((posición%10)>this.tamañoMapa)) {
                    System.out.println("Error, coloque bien las coordenadas.");
                }else{
                    validar2 = true;
                }
            }
            númeroY=(TraductorDePosiciónY(posición)-1);
            númeroX=(TraductorDePosiciónX(posición)-1);
            validar = ValidarLaPosiciónJugador(mapa, númeroY, númeroX);
        }
        vectorDisparo[0]=númeroY;
        vectorDisparo[1]=númeroX;
        ValidarLoQueHayJugador(mapa, númeroY, númeroX);
        
    }
    
    public void ValidarLoQueHayJugador(Mapa mapa,int númeroY, int númeroX){
        if (mapa.tablero[númeroY][númeroX].isPedazoMar()) {
            mapa.tablero[númeroY][númeroX].setPedazo('X');
            mapa.tablero[númeroY][númeroX].setHaSidoDisparado(true);
            setContadorDisparoFallidoJugador(this.contadorDisparoFallidoJugador+1);
        }else{
            if(mapa.tablero[númeroY][númeroX].getVidaDeParte()==1) {
                mapa.tablero[númeroY][númeroX].estáDestruidaLaParte();
            }else{
                mapa.tablero[númeroY][númeroX].estáDañadaLaParte();
            }
            setContadorDisparoAcertadoJugador(this.contadorDisparoAcertadoJugador+1);
            setContadorDisparoAcertadoJugadorCampaña(this.contadorDisparoAcertadoJugadorCampaña+1);
            
        }
    }
    
    public void ValidarLoQueHayComputador(Mapa mapa,int númeroY, int númeroX){
        if (mapa.tablero[númeroY][númeroX].isPedazoMar()) {
            mapa.tablero[númeroY][númeroX].setPedazo('X');
            mapa.tablero[númeroY][númeroX].setHaSidoDisparado(true);
            setContadorDisparoFallidoComputador(this.contadorDisparoFallidoComputador+1);
        }else{
            if(mapa.tablero[númeroY][númeroX].getVidaDeParte()==1) {
                mapa.tablero[númeroY][númeroX].estáDestruidaLaParte();
            }else{
                mapa.tablero[númeroY][númeroX].estáDañadaLaParte();
            }
            setContadorDisparoAcertadoComputador(this.contadorDisparoAcertadoComputador+1);
        }
    }
    
    public boolean ValidarLaPosiciónJugador(Mapa mapa, int númeroY, int númeroX){
        if ((númeroY>mapa.tablero.length || númeroX>mapa.tablero.length) || mapa.tablero[númeroY][númeroX].isHaSidoDisparado()) {
            System.out.println("Ingresa una posición válida.");
            return false;
        }
        return true;
    }
    
    public boolean ValidarLaPosiciónComputador(Mapa mapa, int númeroY, int númeroX){
        if ((númeroY>mapa.tablero.length || númeroX>mapa.tablero.length) || mapa.tablero[númeroY][númeroX].isHaSidoDisparado()) {
       
            return false;
        }
        return true;
    }
    
    
    //Método de disparo de la computadora al mapa del jugador
    public int DispararComputador(Mapa mapa, int[] vectorDisparo){
        int númeroY=0, númeroX=0;
        boolean validar=false;
        System.out.println("Tu tablero: \n \n \n ");
        mapa.imprimirTablero();
        while (!validar) {
            númeroY = (int) ((Math.random()*this.tamañoMapa));
            númeroX = (int) ((Math.random()*this.tamañoMapa));
            validar = ValidarLaPosiciónComputador(mapa, númeroY, númeroX);
        }
        vectorDisparo[0]=númeroY;
        vectorDisparo[1]=númeroX;
        ValidarLoQueHayComputador(mapa, númeroY, númeroX);
        return (númeroY*10)+númeroX;
        
    }
    
    public int NúmeroDePartesEnTablero(Barco barco2, Barco barco31, Barco barco32, Barco barco4, Barco barco5, Barco barcoN){
        int númeroDePartes = barco2.tamañoDelBarco + barco31.tamañoDelBarco + barco32.tamañoDelBarco + barco4.tamañoDelBarco + barco5.tamañoDelBarco + barcoN.tamañoDelBarco;
        return númeroDePartes;
    }
  
    
    
    public boolean Ganó(Mapa mapa, Barco barco2, Barco barco31, Barco barco32, Barco barco4, Barco barco5, Barco barcoN){
        int númeroDePartes, contador=0;
        númeroDePartes = NúmeroDePartesEnTablero(barco2, barco31, barco32, barco4, barco5, barcoN);
        for (int i = 0; i < mapa.tablero.length; i++) {
            for (int j = 0; j < mapa.tablero.length; j++) {
                if(mapa.tablero[i][j].isPedazoDestruido()){
                    contador++;
                }
            }
        }
        if (contador==númeroDePartes) {
            return true;
        }
        return false;
        
    }
     
    public boolean GanóCampaña(Mapa mapa, Barco barco2, Barco barco31, Barco barco32, Barco barco4, Barco barco5, Barco barcoN){
        int contador=0;
        
        for (int i = 0; i < mapa.tablero.length; i++) {
            for (int j = 0; j < mapa.tablero[i].length; j++) {
                if (mapa.tablero[i][j].isPedazoDestruido()) {
                    contador++;
                }
            }
        }
        if (contador==NúmeroDePartesEnTablero(barco2, barco31, barco32, barco4, barco5, barcoN)) {
            return true;
        }else{
            return false;
        }
        
    }
    
    /*Este poder le da una vida más a las piezas del barco*/
    public void PrimerPoder(Barco barco){
        for (int i = 0; i < barco.tamañoDelBarco; i++) {
            barco.partes[i].setVidaDeParte(barco.partes[i].getVidaDeParte()+1);
        }
    }
    
    
    /*Este poder regenera una vida si no ha sido dañada, pero sólo hasta la cantidad de vida inicial*/
    public void SegundoPoder(Mapa mapa, int posiciónY, int posiciónX, int orientación, Barco barco, int[] vectorDisparos){
        boolean validar=false;
        for (int i = 0; i < barco.tamañoDelBarco; i++) {
            if (orientación==1) {
                if (((posiciónY+i)==vectorDisparos[0])&&((posiciónX)==vectorDisparos[1])) {
                    validar=true;
                }    
            }else{
                if ((posiciónY==vectorDisparos[0])&&((posiciónX+i)==vectorDisparos[1])) {
                    validar=true;
                }
            }
        }
        if (!validar) {
            if (orientación==1) {
                for (int i = 0; i < barco.tamañoDelBarco; i++) {
                    if(!(mapa.tablero[posiciónY+i][posiciónX].isHaSidoDisparado())&&(mapa.tablero[posiciónY+i][posiciónX].getVidaDeParte()<barco.getVida())&&(mapa.tablero[posiciónY+i][posiciónX].getVidaDeParte()>0)){
                        mapa.tablero[posiciónY+i][posiciónX].setVidaDeParte(mapa.tablero[posiciónY+i][posiciónX].getVidaDeParte()+1);
                    }
                }
            }else{
                for (int i = 0; i < barco.tamañoDelBarco; i++) {
                    if(!(mapa.tablero[posiciónY][posiciónX+i].isHaSidoDisparado())&&(mapa.tablero[posiciónY][posiciónX+i].getVidaDeParte()<barco.getVida())&&(mapa.tablero[posiciónY][posiciónX+i].getVidaDeParte()>0)){
                        mapa.tablero[posiciónY][posiciónX+i].setVidaDeParte(mapa.tablero[posiciónY][posiciónX+i].getVidaDeParte()+1);
                        
                    }
                }
            }
        }
    }
    
    //Si es capicúo su número de vida, entonces se le agrega una vida
    public void TercerPoder(Mapa mapa, int posiciónY, int posiciónX, int orientación, Barco barco){
        
            if (orientación==1) {
                int num=0;
                for (int i = 0; i < barco.tamañoDelBarco; i++) {
                    num += mapa.tablero[posiciónY+i][posiciónX].getVidaDeParte()*10;
                    if (i==i-1) {
                        num += mapa.tablero[posiciónY+i][posiciónX].getVidaDeParte();
                    }
                    
                    
                }
                int aux = num, cifra, inverso=0;
                while (aux!=0){
                cifra = aux % 10;
                inverso = inverso * 10 + cifra;
                aux = aux / 10;
              
                }
                if (num==inverso) {
                    for (int i = 0; i < barco.tamañoDelBarco; i++) {
                        if(!(mapa.tablero[posiciónY+i][posiciónX].isHaSidoDisparado())&&(mapa.tablero[posiciónY+i][posiciónX].getVidaDeParte()<barco.getVida())&&(mapa.tablero[posiciónY+i][posiciónX].getVidaDeParte()>0)){
                        mapa.tablero[posiciónY+i][posiciónX].setVidaDeParte(mapa.tablero[posiciónY+i][posiciónX].getVidaDeParte()+1);
                    } 
                    }
                }
            }else{
                int num=0;
                for (int i = 0; i < barco.tamañoDelBarco; i++) {
                    num += mapa.tablero[posiciónY][posiciónX+i].getVidaDeParte()*10;
                    if (i==i-1) {
                        num += mapa.tablero[posiciónY][posiciónX+i].getVidaDeParte();
                    }
                    
                    
                }
                int aux = num, cifra, inverso=0;
                while (aux!=0){
                cifra = aux % 10;
                inverso = inverso * 10 + cifra;
                aux = aux / 10;
              
                }
                if (num==inverso) {
                    for (int i = 0; i < barco.tamañoDelBarco; i++) {
                        if(!(mapa.tablero[posiciónY][posiciónX+i].isHaSidoDisparado())&&(mapa.tablero[posiciónY][posiciónX+i].getVidaDeParte()<barco.getVida())&&(mapa.tablero[posiciónY][posiciónX+i].getVidaDeParte()>0)){
                            mapa.tablero[posiciónY][posiciónX+i].setVidaDeParte(mapa.tablero[posiciónY][posiciónX+i].getVidaDeParte()+1);
                        } 
                    }
                }
            }
        
    }
    
    
    
    
    //Menú de opciones que se le ofrese al jugador en cada turno de disparar salir, reiniciar o continuar
    public void Menú() throws IOException{
        System.out.println("¿Qué desea hacer? 1-Reiniciar el juego  2-Salir del juego  3-Volver");
    int reiniciar=sc.nextInt();
    if(reiniciar==1){ 
        ProyectoBaladiBrito.main(null);
        System.out.println("\n\n\n\n\n\n\n\n");
    }
    if(reiniciar==2){ System.exit(0); }
    
    }

    public int getContadorDisparoFallidoJugador() {
        return contadorDisparoFallidoJugador;
    }

    public void setContadorDisparoFallidoJugador(int contadorDisparoFallidoJugador) {
        this.contadorDisparoFallidoJugador = contadorDisparoFallidoJugador;
    }

    public int getContadorDisparoAcertadoJugador() {
        return contadorDisparoAcertadoJugador;
    }

    public void setContadorDisparoAcertadoJugador(int contadorDisparoAcertadoJugador) {
        this.contadorDisparoAcertadoJugador = contadorDisparoAcertadoJugador;
    }

    public int getContadorDisparoFallidoComputador() {
        return contadorDisparoFallidoComputador;
    }

    public void setContadorDisparoFallidoComputador(int contadorDisparoFallidoComputador) {
        this.contadorDisparoFallidoComputador = contadorDisparoFallidoComputador;
    }

    public int getContadorDisparoAcertadoComputador() {
        return contadorDisparoAcertadoComputador;
    }

    public void setContadorDisparoAcertadoComputador(int contadorDisparoAcertadoComputador) {
        this.contadorDisparoAcertadoComputador = contadorDisparoAcertadoComputador;
    }

    // Aumenta la vida de un barco si tiene 5 disparos fallidos
    public void CuartoPoder(Mapa mapa, int posiciónY, int posiciónX, int orientación, Barco barco){
    
        if (orientación==1) {
            for (int i = 0; i < barco.tamañoDelBarco; i++) {
                if(mapa.tablero[posiciónY+i][posiciónX].getVidaDeParte()==0){
                    mapa.tablero[posiciónY+i][posiciónX].setVidaDeParte(mapa.tablero[posiciónY+i][posiciónX].getVidaDeParte()+1);
                    mapa.tablero[posiciónY+i][posiciónX].setHaSidoDisparado(false);
                }
            }
        }else{
            for (int i = 0; i < barco.tamañoDelBarco; i++) {
                if(mapa.tablero[posiciónY][posiciónX+i].getVidaDeParte()==0){
                    mapa.tablero[posiciónY][posiciónX+i].setVidaDeParte(mapa.tablero[posiciónY][posiciónX+i].getVidaDeParte()+1);
                    mapa.tablero[posiciónY][posiciónX+i].setHaSidoDisparado(false);
                }
            }
        }
    }
    
    
    //Muestra una parte del barco del contrincante cada 3 aciertos del jugador.
    public void QuintoPoder(Mapa mapa){
        boolean validar=true;
        for (int i = 0; i < mapa.tablero.length; i++) {
            for (int j = 0; j < mapa.tablero[i].length; j++) {
                if (!mapa.tablero[i][j].isPedazoMar()&&!mapa.tablero[i][j].isPedazoDañado()&&!mapa.tablero[i][j].isPedazoDestruido()) {
                    mapa.tablero[i][j].setPedazo('±');
                    validar = false;
                    break;
                }
            }
            if (!validar) {
                break;
            }
        }
    }

    public int getContadorDisparoAcertadoJugadorCampaña() {
        return contadorDisparoAcertadoJugadorCampaña;
    }

    public void setContadorDisparoAcertadoJugadorCampaña(int contadorDisparoAcertadoJugadorCampaña) {
        this.contadorDisparoAcertadoJugadorCampaña = contadorDisparoAcertadoJugadorCampaña;
    }
    
    public void SumaVidaCampaña(Mapa mapa){
        int contador=0;
        
        for (int i = 0; i < mapa.tablero.length; i++) {
            for (int j = 0; j < mapa.tablero[i].length; j++) {
                if (!mapa.tablero[i][j].isPedazoMar()&&!mapa.tablero[i][j].isPedazoDestruido()) {
                    mapa.tablero[i][j].setVidaDeParte(mapa.tablero[i][j].getVidaDeParte()+1);
                    contador++;
                    if (contador==this.contadorDisparoAcertadoJugadorCampaña) {
                        break;
                    }
                }
            }
            if (contador==this.contadorDisparoAcertadoJugadorCampaña) {
                break;
            }
        }
    }
    
    public void HacerMar(Mapa mapa){
        for (int i = 0; i < mapa.tablero.length; i++) {
            for (int j = 0; j < mapa.tablero[i].length; j++) {
                if (mapa.tablero[i][j].isPedazoMar()) {
                    mapa.tablero[i][j].setHaSidoDisparado(false);
                    mapa.tablero[i][j].setPedazo('=');
                }
                if (mapa.tablero[i][j].isPedazoDañado()&&mapa.tablero[i][j].getVidaDeParte()==this.vidaDelJugador) {
                    mapa.tablero[i][j].setPedazoDañado(false);
                    mapa.tablero[i][j].setPedazo('±');
                }
            }
        }
    }
  
}


