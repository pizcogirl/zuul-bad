
/**
 * Esta clase representa los eventos que afecten a objetos.
 * Por ahora solo se pueden añadir nuevos objetos.
 * 
 * @author Julia Zuara
 * @version (a version number or a date)
 */
public class EventItem extends Event
{
    // Donde tiene efecto el evento. No tiene porque ser en la localizacion donde se encuentra
    private Room localizacionAfectada;
    // El objeto sobre el que tiene efecto el evento
    private Item objeto;

    /**
     * Constructor de la clase eventos de objetos.
     * @param locAfectada La localizacion sobre la que tiene efecto el evento. No tiene porque coincidir con la localizacion 
     *          donde se encuentra el evento.
     * @param opcion El comando que activa este evento.
     * @param infoAdicional Información adicional necesaria para activar el evento, como el objeto que debe soltarse
     * @param pista Pista que se incluye en la descripcion de la zona mientras el evento no este activado
     * @param descripcion Descripcion del evento
     * @param objeto El objeto afectado por el evento
     */
    public EventItem(Room locAfectada, Option opcion, String infoAdicional, String pista, String descripcion, Item objeto)
    {
        super(opcion, infoAdicional, pista, descripcion);
        // initialise instance variables
        this.localizacionAfectada = locAfectada;
        this.objeto = objeto;
    }

    /**
     * El evento añade un nuevo objeto.
     */
    public void activar()
    {
        if(!activado)
        {
            activado = true;
            localizacionAfectada.addItem(objeto);
        }
    }

}
