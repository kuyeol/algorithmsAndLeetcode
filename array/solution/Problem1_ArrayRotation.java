package problems;

import java.util.Arrays;

/**
 * 문제 1: 배열 회전 (Array Rotation)
 * 난이도: 🟢 기본 (Basic)
 * 
 * 정수 배열을 오른쪽으로 k칸만큼 회전시키는 문제
 */
public class Problem1_ArrayRotation {
    
    /**
     * 배열을 오른쪽으로 k칸 회전
     * 제자리에서 수행 (O(1) 추가 공간)
     * 
     * @param arr 회전시킬 배열
     * @param k 회전할 칸 수
     * @return 회전된 배열
     */
    public static int[] rotateArray(int[] arr, int k) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        
        // k가 배열 길이보다 클 수 있으므로 조정
        k = k % arr.length;
        
        if (k == 0) {
            return arr;
        }
        
        // 제자리 회전: 전체 반전 → 앞부분 반전 → 뒷부분 반전
        reverse(arr, 0, arr.length - 1);      // 전체 반전
        reverse(arr, 0, k - 1);               // 앞부분 반전
        reverse(arr, k, arr.length - 1);      // 뒷부분 반전
        
        return arr;
    }
    
    /**
     * 배열의 일부를 반전
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    
    /**
     * 테스트 실행
     */
    public static void main(String[] args) {
        System.out.println("=== 문제 1: 배열 회전 테스트 ===\n");
        
        int passed = 0;
        int total = 5;
        
        // 테스트 1: 일반적인 경우
        int[] test1 = {1, 2, 3, 4, 5};
        int k1 = 2;
        int[] expected1 = {4, 5, 1, 2, 3};
        int[] result1 = rotateArray(test1.clone(), k1);
        boolean pass1 = Arrays.equals(result1, expected1);
        System.out.println("테스트 1: " + (pass1 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 2, 3, 4, 5], k=2");
        System.out.println("  예상: " + Arrays.toString(expected1));
        System.out.println("  결과: " + Arrays.toString(result1));
        if (pass1) passed++;
        System.out.println();
        
        // 테스트 2: k가 배열 길이보다 큰 경우
        int[] test2 = {1, 2, 3};
        int k2 = 5;
        int[] expected2 = {2, 3, 1};
        int[] result2 = rotateArray(test2.clone(), k2);
        boolean pass2 = Arrays.equals(result2, expected2);
        System.out.println("테스트 2: " + (pass2 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 2, 3], k=5");
        System.out.println("  예상: " + Arrays.toString(expected2));
        System.out.println("  결과: " + Arrays.toString(result2));
        if (pass2) passed++;
        System.out.println();
        
        // 테스트 3: k=0인 경우
        int[] test3 = {1, 2, 3};
        int k3 = 0;
        int[] expected3 = {1, 2, 3};
        int[] result3 = rotateArray(test3.clone(), k3);
        boolean pass3 = Arrays.equals(result3, expected3);
        System.out.println("테스트 3: " + (pass3 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 2, 3], k=0");
        System.out.println("  예상: " + Arrays.toString(expected3));
        System.out.println("  결과: " + Arrays.toString(result3));
        if (pass3) passed++;
        System.out.println();
        
        // 테스트 4: 단일 원소
        int[] test4 = {1};
        int k4 = 10;
        int[] expected4 = {1};
        int[] result4 = rotateArray(test4.clone(), k4);
        boolean pass4 = Arrays.equals(result4, expected4);
        System.out.println("테스트 4: " + (pass4 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1], k=10");
        System.out.println("  예상: " + Arrays.toString(expected4));
        System.out.println("  결과: " + Arrays.toString(result4));
        if (pass4) passed++;
        System.out.println();
        
        // 테스트 5: k가 배열 길이의 배수
        int[] test5 = {1, 2, 3, 4};
        int k5 = 8;
        int[] expected5 = {1, 2, 3, 4};
        int[] result5 = rotateArray(test5.clone(), k5);
        boolean pass5 = Arrays.equals(result5, expected5);
        System.out.println("테스트 5: " + (pass5 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 2, 3, 4], k=8");
        System.out.println("  예상: " + Arrays.toString(expected5));
        System.out.println("  결과: " + Arrays.toString(result5));
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
