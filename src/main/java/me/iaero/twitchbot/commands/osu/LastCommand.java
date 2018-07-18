package me.iaero.twitchbot.commands.osu;

import me.iaero.twitchbot.commands.Command;
import me.iaero.twitchbot.helpers.OsuHelper;
import me.iaero.twitchbot.helpers.PermsHelper;
import me.iaero.twitchbot.models.OsuBeatmap;
import me.iaero.twitchbot.models.OsuScore;
import me.iaero.twitchbot.models.OsuUser;
import org.pircbotx.Channel;
import org.pircbotx.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of the !last command.
 *
 * @author Tom Brookes
 * @version 20.10.2017
 */
public class LastCommand extends Command
{
    public LastCommand()
    {
        super("last,osulast", PermsHelper.LEVEL.MOD);
    }

    @Override
    public String execute(User sender, Channel channel, String[] args) throws Exception
    {
        if (args.length == 1) {
            Pattern pattern = Pattern.compile(OsuUser.USERNAME_FORMAT);
            Matcher matcher = pattern.matcher(args[0]);

            if (matcher.find()) {
                OsuScore score = OsuHelper.getRecentScore(matcher.group(0));

                if (score.isValid()) {
                    OsuBeatmap beatmap = OsuHelper.getBeatmap(score.beatmap_id);
                    String mods = OsuHelper.parseMods(score.enabled_mods);

                    if (!mods.equals("")) {
                        mods = " +" + mods;
                    }

                    return String.format(
                            "%s - %s [%s]%s %s/%sx (%sx100 %sx50 %sxmiss)",
                            beatmap.artist, beatmap.title, beatmap.version, mods, score.maxcombo,
                            beatmap.max_combo, score.count100, score.count50, score.countmiss
                    );
                }
            }
        }

        return null;
    }
}
