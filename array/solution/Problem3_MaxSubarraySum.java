package problems;

/**
 * 문제 3: 최대 부분합 (Maximum Subarray Sum)
 * 난이도: 🟡 중급 (Intermediate)
 * 
 * 연속된 부분 배열의 합 중 가장 큰 값을 찾는 문제
 * 카다네 알고리즘(Kadane's Algorithm) 사용
 */
public class Problem3_MaxSubarraySum {
    
    /**
     * 최대 부분합을 찾는 함수
     * 카다네 알고리즘 사용 (O(n))
     * 
     * @param arr 정수 배열
     * @return 최대 부분합
     */
    public static int maxSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("배열은 비어있을 수 없습니다.");
        }
        
        // 현재 위치까지의 최대 부분합
        int currentMax = arr[0];
        // 전체에서 발견된 최대 부분합
        int globalMax = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            // 현재 원소부터 새로 시작하거나, 이전까지의 합에 현재 원소를 더하거나
            // 둘 중 더 큰 값을 선택
            currentMax = Math.max(arr[i], currentMax + arr[i]);
            
            // 전체 최대값 갱신
            globalMax = Math.max(globalMax, currentMax);
        }
        
        return globalMax;
    }
    
    /**
     * 테스트 실행
     */
    public static void main(String[] args) {
        System.out.println("=== 문제 3: 최대 부분합 테스트 ===\n");
        
        int passed = 0;
        int total = 5;
        
        // 테스트 1: mixed 양수/음수
        int[] test1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int expected1 = 6;  // [4, -1, 2, 1]
        int result1 = maxSubArray(test1);
        boolean pass1 = (result1 == expected1);
        System.out.println("테스트 1: " + (pass1 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [-2, 1, -3, 4, -1, 2, 1, -5, 4]");
        System.out.println("  예상: " + expected1 + " (부분배열: [4, -1, 2, 1])");
        System.out.println("  결과: " + result1);
        if (pass1) passed++;
        System.out.println();
        
        // 테스트 2: 단일 원소
        int[] test2 = {1};
        int expected2 = 1;
        int result2 = maxSubArray(test2);
        boolean pass2 = (result2 == expected2);
        System.out.println("테스트 2: " + (pass2 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1]");
        System.out.println("  예상: " + expected2);
        System.out.println("  결과: " + result2);
        if (pass2) passed++;
        System.out.println();
        
        // 테스트 3: 모두 양수 (대부분)
        int[] test3 = {5, 4, -1, 7, 8};
        int expected3 = 23;  // 전체 합
        int result3 = maxSubArray(test3);
        boolean pass3 = (result3 == expected3);
        System.out.println("테스트 3: " + (pass3 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [5, 4, -1, 7, 8]");
        System.out.println("  예상: " + expected3 + " (전체 배열)");
        System.out.println("  결과: " + result3);
        if (pass3) passed++;
        System.out.println();
        
        // 테스트 4: 모두 음수
        int[] test4 = {-1, -2, -3};
        int expected4 = -1;  // 가장 큰 단일 원소
        int result4 = maxSubArray(test4);
        boolean pass4 = (result4 == expected4);
        System.out.println("테스트 4: " + (pass4 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [-1, -2, -3]");
        System.out.println("  예상: " + expected4 + " (단일 원소 -1)");
        System.out.println("  결과: " + result4);
        if (pass4) passed++;
        System.out.println();
        
        // 테스트 5: 교차하는 경우
        int[] test5 = {1, -1, 1, -1, 1};
        int expected5 = 1;  // 어떤 단일 1
        int result5 = maxSubArray(test5);
        boolean pass5 = (result5 == expected5);
        System.out.println("테스트 5: " + (pass5 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, -1, 1, -1, 1]");
        System.out.println("  예상: " + expected5);
        System.out.println("  결과: " + result5);
        if (pass5) passed++;
        System.out.println();
        
        // 결과 요약
        System.out.println("=================================");
        System.out.println("총 " + total + "개 중 " + passed + "개 통과");
        if (passed == total) {
            System.out.println("🎉 모든 테스트를 통과했습니다!");
        } else {
            System.out.println("⚠️  일부 테스트를 실패했습니다.");
        }
    }
}
