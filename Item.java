
/**
 * Esta clase representa los objetos que se pueden encontrar en el juego.
 * Todos los objetos estan definidos por una descripcion y un peso.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // El nombre del objeto;
    protected String nombreObj;
    // Descripcion del objeto
    protected String descripcionObj;
    // Peso del objeto
    protected float peso;
    // Indica si el objeto puede o no ser cogido por el jugador
    protected boolean puedeCogerse;

    /**
     * Constructor de items. Crea un objeto item con los parametros dados.
     * @param nombreObj El nombre del objeto
     * @param desc La descripcion del objeto
     * @param peso El peso del objeto
     * @paran puedeCogerse Si el objeto puede cogerse o no. Sera true si se puede coger.
     */
    public Item( String nombreObj, String desc, float peso, boolean puedeCogerse)
    {
        this.nombreObj = nombreObj;
        this.descripcionObj = desc;
        this.peso = peso;
        this.puedeCogerse = puedeCogerse;
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
    
    /**
     * Devuelve el nombre del objeto.
     * @return El nombre del objeto.
     */
    public String getNombreObj()
    {
        return nombreObj;
    }
    
    /**
     * Devuelve si el objeto puede o no cogerse
     * @return Si el objeto puede o no cogerse
     */
    public boolean puedeCogerse()
    {
        return puedeCogerse;
    }

    /**
     * Devuelve una descripción con toda la información del item.
     * @return La información del objeto.
     */
    public String getLongDescription()
    {
        String info = descripcionObj + "[" + nombreObj + "]" + "(" + peso + "kg)";
        return info;
    }

}
