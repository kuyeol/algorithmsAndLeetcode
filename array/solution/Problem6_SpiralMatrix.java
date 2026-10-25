package problems;

import java.util.Arrays;

/**
 * 문제 6: 스파이럴 행렬 생성 (Spiral Matrix Generation)
 * 난이도: 🔴 고급 (Advanced)
 * 
 * 1 부터 n²까지 나선형으로 채운 n x n 행렬을 생성하는 문제
 */
public class Problem6_SpiralMatrix {
    
    /**
     * 나선형 행렬을 생성
     * 
     * @param n 행렬의 크기
     * @return 나선형으로 채워진 n x n 행렬
     */
    public static int[][] generateSpiralMatrix(int n) {
        if (n <= 0) {
            return new int[0][0];
        }
        
        int[][] matrix = new int[n][n];
        
        // 방향 배열: 오른쪽, 아래, 왼쪽, 위 (시계방향)
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dirIndex = 0;  // 현재 방향 인덱스
        
        int row = 0, col = 0;  // 현재 위치
        
        for (int num = 1; num <= n * n; num++) {
            matrix[row][col] = num;
            
            // 다음 위치 계산
            int nextRow = row + directions[dirIndex][0];
            int nextCol = col + directions[dirIndex][1];
            
            // 다음 위치가 유효한지 확인 (경계 내이고 아직 채워지지 않음)
            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n || matrix[nextRow][nextCol] != 0) {
                // 방향 전환 (시계방향)
                dirIndex = (dirIndex + 1) % 4;
                nextRow = row + directions[dirIndex][0];
                nextCol = col + directions[dirIndex][1];
            }
            
            row = nextRow;
            col = nextCol;
        }
        
        return matrix;
    }
    
    /**
     * 행렬을 문자열로 변환 (출력용)
     */
    private static String matrixToString(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < matrix.length; i++) {
            sb.append("  ");
            sb.append(Arrays.toString(matrix[i]));
            if (i < matrix.length - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * 두 행렬이 같은지 비교
     */
    private static boolean matricesEqual(int[][] a, int[][] b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 테스트 실행
     */
    public static void main(String[] args) {
        System.out.println("=== 문제 6: 스파이럴 행렬 생성 테스트 ===\n");
        
        int passed = 0;
        int total = 5;
        
        // 테스트 1: n=3
        int n1 = 3;
        int[][] expected1 = {
            {1, 2, 3},
            {8, 9, 4},
            {7, 6, 5}
        };
        int[][] result1 = generateSpiralMatrix(n1);
        boolean pass1 = matricesEqual(result1, expected1);
        System.out.println("테스트 1: " + (pass1 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: n=" + n1);
        System.out.println("  예상:");
        System.out.println(matrixToString(expected1));
        System.out.println("  결과:");
        System.out.println(matrixToString(result1));
        if (pass1) passed++;
        System.out.println();
        
        // 테스트 2: n=1
        int n2 = 1;
        int[][] expected2 = {{1}};
        int[][] result2 = generateSpiralMatrix(n2);
        boolean pass2 = matricesEqual(result2, expected2);
        System.out.println("테스트 2: " + (pass2 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: n=" + n2);
        System.out.println("  예상: " + Arrays.deepToString(expected2));
        System.out.println("  결과: " + Arrays.deepToString(result2));
        if (pass2) passed++;
        System.out.println();
        
        // 테스트 3: n=4
        int n3 = 4;
        int[][] expected3 = {
            {1,  2,  3,  4},
            {12, 13, 14, 5},
            {11, 16, 15, 6},
            {10, 9,  8,  7}
        };
        int[][] result3 = generateSpiralMatrix(n3);
        boolean pass3 = matricesEqual(result3, expected3);
        System.out.println("테스트 3: " + (pass3 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: n=" + n3);
        System.out.println("  예상:");
        System.out.println(matrixToString(expected3));
        System.out.println("  결과:");
        System.out.println(matrixToString(result3));
        if (pass3) passed++;
        System.out.println();
        
        // 테스트 4: n=2
        int n4 = 2;
        int[][] expected4 = {
            {1, 2},
            {4, 3}
        };
        int[][] result4 = generateSpiralMatrix(n4);
        boolean pass4 = matricesEqual(result4, expected4);
        System.out.println("테스트 4: " + (pass4 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: n=" + n4);
        System.out.println("  예상:");
        System.out.println(matrixToString(expected4));
        System.out.println("  결과:");
        System.out.println(matrixToString(result4));
        if (pass4) passed++;
        System.out.println();
        
        // 테스트 5: n=5
        int n5 = 5;
        int[][] expected5 = {
            {1,  2,  3,  4,  5},
            {16, 17, 18, 19, 6},
            {15, 24, 25, 20, 7},
            {14, 23, 22, 21, 8},
            {13, 12, 11, 10, 9}
        };
        int[][] result5 = generateSpiralMatrix(n5);
        boolean pass5 = matricesEqual(result5, expected5);
        System.out.println("테스트 5: " + (pass5 ? "✓ 통과" : "✗ 실패"));
        System.out.println("  입력: n=" + n5);
        System.out.println("  예상:");
        System.out.println(matrixToString(expected5));
        System.out.println("  결과:");
        System.out.println(matrixToString(result5));
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
