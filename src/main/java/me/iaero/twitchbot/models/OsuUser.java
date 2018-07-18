package me.iaero.twitchbot.models;

/**
 * Represents an osu! user.
 *
 * @author Tom Brookes
 * @version 18.03.2017
 */
public class OsuUser
{
    public static final String USERNAME_FORMAT = "^[a-zA-Z0-9\\[\\]\\_\\-\\@]{2,15}$";

    public String username, pp_rank, pp_raw;

    /**
     * @return whether the user is tracked by the pp system
     */
    public boolean exists()
    {
        return !pp_raw.equals("0");
    }

    /**
     * @return pp of the user
     */
    public double getPP()
    {
        return Double.parseDouble(pp_raw);
    }
}