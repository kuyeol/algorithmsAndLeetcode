package org.acme;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {

  public static void main(String[] args) {

    //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
    // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.
    System.out.printf("Hello and welcome!");

    for (int i = 1; i <= 5; i++) {
      async();
      System.out.println("i = " + i);
    }
  }




  public static void async(){
      CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
        // 1단계: 숫자 생성
        System.out.println("1단계 실행");
        return 10;
      }).thenApply(num -> {
        // 2단계: 곱하기 2
        System.out.println("2단계 실행");
        return num * 2;
      }).thenApply(num -> {
        // 3단계: 0으로 나누기 시도 (예외 발생용)
        System.out.println("3단계 실행");
        if (num == 20) {
          throw new ArithmeticException("0으로 나누기 시도!");
        }
        return num / 0; // 고의로 예외 발생
      }).thenApply(num -> {
        // 4단계: 결과에 10 더하기
        System.out.println("4단계 실행");
        return num + 10;
      }).exceptionally(ex -> {
        // 예외 처리: 예외 메시지 출력 후 기본값 반환
        System.out.println("예외 발생: " + ex.getMessage());
        return -1;
      });

      try {
        Integer result = future.get();
        System.out.println("최종 결과: " + result);
      } catch ( InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }

  }

}
