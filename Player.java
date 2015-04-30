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
    // La resistencia del jugador
    private int resistencia;
    // El ataque del jugador
    private int ataque;

    /**
     * Constructor del jugador
     */
    public Player(float maxCarry, int resistencia, int ataque)
    {
        currentRoom = null;
        previusRooms = new Stack<Room>();
        inventory = new ArrayList<Item>();
        this.maxCarry = maxCarry;
        // Al inicio el peso del jugador es 0.
        currentCarry = 0;
        this.resistencia = resistencia;
        this.ataque = ataque;
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
     * El jugador busca un objeto en la localización. Si lo encuentra lo intenta añadir al inventario.
     * Si lo añade al inventario, desaparece de la localización.
     * @param El nombre del objeto a buscar e intentar añadir al inventario.
     */
    public void take(String nombre)
    {
        // Busca el objeto en la localizacion
        Item obj = currentRoom.search(nombre);
        if(obj != null)
        {
            addItem(obj);
        }
        else
        {
            System.out.println("No encuentras ese objeto en esta localización");
        }
    }

    /**
     * Intenta añadir un objeto al inventario del jugador. Si el objeto existe 
     * y puede cogerlo, lo añadira a su inventario. Sino mostrara un mensaje indicando el problema
     * @param El nombre del objeto que quiere añadir
     */
    public boolean addItem(Item objeto)
    {
        boolean aniadido = false;
        if(objeto != null)
        {
            // Comprueba si el objeto se puede coger
            if(objeto.getPuedeCogerse())
            {
                if((currentCarry + objeto.getPeso()) < maxCarry)
                {
                    inventory.add(objeto);
                    System.out.println("Coges " + objeto.getLongDescription());
                    currentCarry += objeto.getPeso();
                    aniadido = true;
                }
                else
                {
                    System.out.println("Llevas demasiado peso y no puedes coger ese objeto");
                }
            }
            else
            {
                System.out.println("El objeto no se puede coger");
            }
        }
        return aniadido;
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
     * Muestra por pantalla los objetos que lleva en ese momento el jugador.
     * Sino lleva nada, muestra un mensaje informando de ello.
     */
    public void showInventory()
    {
        String descr = "";
        // Si existen objetos en el inventario, los muestra
        if (inventory.size() > 0)
        {
            descr += "\nLlevas en el inventario los siguientes objetos:";
            for(int i = 0; i < inventory.size(); i++)
            {
                descr += "\n- " + inventory.get(i).getLongDescription();
            }
        }
        else
        {
            descr += "\nTu inventario esta vacio";
        }
        System.out.println(descr);
    }

    /**
     * El jugador conversa con el PNJ que se encuentre en la sala. si hay alguno.
     * Sino muestra un mensaje de error
     */
    public void hablar()
    {
        if(currentRoom.getPNJ() != null)
        {
            Item obj = currentRoom.getPNJ().hablar();
            if (obj != null)
            {
                boolean exito = addItem(obj);
                if(exito)
                {
                    currentRoom.getPNJ().remove(obj);
                }
                else
                {
                    System.out.println("No puedes recibir el objeto que te intenta dar");
                }
            }
        }
    }

    /**
     * El jugador ataca al PNJ que se encuentre en la localización
     */
    public void atacar()
    {
        System.out.println("Golpeas a " + getPNJ().getNombre() + " y le haces " + ataque + " puntos de daño");
        getPNJ().restaRes(ataque);
    }

    /**
     * Modifica la resistencia del PNJ en la cantidad introducida como parametro
     * @param res La resistencia a añadir o deducir de la resistencia del jugador.
     *          sera negativa si hay que restarla, positiva para sumar.
     */
    public void modificaRes(int res)
    {
        resistencia += res; 
    }

    /**
     * Devuelve el PNJ que se encuentre en ese momento en la localización con el jugador
     * @return El PNJ que se encuentre en ese momento en la localización con el jugador,
     *          o null si no hay ninguno.
     */
    public NPC getPNJ()
    {
        return currentRoom.getPNJ();
    }

    /**
     * Devuelve la resistencia del jugador
     * @return La resistencia del jugador
     */
    public int getResistencia()
    {
        return resistencia;
    }
    
    /**
     * Devuelve el ataque del jugador
     * @return El ataque del jugador
     */
    public int getAtaque()
    {
        return ataque;
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
