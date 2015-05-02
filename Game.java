import java.util.Random;

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
        // Por ahora el peso se crea con un random
        Random rand = new Random();
        player = new Player(((rand.nextFloat()*20F) +20F), 50, 5);
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, pasillo, caverna, bifurcacion, habitacionTesoro, guarida, camaraOculta, salidaObstruida;
        NPC granjero, kobold, dragon;

        // create the rooms
        entrada = new Room("la entrada de una mazmorra");
        pasillo = new Room("un pasillo de la mazmorra");
        caverna = new Room("una caverna rocosa");
        bifurcacion = new Room("el camino se divide en dos");
        habitacionTesoro = new Room("una habitacion del tesoro");
        guarida = new Room("la guarida del monstruo");
        camaraOculta = new Room ("en una sala pequeña, a la que entras por un pequeño boquete");
        salidaObstruida = new Room ("un pasillo que termina en una salida de la mazmorra, obstruida por un derrumbamiento");

        // Crea los PNJs
        granjero = new NPC(false, "granjero", "suerte, yo me quedo aqui", "Un granjero asustado de los alrededores", 1, 30);
        kobold = new NPC(true, "kobold", null, "Un kobold pequeño, armado con un palo", 5, 20);
        dragon = new NPC(true, "dragon", null, "Un dragon de aspecto fiero", 20, 200);

        // Añade los PNJ a las localizaciones
        entrada.addPNJ(granjero);
        guarida.addPNJ(kobold);
        habitacionTesoro.addPNJ(dragon);

        // Añade objetos a localizaciones
        entrada.addItem(new Item("piedra", "una piedra enorme", 50F, false, 50, 0));
        entrada.addItem(new Item("antorcha", "una antorcha encendida", 0.5F, true, 2, 0));
        caverna.addItem(new Item("cubo", "un cubo de metal", 1.0F, true, 1, 0));
        bifurcacion.addItem(new Item("piedra", "una piedra de pequeño tamaño", 10.0F, false, 5, 0));
        habitacionTesoro.addItem(new Item("monedas", "unas monedas de oro", 1.0F, true, 0, 0));
        habitacionTesoro.addItem(new Item("pocion", "una poción que cura 20 de resistencia", 0.5F, true, -1, 20));
        guarida.addItem(new Item("espada", "una espada afilada", 2.0F, true, 5, 0));

        // Añade objetos a los PNJs
        granjero.addItem(new Item("pocion", "una pocion que cura 20 de resistencia", 1.F, true, -1, 20));
        granjero.addItem(new Item("espada", "una espada afilada", 2.0F, true, 5, 0));
        kobold.addItem(new Item("diamante", "una piedra preciosa muy valiosa", 0.1F, true, 0, 0));
        kobold.addItem(new Item("monedas", "unas monedas de oro", 1.0F, true, 0, 0));

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
            // Si la resistencia del jugador llega a 0, muere.
            if(player.getResistencia() <= 0)
            {
                muerte();
                finished = true;
            }
        }
        System.out.println("\nGracias por jugar, adios");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido a World of Zuul!");
        System.out.println("World of Zuul es un nuevo y muy aburrido juego de aventuras");
        System.out.println("Escribe '" + Option.AYUDA.getComando() +"' para ver la ayuda");
        System.out.println();
        player.look();
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
        Option commandWord = command.getCommandWord();
        // Comprueba si se ha podido ejecutar el comando
        boolean ejecutado = false;

        switch(commandWord){
            case AYUDA:
            printHelp();
            break;
            case IR:
            ejecutado = goRoom(command);
            break;
            case TERMINAR:
            wantToQuit = quit(command);
            break;
            case EXAMINAR:
            player.look();
            break;
            case COMER:
            ejecutado = player.eat();
            break;
            case VOLVER:
            ejecutado = player.goBack();
            break;
            case COGER:
            ejecutado = take(command);
            break;
            case SOLTAR:
            ejecutado = drop(command);
            break;        
            case ESTADO:
            player.estado();
            break;
            case HABLAR:
            ejecutado = player.hablar();
            break;
            case ATACAR:
            ejecutado = atacar();
            break;
            case EQUIPAR:
            ejecutado = equipar(command);
            break;
            case SAQUEAR:
            ejecutado = player.saquear();
            break;
            case USAR:
            ejecutado = usar(command);
            break;
            case DESCONOCIDO:
            System.out.println("No entiendo las instrucciones");
        }
        if(!(commandWord.esSeguro()) && (ejecutado))
        {
            emboscada();
            ataquePNJ();
        }

        return wantToQuit;
    }

    /**
     * El jugador intenta atacar al PNJ que haya en la localización.
     * Fallara si no hay objetivos validos en la localización.
     * @return True si el ataque se lleva a cabo, false sino.
     */
    private boolean atacar()
    {
        boolean atacado = false;
        NPC pnj = player.getPNJ();
        if((pnj != null) && (pnj.isAgresivo()) && (pnj.getResistencia() > 0))
        {
            // El jugador entra en combate
            player.entraEnCombate();
            // El jugador ataca primero
            player.atacar();
            atacado = true;
            // Comprueba si el PNJ sigue vivo, sino sale de combate
            if ((player.getResistencia() <= 0) || (pnj.getResistencia() <= 0))
            {
                System.out.println("\nEl combate ha terminado");
                pnj.estaMuerto();
                player.saleDeCombate();
                if(pnj.getResistencia() <= 0)
                {
                    System.out.println("Has derrotado a " + pnj.getNombre());
                }
            }
            // Si sigue vivo, el PNJ ataca al jugador
            else
            {
                ataquePNJ();
            }
        }
        else
        {
            System.out.println("No existen objetivos validos en esta localización");
        }
        return atacado;
    }

    /**
     * El PNJ embosca al jugador y lo sorprende
     */
    private void emboscada()
    {
        NPC pnj = player.getPNJ();
        if ((pnj != null) && (pnj.isAgresivo()) && !(player.enCombate()))
        {
            System.out.println("\n¡" + pnj.getNombre() + " te descubre y se lanza al combate!");
            // El jugador entra en combate
            player.entraEnCombate();
        }
    }

    /**
     * El PNJ golpea al jugador
     */
    private void ataquePNJ()
    {
        NPC pnj = player.getPNJ();
        if(pnj != null && pnj.isAgresivo())
        {
            System.out.println(pnj.getNombre() + " te golpea y te hace " + pnj.getAtaque() + " puntos de daño");
            player.sumaResistencia(-1 * (pnj.getAtaque()));
        }
    }

    /**
     * Muestra la ayuda del juego.
     */
    private void printHelp() 
    {
        parser.printValidCommands();
    }

    /** 
     * Intenta ir en una dirección. Si no hay salida muestra un mensaje de error.
     * @return True si logra desplazarse en esa dirección, false sino.
     */
    private boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("¿A donde quieres ir?");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        return (player.goRoom(direction));
    }

    /** 
     * Intenta coger un objeto de la localización.
     * @return True si logra cogerlo, false sino.
     */
    private boolean take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("¿Que quieres coger?");
            return false;
        }

        String objeto = command.getSecondWord();

        // Intenta coger el objeto
        return (player.take(objeto));
    }

    /** 
     * Intenta equipar un objeto.
     * @return True si logra equipar un objeto, false sino.
     */
    private boolean equipar(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to equip...
            System.out.println("¿Que quieres equipar?");
            return false;
        }

        String objeto = command.getSecondWord();

        // Intenta equipar el objeto
        return (player.equipar(objeto));
    }

    /** 
     * Intenta soltar un objeto.
     * @return True si logra soltarlo, false sino.
     */
    private boolean drop(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("¿Que quieres soltar");
            return false;
        }

        String objeto = command.getSecondWord();

        // Intenta soltar un objeto
        return (player.dropItem(objeto));
    }
    
     /** 
     * Intenta usar un objeto del inventario.
     * @return True si logra usarlo, false sino.
     */
    private boolean usar(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use...
            System.out.println("¿Que quieres usar?");
            return false;
        }

        String objeto = command.getSecondWord();

        // Intenta coger el objeto
        return (player.usar(objeto));
    }

    /**
     * El jugador ha muerto. Muestra un mensaje informando de ello por pantalla.
     */
    private void muerte()
    {
        System.out.println("\nTu personaje ha muerto. Ha terminado la partida");
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

}
