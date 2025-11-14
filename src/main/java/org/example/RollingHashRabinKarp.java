package org.example;
import java.util.*;

public class RollingHashRabinKarp {

    // Base for polynomial rolling hash
    private static final long BASE = 257;
    // Large prime modulus to reduce collisions
    private static final long MOD = 1_000_000_007;

    /**
     * Rabin–Karp string matching using polynomial rolling hash.
     * @param text the full text where we search
     * @param pattern the substring to find
     * @return list of starting indices of all matches
     */

    public static List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();

        if (m > n) return result;

        long patternHash = 0;
        long currentHash = 0;
        long highestPow = 1;  // base^(m-1)

        // Precompute BASE^(m-1) % MOD
        for (int i = 0; i < m - 1; i++) {
            highestPow = (highestPow * BASE) % MOD;
        }

        // Compute initial hash for pattern and first window
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * BASE + pattern.charAt(i)) % MOD;
            currentHash = (currentHash * BASE + text.charAt(i)) % MOD;
        }

        // Sliding window over the text
        for (int i = 0; i <= n - m; i++) {
            if (patternHash == currentHash) {
                // Collision check: verify exact match
                if (text.substring(i, i + m).equals(pattern)) {
                    result.add(i);
                }
            }

            // Slide the window: remove leftmost char, add rightmost char
            if (i < n - m) {
                currentHash = (currentHash - text.charAt(i) * highestPow % MOD + MOD) % MOD;
                currentHash = (currentHash * BASE + text.charAt(i + 1 + m - 1)) % MOD;
            }
        }

        return result;
    }

}