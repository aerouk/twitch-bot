package me.iaero.twitchbot.commands.osu;

import me.iaero.twitchbot.commands.Command;
import me.iaero.twitchbot.helpers.OsuHelper;
import me.iaero.twitchbot.helpers.PermsHelper;
import me.iaero.twitchbot.models.OsuUser;
import org.pircbotx.Channel;
import org.pircbotx.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of the !osu command, displaying pp rank info.
 *
 * @author Tom Brookes
 * @version 20.10.2017
 */
public class RankCommand extends Command
{
    public RankCommand()
    {
        super("osu,osurank", PermsHelper.LEVEL.MOD);
    }

    @Override
    public String execute(User sender, Channel channel, String[] args) throws Exception
    {
        Pattern pattern = Pattern.compile(OsuUser.USERNAME_FORMAT);

        if (args.length == 1) {
            Matcher matcher = pattern.matcher(args[0]);

            if (matcher.find()) {
                OsuUser user = OsuHelper.getUser(matcher.group(0));
                String response = String.format("%s #%s PP: %s", user.username, user.pp_rank, user.pp_raw);

                if (user.exists()) {
                    return response;
                }
            }
        } else if (args.length == 2) {
            Matcher match1 = pattern.matcher(args[0]);
            Matcher match2 = pattern.matcher(args[1]);

            if (match1.find() && match2.find()) {
                OsuUser user1 = OsuHelper.getUser(match1.group(0));
                OsuUser user2 = OsuHelper.getUser(match2.group(0));

                if (user1.exists() && user2.exists()) {
                    double calc = user1.getPP() - user2.getPP();
                    String measure = calc <= 0 ? "behind" : "ahead of";

                    return String.format("%s is %spp %s %s", user1.username, String.format("%.2f", Math.abs(calc)), measure, user2.username);
                }
            }
        }

        return null;
    }
}
