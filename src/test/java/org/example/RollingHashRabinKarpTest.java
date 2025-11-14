package org.example;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RollingHashRabinKarpTest {

    @Test
    void testShortString() {
        String text = "hello";
        String pattern = "ell";
        List<Integer> found = RollingHashRabinKarp.search(text, pattern);
        assertEquals(List.of(1), found);
    }

    @Test
    void testMediumString() {
        String text = "abracadabra";
        String pattern = "abra";
        List<Integer> found = RollingHashRabinKarp.search(text, pattern);
        assertEquals(List.of(0, 7), found);
    }

    @Test
    void testLongString() {
        String text = "a".repeat(5000) + "b" + "a".repeat(5000);
        String pattern = "ba";
        List<Integer> found = RollingHashRabinKarp.search(text, pattern);
        assertEquals(List.of(5000), found);
    }

    @Test
    void testNoMatches() {
        String text = "abcdefg";
        String pattern = "xyz";
        List<Integer> found = RollingHashRabinKarp.search(text, pattern);
        assertTrue(found.isEmpty());
    }

    @Test
    void testRepeatedCharacters() {
        String text = "aaaaaa";
        String pattern = "aaa";
        List<Integer> found = RollingHashRabinKarp.search(text, pattern);
        assertEquals(List.of(0, 1, 2, 3), found);
    }
}
