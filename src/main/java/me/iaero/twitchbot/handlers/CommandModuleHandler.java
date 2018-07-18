package me.iaero.twitchbot.handlers;

import me.iaero.twitchbot.listeners.CommandListener;

/**
 * Module handler for the main command listener.
 *
 * @author Tom
 * @version 20.10.2017
 */
public class CommandModuleHandler extends ModuleHandler
{
    @Override
    void registerListeners()
    {
        registerListener(new CommandListener());
    }
}
