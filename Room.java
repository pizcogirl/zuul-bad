import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

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
    private boolean abierta; // True si la localizacion es accesible, false sino
    private String description;
    private HashMap<String, Room> salidas;
    private ArrayList<Item> objetos;
    private NPC pnj;
    private ArrayList<Event> eventos;

    /**
     * Crea una localizacion descrita en la descripcion. Se indica si la localizacion
     * es accesible o no inicialmente. Inicialmente no tiene salidas.
     * @param abierta Indica si la localizacion esta abierta o no.
     * @param description La descripcion de la localizacion.
     */
    public Room(boolean abierta, String description) 
    {
        this.abierta = abierta;
        this.description = description;
        salidas = new HashMap<String, Room>();
        objetos = new ArrayList<Item>();
        pnj = null;
        eventos = new ArrayList<Event>();
    }

    /**
     * Añade una salida a la habitacion. Cada salida debe componerse de una salida y una habitacion.
     * @param dir Nombre de la salida.
     * @param room Habitación que se encuentra tras esa salida.
     */
    public void setExit(String dir, Room room)
    {
        salidas.put(dir, room);
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
            if(habitacion != null && habitacion.estaAbierta())
            {
                descripcion = descripcion + pair.getKey() + " ";
            }
        }
        return descripcion;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String descr = "";
        descr = "\nEstas en " + description + "\nSalidas: " + getExitString();
        // Si hay algun objeto en la habitación, lo incluye en la descripción
        if (objetos.size() > 0)
        {
            descr += "\nVes los siguientes objetos:";
            for(int i = 0; i < objetos.size(); i++)
            {
                descr += "\n- " + objetos.get(i).getLongDescription();
            }
        }
        else
        {
            descr += "\nNo ves nada aqui";
        }
        if(pnj != null)
        {
            descr +="\nTe encuentras aqui con: \n" + pnj.description();
        }
        return descr;
    }

    /**
     * Añade un objeto a la localización
     * @param objeto Objeto a añadir a la localización
     */
    public void addItem(Item objeto)
    {
        objetos.add(objeto);
    }

    /**
     * Busca un objeto en la localización. Si existe lo devuelve,
     * sino devuelve null.
     * @return El objeto si contiene, null sino.
     */
    public Item search(String nombre)
    {
        boolean find = false;
        int index = 0;
        Item objeto = null;
        // Busca el objeto en la localización
        while((index < objetos.size()) & (!find))
        {
            if(nombre.equals(objetos.get(index).getNombreObj()))
            {
                objeto = objetos.get(index);
                find = true;
            }
            index++;
        }
        return objeto;
    }

    /**
     * Elimina un objeto de la localización
     */
    public void remove(Item objeto)
    {
        objetos.remove(objeto);
    }

    /**
     * Introduce un PNJ a la localización. Si ya existe uno, o sobreescribe
     * @param pnj El pnj a introducir en esta localización
     */
    public void addPNJ(NPC pnj)
    {
        this.pnj = pnj;
    }

    /**
     * Devuelve el PNJ que se encuentra en esa localización
     * @return el PNJ que se encuentra en la licalización. Si no hay ninguno
     *          devolvera null.
     */
    public NPC getPNJ()
    {
        return pnj;
    }

    /**
     * Indica si la localizacion es accesible o no.
     * @return True si es accesible, false sino.
     */
    public boolean estaAbierta()
    {
        return abierta;
    }

    /**
     * Abre la localización, haciendola accesible si estaba cerrada.
     * Si estaba abierta no cambia nada.
     */
    public void abrir()
    {
        abierta = true;
    }

    /**
     * Cierra la localización, haciendola inaccesible si estaba abierta.
     * Si estaba cerrada no cambia nada.
     */
    public void cerrar()
    {
        abierta = false;
    }

    /**
     * Añade un evento a la localizacion
     * @param evento El evento a añadir
     */
    public void addEvento(Event evento)
    {
        eventos.add(evento);
    }
    
    /**
     * Devuelve la coleccion de eventos que hay en la localizacion.
     * @return La coleccion de eventos de la localizacion. Si no hay eventos, devuelve null.
     */
    public ArrayList<Event> getEventos()
    {
        if(eventos.size() > 0)
        {
            return eventos;
        }
        else
        {
            return null;
        }
    }
    
}
