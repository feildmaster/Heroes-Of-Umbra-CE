package com.feildmaster.umbra.util;

import net.alcuria.online.client.Config;
import net.alcuria.online.client.FriendlyKeyName;
import net.alcuria.online.client.ui.Hotkeys;

/**
 * Utility for Mapping game keys
 *
 * @author feildmaster
 */
public enum Key {
    JUMP {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_JUMP = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_JUMP;
        }
    },
    ATTACK {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_ATTACK = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_ATTACK;
        }
    },
    CONFIRM {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_CONFIRM = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_CONFIRM;
        }
    },
    CANCEL {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key) && !Key.get(key).equals(MENU)) {
                return false;
            }
            Config.CUST_KEY_CANCEL = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_CANCEL;
        }
    },
    MENU {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key) && !Key.get(key).equals(CANCEL)) {
                return false;
            }
            Config.CUST_KEY_MENU = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_MENU;
        }
    },
    UP {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key) && !Key.get(key).equals(INSPECT)) {
                return false;
            }
            Config.CUST_KEY_UP = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_UP;
        }
    },
    INSPECT {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key) && !Key.get(key).equals(UP)) {
                return false;
            }
            Config.CUST_KEY_INSPECT = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_INSPECT;
        }
    },
    DOWN {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_DOWN = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_DOWN;
        }
    },
    LEFT {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_LEFT = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_LEFT;
        }
    },
    RIGHT {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_RIGHT = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_RIGHT;
        }
    },
    WORLDMAP {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_WORLDMAP = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_WORLDMAP;
        }
    },
    MAP_UP {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_MINIMAP_UP = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_MINIMAP_UP;
        }
    },
    MAP_DOWN {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_MINIMAP_DOWN = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_MINIMAP_DOWN;
        }
    },
    MAP_LEFT {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_MINIMAP_LEFT = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_MINIMAP_LEFT;
        }
    },
    MAP_RIGHT {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_MINIMAP_RIGHT = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_MINIMAP_RIGHT;
        }
    },
    SKILL_1 {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_SKILL_1 = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_SKILL_1;
        }
    },
    SKILL_2 {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_SKILL_2 = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_SKILL_2;
        }
    },
    SKILL_3 {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_SKILL_3 = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_SKILL_3;
        }
    },
    SKILL_4 {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_SKILL_4 = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_SKILL_4;
        }
    },
    SKILL_5 {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_SKILL_5 = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_SKILL_5;
        }
    },
    SKILL_6 {
        @Override
        boolean _set(int key) {
            if (!isAvailable(key)) {
                return false;
            }
            Config.CUST_KEY_SKILL_6 = key;
            return true;
        }

        @Override
        public int get() {
            return Config.CUST_KEY_SKILL_6;
        }
    },
    INVALID {
        @Override
        boolean _set(int key) {
            return false;
        }

        @Override
        public int get() {
            return -1;
        }
    };

    abstract boolean _set(int key);

    public boolean set(int key) {
        if (_set(key)) {
            Config.customControls = true;
            return true;
        }
        return false;
    }

    public abstract int get();

    public String getName() {
        return FriendlyKeyName.get(this.get());
    }

    public static Key get(int key) {
        if (key >= 0) {
            for (Key _key : values()) {
                if (_key.get() == key) {
                    return _key;
                }
            }
        }
        return INVALID;
    }

    public static boolean isAvailable(int key) {
        return get(key) == INVALID;
    }

    public static boolean isCustom() {
        return Config.customControls;
    }

    public static void updateHotkeyStrings() {
        if (Hotkeys.hotkeyStrings == null) {
            Hotkeys.hotkeyStrings = new String[Hotkeys.NUM_HOTKEYS];
        }

        final String[] strings = Hotkeys.hotkeyStrings;
        strings[0] = SKILL_1.getName();
        strings[1] = SKILL_2.getName();
        strings[2] = SKILL_3.getName();
        strings[3] = SKILL_4.getName();
        strings[4] = SKILL_5.getName();
        strings[5] = SKILL_6.getName();

    }

    public static void resetAll() {
        Config.setDefaultKeys();
        updateHotkeyStrings();
    }
}
