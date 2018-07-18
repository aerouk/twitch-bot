package me.iaero.twitchbot.handlers;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.Objects;

/**
 * Provides a wrapper for the {@link ListenerAdapter} to allow extra
 * functionality.
 *
 * @author Tom
 * @version 20.10.2017
 */
public class BotListenerAdapter extends ListenerAdapter
{
    @Override
    public void onMessage(MessageEvent event) throws Exception
    {
        if (event.getMessage().startsWith("!")) {
            // valid command
            System.out.println(event.getTimestamp() + " | " + Objects.requireNonNull(event.getUser()).getNick() + ": " + event.getMessage());
        }
    }
}
