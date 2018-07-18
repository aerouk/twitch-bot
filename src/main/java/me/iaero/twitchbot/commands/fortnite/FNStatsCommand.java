package me.iaero.twitchbot.commands.fortnite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pircbotx.Channel;
import org.pircbotx.User;

import me.iaero.twitchbot.commands.Command;
import me.iaero.twitchbot.helpers.FortniteHelper;
import me.iaero.twitchbot.helpers.PermsHelper;

public class FNStatsCommand extends Command
{
    public FNStatsCommand()
    {
        super("fn,fnstats", PermsHelper.LEVEL.USER);
    }

    @Override
    public String execute(User sender, Channel channel, String[] args) throws Exception
    {
        if (args.length == 2) {
            Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{2,16}$");
            Matcher matcher = pattern.matcher(args[1]);
            
            if (matcher.find()) {
                return FortniteHelper.getPlayerStats(args[0], args[1]);
            }
        }
        
        return null;
    }
}
