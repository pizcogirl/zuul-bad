
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
    private String nombreObj;
    // Descripcion del objeto
    private String descripcionObj;
    // Peso del objeto
    private float peso;
    // Indica si el objeto puede o no ser cogido por el jugador
    private boolean puedeCogerse;
    // Ataque que el objeto proporciona al jugador al equiparlo
    private int ataque;

    /**
     * Constructor de items. Crea un objeto item con los parametros dados.
     * @param nombreObj El nombre del objeto
     * @param desc La descripcion del objeto
     * @param peso El peso del objeto
     * @paran puedeCogerse Si el objeto puede cogerse o no. Sera true si se puede coger.
     * @param ataque El ataque que proporciona el objeto al jugador al equiparlo.
     */
    public Item( String nombreObj, String desc, float peso, boolean puedeCogerse, int ataque)
    {
        this.nombreObj = nombreObj;
        this.descripcionObj = desc;
        this.peso = peso;
        this.puedeCogerse = puedeCogerse;
        this.ataque = ataque;
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
     * Devuelve el ataque del objeto
     * @return El ataque del objeto
     */
    public int getAtaque()
    {
        return ataque;
    }

    /**
     * Devuelve una descripción con toda la información del item.
     * @return La información del objeto.
     */
    public String getLongDescription()
    {
        String info = descripcionObj + " que pesa " + peso;
        return info;
    }

}
