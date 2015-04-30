
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option {
    IR("ir"), TERMINAR("terminar"), AYUDA ("ayuda"), 
    EXAMINAR ("examinar"), COMER ("comer"), VOLVER("volver"), 
    COGER("coger"), SOLTAR("soltar"), OBJETOS("objetos"), DESCONOCIDO("desconocido"), HABLAR("hablar");
    private String command;
    private Option(String command)
    {this.command = command;}
    public String getCommand()
    {return command;}
 };
    
