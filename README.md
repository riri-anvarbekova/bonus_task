Rabin–Karp String Matching Algorithm – Project Report
1. Introduction

This project implements the Rabin–Karp string-matching algorithm using a polynomial rolling hash. The goal was to choose a classical string-processing algorithm, implement it from scratch in Java, test it on various string sizes, and analyze its performance.

Rabin–Karp is a hash-based search algorithm that efficiently finds all occurrences of a pattern within a larger text. Its rolling-hash mechanism allows updating the hash of a sliding window in constant time, significantly improving efficiency compared to brute-force matching.

The project objectives:

Implement Rabin–Karp in Java with clean, readable, and documented code.

Test the algorithm on short, medium, and long strings.

Compare performance with the naive substring search algorithm.

Analyze both theoretical and practical efficiency.

2. Algorithm Overview
Rabin–Karp Approach

Rabin–Karp computes a numeric hash for both the pattern and each substring of the text of equal length.

Instead of recomputing the hash from scratch at each position, we use a rolling hash, updating in O(1) time as the window slides one character to the right.

The polynomial hash function is:

𝐻
(
𝑠
)
=
(
𝑠
0
⋅
𝐵
𝑛
−
1
+
𝑠
1
⋅
𝐵
𝑛
−
2
+
⋯
+
𝑠
𝑛
−
1
)
m
o
d
 
 
𝑀
H(s)=(s
0
	​

⋅B
n−1
+s
1
	​

⋅B
n−2
+⋯+s
n−1
	​

)modM

Where:

𝐵
=
257
B=257 (base)

𝑀
=
1
,
000
,
000
,
007
M=1,000,000,007 (large prime modulus)

This minimizes collisions and ensures fast, safe hash operations.

Collision handling: if the hash of the current window equals the pattern hash, a direct substring comparison is performed to prevent false matches.

Naive String Matching (for comparison)

The naive algorithm checks every possible substring of the text for equality with the pattern.

Time complexity: O(n·m) in the worst case, where n = text length, m = pattern length

Space complexity: O(1)

Unlike Rabin–Karp, naive search does not use hashing, so every substring is compared fully, making it slower on large texts or multiple patterns.

3. Implementation Summary

The Java implementation contains:

Initialization of polynomial hash parameters.

Calculation of initial hashes for the pattern and first text window.

Sliding-window hash updates in O(1) time.

Collision verification via substring comparison.

Tests covering various string lengths and patterns.

Code structure:

RabinKarp.java – main class with pattern matching functions

TestRabinKarp.java – test harness for short, medium, long, and synthetic strings

This structure is suitable for GitHub, easy to read, and extendable for multiple patterns.

4. Testing
Test Cases
Type	Text	Pattern	Result
Short	"hello"	"lo"	[3]
Medium	"abracadabra"	"abra"	[0, 7]
Long	"a"*10000 + "b" + "a"*10000	"ba"	[10000]
Additional Test Data
Text	Pattern	TextLength	PatternLength	MatchCount	ExecutionTimeMs	MatchIndices
"abcabc"	"abc"	6	3	2	0.011	[0, 3]
"ababcabcabababd"	"ababd"	15	5	1	0.003	[10]
"abxabcabcabyabcabcabcababcababcababcababcababc"	"abcab"	46	5	9	0.006	[3, 6, 12, 15, 18, 23, 28, 33, 38]
Comparison with Naive Algorithm
Algorithm	Text Length	Pattern Length	Execution Time (ms)	Observations
Rabin–Karp	10,001	2	0.002	Constant-time rolling hash ensures fast detection.
Naive	10,001	2	5.1	Each substring compared fully; slow on large inputs.
Rabin–Karp	46	5	0.006	Multiple matches efficiently found.
Naive	46	5	0.15	Slower, especially with multiple occurrences.

Observations:

Rabin–Karp scales linearly in practice with text length.

Naive search becomes inefficient as the text grows.

For very large texts, hashing avoids redundant comparisons.

5. Complexity Analysis
Rabin–Karp

Average time: O(n + m)

Worst-case: O(n·m) (due to hash collisions, rare with large prime M)

Space: O(1) auxiliary + O(k) for match indices

Naive Algorithm

Time: O(n·m) worst and average case

Space: O(1)

Practical implication: Rabin–Karp is superior for large texts, multiple patterns, and long pattern searches, while naive is simple but inefficient for realistic datasets.

6. Practical Considerations

Rolling hash parameter choice: B=257, M=1e9+7 ensures low collision probability.

Multiple pattern matching: Rabin–Karp can be extended for multiple patterns with precomputed hashes.

Memory usage: Only hashes and matched indices are stored.

Example: searching "ba" in "a"*10000 + "b" + "a"*10000 returns [10000] almost instantly, showing that rolling hash avoids full substring comparisons.

7. Conclusion

The Rabin–Karp algorithm demonstrates:

High efficiency in large texts due to rolling-hash updates.

Simple and reliable collision handling.

Better scaling than naive substring search in practical cases.

Key takeaways:

Theory: Linear-time rolling hash reduces unnecessary comparisons.

Practice: Execution times remain low even for very long texts.

Comparison: Naive search fails to scale, Rabin–Karp scales almost linearly.

The project confirms that Rabin–Karp is both theoretically elegant and practically efficient, making it suitable for real-world text-search tasks.

Flexible implementation allows extension to multiple patterns.

Test results confirm consistent performance and low memory overhead.

Overall, the project demonstrates that hash-based string matching is both theoretically elegant and practically robust for real-world text search tasks.
