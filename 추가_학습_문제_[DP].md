# 누적 합(Prefix Sum) & DP 패턴 학습

## 🟢 레벨 1: 기초 다지기 (개념 이해)

먼저 가장 기본적인 "누적해서 더하기"와 "왼쪽 vs 오른쪽 비교하기"를 연습합니다.

### 1. Running Sum of 1d Array (LeetCode 1480)
- [ ] [1. Running Sum of 1d Array](https://leetcode.com/problems/running-sum-of-1d-array/)

**핵심:** `answer[i] = nums[0] + ... + nums[i]`
**추천 이유:** 가장 기초적인 누적 합(Prefix Sum) 문제입니다. 반복문 하나로 값을 누적시키는 감각을 익힐 수 있습니다.

**난이도:** 매우 쉬움

### 2. Find Pivot Index (LeetCode 724)
- [ ] [724. Find Pivot Index](https://leetcode.com/problems/find-pivot-index/)

**핵심:** 왼쪽 합과 오른쪽 합이 같아지는 인덱스 찾기.

**추천 이유:** 질문하신 문제와 아주 유사합니다. 전체 합을 구해놓고, **왼쪽 합을 누적해가며 오른쪽 합을 계산(전체 - 왼쪽)**하는 방식입니다. **"정보의 재활용"**을 배울 수 있습니다.

---

## 🟡 레벨 2: "왼쪽 & 오른쪽" 패턴 마스터 (핵심 추천)

질문하신 문제(Product of Array Except Self)와 논리 구조가 99% 똑같은 문제들입니다. "왼쪽에서 한 번, 오른쪽에서 한 번" 전략이 필수입니다.

### 3. Trapping Rain Water (LeetCode 42) ⭐ (강력 추천)
- [ ] [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)

**문제:** 빗물이 얼마나 고일지 계산하라.

**논리:** 어떤 위치에 물이 고이려면, 내 왼쪽에서 가장 높은 벽과 내 오른쪽에서 가장 높은 벽을 알아야 합니다.

**접근법:**

- 왼쪽에서 오른쪽으로 가며 `maxLeft` 배열 만들기.
- 오른쪽에서 왼쪽으로 가며 `maxRight` 배열 만들기.
- 둘 중 낮은 벽 높이만큼 물이 고임.

**추천 이유:** Product Except Self 문제의 시각화 버전이라고 봐도 무방합니다. 이 문제를 풀면 오늘 배운 내용이 완벽히 이해됩니다.

### 4. Candy (LeetCode 135)
- [ ] [135. Candy](https://leetcode.com/problems/candy/)

**문제:** 아이들에게 사탕을 나눠주는데, 옆 친구보다 점수가 높으면 사탕을 더 받아야 한다.

**논리:**

- 왼쪽 친구보다 점수가 높으면 사탕 +1 (왼쪽 → 오른쪽 순회).
- 오른쪽 친구보다 점수가 높으면 사탕 +1 (오른쪽 → 왼쪽 순회).

**추천 이유:** 조건을 만족시키기 위해 반복문을 두 번 분리해서 실행해야 한다는 점을 명확하게 보여줍니다.

---

## 🔴 레벨 3: 응용 및 심화

이 개념을 다른 알고리즘과 섞거나, 조금 더 꼬아놓은 문제입니다.

### 5. Maximum Product Subarray (LeetCode 152)
- [ ] [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)

**문제:** 연속된 부분 배열의 곱 중 최댓값 찾기.

**논리:** 음수와 음수가 곱해지면 양수가 되는 성질 때문에, 단순히 누적만 해서는 안 되고 최솟값과 최댓값을 동시에 유지하며 가야 합니다.

**추천 이유:** 누적 곱(Prefix Product)의 개념을 동적 계획법(DP)으로 확장하는 문제입니다.

### 6. Subarray Sum Equals K (LeetCode 560)
- [ ] [560. Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

**문제:** 부분 배열의 합이 K가 되는 경우의 수 찾기.

**논리:** 단순 누적 합만으로는 $O(N^2)$이 나옵니다. **누적 합 + 해시맵(HashMap)**을 사용하여 $O(N)$으로 줄여야 합니다.

**추천 이유:** "누적된 정보를 어떻게 효율적으로 검색할 것인가?"에 대한 다음 단계의 고민을 하게 해줍니다.

---

## 🔥 레벨 4: 극상급 도전 (Hard)

이전의 누적 합 개념과 복합적인 알고리즘을 결합한 문제들입니다.

### 7. Largest Rectangle in Histogram (LeetCode 84) ⭐⭐ (극강 추천)
- [ ] [84. Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/)

**문제:** 히스토그램에서 가장 큰 직사각형의 넓이를 구하라.

**논리:** Trapping Rain Water와 유사하지만, 더 복잡합니다. 각 막대마다 왼쪽에서 확장 가능한 거리와 오른쪽에서 확장 가능한 거리를 구해야 합니다.

**접근법:**

- 스택(Stack)을 사용해 왼쪽 경계와 오른쪽 경계를 $O(N)$으로 계산.
- 각 막대의 높이 × (오른쪽 - 왼쪽) 계산하여 최댓값 도출.

**추천 이유:** 누적 합 + 스택 + 투 포인터 개념을 모두 필요로 하는 마스터급 문제입니다. 이 문제를 풀 수 있으면 대부분의 배열 최적화 문제를 해결할 수 있습니다.

**난이도:** Hard

### 8. Pour Water (LeetCode 755)
- [ ] [755. Pour Water](https://leetcode.com/problems/pour-water/)

**문제:** 높이가 다른 2D 배열에 물을 붓는데, 물이 흘러가는 경로를 시뮬레이션하라.

**논리:** 각 물 입자가 떨어질 때 양쪽(왼쪽, 오른쪽)의 경향을 비교하며 최저점으로 흘러가야 합니다.

**접근법:**

- 물이 떨어질 위치에서 왼쪽으로 내려갈 수 있는지, 오른쪽으로 내려갈 수 있는지 탐색.
- 양쪽 모두 내려갈 수 없으면 그 자리에서 멈춤.
- 이를 $V$번 반복(물의 양).

**추천 이유:** 누적 합과 좌우 탐색을 동시에 활용하는 시뮬레이션 문제입니다. 실제 물리적 상황을 코드로 표현하는 능력을 기르게 됩니다.

**난이도:** Hard
---

## 💡 학습 로드맵
1. **Find Pivot Index (724번):** "왼쪽 합과 오른쪽 합"을 동시에 다루는 연습.
2. **Trapping Rain Water (42번):** "왼쪽 최대, 오른쪽 최대" 배열을 따로 만드는 연습. 
3. **Candy (135번):** 조건에 따라 양방향 순회를 적용하는 연습.
