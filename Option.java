
/**
 * Comandos usables en el juego
 * 
 * @author Julia Zuara
 * @version (version number or date here)
 */
public enum Option {
    IR("ir", true), 

    TERMINAR("terminar", true), 

    AYUDA ("ayuda", true), 

    EXAMINAR ("examinar", true), 

    COMER ("comer", false), 

    VOLVER("volver", true), 

    COGER("coger", false), 

    SOLTAR("soltar", false), 

    OBJETOS("objetos", false), 

    DESCONOCIDO("desconocido", true),

    HABLAR("hablar", false), 

    ATACAR("atacar", true), 

    EQUIPAR("equipar", false);
    private String comando;
    private boolean seguro;
    private Option(String comando, boolean seguro)
    {this.comando = comando;
        this.seguro = seguro;}

    public String getComando()
    {return comando;}

    public boolean esSeguro()
    {return seguro;}
};

