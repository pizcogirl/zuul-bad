
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
    // Donde tiene efecto el evento. No tiene porque ser en la localizacion donde se encuentra
    private Room localizacionAfectada;
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
    // Si el evento implica la aparici�n de un objeto, se guarda aqui
    private Item objeto;
    // Si el evento implica la aparici�n de una nueva localizaci�n, se guarda aqui
    private Room localizacion;

    /**
     * Constructor de la clase eventos.
     * @param locAfectada La localizacion sobre la que tiene efecto el evento. No tiene porque coincidir con la localizacion 
     *          donde se encuentra el evento.
     * @param opcion El comando que activa este evento.
     * @param infoAdicional Informaci�n adicional necesaria para activar el evento, como el objeto que debe soltarse
     * @param pista Pista que se incluye en la descripcion de la zona mientras el evento no este activado
     * @param descripcion Descripcion del evento
     * @param pnj Si el evento implica la aparici�n de un PNJ, el PNJ, sino null
     * @param objeto Si el evento implica la aparicion de un objeto, el objeto, sino null
     * @param localizacion Si el evento implica la aparicion de una localizacion, la localizacion, sino null
     */
    public Event(Room locAfectada, Option opcion, String infoAdicional, String pista, String descripcion, NPC pnj, Item objeto, Room localizacion)
    {
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
     * Devuelve la informaci�n adicional para activar el evento
     * @return La informaci�n adicional para activar el evento
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
        if(!activado)
        {
            System.out.println(descripcion);
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
     * El evento a�ade un nuevo pnj.
     */
    private void activaPNJ()
    {
        localizacionAfectada.addPNJ(pnj);
    }

    /**
     * El evento a�ade un nuevo objeto.
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
        localizacion.abrir();
    }
}
