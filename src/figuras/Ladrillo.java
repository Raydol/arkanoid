
package figuras;

import figuras.base.Eliminable;
import figuras.base.Sprite;


public class Ladrillo extends Sprite implements Eliminable{
    private boolean esEliminado;

    public Ladrillo(String[] filenames, int x, int y) {
        super(filenames, x, y);
        esEliminado = false;
    }
        

    @Override
    public boolean estaEliminado() {
        return esEliminado;
    }

    public boolean isEsEliminado() {
        return esEliminado;
    }

    public void setEsEliminado(boolean esEliminado) {
        this.esEliminado = esEliminado;
    }
    
    
    
}
