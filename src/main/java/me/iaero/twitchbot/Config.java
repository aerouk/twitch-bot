package me.iaero.twitchbot;

import java.io.*;
import java.util.Properties;

/**
 * Represents a configuration file.
 *
 * @author Tom Brookes
 * @version 19.10.2017
 */
public class Config
{
    private Properties props;

    /**
     * Sets up a new config file if one does not exist.
     */
    Config()
    {
        if (!configExists()) createConfig();

        props = getConfig();
    }

    /**
     * Creates a new configuration file with default options.
     */
    private void createConfig()
    {
        Properties props = new Properties();

        try (OutputStream output = new FileOutputStream("config.properties", true)) {
            props.setProperty("osuAPI", "");
            props.setProperty("fnTrackerAPI", "");
            props.setProperty("ircServer", "irc.chat.twitch.tv");
            props.setProperty("ircUsername", "");
            props.setProperty("ircPassword", "");
            props.setProperty("ircChannels", "#example");
            props.setProperty("msgCooldown", "2");

            props.store(output, null);
        } catch (IOException ignored) {
        }
    }

    /**
     * Returns whether the configuration file already exists.
     *
     * @return whether config file already exists
     */
    private boolean configExists()
    {
        File file = new File("config.properties");

        return file.exists();
    }

    /**
     * Gets the properties inside the config file.
     *
     * @return properties of config file
     */
    private Properties getConfig()
    {
        Properties props = new Properties();

        try (InputStream input = new FileInputStream("config.properties")) {
            props.load(input);
        } catch (IOException ignored) {
        }

        return props;
    }

    /**
     * Gets the properties.
     *
     * @return properties of config
     */
    public Properties getProps()
    {
        return props;
    }
}
