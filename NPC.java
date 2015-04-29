import java.util.ArrayList;

/**
 * Esta clase representa a los personajes no jugadores (PNJ o NPC en ingles) que se pueden
 * encontrar en el juego.
 * 
 * @author Julia
 * @version (a version number or a date)
 */
public class NPC
{
    private boolean agresivo;
    private String nombre;
    private String conversacion;
    private int ataque;
    private int resistencia;
    private ArrayList<Item> inventario;

    /**
     * Constructor for objects of class NPC
     * @param agresivo Si es un PNJ agresivo o no
     * @param nombre El nombre del PNJ
     * @param conversacion La respuesta del PNJ al comando hablar
     * @param ataque El ataque del PNJ
     * @param resistencia La resistencia del PNJ
     */
    public NPC(boolean agresivo, String nombre, String conversacion, int ataque, int resistencia)
    {
        this.agresivo = agresivo;
        this.nombre = nombre;
        this.conversacion = conversacion;
        this.ataque = ataque;
        this.resistencia = resistencia;
        inventario = new ArrayList<Item>();
    }

    /**
     * Habla con el jugador, muestra la respuesta que tenga el PNJ al comando hablar
     */
    public void hablar()
    {
        System.out.println(conversacion);
    }
    
    /**
     * Resta resistencia al PNJ
     * @param res La resistencia a restar
     */
    public void restaRes(int res)
    {
        resistencia -= res;
    }
}
