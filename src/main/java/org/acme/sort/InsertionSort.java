package org.acme.sort;

public class InsertionSort {


  public static void printArr( int[] arr ) {
    int count = 0;
    for ( int i : arr ) {
      System.out.println( count + ":" + i );
      count++;
    }
  }

  public static void main( String[] args ) {

    final int[] arr = { 10 , 3 , 4 , 5 , 24 , 34 , 2 , 1 , 43 };
    int[] sorted = sortArr( arr );
    printArr( sorted );

  }

  public static int[] sortArr( int[] arr ) {
    int key;
    for ( int i = 1; i < arr.length; i++ ) {
      System.out.println("\n############# [ for : start ] ############# ");


      int j = i - 1;
      key = arr[ i ];

      while ( j >= 0 && arr[ j ] > key ) {
        System.out.println("\n========[ while : start ]======");

        System.out.printf("key = %d , A[%d] = %d , A[%d] = %d \n",key, j,  arr[j],j+1,arr[j+1]);

        arr[ j + 1 ] = arr[j];
        System.out.print("Swap >> \t");
        System.out.printf("A[%d] = %d <- A[%d] = %d%n", j+1, arr[j+1], j, arr[j]);

        j = j - 1;
        System.out.println( "J - 1 ->  " + j );
        System.out.println("========[ while : end ]======\n");
      }
      System.out.printf("before -> A[%d+1] = %d\n",j,  arr[j+1]);
      arr[ j + 1 ] = key;
      System.out.printf("after -> A[%d+1] = %d\n",j,  arr[j+1]);

      System.out.println("\n############# [ for : end ] #############\n");
    }

    return arr;
  }

//       System.out.printf("key : %d, A[%d] : %d",j, key, arr[j]);
  //  System.out.printf("before -> A[%d+1] : [%d]\n",j,  arr[j+1]);
}
