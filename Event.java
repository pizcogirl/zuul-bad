
/**
 * Esta clase representa los distintos eventos que pueden suceder en el juego.
 * Por ahora existen tres tipos de eventos, seg�n sus consecuencias: 
 * - Aparici�n de objetos.
 * - Aparici�n de zonas nuevas.
 * - Aparici�n de nuevos PNJs.
 * 
 * 
 * @author Julia Zuara
 * @version (a version number or a date)
 */
public class Event
{
    // Pista que se incluye en la descripcion de la zona mientras el evento no este activado
    private String pista;
    // Texto que se muestra al activar el evento
    private String descripcion;
    // Recoge si el evento ha sido activado o no. Sera true si ha sido activado, false sino
    private boolean activado;
    // Si el evento implica la aparici�n de un nuevo PNJ, se guarda aqui
    private NPC pnj;
    // Si el evento implica la aparici�n de un objeto, se guarda aqui
    private Item objeto;
    // Si el evento implica la aparici�n de una nueva localizaci�n, se guarda aqui
    private Room localizacion;

    /**
     * Constructor de la clase eventos.
     * @param pista Pista que se incluye en la descripcion de la zona mientras el evento no este activado
     * @param descripcion Descripcion del evento
     * @param pnj Si el evento implica la aparici�n de un PNJ, el PNJ, sino null
     * @param objeto Si el evento implica la aparicion de un objeto, el objeto, sino null
     * @param localizacion Si el evento implica la aparicion de una localizacion, la localizacion, sino null
     */
    public Event(String pista, String descripcion, NPC pnj, Item objeto, Room localizacion)
    {
        // initialise instance variables
        this.pista = pista;
        this.descripcion = descripcion;
        this.pnj = pnj;
        this.objeto = objeto;
        this.localizacion = localizacion;
        // El evento siempre se crea desactivado
        this.activado = false;
    }

    /**
     * El evento a�ade un nuevo pnj si es la primera vez que se cumple la condicion del evento
     * @return el nuevo PNJ.
     */
    public NPC activaPNJ()
    {
        NPC nuevo = null;
        if(!activado)
        {
            activado = true;
            nuevo = pnj;
        }
        return nuevo;
    }

    /**
     * El evento a�ade un nuevo objeto si es la primera vez que se cumple la condicion del evento
     * @return el nuevo objeto.
     */
    public Item activaObjeto()
    {
        Item nuevo = null;
        if(!activado)
        {
            activado = true;
            nuevo = objeto;
        }
        return nuevo;
    }
}
