/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import audio.StdSound;
import figuras.Bola;
import figuras.Breakout;
import figuras.Fondo;
import figuras.Ladrillo;
import figuras.Marcador;
import figuras.base.Animable;
import figuras.base.Dibujable;
import figuras.base.Eliminable;
import figuras.base.Sprite;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author victor
 */
public class GameLogic {
    // CONSTANTES DEL JUEGO
    public static final int NUM_VIDAS = 3;
    // añade las constantes que estimes oportuno.
    
    private int vidas;
    private int puntos;
    private String cadena;
    private int x;
    private int y;
    
    /** Lista de todos los objetos del juego, para dibujar, automover y eliminar */
    private List<Dibujable> listaObjetosDibujables;
   
    // --- Los objetos de los que quieras tener una referencia
    Breakout breakout;
    Bola bola;
    
    // TODO Añadir la pelota, una colección con los ladrillos, etc..
    

    public GameLogic() {
        listaObjetosDibujables = new LinkedList<>();
        this.x = 70;
        this.y = 60;
        empezar();
    }
    /**
     * Invocado en cada fotograma desde el frame
     * @param g 
     */
    public void dibujarYActualizarTodo(Graphics g) { 
        int contadorTocados = 0;
        Iterator<Dibujable> iter = listaObjetosDibujables.iterator();
        while (true) {
            if (!iter.hasNext()) { // Si no hay siguiente, salir del bucle
                break;
            }
            Dibujable objetoDelJuego = iter.next(); // Acceder al objeto
            if (objetoDelJuego instanceof Eliminable) { // Si está eliminado lo quitamos
                if (((Eliminable) objetoDelJuego).estaEliminado()) {
                    iter.remove();
                    continue;
                }
            }
            objetoDelJuego.dibujar(g); // lo dibujamos
            if (objetoDelJuego instanceof Animable) { // Y si está auto-animado, lo movemos
                ((Animable) objetoDelJuego).mover();
            }
            
            if (objetoDelJuego instanceof Ladrillo) {
                if (bola.colisonaCon((Sprite) objetoDelJuego)) {
                    ((Ladrillo) objetoDelJuego).setEsEliminado(true);
                    
                    if (contadorTocados == 0) {
                        bola.setIncrY(6);
                    } else if (contadorTocados % 2 == 0) {
                        bola.setIncrY(-6);
                    } else {
                        bola.setIncrY(6);
                    }
                    
                    contadorTocados++;
                    
                }
                contadorTocados = 0;
            }
            
                       
            if (bola.colisonaCon(breakout)) {
                bola.setIncrY(-6);
            }
            
            
            
            
            
            
            
            
        } 
    }
    
    /**
     * Invocado en cada fotograma desde el frame
     * @param teclas 
     */
    public void gestionarTeclas(Set<Integer> teclas) {
        if (breakout.getX() > 4) {
            if (teclas.contains(KeyEvent.VK_LEFT)) {
                breakout.moverIzquierda();
            }
        }
        
        if (breakout.getX() < 425) {
            if (teclas.contains(KeyEvent.VK_RIGHT)) {
                breakout.moverDerecha();
            }
        }
        
    }

    public List<Dibujable> getListaObjetos() {
        return listaObjetosDibujables;
    }
    
    public void empezar() {
        // TO-DO Inicia el juego!
        listaObjetosDibujables.clear();
        inicializarNivel(0);
    }
    
    public void inicializarNivel(int nivel) {
        // TO-DO
        if (nivel == 0) {
            StdSound.playMidi(("assets/audio/start_level.mid"));
            vidas = NUM_VIDAS;
            puntos = 0;
            // TODO
            listaObjetosDibujables.add(new Fondo("assets/img/fondo00.jpg"));
            listaObjetosDibujables.add(new Marcador(this)); // inyección de dependencias
            breakout = new Breakout(this);
            bola = new Bola(new String[] {
           "assets/img/ball.png" 
            }); // inyección de dependencias
            listaObjetosDibujables.add(breakout);
            listaObjetosDibujables.add(bola);
            
            for (int i = 0; i < MapaNivel.mapa[0].length; i++) {
                cadena = MapaNivel.mapa[0][i];
                for (int j = 0; j < cadena.length(); j++) {
                    if (cadena.charAt(j) == 'b') {
                        listaObjetosDibujables.add(new Ladrillo(new String[] {
                            "assets/img/brick_blue.png"
                        }, this.x, this.y));
                    } else if (cadena.charAt(j) == 'c') {
                        listaObjetosDibujables.add(new Ladrillo(new String[] {
                            "assets/img/brick_cyan.png"
                        }, this.x, this.y));
                    } else if (cadena.charAt(j) == 'g') {
                        listaObjetosDibujables.add(new Ladrillo(new String[] {
                            "assets/img/brick_green.png"
                        }, this.x, this.y));
                    } else if (cadena.charAt(j) == 'r') {
                        listaObjetosDibujables.add(new Ladrillo(new String[] {
                            "assets/img/brick_red.png"
                        }, this.x, this.y));
                    } else if (cadena.charAt(j) == 'm') {
                        listaObjetosDibujables.add(new Ladrillo(new String[] {
                            "assets/img/brick_magenta.png"
                        }, this.x, this.y));
                    } else if (cadena.charAt(j) == 'o') {
                        listaObjetosDibujables.add(new Ladrillo(new String[] {
                            "assets/img/brick_orange.png"
                        }, this.x, this.y));
                    } else if (cadena.charAt(j) == 'h') {
                        listaObjetosDibujables.add(new Ladrillo(new String[] {
                            "assets/img/hard.png"
                        }, this.x, this.y));
                    } else if (cadena.charAt(j) == 'y') {
                        listaObjetosDibujables.add(new Ladrillo(new String[] {
                            "assets/img/brick_yellow.png"
                        }, this.x, this.y));
                    }
                    x += 44;
                }
                y += 22;
                x = 70;
            }
            // TODO 
        }
        
        // TODO 
        
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }
    
    
    
}
