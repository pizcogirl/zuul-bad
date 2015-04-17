import java.util.Stack;
import java.util.ArrayList;

/**
 * Esta clase representa al jugador del juego. Realiza las acciones
 * relacionadas con el (examinar, comer, etc).
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // La habitación en la que se encuentra en ese momento el jugador
    private Room currentRoom;
    // Pila de habitaciones que ha visitado
    private Stack<Room> previusRooms;
    // Objetos que tiene el jugador
    private ArrayList<Item> inventory;
    // El peso maximo que acepta el jugador
    private float maxCarry;
    // El peso que lleva ahora el jugador
    private float currentCarry;

    /**
     * Constructor del jugador
     */
    public Player(float maxCarry)
    {
        currentRoom = null;
        previusRooms = new Stack<Room>();
        inventory = new ArrayList<Item>();
        this.maxCarry = maxCarry;
        // Al inicio el peso del jugador es 0.
        currentCarry = 0;
    }

    /**
     * Coloca al jugador en una habitación
     * @param room La habitación en la que se encuentra el jugador.
     */
    public void setRoom(Room room)
    {
        if(currentRoom != null)
        {
            previusRooms.push(currentRoom);
        }
        this.currentRoom = room;
    }

    /**
     * El jugador vuelve a la habitación anterior
     */
    public void goBack()
    {
        if(!previusRooms.empty())
        {
            currentRoom = previusRooms.pop();
            printLocationInfo();
            System.out.println();
        }
        else
        {
            System.out.println("No existen localizaciones a las que volver");
        }
    }

    /**
     * El jugador examina la localización en la que se encuentra
     */
    public void look()
    {
        printLocationInfo();
    }

    /**
     * El jugador come
     */
    public void eat()
    {
        System.out.println("Acabas de comer y ya no estas hambriento");
    }

    /**
     * El jugador intenta moverse a otra otra habitación. Si existe una habitación en esa
     * dirección lo hara, sino imprimira un mensaje avisando de que no puede ir en esa dirección.
     * @param direccion La direccion en la que intenta moverse
     */
    public void goRoom(String direccion)
    {
        Room nextRoom = currentRoom.getExit(direccion);

        if (nextRoom == null) {
            System.out.println("No puedes continuar por ahí");
        }
        else {
            setRoom(nextRoom);
            printLocationInfo();
            System.out.println();
        }
    }

    /**
     * Intenta añadir un objeto al inventario del jugador. Si el objeto existe en la habitacion
     * y puede cogerlo, lo añadira a su inventario. Sino mostrara un mensaje indicando el problema
     * @param El nombre del objeto que quiere añadir
     */
    public void addItem(String objeto)
    {
        // busca el objeto en la habitacion
        Item tempObj = currentRoom.search(objeto);
        if(tempObj != null)
        {
            if((currentCarry + tempObj.getPeso()) < maxCarry)
            {
                inventory.add(tempObj);
                currentRoom.remove(tempObj);
                System.out.println("Coges " + tempObj.getLongDescription());
                currentCarry += tempObj.getPeso();
            }
            else
            {
                System.out.println("Llevas demasiado peso y no puedes coger ese objeto");
            }
        }
        else
        {
            System.out.println("No encuentras ese objeto en la localización actual");
        }
    }

    /**
     * Intenta soltar un objeto al inventario del jugador. Si el objeto esta en el inventario del jugador
     * lo soltara, sino mostrara un mensaje.
     * @param El nombre del objeto que quiere añadir
     */
    public void dropItem(String objeto)
    {
        // busca el objeto en el inventario
        Item tempObj = search(objeto);
        if(tempObj != null)
        {
            inventory.remove(tempObj);
            currentRoom.addItem(tempObj);
            System.out.println("Sueltas " + tempObj.getLongDescription());
            currentCarry -= tempObj.getPeso();
        }
        else
        {
            System.out.println("No tienes ese objeto en tu inventario");
        }
    }

    /**
     * Busca un objeto en el inventario del jugador
     * @param nombre El nombre del objeto a buscar
     * @return El objeto si lo encuentra, sino devolvera null
     */
    private Item search(String nombre)
    {
        boolean find = false;
        int index = 0;
        Item objeto = null;
        // Busca el objeto en el inventario
        while((index < inventory.size()) & (!find))
        {
            if(nombre.equals(inventory.get(index).getNombreObj()))
            {
                objeto = inventory.get(index);
                find = true;
            }
            index++;
        }
        return objeto;
    }

    /**
     * Imprime la información de la localización en la que se encuentra el jugador
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }
}
