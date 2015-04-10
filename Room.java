import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> salidas;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<String, Room>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param sureste La salida al sureste.
     * @param noroeste La salida al noroeste.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room sureste, Room noroeste) 
    {
        salidas.put("norte", north);
        salidas.put("este", east);
        salidas.put("sur", south);
        salidas.put("oeste", west);
        salidas.put("sureste", sureste);
        salidas.put("noroeste", noroeste);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Devuelve la habitación que se encuentra en la direccion indicada como parametro
     * @param dir Dirección por la que quiere salir
     * @return la habitación que se encuentra en esa dirección, o null si no hay ninguna
     */
    public Room getExit(String dir)
    {
        Room salida = null;
        salida = salidas.get(dir);
        return salida;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String descripcion = "";
        // Itera sobre el hashMap, si la habitacion no es null
        // guarda la key de la dirección
        Iterator it = salidas.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String, Room> pair = (Map.Entry)it.next();
            Room habitacion = pair.getValue();
            if(habitacion != null)
            {
                descripcion = descripcion + pair.getKey() + " ";
            }
        }
        return descripcion;
    }

}
