import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, pasillo, caverna, bifurcacion, habitacionTesoro, guarida, camaraOculta, salidaObstruida;

        // create the rooms
        entrada = new Room("la entrada de una mazmorra");
        pasillo = new Room("un pasillo de la mazmorra");
        caverna = new Room("una caverna rocosa");
        bifurcacion = new Room("el camino se divide en dos");
        habitacionTesoro = new Room("una habitacion del tesoro");
        guarida = new Room("la guarida del monstruo");
        camaraOculta = new Room ("en una sala pequeña, a la que entras por un pequeño boquete");
        salidaObstruida = new Room ("un pasillo que termina en una salida de la mazmorra, obstruida por un derrumbamiento");

        // Añadimos objetos a las localizaciones
        entrada.addItem(new Item( "una antorcha", 0.5F));
        caverna.addItem(new Item("un cubo", 1.0F));
        bifurcacion.addItem(new Item( "una piedra", 7.0F));
        habitacionTesoro.addItem(new Item("unas monedas de oro", 1.0F));
        habitacionTesoro.addItem(new Item("una poción", 0.5F));
        guarida.addItem(new Item("una espada", 2.0F));

        // initialise room exits (norte, este, sur, oeste, sureste, noroeste)
        entrada.setExit("este", pasillo);
        pasillo.setExit("este", bifurcacion);
        pasillo.setExit("sur", caverna);
        pasillo.setExit("oeste", entrada);
        caverna.setExit("este", pasillo);
        caverna.setExit("sureste", camaraOculta);
        bifurcacion.setExit("norte", habitacionTesoro);
        bifurcacion.setExit("este", guarida);
        bifurcacion.setExit("oeste", pasillo);
        habitacionTesoro.setExit("sur", bifurcacion);
        guarida.setExit("oeste", bifurcacion);
        camaraOculta.setExit("suroeste", salidaObstruida);
        camaraOculta.setExit("noroeste", caverna);
        salidaObstruida.setExit("noroeste", camaraOculta);

        player.setRoom(entrada);  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Gracias por jugar, adios");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido a World of Zuul!");
        System.out.println("World of Zuul es un nuevo y muy aburrido juego de aventuras");
        System.out.println("Escribe 'ayuda' para ver la ayuda");
        System.out.println();
        printLocationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("No entiendo las instrucciones");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ayuda")) {
            printHelp();
        }
        else if (commandWord.equals("ir")) {
            goRoom(command);
        }
        else if (commandWord.equals("terminar")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("examinar")){
            printLocationInfo();
        }
        else if (commandWord.equals("comer")){
            System.out.println("Acabas de comer y ya no estas hambriento");
        }
        else if (commandWord.equals("volver")){
            goLastRoom();
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        parser.printValidCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("¿A donde quieres ir?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("No puedes continuar por ahí");
        }
        else {
            lastRooms.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
            System.out.println();
        }
    }
    
    /**
     * Intenta ir a la habitación anterior. Si no hay ninguna, devuelve
     * un mensaje de error.
     */
    private void goLastRoom()
    {
        // Si existe la habitacion, va a ella y cambia la ultima habitacion por la que se encontraba
        if(!lastRooms.empty())
        {
            currentRoom = lastRooms.pop();
            printLocationInfo();
            System.out.println();
        }
        else
        {
            System.out.println("No puedes continuar por ahí");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("¿Salir?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * Imprime la información de la localización
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }
}
