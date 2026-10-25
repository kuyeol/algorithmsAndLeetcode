package problems;

import java.util.Arrays;

/**
 * 문제 2: 중복 제거 (Remove Duplicates)
 * 난이도: 🟢 기본 (Basic)
 * 
 * 정렬된 배열에서 중복 원소를 제자리에서 제거하는 문제
 */
public class Problem2_RemoveDuplicates {
    
    /**
     * 정렬된 배열에서 중복 원소를 제거하고 새로운 길이 반환
     * 제자리에서 수행 (O(1) 추가 공간)
     * 
     * @param arr 정렬된 정수 배열
     * @return 중복 제거 후 배열의 새로운 길이
     */
    public static int removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        // 투 포인터 기법
        // slow: 고유 원소의 다음 위치
        // fast: 현재 탐색 중인 위치
        int slow = 1;
        
        for (int fast = 1; fast < arr.length; fast++) {
            // 이전 원소와 다르면 고유 원소
            if (arr[fast] != arr[fast - 1]) {
                arr[slow] = arr[fast];
                slow++;
            }
        }
        
        return slow;
    }
    
    /**
     * 테스트 실행
     */
    public static void main(String[] args) {
        System.out.println("=== 문제 2: 중복 제거 테스트 ===\n");
        
        int passed = 0;
        int total = 5;
        
        // 테스트 1: 일반적인 중복
        int[] test1 = {1, 1, 2};
        int expectedLen1 = 2;
        int[] expectedArr1 = {1, 2};
        int resultLen1 = removeDuplicates(test1);
        boolean pass1 = (resultLen1 == expectedLen1) && 
                        Arrays.equals(Arrays.copyOf(test1, resultLen1), expectedArr1);
        System.out.println("테스트 1: " + (pass1 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 1, 2]");
        System.out.println("  예상 길이: " + expectedLen1 + ", 첫 " + expectedLen1 + "개: " + Arrays.toString(expectedArr1));
        System.out.println("  결과 길이: " + resultLen1 + ", 첫 " + resultLen1 + "개: " + Arrays.toString(Arrays.copyOf(test1, resultLen1)));
        if (pass1) passed++;
        System.out.println();
        
        // 테스트 2: 여러 중복
        int[] test2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int expectedLen2 = 5;
        int[] expectedArr2 = {0, 1, 2, 3, 4};
        int resultLen2 = removeDuplicates(test2);
        boolean pass2 = (resultLen2 == expectedLen2) && 
                        Arrays.equals(Arrays.copyOf(test2, resultLen2), expectedArr2);
        System.out.println("테스트 2: " + (pass2 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]");
        System.out.println("  예상 길이: " + expectedLen2 + ", 첫 " + expectedLen2 + "개: " + Arrays.toString(expectedArr2));
        System.out.println("  결과 길이: " + resultLen2 + ", 첫 " + resultLen2 + "개: " + Arrays.toString(Arrays.copyOf(test2, resultLen2)));
        if (pass2) passed++;
        System.out.println();
        
        // 테스트 3: 빈 배열
        int[] test3 = {};
        int expectedLen3 = 0;
        int resultLen3 = removeDuplicates(test3);
        boolean pass3 = (resultLen3 == expectedLen3);
        System.out.println("테스트 3: " + (pass3 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: []");
        System.out.println("  예상 길이: " + expectedLen3);
        System.out.println("  결과 길이: " + resultLen3);
        if (pass3) passed++;
        System.out.println();
        
        // 테스트 4: 모든 원소가 동일
        int[] test4 = {1, 1, 1, 1};
        int expectedLen4 = 1;
        int[] expectedArr4 = {1};
        int resultLen4 = removeDuplicates(test4);
        boolean pass4 = (resultLen4 == expectedLen4) && 
                        Arrays.equals(Arrays.copyOf(test4, resultLen4), expectedArr4);
        System.out.println("테스트 4: " + (pass4 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 1, 1, 1]");
        System.out.println("  예상 길이: " + expectedLen4 + ", 첫 " + expectedLen4 + "개: " + Arrays.toString(expectedArr4));
        System.out.println("  결과 길이: " + resultLen4 + ", 첫 " + resultLen4 + "개: " + Arrays.toString(Arrays.copyOf(test4, resultLen4)));
        if (pass4) passed++;
        System.out.println();
        
        // 테스트 5: 중복 없음
        int[] test5 = {1, 2, 3, 4, 5};
        int expectedLen5 = 5;
        int[] expectedArr5 = {1, 2, 3, 4, 5};
        int resultLen5 = removeDuplicates(test5);
        boolean pass5 = (resultLen5 == expectedLen5) && 
                        Arrays.equals(Arrays.copyOf(test5, resultLen5), expectedArr5);
        System.out.println("테스트 5: " + (pass5 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 2, 3, 4, 5]");
        System.out.println("  예상 길이: " + expectedLen5 + ", 첫 " + expectedLen5 + "개: " + Arrays.toString(expectedArr5));
        System.out.println("  결과 길이: " + resultLen5 + ", 첫 " + resultLen5 + "개: " + Arrays.toString(Arrays.copyOf(test5, resultLen5)));
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
