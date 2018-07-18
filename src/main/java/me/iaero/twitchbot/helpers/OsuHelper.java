package me.iaero.twitchbot.helpers;

import com.google.gson.Gson;
import me.iaero.twitchbot.App;
import me.iaero.twitchbot.models.OsuBeatmap;
import me.iaero.twitchbot.models.OsuScore;
import me.iaero.twitchbot.models.OsuUser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Various utilities for dealing with osu! data.
 *
 * @author Tom Brookes
 * @version 20.10.2017
 */
public class OsuHelper
{
    private static final String key = App.getConfig().getProps().getProperty("osuAPI");
    private static final Map<String, Integer> osuMods;

    static {
        osuMods = new LinkedHashMap<>();

        osuMods.put("NF", 1);
        osuMods.put("EZ", 2);
        osuMods.put("NV", 4);
        osuMods.put("HD", 8);
        osuMods.put("HR", 16);
        osuMods.put("SD", 32);
        osuMods.put("DT", 64);
        osuMods.put("RL", 128);
        osuMods.put("HT", 256);
        osuMods.put("NC", 512);
        osuMods.put("FL", 1024);
        osuMods.put("AO", 2048);
        osuMods.put("SO", 4096);
        osuMods.put("AP", 8192);
        osuMods.put("PF", 16384);
    }

    /**
     * Retrieves the {@link OsuUser} object for a specified username or ID.
     *
     * @param userId username or ID to fetch user object for
     * @return OsuUser object for username or ID
     * @throws IOException upon invalid url or inability to open url
     */
    public static OsuUser getUser(String userId) throws IOException
    {
        URL url = new URL("https://osu.ppy.sh/api/get_user?k=" + key + "&u=" + userId);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        return new Gson().fromJson(reader, OsuUser[].class)[0];
    }

    /**
     * Retrieves the most recent {@link OsuScore} object for the specified
     * username or ID.
     *
     * @param userId username or ID to fetch score for
     * @return OsuScore object for username or ID
     * @throws IOException upon invalid url or inability to open url
     */
    public static OsuScore getRecentScore(String userId) throws IOException
    {
        URL url = new URL("https://osu.ppy.sh/api/get_user_recent?k=" + key + "&u=" + userId + "&limit=1");
        InputStreamReader reader = new InputStreamReader(url.openStream());

        return new Gson().fromJson(reader, OsuScore[].class)[0];
    }

    /**
     * Retrieves the top {@link OsuScore} of the player with the specified
     * username or user ID.
     *
     * @param userId username or ID to fetch score for
     * @return OsuScore object for username or ID
     * @throws IOException upon invalid url or inability to open url
     */
    public static OsuScore getTopScore(String userId) throws IOException
    {
        URL url = new URL("https://osu.ppy.sh/api/get_user_best?k=" + key + "&u=" + userId + "&limit=1");
        InputStreamReader reader = new InputStreamReader(url.openStream());

        return new Gson().fromJson(reader, OsuScore[].class)[0];
    }

    /**
     * Retrieves the {@link OsuBeatmap} object for the specified beatmap ID.
     *
     * @param beatmapId beatmap id to fetch object for
     * @return OsuBeatmap for specified beatmap ID
     * @throws IOException upon invalid url or inability to open url
     */
    public static OsuBeatmap getBeatmap(String beatmapId) throws IOException
    {
        URL url = new URL("https://osu.ppy.sh/api/get_beatmaps?k=" + key + "&b=" + beatmapId);
        InputStreamReader reader = new InputStreamReader(url.openStream());

        return new Gson().fromJson(reader, OsuBeatmap[].class)[0];
    }

    /**
     * Parses the mods from the corresponding bitwise value.
     *
     * @param bitVal bitwise mod value
     * @return corresponding mods enabled
     */
    public static String parseMods(String bitVal)
    {
        StringBuilder mods = new StringBuilder();
        int i = Integer.parseInt(bitVal);
        ListIterator<Entry<String, Integer>> modIterator = new ArrayList<>(osuMods.entrySet()).listIterator(osuMods.size());

        while (modIterator.hasPrevious()) {
            Entry<String, Integer> entry = modIterator.previous();

            if (i >= entry.getValue()) {
                i -= entry.getValue();

                // handle repeated implied mods (e.g. NC implies DT)
                if (entry.getKey().equals("NC")) {
                    i -= osuMods.get("DT");
                }

                mods.insert(0, entry.getKey());
            }
        }

        return mods.toString();
    }
}
