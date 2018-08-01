package Model;

public abstract class Tool implements Selectable {

    public Tool() { }
    
    public abstract boolean use(Tile t);
    
    public abstract String getDescription();
}
