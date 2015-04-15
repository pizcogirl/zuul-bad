
/**
 * Esta clase representa los objetos que se pueden encontrar en el juego.
 * Todos los objetos estan definidos por una descripcion y un peso.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // Descripcion del objeto
    private String descripcionObj;
    // Peso del objeto
    private float peso;

    /**
     * Constructor de items. Crea un objeto item con una descripción y un peso dados.
     */
    public Item(String desc, float peso)
    {
        this.descripcionObj = desc;
        this.peso = peso;
    }
    
    /**
     * Devuelve la descripción del objeto.
     * @return La descripción del objeto.
     */
    public String getDescripcionObj()
    {
        return descripcionObj;
    }
    
    /**
     * Devuelve el peso del objeto.
     * @return El peso del objeto.
     */
    public float getPeso()
    {
        return peso;
    }

}
