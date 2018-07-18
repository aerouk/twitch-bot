package me.iaero.twitchbot.handlers;

import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.managers.ListenerManager;
import org.pircbotx.hooks.managers.ThreadedListenerManager;

/**
 * Handles the registering of listeners within the bot.
 *
 * @author Tom Brookes
 * @version 18.03.2017
 */
public class ListenerHandler
{
    private final ListenerManager manager;

    private long lastMessageMillis;

    /**
     * Creates a new listener handler, also register all listeners.
     */
    public ListenerHandler()
    {
        manager = new ThreadedListenerManager();

        lastMessageMillis = 0;
    }

    /**
     * Print currently registered listeners to stdout.
     *
     * @param manager manager to retrieve listeners from
     */
    private void printListeners(ListenerManager manager)
    {
        System.out.print("Listeners loaded: ");

        for (Listener l : manager.getListeners()) {
            System.out.print(l.getClass().getSimpleName() + " ");
        }

        System.out.println();
    }

    /**
     * Adds a new listener to the {@link ListenerManager}.
     *
     * @param listener listener to add
     */
    void addListener(Listener listener)
    {
        manager.addListener(listener);
    }

    /**
     * Gets the {@link ListenerManager} containing all currently registered
     * listeners, as well as printing currently registered listeners to stdout.
     *
     * @return manager with currently registered listeners
     */
    public ListenerManager getManager()
    {
        printListeners(manager);

        return manager;
    }

    /**
     * Gets the last time a message was sent by the bot.
     *
     * @return timestamp of last message sent
     */
    public long getLastMessageMillis()
    {
        return lastMessageMillis;
    }

    /**
     * Updates last message send time.
     */
    public void setLastMessageMillis()
    {
        lastMessageMillis = System.currentTimeMillis() / 1000;
    }
}
