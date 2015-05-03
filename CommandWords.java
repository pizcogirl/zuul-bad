import java.util.ArrayList;
import java.util.Set;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael KÃ¶lling and David J. Barnes
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
            if(commands.get(index).getComando().equals(aString))
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
        int contador = 0;
        for(Option command : commands)
        {
            validCommands += command.getComando() + " ";
            // Mete un salto de linea cada 6 comandos.
            if(contador == 5)
            {
                validCommands += "\n";
                contador = 0;
            }
            contador++;
        }
        validCommands += "\nPara obtener ayuda sobre un comando especifico, escriba " +  Option.AYUDA.getComando() + " más el comando";
        System.out.println(validCommands);
    }
    
    /**
     * Imprime la ayuda de un comando especifico
     * @param el comando sobre el que se necesita ayuda
     */
    public void show(String comando)
    {
        String infoComando = "Ese comando no es valido, no se puede mostrar ayuda";
        for(Option command : commands)
        {
            if(command.getComando().equals(comando))
            {
                infoComando = command.getInfo();
            }
        }
        System.out.println(infoComando);
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
            if(commands.get(index).getComando().equals(commandWord))
            {
                opcion = commands.get(index);
                encontrado = true;
            }
            index++;
        }
        return opcion;
    }
}
