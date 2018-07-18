package me.iaero.twitchbot.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import me.iaero.twitchbot.App;

public class FortniteHelper
{
    private static final String key = App.getConfig().getProps().getProperty("fnTrackerAPI");
    
    public static String getPlayerStats(String platform, String user) throws IOException
    {
        URL url = new URL("http://api.fortnitetracker.com/v1/profile/" + platform + "/" + user);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        System.setProperty("http.agent", "");
        
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        connection.setRequestMethod("GET");
        connection.addRequestProperty("TRN-Api-Key", key);
        connection.setUseCaches(false);
        connection.setAllowUserInteraction(false);
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        connection.setDoOutput(true);
        connection.connect();
        
        InputStream in = connection.getInputStream();
        
        InputStreamReader reader = new InputStreamReader(in);

        Gson gson = new Gson();
        JsonObject body = gson.fromJson(reader, JsonObject.class);
        JsonArray lifeTimeStats = body.get("lifeTimeStats").getAsJsonArray();
        JsonObject wins = lifeTimeStats.get(8).getAsJsonObject();
        String winsElement = wins.get("value").getAsString();
        JsonObject kills = lifeTimeStats.get(10).getAsJsonObject();
        String killsElement = kills.get("value").getAsString();
        
        String output = user + " - " + winsElement + " wins, " + killsElement + " kills";
        
        return output;
    }
}
