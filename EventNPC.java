
/**
 * Esta clase representa los eventos que afecten a NPCs
 * 
 * @author Julia Zuara
 * @version (a version number or a date)
 */
public class EventNPC
{
    // Donde tiene efecto el evento. No tiene porque ser en la localizacion donde se encuentra
    private Room localizacionAfectada;
    // El NPC sobre el que tiene efecto el evento
    private NPC pnj;

    /**
     * Constructor de la clase eventos.
     * @param locAfectada La localizacion sobre la que tiene efecto el evento. No tiene porque coincidir con la localizacion 
     *          donde se encuentra el evento.
     * @param opcion El comando que activa este evento.
     * @param infoAdicional Información adicional necesaria para activar el evento, como el objeto que debe soltarse
     * @param pista Pista que se incluye en la descripcion de la zona mientras el evento no este activado
     * @param descripcion Descripcion del evento
     * @param pnj Si el evento implica la aparición de un PNJ, el PNJ, sino null
     * @param objeto Si el evento implica la aparicion de un objeto, el objeto, sino null
     * @param localizacion Si el evento implica la aparicion de una localizacion, la localizacion, sino null
     */
    public Event(Room locAfectada, Option opcion, String infoAdicional, String pista, String descripcion, NPC pnj, Item objeto, Room localizacion)
    {
        s
        // initialise instance variables
        this.localizacionAfectada = locAfectada;
        this.opcion = opcion;
        this.infoAdicional = infoAdicional;
        this.pista = pista;
        this.descripcion = descripcion;
        this.pnj = pnj;
        this.objeto = objeto;
        this.localizacion = localizacion;
        // El evento siempre se crea desactivado
        this.activado = false;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
