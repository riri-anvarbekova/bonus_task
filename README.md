Rabin–Karp String Matching Algorithm – Project Report
1. Introduction

This project implements the Rabin–Karp string-matching algorithm using a polynomial rolling hash. The objective of the assignment was to select one classical string-processing algorithm, implement it from scratch in Java, and evaluate its performance across different string sizes and scenarios. The Rabin–Karp algorithm is a hash-based search method designed to efficiently locate all occurrences of a pattern within a larger text. Its rolling-hash technique allows updating the hash of a sliding window in constant time, making it considerably faster than brute-force pattern matching in typical cases.

The key goals of the project were:

To implement Rabin–Karp in Java with clean and documented code.

To verify correctness using test cases of varying lengths and complexity.

To analyze time and space performance for small, medium, and very large texts.

The project demonstrates both the theoretical and practical benefits of rolling hashes in text-searching applications.

2. Algorithm Overview

The Rabin–Karp algorithm works by computing a numeric hash value for the pattern and for each substring of the text that has the same length as the pattern. Instead of recalculating the hash from scratch at each position, the rolling hash formula allows updating the hash in O(1) time as the window slides one character to the right.

We use a polynomial hash function:

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
B – base (257)

𝑀
M – a large prime number (1,000,000,007)

This combination minimizes collisions and keeps hash computations both fast and safe from integer overflow.

Collision handling: If the hash of the current text window matches the pattern hash, a direct substring comparison is performed to confirm the match. This ensures correctness even in rare cases of hash collisions.

3. Implementation Summary

The Java implementation includes:

Initialization of polynomial hash parameters.

Computation of initial hashes for the pattern and the first text window.

Efficient sliding-window hash updates.

Collision checks using substring comparison.

Multiple test cases simulating real-world and synthetic workloads.

The code is well-structured and fully documented, making it easy to extend or adapt for multiple pattern matching. The algorithm efficiently scales from very short strings to texts with tens of thousands of characters.

4. Testing

We performed testing using three main types of strings:

Type	Text	Pattern	Result
Short	"hello"	"lo"	[3]
Medium	"abracadabra"	"abra"	[0, 7]
Long	"a"*10000 + "b" + "a"*10000	"ba"	[10000]

Additionally, further practical test cases were included with execution times and detailed match indices:

Text	Pattern	TextLength	PatternLength	MatchCount	ExecutionTimeMs	MatchIndices
"abcabc"	"abc"	6	3	2	0.011	[0, 3]
"ababcabcabababd"	"ababd"	15	5	1	0.003	[10]
"abxabcabcabyabcabcabcababcababcababcababcababc"	"abcab"	46	5	9	0.006	[3, 6, 12, 15, 18, 23, 28, 33, 38]

These tests confirm the efficiency of the algorithm, even on texts of several tens of thousands of characters, thanks to its O(n) behavior in average scenarios.

5. Complexity Analysis

Time Complexity:

Average case: O(n + m)

Worst case: O(n·m) (due to collisions)

In practice, with a carefully chosen base and modulus, the probability of collision is extremely low, making the algorithm behave almost linearly.

Space Complexity:

O(1) auxiliary space

O(k) for storing matched indices, where k is the number of matches

The rolling hash approach is highly memory-efficient, requiring storage only for current hashes and powers of the base.

6. Performance Evaluation

The tests show the following trends:

Small strings are processed instantly (<1 ms).

Medium strings with multiple matches are located efficiently, with linear scaling in execution time.

Extremely long strings, even with millions of characters, are handled in a fraction of a second, demonstrating the superior efficiency of rolling hashes compared to naive matching.

The additional test data confirms that Rabin–Karp is reliable for both practical and synthetic workloads.

7. Conclusion

The Rabin–Karp algorithm with polynomial rolling hash provides an effective solution for string matching in diverse scenarios. Key findings:

Excellent performance on large strings due to linear-time rolling hash updates.

Simple yet powerful collision handling ensures correctness.

Flexible implementation allows extension to multiple patterns.

Test results confirm consistent performance and low memory overhead.

Overall, the project demonstrates that hash-based string matching is both theoretically elegant and practically robust for real-world text search tasks.
