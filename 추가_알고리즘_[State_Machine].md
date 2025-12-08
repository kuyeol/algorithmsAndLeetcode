
# *`상태 기계(State Machine)`* 패턴
- 배열의 인덱스(i)뿐만 아니라, **현재의 "상태(State)"**를 변수로 관리하며 순회하는 방식.
- 빈도: ★★★☆☆ (난이도 상)
- 특징: 값의 누적뿐만 아니라 "내가 현재 어떤 상태인가(주식을 샀나? 팔았나?)"를 함께 고려.

---

## 🟢 레벨 1: 기초 상태 관리

### 1. Best Time to Buy and Sell Stock (LeetCode 121) [easy]
- [ ] [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)

**문제:** 주식을 한 번 사고 팔아서 최대 이익을 구하라.

**핵심:** **상태 1: 가장 낮은 가격 추적** | **상태 2: 최대 이익 추적**

**논리:** 
- 현재까지 본 가장 싼 가격(`minPrice`)을 유지.
- 현재 가격에서 최저가를 뺀 이익(`maxProfit`)을 매번 갱신.

**추천 이유:** 상태 기계 패턴의 가장 단순한 형태이다. "최소값 추적"의 개념을 학습.

### 2. Best Time to Buy and Sell Stock II (LeetCode 122) [medium]
- [ ] [122. Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-sell-stock-ii/)

**문제:** 주식을 여러 번 사고 팔아서 최대 이익을 구하라. (보유 기한 제한 없음)

**핵심:** **상태: 현재 주식을 "보유 중인가" vs "보유하지 않은가"**

**논리:**
- 그리디(Greedy) 방식: 가격이 오르면 사고, 내려가기 전에 팔기.
- 또는 DP: `hold`(보유 상태의 최대 수익) vs `sold`(판매 상태의 최대 수익) 두 변수 유지.

**추천 이유:** 단순 누적이 아닌 "조건부 선택(사? 팔? 아무것도 안 함?)"의 개념을 학습.

### 3. Best Time to Buy and Sell Stock with Cooldown (LeetCode 309) [medium]
- [ ] [309. Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)

**문제:** 주식을 여러 번 사고 팔되, 팔고 나면 하루 쉬어야 다시 살 수 있다.

**핵심:** **상태 3가지: `buy`, `sell`, `cooldown`**

**논리:**
- `buy[i]` = i일에 구매 완료했을 때의 최대 수익.
- `sell[i]` = i일에 판매 완료했을 때의 최대 수익.
- `cooldown[i]` = i일에 대기 중일 때의 최대 수익.

**추천 이유:** 3가지 상태를 동시에 관리해야 하므로, 상태 기계의 본질적인 학습.

### 4. Best Time to Buy and Sell Stock III (LeetCode 123) [hard]
- [ ] [123. Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/)

**문제:** 최대 **2번** 거래(사고 팔기)만 할 수 있을 때의 최대 이익.

**핵심:** **상태: 거래 횟수 + 보유 여부** (4가지 상태)

**논리:**
- `buy1`, `sell1` = 첫 번째 거래.
- `buy2`, `sell2` = 두 번째 거래.
- 각 상태마다 최대값 업데이트.

**추천 이유:** 제약 조건(최대 거래 수)이 생기면서 상태 설계의 중요성이 두드러짐.

---

## 🟡 레벨 2: 복합 상태 관리

### 5. Maximum Product Subarray (LeetCode 152) [medium]
- [ ] [152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)

**문제:** 연속된 부분 배열의 곱 중 최댓값 찾기. (음수 포함)

**핵심:** **상태 2가지: 현재까지의 최댓값(`maxProd`) & 최솟값(`minProd`)**

**논리:**
- 음수를 만나면 최솟값(음수)이 최댓값으로 변할 수 있음.
- 매 단계마다 `maxProd = max(nums[i], maxProd * nums[i], minProd * nums[i])`.
- `minProd = min(nums[i], maxProd * nums[i], minProd * nums[i])`.

**추천 이유:** 누적 곱의 개념과 상태 기계 패턴을 동시에 활용하여. "최댓값과 최솟값을 동시에 추적"하는 아이디어가 핵심이다.

---

## 💡 학습 로드맵 (상태 기계)

1. **Best Time to Buy and Sell Stock (121번):** 기본 상태 추적 (최저가).
2. **Best Time to Buy and Sell Stock II (122번):** 상태 전환의 개념 (사/팔).
3. **Best Time to Buy and Sell Stock with Cooldown (309번):** 3가지 상태 동시 관리.
4. **Maximum Product Subarray (152번):** 최댓값과 최솟값 동시 추적 (쌍 상태).

