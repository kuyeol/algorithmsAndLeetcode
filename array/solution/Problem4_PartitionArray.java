package problems;

import java.util.Arrays;

/**
 * 문제 4: 배열 분할 (Partition Array)
 * 난이도: 🟡 중급 (Intermediate)
 * 
 * 배열을 기준값 k 로 분할하는 문제 (k 미만은 왼쪽, k 이상은 오른쪽)
 */
public class Problem4_PartitionArray {
    
    /**
     * 배열을 k 기준으로 분할하고 k 미만 원소들의 개수 반환
     * 제자리에서 수행 (O(1) 추가 공간)
     * 
     * @param arr 정수 배열
     * @param k 분할 기준
     * @return k 보다 작은 원소들의 개수
     */
    public static int partitionArray(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        
        // 투 포인터 기법 (단일 패스)
        // smaller: k 미만 원소들이 위치할 다음 인덱스
        int smaller = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < k) {
                // k 미만 원소를 발견하면 smaller 위치로 스왑
                int temp = arr[smaller];
                arr[smaller] = arr[i];
                arr[i] = temp;
                smaller++;
            }
        }
        
        return smaller;
    }
    
    /**
     * 테스트 실행
     */
    public static void main(String[] args) {
        System.out.println("=== 문제 4: 배열 분할 테스트 ===\n");
        
        int passed = 0;
        int total = 5;
        
        // 테스트 1: 일반적인 경우
        int[] test1 = {3, 2, 2, 1};
        int k1 = 2;
        int expectedCount1 = 1;
        int resultCount1 = partitionArray(test1, k1);
        boolean pass1 = (resultCount1 == expectedCount1) &&
                        verifyPartition(test1, k1, resultCount1);
        System.out.println("테스트 1: " + (pass1 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [3, 2, 2, 1], k=2");
        System.out.println("  예상 개수: " + expectedCount1);
        System.out.println("  결과 개수: " + resultCount1);
        System.out.println("  분할 후 배열: " + Arrays.toString(test1));
        if (pass1) passed++;
        System.out.println();
        
        // 테스트 2: 중간 분할
        int[] test2 = {1, 2, 3, 4, 5};
        int k2 = 3;
        int expectedCount2 = 2;
        int resultCount2 = partitionArray(test2, k2);
        boolean pass2 = (resultCount2 == expectedCount2) &&
                        verifyPartition(test2, k2, resultCount2);
        System.out.println("테스트 2: " + (pass2 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 2, 3, 4, 5], k=3");
        System.out.println("  예상 개수: " + expectedCount2);
        System.out.println("  결과 개수: " + resultCount2);
        System.out.println("  분할 후 배열: " + Arrays.toString(test2));
        if (pass2) passed++;
        System.out.println();
        
        // 테스트 3: 모두 k 이상
        int[] test3 = {5, 5, 5, 5};
        int k3 = 5;
        int expectedCount3 = 0;
        int resultCount3 = partitionArray(test3, k3);
        boolean pass3 = (resultCount3 == expectedCount3) &&
                        verifyPartition(test3, k3, resultCount3);
        System.out.println("테스트 3: " + (pass3 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [5, 5, 5, 5], k=5");
        System.out.println("  예상 개수: " + expectedCount3);
        System.out.println("  결과 개수: " + resultCount3);
        System.out.println("  분할 후 배열: " + Arrays.toString(test3));
        if (pass3) passed++;
        System.out.println();
        
        // 테스트 4: 모두 k 미만
        int[] test4 = {1, 1, 1, 1};
        int k4 = 2;
        int expectedCount4 = 4;
        int resultCount4 = partitionArray(test4, k4);
        boolean pass4 = (resultCount4 == expectedCount4) &&
                        verifyPartition(test4, k4, resultCount4);
        System.out.println("테스트 4: " + (pass4 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [1, 1, 1, 1], k=2");
        System.out.println("  예상 개수: " + expectedCount4);
        System.out.println("  결과 개수: " + resultCount4);
        System.out.println("  분할 후 배열: " + Arrays.toString(test4));
        if (pass4) passed++;
        System.out.println();
        
        // 테스트 5: 빈 배열
        int[] test5 = {};
        int k5 = 5;
        int expectedCount5 = 0;
        int resultCount5 = partitionArray(test5, k5);
        boolean pass5 = (resultCount5 == expectedCount5);
        System.out.println("테스트 5: " + (pass5 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: [], k=5");
        System.out.println("  예상 개수: " + expectedCount5);
        System.out.println("  결과 개수: " + resultCount5);
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
    
    /**
     * 분할이 올바르게 되었는지 검증
     * 왼쪽은 모두 k 미만, 오른쪽은 모두 k 이상
     */
    private static boolean verifyPartition(int[] arr, int k, int count) {
        // 처음 count 개 원소는 모두 k 미만이어야 함
        for (int i = 0; i < count; i++) {
            if (arr[i] >= k) {
                return false;
            }
        }
        // 나머지 원소들은 모두 k 이상이어야 함
        for (int i = count; i < arr.length; i++) {
            if (arr[i] < k) {
                return false;
            }
        }
        return true;
    }
}
