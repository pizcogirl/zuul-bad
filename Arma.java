
/**
 * Esta clase representa un arma equipable en el juego.
 * 
 * @author Julia Zuara
 * @version (a version number or a date)
 */
public class Arma extends Equipable
{
    private int ataque;

    /**
     * Constructor de items de tipo arma. Crea un objeto item con los parametros dados.
     * @param nombreObj El nombre del objeto
     * @param desc La descripcion del objeto
     * @param peso El peso del objeto
     * @paran puedeCogerse Si el objeto puede cogerse o no. Sera true si se puede coger.
     * @param ataque El ataque que proporciona el objeto al jugador al equiparlo.
     */
    public Arma( String nombreObj, String desc, float peso, boolean puedeCogerse, int ataque)
    {
        super(nombreObj, desc, peso, puedeCogerse);
        this.ataque = ataque;
    }

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
        String info = descripcionObj + "[" + nombreObj + "]" + "(" + peso + "kg)" + " Ataque: " + ataque;;
        return info;
    }
}
