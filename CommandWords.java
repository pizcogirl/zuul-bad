import java.util.HashMap;
import java.util.Set;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private HashMap<String, Option> commands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        commands = new HashMap();
        commands.put("go", Option.IR);
        commands.put("quit", Option.TERMINAR);
        commands.put("help", Option.AYUDA);
        commands.put("look", Option.EXAMINAR);
        commands.put("eat", Option.COMER);
        commands.put("back", Option.VOLVER);
        commands.put("take", Option.COGER);
        commands.put("drop", Option.SOLTAR);
        commands.put("objects", Option.OBJETOS);
        commands.put("unknown", Option.DESCONOCIDO);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
            return (commands.containsKey(aString));
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        String validCommands = "Los comandos son: \n";
        Set<String> keys = commands.keySet();
        for(String com : keys)
        {
            validCommands = validCommands + com + " ";
        }
        System.out.println(validCommands);
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord)
    {
        Option opcion = commands.get(commandWord);
        if (opcion == null)
        {
            opcion = Option.DESCONOCIDO;
        }
        return opcion;
    }
}
