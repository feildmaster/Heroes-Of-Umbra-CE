package com.feildmaster.umbra;

import static org.junit.Assert.assertEquals;

import net.alcuria.online.client.FriendlyKeyName;
import org.junit.Test;

public class CharacterTest {
    @Test
    public void testUnknownCharacter() {
        assertEquals("???", FriendlyKeyName.get(77));
    }

    @Test
    public void testAlphabet() {
        for (int x = 0; x < 26; x++) {
            // Skip the first 29 characters
            final char character = (char) (x + 29);
            final String val = Character.toString((char) (character + 68));
            assertEquals(val, FriendlyKeyName.get(character));
        }
    }
}
