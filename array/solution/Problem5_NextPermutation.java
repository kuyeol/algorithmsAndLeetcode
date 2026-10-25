package problems;

import java.util.Arrays;

/**
 * 문제 5: 다음 순열 (Next Permutation)
 * 난이도: 🔴 고급 (Advanced)
 * 
 * 사전순으로 다음에 오는 순열을 찾는 문제
 */
public class Problem5_NextPermutation {
    
    /**
     * 다음 순열을 구하여 배열을 제자리에서 수정
     * O(n) 시간, O(1) 추가 공간
     * 
     * @param arr 숫자 배열
     */
    public static void nextPermutation(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        // 1. 오른쪽에서 왼쪽으로 scan하며 arr[i] < arr[i+1]인 첫 번째 위치 i 찾기
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        
        if (i >= 0) {
            // 2. 오른쪽에서 왼쪽으로 scan하며 arr[i]보다 큰 첫 번째 원소 arr[j] 찾기
            int j = arr.length - 1;
            while (arr[j] <= arr[i]) {
                j--;
            }
            
            // 3. arr[i]와 arr[j] 스왑
            swap(arr, i, j);
        }
        
        // 4. 위치 i+1 부터 끝까지 뒤집기
        reverse(arr, i + 1, arr.length - 1);
    }
    
    /**
     * 배열의 두 원소를 스왑
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * 배열의 일부를 뒤집음
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }
    
    /**
     * 테스트 실행
     */
    public static void main(String[] args) {
        System.out.println("=== 문제 5: 다음 순열 테스트 ===\n");
        
        int passed = 0;
        int total = 5;
        
        // 테스트 1: 일반적인 경우
        int[] test1 = {1, 2, 3};
        int[] expected1 = {1, 3, 2};
        nextPermutation(test1);
        boolean pass1 = Arrays.equals(test1, expected1);
        System.out.println("테스트 1: " + (pass1 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 2, 3]");
        System.out.println("  예상: " + Arrays.toString(expected1));
        System.out.println("  결과: " + Arrays.toString(test1));
        if (pass1) passed++;
        System.out.println();
        
        // 테스트 2: 최대 순열 (가장 큰 순열)
        int[] test2 = {3, 2, 1};
        int[] expected2 = {1, 2, 3};
        nextPermutation(test2);
        boolean pass2 = Arrays.equals(test2, expected2);
        System.out.println("테스트 2: " + (pass2 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [3, 2, 1]");
        System.out.println("  예상: " + Arrays.toString(expected2) + " (최소 순열로)");
        System.out.println("  결과: " + Arrays.toString(test2));
        if (pass2) passed++;
        System.out.println();
        
        // 테스트 3: 중복 포함
        int[] test3 = {1, 1, 5};
        int[] expected3 = {1, 5, 1};
        nextPermutation(test3);
        boolean pass3 = Arrays.equals(test3, expected3);
        System.out.println("테스트 3: " + (pass3 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 1, 5]");
        System.out.println("  예상: " + Arrays.toString(expected3));
        System.out.println("  결과: " + Arrays.toString(test3));
        if (pass3) passed++;
        System.out.println();
        
        // 테스트 4: 중간 경우
        int[] test4 = {1, 3, 2};
        int[] expected4 = {2, 1, 3};
        nextPermutation(test4);
        boolean pass4 = Arrays.equals(test4, expected4);
        System.out.println("테스트 4: " + (pass4 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 3, 2]");
        System.out.println("  예상: " + Arrays.toString(expected4));
        System.out.println("  결과: " + Arrays.toString(test4));
        if (pass4) passed++;
        System.out.println();
        
        // 테스트 5: 또 다른 경우
        int[] test5 = {2, 3, 1};
        int[] expected5 = {3, 1, 2};
        nextPermutation(test5);
        boolean pass5 = Arrays.equals(test5, expected5);
        System.out.println("테스트 5: " + (pass5 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [2, 3, 1]");
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
