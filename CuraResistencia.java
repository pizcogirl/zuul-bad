
/**
 * Esta clase representa a los objetos que curan la resistencia del jugador al usarse
 * 
 * @author Julia Zuara
 * @version (a version number or a date)
 */
public class CuraResistencia extends Usable
{

    /**
     * Constructor de items usables. Crea un objeto item con los parametros dados.
     * @param nombreObj El nombre del objeto
     * @param desc La descripcion del objeto
     * @param peso El peso del objeto
     * @paran puedeCogerse Si el objeto puede cogerse o no. Sera true si se puede coger.
     * @param efecto El efecto númerico del objeto al usarse.
     */
    public CuraResistencia(String nombreObj, String desc, float peso, boolean puedeCogerse, int efecto)
    {
        super(nombreObj, desc, peso, puedeCogerse, efecto);
    }

}
