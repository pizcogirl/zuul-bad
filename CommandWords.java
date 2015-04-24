import java.util.ArrayList;
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
    private ArrayList<Option> commands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        commands = new ArrayList();
        for(Option opt : Option.values())
        {
            commands.add(opt);
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        boolean esComando = false;
        int index = 0;
        while(index < commands.size() && !esComando)
        {
            if(commands.get(index).getCommand().equals(aString))
            {
                esComando = true;
            }
            index++;
        }
        return esComando;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        String validCommands = "Los comandos son: \n";
        for(Option command : commands)
        {
            validCommands += command.getCommand() + " ";
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
        Option opcion = Option.DESCONOCIDO;
        boolean encontrado = false;
        int index = 0;
        while(index < commands.size() && !encontrado)
        {
            if(commands.get(index).getCommand().equals(commandWord))
            {
                opcion = commands.get(index);
                encontrado = true;
            }
            index++;
        }
        return opcion;
    }
}
