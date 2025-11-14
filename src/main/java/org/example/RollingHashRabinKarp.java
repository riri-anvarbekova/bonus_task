package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RollingHashRabinKarp {

    private static final long BASE = 257;
    private static final long MOD = 1_000_000_007;

    public static List<Integer> search(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        if (m > n) return result;

        long patternHash = 0;
        long currentHash = 0;
        long highestPow = 1;

        for (int i = 0; i < m - 1; i++) {
            highestPow = (highestPow * BASE) % MOD;
        }

        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * BASE + pattern.charAt(i)) % MOD;
            currentHash = (currentHash * BASE + text.charAt(i)) % MOD;
        }

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == currentHash) {
                if (text.substring(i, i + m).equals(pattern)) {
                    result.add(i);
                }
            }
            if (i < n - m) {
                currentHash = (currentHash - text.charAt(i) * highestPow % MOD + MOD) % MOD;
                currentHash = (currentHash * BASE + text.charAt(i + m)) % MOD;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example texts and patterns
        List<String> texts = List.of(
                "abcabc", // short
                "ababcabcabababd", // medium
                "abxabcabcabyabcabcabcababcababcababcababcababc" // long
        );

        List<String> patterns = List.of(
                "abc",
                "ababd",
                "abcab"
        );

        String csvFile = "matches_with_metrics.csv";

        try (FileWriter writer = new FileWriter(csvFile)) {
            // CSV header
            writer.append("Text,Pattern,TextLength,PatternLength,MatchCount,ExecutionTimeMs,MatchIndices\n");

            for (int i = 0; i < texts.size(); i++) {
                String text = texts.get(i);
                String pattern = patterns.get(i);

                // Measure execution time
                long startTime = System.nanoTime();
                List<Integer> matches = search(text, pattern);
                long endTime = System.nanoTime();
                double elapsedMs = (endTime - startTime) / 1_000_000.0;

                // CSV row
                writer.append("\"").append(text).append("\",")
                        .append("\"").append(pattern).append("\",")
                        .append(String.valueOf(text.length())).append(",")
                        .append(String.valueOf(pattern.length())).append(",")
                        .append(String.valueOf(matches.size())).append(",")
                        .append(String.format("%.3f", elapsedMs)).append(",")
                        .append("\"").append(matches.toString()).append("\"\n");

                System.out.println("Pattern \"" + pattern + "\": matches=" + matches.size()
                        + ", time=" + String.format("%.3f", elapsedMs) + "ms, indices=" + matches);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
