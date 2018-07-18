package me.iaero.twitchbot.commands;

import me.iaero.twitchbot.App;
import me.iaero.twitchbot.helpers.PermsHelper;
import org.pircbotx.Channel;
import org.pircbotx.User;

import java.util.Arrays;

/**
 * Represents a command which can be executed by a chat user.
 * <p>
 * A command is denoted by a specified prefix character, followed by a specified
 * alias tag string.
 *
 * @author Tom Brookes
 * @version 20.10.2017
 */
public abstract class Command
{
    private final String[] aliases;
    private final PermsHelper.LEVEL level;

    /**
     * Create a new command with the given aliases, executable only by users
     * with a permission level equal to or higher than the one provided.
     *
     * @param cmdAliases aliases for command
     * @param level permission level required to execute command
     */
    protected Command(String cmdAliases, PermsHelper.LEVEL level)
    {
        aliases = cmdAliases.split(",");
        this.level = level;

        for (String alias : aliases) {
            App.getCommandManager().registerCommand(alias, this);
        }
    }

    /**
     * Executes the command in the given channel with the specified arguments.
     *
     * @param sender user executing the command
     * @param channel channel the command was executed in
     * @param args arguments passed from command
     * @return output for bot to reply to user with, null if no message to send
     */
    public abstract String execute(User sender, Channel channel, String[] args) throws Exception;

    /**
     * Gets the registered aliases for this command.
     *
     * @return registered aliases for command
     */
    public String getAliases()
    {
        return Arrays.toString(aliases);
    }

    /**
     * Gets the permission level required to execute this command.
     *
     * @return the permission level required
     */
    public PermsHelper.LEVEL getLevel()
    {
        return level;
    }
}
