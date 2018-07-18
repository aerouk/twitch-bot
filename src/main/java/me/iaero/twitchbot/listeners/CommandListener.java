package me.iaero.twitchbot.listeners;

import me.iaero.twitchbot.App;
import me.iaero.twitchbot.commands.Command;
import me.iaero.twitchbot.handlers.BotListenerAdapter;
import me.iaero.twitchbot.helpers.PermsHelper;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.Arrays;
import java.util.Objects;

/**
 * Listener to handle prefixed commands.
 *
 * @author Tom
 * @version 20.10.2017
 */
public class CommandListener extends BotListenerAdapter
{
    @Override
    public void onMessage(MessageEvent event) throws Exception
    {
        super.onMessage(event);

        if (event.getMessage().startsWith("!")) {
            if ((System.currentTimeMillis() / 1000) - App.getListenerHandler().getLastMessageMillis() > Integer.parseInt(App.getConfig().getProps().getProperty("msgCooldown"))) {
                String[] msg = event.getMessage().split(" ");
                String command = msg[0].substring(1);
                String[] args = Arrays.copyOfRange(msg, 1, msg.length);

                Command cmd = App.getCommandManager().getCommandByName(command);

                if (PermsHelper.canUse(cmd, Objects.requireNonNull(event.getUser()), event.getChannel())) {
                    String response = cmd.execute(event.getUser(), event.getChannel(), args);

                    if (response != null) {
                        event.respond(response);
                    }
                }

                App.getListenerHandler().setLastMessageMillis();
            } else {
                System.out.println("commands on cooldown");
            }
        }
    }
}
