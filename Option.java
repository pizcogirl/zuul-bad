
/**
 * Comandos usables en el juego
 * 
 * @author Julia Zuara
 * @version (version number or date here)
 */
public enum Option {
    IR("ir", true, "El personaje se desplaza en la dirección indicada si es posible. Formato: ir [direccion]"), 

    TERMINAR("terminar", true, "Finaliza el juego"), 

    AYUDA ("ayuda", true, "Muestra la ayuda del juego"), 

    EXAMINAR ("examinar", true, "Examina la localización, mostrando la información disponible"), 

    COMER ("comer", false, "El personaje come"), 

    VOLVER("volver", true, "Vuelve a la localización anterior visitada, si es posible"), 

    COGER("coger", false, "Coge un objeto especificado de la localización. Formato: coger [nombre del objeto]"), 

    SOLTAR("soltar", false, "Suelta un objeto especificado que se encuentra en el inventario. Formato: soltar [nombre del objeto]"), 

    ESTADO("estado", false, "Muestra el estado del personaje, su vida, ataque y objetos en el inventario"), 

    DESCONOCIDO("desconocido", true, "Comando desconocido"),

    HABLAR("hablar", false, "Habla con el personaje amistoso que se encuentre en la localización"), 

    ATACAR("atacar", true, "Ataca al personaje agresivo que se encuentre en la localización"), 

    EQUIPAR("equipar", false, "Equipa el objeto especificado que se encuentre en el inventario del jugador. Formato: equipar [nombre del objeto]"),

    SAQUEAR("saquear", false, "Coge los objetos que se encuentren en un personaje agresivo derrotado"),

    USAR("usar", false, "Usa una poción que tenga en el inventario para recuperar resistencia. Formato: usar [nombre del objeto]"),

    BUSCAR("buscar", false, "Revisa la localización en la que se encuentra el personaje, descubriendo objetos o caminos ocultos");
    private String comando;
    // Indica si es seguro usarlo delante de un PNJ agresivo, o desencadenada un combate
    private boolean seguro;
    // Informacion del comando para la ayuda
    private String info;
    private Option(String comando, boolean seguro, String info)
    {this.comando = comando;
        this.seguro = seguro;
        this.info = info;}

    public String getComando()
    {return comando;}

    public boolean esSeguro()
    {return seguro;}

    public String getInfo()
    {return info;}
};

