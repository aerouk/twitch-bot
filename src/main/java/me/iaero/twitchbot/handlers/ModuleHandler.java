package me.iaero.twitchbot.handlers;

import me.iaero.twitchbot.App;
import org.pircbotx.hooks.Listener;

/**
 * Represents an abstract model of a module handler.
 *
 * @author Tom Brookes
 * @version 18.03.2017
 */
abstract class ModuleHandler
{
    /**
     * Creates the module handler, registering listeners by default.
     */
    ModuleHandler()
    {
        registerListeners();
    }

    /**
     * Register necessary listeners for the module.
     */
    abstract void registerListeners();

    /**
     * Register new listener.
     *
     * @param listener listener to register
     */
    void registerListener(Listener listener)
    {
        App.getListenerHandler().addListener(listener);
    }
}
