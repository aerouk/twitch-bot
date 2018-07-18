package me.iaero.twitchbot.helpers;

import me.iaero.twitchbot.commands.Command;
import org.pircbotx.Channel;
import org.pircbotx.User;

/**
 * Various utilities to handle permission checking for bot commands.
 *
 * @author Tom Brookes
 * @version 20.10.2017
 */
public class PermsHelper
{
    /**
     * Returns whether a user can use a given command in a given channel.
     *
     * @param cmd command to check permissions for
     * @param user user executing command
     * @param channel channel command was executed in
     * @return whether the user can use the command in the given channel
     */
    public static boolean canUse(Command cmd, User user, Channel channel)
    {
        // override(s)
        if (user.getNick().equals("aerouk") || user.getNick().equals("aero")) {
            return true;
        }

        if (cmd.getLevel().getVal() == 4) {
            return user.getNick().equals("aerouk") || user.getNick().equals("aero");
        }

        // is channel owner
        if (cmd.getLevel().getVal() == 3) {
            return user.getNick().equals(channel.getName().substring(1));
        }

        // op/mod in channel
        if (cmd.getLevel().getVal() == 2) {
            return channel.getOps().contains(user);
        }

        // permed in channel
        if (cmd.getLevel().getVal() == 1) {
            // TODO: implement for permed users
        }

        // user level
        return cmd.getLevel().getVal() == 0;
    }

    /**
     * Represents a permission level based on the user's permissions.
     *
     * @author Tom Brookes
     * @version 20.10.2017
     */
    public enum LEVEL
    {
        ADMIN(4), MODPLUS(3), MOD(2), PERMED(1), USER(0);

        private final int val;

        LEVEL(int val)
        {
            this.val = val;
        }

        /**
         * @return numerical value of the permission level
         */
        int getVal()
        {
            return val;
        }
    }
}
