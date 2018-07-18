package me.iaero.twitchbot.handlers;

import me.iaero.twitchbot.commands.Command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Class to group commands.
 *
 * @author Tom Brookes
 * @version 20.10.2017
 */
public class CommandManager
{
    private Map<String, Command> commands;

    /**
     * Creates a new command manager, initialising the command map.
     */
    public CommandManager()
    {
        commands = new HashMap<>();
    }

    /**
     * Gets the full command map.
     *
     * @return command map
     */
    public Map<String, Command> getCommands()
    {
        return commands;
    }

    /**
     * Gets the command object for a given command alias.
     *
     * @param cmd command alias to get command for
     * @return command associated with alias
     */
    public Command getCommandByName(String cmd)
    {
        return commands.getOrDefault(cmd, null);
    }

    /**
     * Registers a command in the map. Will overwrite previously registered
     * alias(es) if conflicting.
     *
     * @param alias   alias to register command to
     * @param command command to associate with alias
     */
    public void registerCommand(String alias, Command command)
    {
        commands.put(alias, command);
    }

    /**
     * Prints all currently registered commands and their aliases.
     */
    public void printCommands()
    {
        System.out.print("Commands loaded: ");

        for (Command command : new HashSet<>(commands.values())) {
            System.out.print(command.getClass().getSimpleName());
            System.out.print(command.getAliases() + " ");
        }

        System.out.println();

        System.out.println(commands.toString());
    }
}
