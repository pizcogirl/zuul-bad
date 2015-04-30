import java.util.ArrayList;
import java.util.Random;

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
    private String descripcion;
    private int ataque;
    private int resistencia;
    private ArrayList<Item> inventario;

    /**
     * Constructor for objects of class NPC
     * @param agresivo Si es un PNJ agresivo o no
     * @param nombre El nombre del PNJ
     * @param conversacion La respuesta del PNJ al comando hablar
     * @param descripcion La descripcion del PNJ
     * @param ataque El ataque del PNJ
     * @param resistencia La resistencia del PNJ
     */
    public NPC(boolean agresivo, String nombre, String conversacion, String descripcion, int ataque, int resistencia)
    {
        this.agresivo = agresivo;
        this.nombre = nombre;
        this.conversacion = conversacion;
        this.descripcion = descripcion;
        this.ataque = ataque;
        this.resistencia = resistencia;
        inventario = new ArrayList<Item>();
    }

    /**
     * Habla con el jugador, muestra la respuesta que tenga el PNJ al comando hablar. Si el PNJ
     * tiene algun objeto que entregar al jugador, se lo entregara.
     * @return Si el PNJ tiene algun objeto que entregar la jugador, ese objeto, sino null
     */
    public Item hablar()
    {
        // Si no es agresivo le contesta
        Item obj = null;
        if(!agresivo)
        {
            System.out.println(conversacion);
            // Si tiene objetos en el inventario, los entrega
            if(inventario.size() > 0)
            {
                Random rand = new Random();
                String nombre = inventario.get(rand.nextInt(inventario.size())).getNombreObj();
                obj = search(nombre);
            }
        }
        return obj;
    }

    /**
     * Resta resistencia al PNJ
     * @param res La resistencia a restar
     */
    public void restaRes(int res)
    {
        resistencia -= res;
    }

    /**
     * Devuelve un objeto del inventario indicado por parametro
     * @param nombre El nombre del objeto a entregar
     * @return El objeto una vez encontrado
     */
    public Item search(String nombre)
    {
        boolean encontrado = false;
        Item objeto = null;
        int index = 0;
        // Busca el objeto en el inventario, si lo encuentra lo devuelve
        while((index < inventario.size()) && !(encontrado))
        {
            if(inventario.get(index).getNombreObj().equals(nombre))
            {
                objeto = inventario.get(index);
                encontrado = true;
            }
            index++;
        }
        return objeto;
    }

    /**
     * Elimina un objeto del inventario del PNJ pasado como parametro
     * @param obj El objeto a eliminar del inventario
     */
    public void remove(Item obj)
    {
        inventario.remove(obj);
    }

    /**
     * Devuelve la resistencia del PNJ
     * @return La resisencia del PNJ
     */
    public int getResistencia()
    {
        return resistencia;
    }

    /**
     * Devuelve el ataque del PNJ
     * @return El ataque del PNJ
     */
    public int getAtaque()
    {
        return ataque;
    }

    /**
     * A�ade un objeto al inventario del PNJ
     * @param obj El objeto a a�adir al inventario
     */
    public void addItem(Item obj)
    {
        inventario.add(obj);
    }

    /**
     * Devuelve el nombre del PNJ
     * @return El nombre del PNJ
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * Devuelve si el PNJ es agresivo o no
     * @return True si es agresivo, false sino
     */
    public boolean isAgresivo()
    {
        return agresivo;
    }
    
    /**
     * Devuelve un String con la informaci�n del PNJ
     * @return un String con la informaci�n del PNJ
     */
    public String description()
    {
        String desc = "";
        if(!agresivo)
        {
            desc = nombre + ", " +descripcion + " (amistoso)";
        }
        else
        {
            desc = nombre + ", " +descripcion + " (agresivo)";
        }
        return desc;
    }
}
