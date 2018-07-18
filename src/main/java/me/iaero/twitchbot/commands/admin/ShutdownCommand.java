package me.iaero.twitchbot.commands.admin;

import me.iaero.twitchbot.App;
import me.iaero.twitchbot.commands.Command;
import me.iaero.twitchbot.helpers.PermsHelper;
import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 * Implementation of the !shutdown command, shutting the bot down.
 *
 * @author Tom Brookes
 * @version 25.01.2018
 */
public class ShutdownCommand extends Command
{
    public ShutdownCommand()
    {
        super("shutdown,die", PermsHelper.LEVEL.ADMIN);
    }

    @Override
    public String execute(User sender, Channel channel, String[] args)
    {
        App.getBot().sendIRC().message(channel.getName(), "Shutting down");
        App.getBot().close();

        return "Shutting down";
    }
}
