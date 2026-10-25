package problems;

import java.util.Arrays;

/**
 * 문제 7: 0 이동시키기 (Move Zeroes with Constraints)
 * 난이도: 🔴 고급 (Advanced)
 * 
 * 모든 0 을 배열 끝으로 이동시키는 문제
 * 0 이 아닌 원소들의 상대적 순서 유지, 제자리 수행, 최소 연산
 */
public class Problem7_MoveZeroes {
    
    /**
     * 모든 0 을 배열 끝으로 이동
     * 0 이 아닌 원소들의 상대적 순서 유지
     * 제자리에서 수행 (O(1) 추가 공간), 최소 연산
     * 
     * @param arr 정수 배열
     */
    public static void moveZeroes(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        
        // 0 이 아닌 원소들을 앞으로 배치할 위치
        int nonZeroPos = 0;
        
        // 1 단계: 0 이 아닌 모든 원소를 앞으로 이동 (상대적 순서 유지)
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[nonZeroPos] = arr[i];
                nonZeroPos++;
            }
        }
        
        // 2 단계: 나머지 위치를 0 으로 채움
        while (nonZeroPos < arr.length) {
            arr[nonZeroPos] = 0;
            nonZeroPos++;
        }
    }
    
    /**
     * 테스트 실행
     */
    public static void main(String[] args) {
        System.out.println("=== 문제 7: 0 이동시키기 테스트 ===\n");
        
        int passed = 0;
        int total = 5;
        
        // 테스트 1: 일반적인 경우
        int[] test1 = {0, 1, 0, 3, 12};
        int[] expected1 = {1, 3, 12, 0, 0};
        moveZeroes(test1);
        boolean pass1 = Arrays.equals(test1, expected1);
        System.out.println("테스트 1: " + (pass1 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [0, 1, 0, 3, 12]");
        System.out.println("  예상: " + Arrays.toString(expected1));
        System.out.println("  결과: " + Arrays.toString(test1));
        if (pass1) passed++;
        System.out.println();
        
        // 테스트 2: 앞쪽에 0 집중
        int[] test2 = {0, 0, 1};
        int[] expected2 = {1, 0, 0};
        moveZeroes(test2);
        boolean pass2 = Arrays.equals(test2, expected2);
        System.out.println("테스트 2: " + (pass2 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [0, 0, 1]");
        System.out.println("  예상: " + Arrays.toString(expected2));
        System.out.println("  결과: " + Arrays.toString(test2));
        if (pass2) passed++;
        System.out.println();
        
        // 테스트 3: 0 없음
        int[] test3 = {1, 2, 3};
        int[] expected3 = {1, 2, 3};
        moveZeroes(test3);
        boolean pass3 = Arrays.equals(test3, expected3);
        System.out.println("테스트 3: " + (pass3 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 2, 3]");
        System.out.println("  예상: " + Arrays.toString(expected3));
        System.out.println("  결과: " + Arrays.toString(test3));
        if (pass3) passed++;
        System.out.println();
        
        // 테스트 4: 모두 0
        int[] test4 = {0, 0, 0};
        int[] expected4 = {0, 0, 0};
        moveZeroes(test4);
        boolean pass4 = Arrays.equals(test4, expected4);
        System.out.println("테스트 4: " + (pass4 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [0, 0, 0]");
        System.out.println("  예상: " + Arrays.toString(expected4));
        System.out.println("  결과: " + Arrays.toString(test4));
        if (pass4) passed++;
        System.out.println();
        
        // 테스트 5: 복잡한 경우
        int[] test5 = {5, 0, 0, 3, 0, 2, 0, 1};
        int[] expected5 = {5, 3, 2, 1, 0, 0, 0, 0};
        moveZeroes(test5);
        boolean pass5 = Arrays.equals(test5, expected5);
        System.out.println("테스트 5: " + (pass5 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [5, 0, 0, 3, 0, 2, 0, 1]");
        System.out.println("  예상: " + Arrays.toString(expected5));
        System.out.println("  결과: " + Arrays.toString(test5));
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
