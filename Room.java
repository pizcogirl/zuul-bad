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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room suresteExit;
    private Room noroesteExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
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
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(sureste != null)
            suresteExit = sureste;
        if(noroeste != null)
            noroesteExit = noroeste;
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
        if(dir.equals("norte"))
            salida = northExit;
        if(dir.equals("este"))
            salida = eastExit;
        if(dir.equals("sur"))
            salida = southExit;
        if(dir.equals("oeste"))
            salida = westExit;
        if(dir.equals("sureste"))
            salida = suresteExit;
        if(dir.equals("noroeste"))
            salida = noroesteExit;
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
        if(northExit != null)
            descripcion = descripcion + " norte";
        if(eastExit != null)
            descripcion = descripcion + " este";
        if(southExit != null)
            descripcion = descripcion + " sur";
        if(westExit != null)
            descripcion = descripcion + " oeste";
        if(suresteExit != null)
            descripcion = descripcion + " sureste";
        if(noroesteExit != null)
            descripcion = descripcion + " noroeste";
        return descripcion;
    }

}
