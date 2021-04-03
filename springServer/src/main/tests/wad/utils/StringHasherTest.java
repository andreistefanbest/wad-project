package wad.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringHasherTest {

    final String password = "12345678";
    final String hashedPassword = "-7474670634070922842409221270356628584256731607689493240956293120159155444145";
    StringHasher stringHasher;

    @BeforeEach
    void setUp() {
        stringHasher = new StringHasher();
    }

    @Test
    void getHash() throws Exception {
        String hash = stringHasher.getHash(password);

        assertEquals(hashedPassword, hash);
    }
}