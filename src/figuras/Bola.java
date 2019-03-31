/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import figuras.base.Animable;
import figuras.base.Sprite;

/**
 *
 * @author david
 */
public class Bola extends Sprite implements Animable{
    private int incrX;
    private int incrY;
    public Bola(String[] filename) {
        super(filename);
        setX(250);
        setY(545);
        incrX = -6;
        incrY = -6;
    }

    @Override
    public void mover() {
        setX(getX() + incrX);
        setY(getY() + incrY);
        
        if (getX() < 2 || getX() > 485) {
            incrX = -incrX;
        }
        
        if (getY() < 26) {
            incrY = -incrY;
        }
        
    }

    public int getIncrX() {
        return incrX;
    }

    public void setIncrX(int incrX) {
        this.incrX = incrX;
    }

    public int getIncrY() {
        return incrY;
    }

    public void setIncrY(int incrY) {
        this.incrY = incrY;
    }
    
    
    
    
    
}
