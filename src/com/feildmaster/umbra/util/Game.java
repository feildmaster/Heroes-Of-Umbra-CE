package com.feildmaster.umbra.util;

import net.alcuria.online.client.Config;

/**
 * Utility for game information
 *
 * @author feildmaster
 */
public final class Game {
    private Game() {
        // nope
    }

    public static String getVersion() {
        return Config.version;
    }
}
