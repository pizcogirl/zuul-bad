
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
    // Donde tiene efecto el evento. No tiene porque ser en la localizacion donde se encuentra
    private Room localizacionAfectada;
    // Indica el comando necesario para activa el evento
    private Option opcion;
    // Pista que se incluye en la descripcion de la zona mientras el evento no este activado
    private String pista;
    // Texto que se muestra al activar el evento
    private String descripcion;
    // Recoge si el evento ha sido activado o no. Sera true si ha sido activado, false sino
    private boolean activado;
    // Si el evento implica la aparición de un nuevo PNJ, se guarda aqui
    private NPC pnj;
    // Si el evento implica la aparición de un objeto, se guarda aqui
    private Item objeto;
    // Si el evento implica la aparición de una nueva localización, se guarda aqui
    private Room localizacion;

    /**
     * Constructor de la clase eventos.
     * @param locAfectada La localizacion sobre la que tiene efecto el evento. No tiene porque coincidir con la localizacion 
     *          donde se encuentra el evento.
     * @param opcion El comando que activa este evento.
     * @param pista Pista que se incluye en la descripcion de la zona mientras el evento no este activado
     * @param descripcion Descripcion del evento
     * @param pnj Si el evento implica la aparición de un PNJ, el PNJ, sino null
     * @param objeto Si el evento implica la aparicion de un objeto, el objeto, sino null
     * @param localizacion Si el evento implica la aparicion de una localizacion, la localizacion, sino null
     */
    public Event(Room locAfectada, Option opcion, String pista, String descripcion, NPC pnj, Item objeto, Room localizacion)
    {
        // initialise instance variables
        this.localizacionAfectada = locAfectada;
        this.opcion = opcion;
        this.pista = pista;
        this.descripcion = descripcion;
        this.pnj = pnj;
        this.objeto = objeto;
        this.localizacion = localizacion;
        // El evento siempre se crea desactivado
        this.activado = false;
    }

    /**
     * Activa el efecto del evento
     */
    public void activar()
    {
        if(!activado)
        {
            activado = true;
            if(pnj != null)
            {
                activaPNJ();
            }
            else if(objeto != null)
            {
                activaObjeto();
            }
            else if (localizacion != null)
            {
                activaLocalizacion();
            }
        }
    }

    /**
     * El evento añade un nuevo pnj.
     */
    private void activaPNJ()
    {
        localizacionAfectada.addPNJ(pnj);
    }

    /**
     * El evento añade un nuevo objeto.
     */
    private void activaObjeto()
    {
        localizacionAfectada.addItem(objeto);
    }

    /**
     * El evento abre el acceso a una localizacion.
     */
    private void activaLocalizacion()
    {
        localizacionAfectada.abrir();
    }
}
