import java.util.Stack;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Esta clase representa al jugador del juego. Realiza las acciones
 * relacionadas con el (examinar, comer, etc).
 * 
 * @author Julia Zuara 
 * @version (a version number or a date)
 */
public class Player
{
    // La habitaci�n en la que se encuentra en ese momento el jugador
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
    // Recoge si el jugador esta en combate
    private boolean enCombate;
    // El objeto equipado por el jugador en estos momentos
    private Item equipo;

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
        // El jugador empieza la partida fuera de combate y sin equipo
        this.enCombate = false;
        this.equipo = null;
    }

    /**
     * Coloca al jugador en una habitaci�n
     * @param room La habitaci�n en la que se encuentra el jugador.
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
     * El jugador vuelve a la habitaci�n anterior. Si esta en combate no puede volver.
     * @return True si puede volver, false sino.
     */
    public boolean goBack()
    {
        boolean volver = false;
        if(!enCombate)
        {
            if(!previusRooms.empty())
            {
                currentRoom = previusRooms.pop();
                printLocationInfo();
                System.out.println();
                volver = true;
            }
            else
            {
                System.out.println("No existen localizaciones a las que volver");
            }
        }
        else
        {
            System.out.println("No puedes hacer eso en combate");
        }
        return volver;
    }

    /**
     * El jugador examina la localizaci�n en la que se encuentra. Puedes examinar en combate.
     */
    public void look()
    {
            printLocationInfo();
    }

    /**
     * El jugador come. Si esta en combate no puede usarse.
     * @return True si puede comer, false sino.
     */
    public boolean eat()
    {
        boolean comer = false;
        if(!enCombate)
        {
            System.out.println("Acabas de comer y ya no estas hambriento");
            comer = true;
        }
        else
        {
            System.out.println("No puedes hacer eso en combate");
        }
        return comer;
    }

    /**
     * El jugador intenta moverse a otra otra localizaci�n. Si existe una localizaci�n en esa
     * direcci�n lo hara, sino imprimira un mensaje avisando de que no puede ir en esa direcci�n.
     * Si esta en combate no puede ir a otra localizaci�n.
     * @param direccion La direccion en la que intenta moverse.
     * @return True si ha podido desplazarse, false sino.
     */
    public boolean goRoom(String direccion)
    {
        boolean desplazarse = false;
        if(!enCombate)
        {
            Room nextRoom = currentRoom.getExit(direccion);

            if (nextRoom == null) {
                System.out.println("No puedes continuar por ah�");
            }
            else {
                setRoom(nextRoom);
                printLocationInfo();
                System.out.println();
                desplazarse = true;
            }
        }
        else
        {
            System.out.println("No puedes hacer eso en combate");
        }
        return desplazarse;
    }

    /**
     * El jugador busca un objeto en la localizaci�n. Si lo encuentra lo intenta a�adir al inventario.
     * Si lo a�ade al inventario, desaparece de la localizaci�n. Si esta en combate no puede coger objetos del suelo.
     * @param El nombre del objeto a buscar e intentar a�adir al inventario.
     * @return True si ha podido coger el objeto, false sino.
     */
    public boolean take(String nombre)
    {
        boolean coger = false;
        if(!enCombate)
        {
            // Busca el objeto en la localizacion
            Item obj = currentRoom.search(nombre);
            // Si lo encuentra lo a�ade al inventario
            if(obj != null)
            {
                addItem(obj);
                coger = true;
                currentRoom.remove(obj);
            }
            else
            {
                System.out.println("No encuentras ese objeto en esta localizaci�n");
            }
        }
        else
        {
            System.out.println("No puedes hacer eso en combate");
        }
        return coger;
    }

    /**
     * El jugador intenta equipar un objeto. Si no puede, informa de ello.
     * Si el jugador suelta el objeto, deja de estar equipado.
     * @param nombre El nombre del objeto a equipar
     * @return True si ha podido equiparlo, false sino.
     */
    public boolean equipar(String nombre)
    {
        boolean equipar = false;
        Item objeto = search(nombre);
        if ((objeto != null) && (objeto != equipo))
        {
            equipo = objeto;
            System.out.println("\nEquipas " + objeto.getNombreObj() + " y te proporciona " + objeto.getAtaque() + " ataque");
            equipar = true;
        }
        else
        {
            System.out.println("No tienes ese objeto en tu inventario para equiparlo");
        }
        return equipar;
    }

    /**
     * El jugador intenta saquear el cuerpo de un enemigo caido. Si no puede a�adir alguno
     * de los objetos saqueados informa de ello.
     * @return True si ha podido saquear todos los objetos, false sino.
     */
    public boolean saquear()
    {
        boolean saqueado = false;
        // Toma el inventario del PNJ
        ArrayList<Item> loot = getPNJ().saquear();
        // Intenta a�adir cada objeto al inventario del PNJ
        if(loot != null)
        {
            Iterator<Item> it = loot.iterator();
            while(it.hasNext())
            {
                Item item = it.next();
                saqueado = addItem(item);
                if(saqueado)
                {
                    it.remove();
                }
            }
        }
        else
        {
            System.out.println("Aqui no hay nada que saquear");
        }
        return saqueado;
    }

    /**
     * Intenta a�adir un objeto al inventario del jugador. Si el objeto existe 
     * y puede cogerlo, lo a�adira a su inventario. Sino mostrara un mensaje indicando el problema
     * @param El nombre del objeto que quiere a�adir
     * @return True si ha podido a�adir el objeto, false sino.
     */
    public boolean addItem(Item objeto)
    {
        boolean aniadido = false;
        if(objeto != null)
        {
            // Comprueba si el objeto se puede coger
            if(objeto.puedeCogerse())
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
     * lo soltara, sino mostrara un mensaje. Si esta en combate no puede soltar objetos.
     * @param El nombre del objeto que quiere a�adir
     * @return True si el objeto ha podido ser soltado, false sino.
     */
    public boolean dropItem(String objeto)
    {
        boolean soltar = false;
        if(!enCombate)
        {
            // busca el objeto en el inventario
            Item tempObj = search(objeto);
            if(tempObj != null)
            {
                inventory.remove(tempObj);
                currentRoom.addItem(tempObj);
                System.out.println("Sueltas " + tempObj.getLongDescription());
                currentCarry -= tempObj.getPeso();
                soltar = true;
                if(tempObj == equipo)
                {
                    equipo = null;
                }
            }
            else
            {
                System.out.println("No tienes ese objeto en tu inventario");
            }
        }
        else
        {
            System.out.println("No puedes hacer eso en combate");
        }
        return soltar;
    }

    /**
     * Muestra por pantalla los objetos que lleva en ese momento el jugador.
     * Si no lleva nada, muestra un mensaje informando de ello. El jugador puede 
     * examinar su inventario en combate.
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
     * Sino muestra un mensaje de error. El jugador no puede hablar en combate.
     */
    public boolean hablar()
    {
        boolean hablar = false;
        if(!enCombate)
        {
            if(currentRoom.getPNJ() != null)
            {
                Item obj = currentRoom.getPNJ().hablar();
                hablar = true;
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
        else
        {
            System.out.println("No puedes hacer eso en combate");
        }
        return hablar;
    }

    /**
     * El jugador ataca al PNJ que se encuentre en la localizaci�n
     */
    public void atacar()
    {
        System.out.println("\nGolpeas a " + getPNJ().getNombre() + " y le haces " + getAtaque() + " puntos de da�o");
        getPNJ().restaRes(getAtaque());
    }

    /**
     * Introduce al jugador en combate
     */
    public void entraEnCombate()
    {
        enCombate = true;
    }

    /**
     * Saca al jugador de combate
     */
    public void saleDeCombate()
    {
        enCombate = false;
    }

    /**
     * Suma a la resistencia del jugador en la cantidad introducida como parametro
     * @param res La resistencia a a�adir a la resistencia del jugador.
     *              Sera negativa si hay que disminuirla, positiva para aumentarla.
     */
    public void sumaResistencia(int res)
    {
        resistencia += res; 
    }

    /**
     * Devuelve el PNJ que se encuentre en ese momento en la localizaci�n con el jugador
     * @return El PNJ que se encuentre en ese momento en la localizaci�n con el jugador,
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
        int ataqueTotal = ataque;
        if(equipo != null)
        {
            ataqueTotal += equipo.getAtaque();
        }
        return ataqueTotal;
    }

    /**
     * Devuelve si el jugador esta o no en combate
     * @return True si esta en combate, false sino
     */
    public boolean enCombate()
    {
        return enCombate;
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
     * Imprime la informaci�n de la localizaci�n en la que se encuentra el jugador
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }
}
