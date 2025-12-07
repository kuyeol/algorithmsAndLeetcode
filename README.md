# 🚀 High-Efficiency Algorithm Study Plan

> **최소 시간으로 최대 효율**을 위한 알고리즘 학습 로드맵.  
> `Blind 75`와 `NeetCode 150`을 기반으로, 실무 및 코딩 테스트에 가장 빈출되는 **핵심 패턴(Archetype)** 문제

## 💡 학습 전략 (Strategy)

*   **황금 비율:** `Easy(20%)` : `Medium(70%)` : `Hard(10%)`
*   **Easy:** 문법 확인 및 워밍업
*   **Medium:** **학습의 핵심.** 유형별 표준 패턴 체화 (시간 투자의 70% 집중)
*   **Hard:** 사고력 확장 (Medium 2개를 섞어놓은 형태)

---

# 📚 Problem List (Essential 20)

## 1. 배열 & 해시 (Arrays & Hashing)
*기본 자료구조 활용 능력. 모든 문제의 기초.*

### 1_Two Sum (Easy)

- [x] [1. Two Sum (Easy)](https://leetcode.com/problems/two-sum/) - `HashMap` 사용의 정석

> [!NOTE]
> **핵심:** 브루트 포스($O(n^2)$)가 아닌 해시맵($O(n)$)을 사용하는 것이 관건.  
> **접근:** 반복문을 돌며 `target - current_value`가 해시맵(Key: 숫자, Value: 인덱스)에 존재하는지 확인한다.

#### 풀이

```java
class Solution {
  public static int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if (map.containsKey(complement)) {
        return new int[] { map.get(complement), i };
      }
      map.put(nums[i], i);
    }
    return null;
  }
}
```

---

### 49_Group Anagrams (Java Patterns)

- [x] [49. Group Anagrams (Medium)](https://leetcode.com/problems/group-anagrams/) - 정렬과 해시의 조합

> [!NOTE]
> **핵심:** 애너그램(철자가 같은 단어)들을 어떻게 동일한 키로 묶을 것인가?  
> **접근:** 각 문자열을 정렬(예: "eat" -> "aet")하여 이를 Key로 사용하거나,알파벳 개수 카운트 배열(26개)을 Key로 사용하여 해시맵에 리스트를 저장한다.

- 문자열을 정렬하여 Map의 Key로 사용할 문자열을 반환. `Arrays.toString()`보다 `new String()`이 성능상 유리.

### 풀이

#### 0. 공통 Helper Method (Key 생성)

```java
private String toSortWord(String str) {
    char[] ch = str.toCharArray();
    Arrays.sort(ch);
    return new String(ch);
}
```

#### For Loop Version 6~7ms

##### 1. [Standard] 고전적 For 루프 (Classic)

가장 기초적인 방법. 디버깅이 쉽고 직관적임.

**특징:** `containsKey` 체크 후 `put`.

**장점:** 가독성이 좋고, 로직 변경(예외 처리 등)에 유연함.

```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();

    for (String s : strs) {
        String key = toSortWord(s);

        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(s);
    }

    return new ArrayList<>(map.values());
}
```

#### 2. computeIfAbsent 사용 (Modern & Efficient)

자바 8에 도입된 API. 코드가 가장 간결하고, List 생성 비용도 최적화됨.

**특징:** Key가 없을 때만 `new ArrayList`가 실행됨 (Lazy Evaluation).


```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();

    for (String s : strs) {
        String key = toSortWord(s);
        // 없으면 만들고, 리턴된 리스트에 바로 add (Chaining)
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }

    return new ArrayList<>(map.values());
}
```

#### 3. [Alternative] putIfAbsent 사용

`computeIfAbsent` 이전의 방식.

**단점:** Key가 이미 존재해도 `new ArrayList<>()` 객체가 매번 생성되었다가 GC됨 (메모리 낭비).

```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();

    for (String s : strs) {
        String key = toSortWord(s);
        
        // 주의: 키가 있어도 new ArrayList()는 항상 실행됨 (비효율)
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(s);
    }

    return new ArrayList<>(map.values());
}
```

### Stream API Version 13ms

#### 4. [Stream] 스트림 API 

가독성과 선언적 프로그래밍을 중시할 때 사용.

**특징:** `Collectors.groupingBy`를 이용한 한 줄 처리.
**단점:** 초기 오버헤드로 인해 코테에서는 for문 대비 느림(약 2배 차이).

```java
public List<List<String>> groupAnagrams(String[] strs) {
    return new ArrayList<>(
        Arrays.stream(strs)
              .collect(Collectors.groupingBy(this::toSortWord))
              .values());
}
```

---
### 238_Product of Array Except Self (Medium)

- [ ] [238. Product of Array Except Self (Medium)](https://leetcode.com/problems/product-of-array-except-self/) - `O(n)` 사고력 (나눗셈 금지)
> [!NOTE]
> **핵심:** 나눗셈을 쓰지 않고 자기 자신을 제외한 곱을 구하는 방법 (Prefix & Suffix).  
> **접근:** 왼쪽에서부터 누적 곱을 구한 배열과, 오른쪽에서부터 누적 곱을 구한 값을 곱해서 한 번에 계산한다. 공간 복잡도 $O(1)$(결과 배열 제외) 최적화까지 고민해볼 것.


## 2. 투 포인터 & 슬라이딩 윈도우 (Two Pointers & Sliding Window)

*효율성(시간 복잡도) 테스트 빈출 유형.*

### 125_Valid Palindrome (Easy)

- [ ] [125. Valid Palindrome (Easy)](https://leetcode.com/problems/valid-palindrome/) - 양방향 투 포인터 기초
> [!NOTE]
> **핵심:** 문자열 전처리(특수문자 제거, 소문자 변환)와 양 끝 인덱스 조작.  
> **접근:** `left`는 0, `right`는 끝 인덱스에서 시작해 서로 중앙으로 좁혀오며 문자가 같은지 비교한다.

### 15_3Sum (Medium)

- [ ] [15. 3Sum (Medium)](https://leetcode.com/problems/3sum/) - 중복 처리가 핵심인 투 포인터 확장
> [!NOTE]
> **핵심:** 3개의 수의 합을 0으로 만들기. 정렬 후 하나를 고정하고 나머지 둘을 투 포인터로 찾기.  
> **접근:** 배열을 먼저 **정렬**해야 한다. `i`를 고정시키고 나머지 구간에서 `Two Sum` 문제를 푸는 방식. 중복된 숫자를 건너뛰는(Skip) 로직이 필수.

### 3_Longest Substring Without Repeating Characters (Medium)

- [ ] [3. Longest Substring Without Repeating Characters (Medium)](https://leetcode.com/problems/longest-substring-without-repeating-characters/) - **★ 슬라이딩 윈도우 교과서**
> [!NOTE]
> **핵심:** 중복 문자가 나오기 전까지 윈도우를 늘리고, 중복이 나오면 윈도우를 줄이는 테크닉.  
> **접근:** `Set`이나 `Map`을 사용해 윈도우 내의 문자를 저장한다. 중복 문자를 만나면 `left` 포인터를 이동시켜 중복을 제거하며 최대 길이를 갱신한다.

## 3. 스택 (Stack)

*순서가 중요하거나 짝을 맞춰야 할 때.*

### 20_Valid Parentheses (Easy)

- [ ] [20. Valid Parentheses (Easy)](https://leetcode.com/problems/valid-parentheses/) - 스택 입문 필수
> [!NOTE]
> **핵심:** LIFO(Last In First Out) 구조 이해.  
> **접근:** 여는 괄호는 스택에 `push`. 닫는 괄호가 나오면 스택 `top`과 짝이 맞는지 확인하고 `pop`. 마지막에 스택이 비어있어야 성공.

### 739_Daily Temperatures (Medium)

- [ ] [739. Daily Temperatures (Medium)](https://leetcode.com/problems/daily-temperatures/) - **Monotonic Stack (단조 스택)** 패턴
> [!NOTE]
> **핵심:** 나보다 더 따뜻한 날이 올 때까지 기다리기.  
> **접근:** 스택에는 **인덱스**를 저장한다. 현재 온도가 스택의 top 온도보다 높다면, 스택에서 꺼내(pop) 정답 배열의 `현재 인덱스 - 꺼낸 인덱스` 위치에 저장한다. (감소하는 순서 유지)

## 4. 트리 & 그래프 (Trees & Graphs)

*한국 기업 코테 출제 비중 1순위 (DFS/BFS).*

### 226_Invert Binary Tree (Easy)

- [ ] [226. Invert Binary Tree (Easy)](https://leetcode.com/problems/invert-binary-tree/) - 재귀(Recursion) 감각 익히기
> [!NOTE]
> **핵심:** 트리의 좌우 자식을 바꾸는 재귀적 사고.  
> **접근:** 루트의 `left`와 `right`를 swap한다. 그리고 `left` 자식과 `right` 자식에 대해 각각 재귀적으로 함수를 호출한다.

### 200_Number of Islands (Medium)

- [ ] [200. Number of Islands (Medium)](https://leetcode.com/problems/number-of-islands/) - **★ Grid DFS/BFS 필수 문제**
> [!NOTE]
> **핵심:** 연결된 요소(Connected Component)의 개수 세기.  
> **접근:** 2중 for문으로 격자를 순회하다가 땅('1')을 만나면 카운트를 증가시키고, DFS/BFS를 수행하여 연결된 모든 땅을 방문 처리('0'으로 변경)한다.

### 102_Binary Tree Level Order Traversal (Medium)

- [ ] [102. Binary Tree Level Order Traversal (Medium)](https://leetcode.com/problems/binary-tree-level-order-traversal/) - BFS 계층 순회
> [!NOTE]
> **핵심:** 큐(Queue)를 이용해 깊이(Level) 별로 노드를 처리하기.  
> **접근:** 큐에 노드를 넣고, 큐의 현재 사이즈(`len(q)`)만큼 반복문을 돌리는 것이 핵심 패턴. 그래야 같은 레벨의 노드들을 묶을 수 있다.

### [207. Course Schedule] (Medium)

- [ ] [207. Course Schedule (Medium)](https://leetcode.com/problems/course-schedule/) - 위상 정렬 & 사이클 탐지
> [!NOTE]
> **핵심:** 방향 그래프(Directed Graph)에서 순환(Cycle)이 존재하는지 판별.  
> **접근:** DFS를 이용해 방문 상태를 3가지(미방문, 방문 중, 방문 완료)로 나누거나, 진입 차수(Indegree)를 이용한 위상 정렬(Kahn's Algorithm)을 사용한다.

## 5. 다이나믹 프로그래밍 (Dynamic Programming)

*점화식 세우기 연습.*

### 70_Climbing Stairs (Easy)

- [ ] [70. Climbing Stairs (Easy)](https://leetcode.com/problems/climbing-stairs/) - 피보나치 변형 (DP 기초)
> [!NOTE]
> **핵심:** 큰 문제를 작은 문제의 합으로 쪼개기.  
> **접근:** $n$계단에 도달하는 방법 = ($n-1$계단 방법 수) + ($n-2$계단 방법 수). 즉, `dp[i] = dp[i-1] + dp[i-2]`.

### 322_Coin Change (Medium)

- [ ] [322. Coin Change (Medium)](https://leetcode.com/problems/coin-change/) - 배낭 문제(Knapsack) 유형
> [!NOTE]
> **핵심:** 최소 개수로 금액을 만드는 방법. (Greedy로 풀 수 없음)  
> **접근:** `dp[amount]` = 금액을 만드는 최소 동전 수. `dp[i] = min(dp[i], dp[i - coin] + 1)` 점화식을 사용하여 Bottom-up으로 채운다.

### 300_Longest Increasing Subsequence (Medium)

- [ ] [300. Longest Increasing Subsequence (Medium)](https://leetcode.com/problems/longest-increasing-subsequence/) - LIS `O(nlogn)` 최적화
> [!NOTE]
> **핵심:** 가장 긴 증가하는 부분 수열 찾기.  
> **접근:** 기본 DP는 $O(n^2)$ (`dp[i]` = i번째 문자를 포함했을 때의 최대 길이). $O(n \log n)$으로 풀려면 `tails` 배열을 만들고 이분 탐색(Lower Bound)을 활용해야 한다.

## 6. 이분 탐색 & 구간 (Binary Search & Intervals)

*정렬된 데이터 처리.*

### 33_Search in Rotated Sorted Array (Medium)

- [ ] [33. Search in Rotated Sorted Array (Medium)](https://leetcode.com/problems/search-in-rotated-sorted-array/) - 조건이 까다로운 이분 탐색
> [!NOTE]
> **핵심:** 회전되어 있어도 정렬된 부분(Sorted Half)은 반드시 존재한다.  
> **접근:** 중간값 `mid`를 기준으로 왼쪽 절반이 정렬되어 있는지, 오른쪽 절반이 정렬되어 있는지 확인한다. 타겟이 그 정렬된 범위 안에 있는지 체크하여 탐색 범위를 좁힌다.
 
### 53_Maximum Subarray (Medium)

- [ ] [53. Maximum Subarray (Medium)](https://leetcode.com/problems/maximum-subarray/) - 카데인 알고리즘 (Kadane's)
> [!NOTE]
> **핵심:** 부분 배열의 합 중 최댓값 구하기 ($O(n)$).  
> **접근:** "이전까지의 합이 음수라면, 버리고 현재 숫자부터 새로 시작하는 게 이득이다." 점화식: `cur_sum = max(num, cur_sum + num)`.

### 56_Merge Intervals (Medium)

- [ ] [56. Merge Intervals (Medium)](https://leetcode.com/problems/merge-intervals/) - 구간 병합 및 정렬
> [!NOTE]
> **핵심:** 겹치는 구간을 하나로 합치기.  
> **접근:** 시작 시간(`start`)을 기준으로 먼저 정렬하는 것이 필수. 순회하면서 앞 구간의 `end`가 뒷 구간의 `start`보다 크거나 같다면 겹친 것이므로 병합한다.

---
