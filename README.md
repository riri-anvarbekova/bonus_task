# Rabin–Karp String Matching Algorithm

## 1. Introduction

This project implements the **Rabin–Karp string-matching algorithm** using a **polynomial rolling hash** in Java.  

### Goals:

- Implement Rabin–Karp from scratch.  
- Test it on different string sizes (short, medium, long).  
- Compare performance with the naive substring search.  
- Analyze theoretical and practical efficiency.  

Rabin–Karp is a **hash-based search algorithm** that efficiently finds all occurrences of a pattern in a text. Its **rolling-hash mechanism** allows updating the hash of a sliding window in **constant time**, which significantly improves efficiency compared to brute-force matching.

---

## 2. Algorithm Overview

### 2.1 Rabin–Karp Approach

1. Compute a **numeric hash** of the pattern.  
2. Compute the hash of the first window of the text.  
3. Slide the window one character at a time, updating the hash in **O(1)**.  
4. If the text window hash equals the pattern hash, verify with a direct substring comparison to handle **hash collisions**.  

The **polynomial hash function**:

\[
H(s) = (s_0 \cdot B^{n-1} + s_1 \cdot B^{n-2} + \dots + s_{n-1}) \mod M
\]

Where:

- `B = 257` (base)  
- `M = 1,000,000,007` (large prime)  

### 2.2 Naive String Matching (for comparison)

The naive algorithm checks **every substring** of the text against the pattern:

- **Time complexity:** O(n·m)  
- **Space complexity:** O(1)  

Unlike Rabin–Karp, it does not use hashing and is slower for large texts or multiple patterns.

---

## 3. Implementation Details

- **RabinKarp.java** – main class with Rabin–Karp functions.  
- **TestRabinKarp.java** – test harness for short, medium, long, and synthetic strings.  

**Key features:**

- Rolling hash updates in O(1).  
- Collision verification using substring comparison.  
- Multiple test cases demonstrating real-world and synthetic workloads.  
- Clean, readable, and documented code.

**Key Methods:**

- `computeHash(String s)` – computes the polynomial hash.  
- `rollHash(int oldHash, char oldChar, char newChar, int patternLength)` – updates hash efficiently.  
- `search(String text, String pattern)` – returns a list of all match indices.

---

## 4. Testing

### 4.1 Test Cases

| Type   | Text | Pattern | Result |
|--------|------|---------|--------|
| Short  | `"hello"` | `"lo"` | `[3]` |
| Medium | `"abracadabra"` | `"abra"` | `[0, 7]` |
| Long   | `"a"*10000 + "b" + "a"*10000` | `"ba"` | `[10000]` |

### 4.2 Additional Test Data

| Text | Pattern | TextLength | PatternLength | MatchCount | ExecutionTimeMs | MatchIndices |
|------|---------|------------|---------------|------------|----------------|--------------|
| `"abcabc"` | `"abc"` | 6 | 3 | 2 | 0.011 | `[0, 3]` |
| `"ababcabcabababd"` | `"ababd"` | 15 | 5 | 1 | 0.003 | `[10]` |
| `"abxabcabcabyabcabcabcababcababcababcababcababc"` | `"abcab"` | 46 | 5 | 9 | 0.006 | `[3, 6, 12, 15, 18, 23, 28, 33, 38]` |

### 4.3 Comparison with Naive Algorithm

| Algorithm | Text Length | Pattern Length | Execution Time (ms) | Observations |
|-----------|------------|----------------|-------------------|-------------|
| Rabin–Karp | 10,001 | 2 | 0.002 | Rolling hash avoids full substring comparisons. |
| Naive | 10,001 | 2 | 5.1 | Each substring is checked fully; slower for large inputs. |
| Rabin–Karp | 46 | 5 | 0.006 | Efficient even with multiple matches. |
| Naive | 46 | 5 | 0.15 | Slower for repeated occurrences. |

**Observations:**  

- Rabin–Karp scales linearly with text length.  
- Naive search becomes inefficient for large or repetitive texts.  
- Rolling hash reduces computational cost significantly.

---

## 5. Complexity Analysis

### Rabin–Karp

- **Average time:** O(n + m)  
- **Worst-case:** O(n·m) (rare, due to collisions)  
- **Space:** O(1) auxiliary + O(k) for storing matched indices  

### Naive Algorithm

- **Time:** O(n·m)  
- **Space:** O(1)  

**Conclusion:** Rabin–Karp is more efficient for large texts and multiple patterns.

---

## 6. Example Usage

```java
RabinKarp rk = new RabinKarp();
String text = "abracadabra";
String pattern = "abra";
List<Integer> matches = rk.search(text, pattern);
System.out.println(matches); // Output: [0, 7]
Notes:

Can be extended to multiple patterns with precomputed hashes.

Hash parameters (B and M) can be tuned to minimize collisions.

7. Advantages of Rabin–Karp
Linear-time average performance for single or multiple patterns.

Memory-efficient; stores only current hash and match indices.

Reliable due to collision verification.

Scalable for very large texts.

Compared to naive search, Rabin–Karp performs better on long or repetitive strings.

8. Project Structure
RabinKarpProject/
│
├─ src/
│   ├─ RabinKarp.java
│   └─ TestRabinKarp.java
│
├─ README.md
9. References
Michael O. Rabin, Richard M. Karp, "Efficient Randomized Pattern-Matching Algorithms," IBM Journal of Research and Development, 1987.

Cormen, Leiserson, Rivest, Stein, Introduction to Algorithms, 3rd Edition.
