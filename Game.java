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
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
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

        // initialise room exits (norte, este, sur, oeste, sureste, noroeste)
        entrada.setExits(null, pasillo, null, null, null, null);
        pasillo.setExits(null, bifurcacion, caverna, entrada, null, null);
        caverna.setExits(pasillo, null, null, null, camaraOculta, null);
        bifurcacion.setExits(habitacionTesoro, guarida, null, pasillo, null, null);
        habitacionTesoro.setExits(null, null, bifurcacion, null, null, null);
        guarida.setExits(null, null, null, bifurcacion, null, null);
        camaraOculta.setExits(null, null, null, null, salidaObstruida, caverna);
        salidaObstruida.setExits(null, null, null, null, null, camaraOculta);

        currentRoom = entrada;  // start game outside
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
        System.out.println("Estas perdido en mitad de ninguna parte");
        System.out.println("entras en una mazmorra.");
        System.out.println();
        System.out.println("Los comandos son:");
        System.out.println(" ir terminar ayuda");
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
            currentRoom = nextRoom;
            printLocationInfo();
            System.out.println();
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
        System.out.println("Estas en " + currentRoom.getDescription());
        System.out.print("Salidas: ");
        System.out.print(currentRoom.getExitString());
    }
}
