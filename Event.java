
/**
 * Esta clase representa los distintos eventos que pueden suceder en el juego.
 * Por ahora existen tres tipos de eventos, según sus consecuencias: 
 * - Aparición de objetos.
 * - Aparición de zonas nuevas.
 * - Aparición de nuevos PNJs.
 * 
 * 
 * @author Julia Zuara
 * @version (a version number or a date)
 */
public class Event
{
    // Indica el comando necesario para activa el evento
    protected Option opcion;
    // Informacion adicional necesaria para activar el evento, por ejemplo el objeto necesario
    protected String infoAdicional;
    // Pista que se incluye en la descripcion de la zona mientras el evento no este activado
    protected String pista;
    // Texto que se muestra al activar el evento
    protected String descripcion;
    // Recoge si el evento ha sido activado o no. Sera true si ha sido activado, false sino
    protected boolean activado;

    /**
     * Constructor de la clase eventos.
     * @param locAfectada La localizacion sobre la que tiene efecto el evento. No tiene porque coincidir con la localizacion 
     *          donde se encuentra el evento.
     * @param opcion El comando que activa este evento.
     * @param infoAdicional Información adicional necesaria para activar el evento, como el objeto que debe soltarse
     * @param pista Pista que se incluye en la descripcion de la zona mientras el evento no este activado
     * @param descripcion Descripcion del evento
     */
    public Event(Option opcion, String infoAdicional, String pista, String descripcion)
    {
        // initialise instance variables
        this.opcion = opcion;
        this.infoAdicional = infoAdicional;
        this.pista = pista;
        this.descripcion = descripcion;
        // El evento siempre se crea desactivado
        this.activado = false;
    }
    
    /**
     * Devuelve la pista del evento
     * @return La pista del evento
     */
    public String getPista()
    {
        return pista;
    }
    
    /**
     * Devuelve la opcion que activa el evento
     * @return la opcion que activa el evento
     */
    public Option getOpcion()
    {
        return opcion;
    }
    
    /**
     * Devuelve la información adicional para activar el evento
     * @return La información adicional para activar el evento
     */
    public String getInfoAdicional()
    {
        return infoAdicional;
    }
    
    /**
     * Devuelve si el evento ha sido ha activado
     * @return true si ya ha sido activado, false sino.
     */
    public boolean estaActivado()
    {
        return activado;
    }

    /**
     * Activa el efecto del evento
     */
    public void activar()
    {
    }

}
