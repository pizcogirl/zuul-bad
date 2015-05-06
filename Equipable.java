
/**
 * Esta clase representa a los objetos equipables en el juego
 * 
 * @author Julia Zuara
 * @version (a version number or a date)
 */
public class Equipable extends Item
{

     /**
     * Constructor de items equipables. Crea un objeto item con los parametros dados.
     * @param nombreObj El nombre del objeto
     * @param desc La descripcion del objeto
     * @param peso El peso del objeto
     * @paran puedeCogerse Si el objeto puede cogerse o no. Sera true si se puede coger.
     */
    public Equipable( String nombreObj, String desc, float peso, boolean puedeCogerse)
    {
        super(nombreObj, desc, peso, puedeCogerse);
    }

}
