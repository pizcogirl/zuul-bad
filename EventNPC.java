
/**
 * Esta clase representa los eventos que afecten a PNJs.
 * Por ahora solo se pueden añadir PNJs nuevos.
 * 
 * @author Julia Zuara
 * @version (a version number or a date)
 */
public class EventNPC extends Event
{
    // Donde tiene efecto el evento. No tiene porque ser en la localizacion donde se encuentra
    private Room localizacionAfectada;
    // El NPC sobre el que tiene efecto el evento
    private NPC pnj;

    /**
     * Constructor de la clase eventos de PNJ.
     * @param locAfectada La localizacion sobre la que tiene efecto el evento. No tiene porque coincidir con la localizacion 
     *          donde se encuentra el evento.
     * @param opcion El comando que activa este evento.
     * @param infoAdicional Información adicional necesaria para activar el evento, como el objeto que debe soltarse
     * @param pista Pista que se incluye en la descripcion de la zona mientras el evento no este activado
     * @param descripcion Descripcion del evento
     * @param pnj El pnj afectado por el evento
     */
    public EventNPC(Room locAfectada, Option opcion, String infoAdicional, String pista, String descripcion, NPC pnj)
    {
        super(opcion, infoAdicional, pista, descripcion);
        // initialise instance variables
        this.localizacionAfectada = locAfectada;
        this.pnj = pnj;
    }

    /**
     * El evento añade un nuevo pnj.
     */
    public void activar()
    {
        if(!activado)
        {
            System.out.println(descripcion);
            activado = true;
            localizacionAfectada.addPNJ(pnj);
        }
    }
}
