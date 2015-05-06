
/**
 * Representa a los objetos usables en el juego. Los objetos usables tienen diferentes efectos
 * 
 * @author Julia Zuara 
 * @version (a version number or a date)
 */
public class Usable extends Item
{
    // Resistencia que otorga el objeto al usarse, si vale 0 no es usable
    protected int efecto;

    /**
     * Constructor de items usables. Crea un objeto item con los parametros dados.
     * @param nombreObj El nombre del objeto
     * @param desc La descripcion del objeto
     * @param peso El peso del objeto
     * @paran puedeCogerse Si el objeto puede cogerse o no. Sera true si se puede coger.
     * @param efecto El efecto númerico del objeto al usarse.
     */
    public Usable( String nombreObj, String desc, float peso, boolean puedeCogerse, int efecto)
    {
        super(nombreObj, desc, peso, puedeCogerse);
        this.efecto = efecto;
    }

    /**
     * Devuelve el efecto númerico del objeto al usarse
     * @return El efecto númerico del objeto al usarse
     */
    public int getEfecto()
    {
        return efecto;
    }

}

