package me.iaero.twitchbot;

import me.iaero.twitchbot.commands.admin.ShutdownCommand;
import me.iaero.twitchbot.commands.fortnite.FNStatsCommand;
import me.iaero.twitchbot.commands.osu.LastCommand;
import me.iaero.twitchbot.commands.osu.RankCommand;
import me.iaero.twitchbot.commands.osu.TopRankCommand;
import me.iaero.twitchbot.handlers.CommandManager;
import me.iaero.twitchbot.handlers.CommandModuleHandler;
import me.iaero.twitchbot.handlers.ListenerHandler;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import java.util.Arrays;
import java.util.List;

/**
 * The main bot application class.
 *
 * @author Tom Brookes
 * @version 18.03.2017
 */
public class App
{
    private static CommandManager commandManager;
    private static ListenerHandler listenerHandler;
    private static Config iconfig;
    private static PircBotX bot;

    public static void main(String[] args) throws Exception
    {
        commandManager = new CommandManager();
        listenerHandler = new ListenerHandler();
        iconfig = new Config();

        new CommandModuleHandler();

        registerCommands();
        commandManager.printCommands();

        List<String> channels = Arrays.asList(iconfig.getProps().getProperty("ircChannels").split(","));

        Configuration.Builder builder = new Configuration.Builder()
            // adds the configured server, parsing the port
            // if no port provided, 6667 will be used
            .addServer(
                iconfig.getProps().getProperty("ircServer"),
                Integer.parseInt(iconfig.getProps().getProperty("ircPort", "6667")))
            .setServerPassword(iconfig.getProps().getProperty("ircPassword"))
            .setName(iconfig.getProps().getProperty("ircUsername"))
            .setLogin(iconfig.getProps().getProperty("ircUsername"))
            .setRealName(iconfig.getProps().getProperty("ircUsername"))
            .setListenerManager(listenerHandler.getManager())
            .addAutoJoinChannels(channels);

        Configuration config = builder.buildConfiguration();
        bot = new PircBotX(config);

        bot.startBot();
        bot.close();
    }

    /**
     * Registers all available commands.
     */
    private static void registerCommands()
    {
        // admin commands
        new ShutdownCommand();

        // osu commands
        new LastCommand(); // last, osulast
        new RankCommand(); // osu, osurank
        new TopRankCommand(); // top, osutop
        
        // fortnite commands
        new FNStatsCommand(); // fn, fnstats
    }

    /**
     * Gets the bot's command manager.
     *
     * @return command manager
     */
    public static CommandManager getCommandManager()
    {
        return commandManager;
    }

    /**
     * Gets the bot's listener handler.
     *
     * @return active listener handler
     */
    public static ListenerHandler getListenerHandler()
    {
        return listenerHandler;
    }

    /**
     * Gets the bot config.
     *
     * @return bot configuration
     */
    public static Config getConfig()
    {
        return iconfig;
    }

    /**
     * Gets the bot.
     *
     * @return the bot
     */
    public static PircBotX getBot()
    {
        return bot;
    }
}
