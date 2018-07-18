package me.iaero.twitchbot.models;

/**
 * Represents an osu! score.
 *
 * @author Tom Brookes
 * @version 18.03.2017
 */
public class OsuScore
{
    public String beatmap_id, maxcombo, count300, count100, count50, countmiss, enabled_mods, pp;

    /**
     * @return whether the score is valid
     */
    public boolean isValid()
    {
        return beatmap_id != null;
    }

    /**
     * Calculates the accuracy of a play, using the count of 300s, 100s, 50s and
     * misses from the score.
     *
     * @return decimal accuracy from provided values
     */
    public double getAccuracy()
    {
        int c300 = Integer.parseInt(count300);
        int c100 = Integer.parseInt(count100);
        int c50 = Integer.parseInt(count50);
        int cMiss = Integer.parseInt(countmiss);

        double top = 50 * c50 + 100 * c100 + 300 * c300;
        double bottom = 300 * (cMiss + c50 + c100 + c300);
        double calc = Math.round((top / bottom) * 10000);

        return calc / 100;
    }
}
