package net.alcuria.online.client;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.mappings.Ouya;

/**
 * Imported from game source
 */
public class FriendlyKeyName {
    public static String get(int key) {
        switch (key) {
            case 21:
                return "left";
            case 22:
                return "right";
            case 19:
                return "up";
            case 20:
                return "down";
            case 8:
                return "1";
            case 9:
                return "2";
            case 10:
                return "3";
            case 11:
                return "4";
            case 12:
                return "5";
            case 13:
                return "6";
            case 14:
                return "7";
            case 15:
                return "8";
            case 16:
                return "9";
            case 7:
                return "0";
            case 60:
                return "r.sh";
            case 130:
                return "r.ctr";
            case 58:
                return "r.alt";
            case 59:
                return "l.sh";
            case 129:
                return "l.ctr";
            case 57:
                return "l.alt";
            case 62:
                return "spc";
            case 66:
                return "entr";
            case 131:
                return "esc";
            case 133:
                return "ins";
            case 67:
                return "del";
            case 3:
                return "home";
            case 132:
                return "end";
            case 93:
                return "pgdn";
            case 92:
                return "pgup";
        }
        if ((key >= 29) && (key <= 54)) {
            return Character.toString((char) (key + 68));
        }
        if (Gdx.app.getType() == Application.ApplicationType.Android) {
            if (key == Ouya.BUTTON_L1) {
                return "L1";
            }
            if (key == Ouya.BUTTON_L2) {
                return "L2";
            }
            if (key == Ouya.BUTTON_L3) {
                return "L3";
            }
            if (key == Ouya.BUTTON_R1) {
                return "R1";
            }
            if (key == Ouya.BUTTON_R2) {
                return "R2";
            }
            if (key == Ouya.BUTTON_R3) {
                return "R3";
            }
        }
        return "???";
    }
}
