
/**
 * Esta clase representa los eventos que afecten a localizaciones. 
 * Por ahora solo existen eventos que añaden localizaciones nuevas.
 * 
 * @author Julia Zuara
 * @version (a version number or a date)
 */
public class EventRoom extends Event
{
    // La localización sobre el que tiene efecto el evento
    private Room localizacion;

    /**
     * Constructor de la clase eventos de localización.
     * @param locAfectada La localizacion sobre la que tiene efecto el evento. No tiene porque coincidir con la localizacion 
     *          donde se encuentra el evento.
     * @param opcion El comando que activa este evento.
     * @param infoAdicional Información adicional necesaria para activar el evento, como el objeto que debe soltarse
     * @param pista Pista que se incluye en la descripcion de la zona mientras el evento no este activado
     * @param descripcion Descripcion del evento
     * @param localizacion La localizacion afectada por el evento
     */
    public EventRoom( Option opcion, String infoAdicional, String pista, String descripcion, Room localizacion)
    {
        super(opcion, infoAdicional, pista, descripcion);
        // initialise instance variables
        this.localizacion = localizacion;
    }

    /**
     * El evento abre el acceso a una localizacion.
     */
    public void activar()
    {
        if(!activado)
        {
            activado = true;
            localizacion.abrir();
        }
    }

}