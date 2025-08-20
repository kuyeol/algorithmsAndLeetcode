package org.acme.sort;

import org.acme.datastructure.ListNode;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ListNodeMergeSort {

  @SuppressWarnings("null")


  public static ListNode makeTestNode( int[] arr){

    ListNode root = null;
    for ( int i = arr.length-1; i >= 0 ;i-- ){
      ListNode node = new ListNode(arr[i],root);
      root=node;
    }

    return root;
  }


  public static void main( String[] args ) {


    ListNode rootA = makeNodeList( makeSize() );
    ListNode rootB = makeNodeList( makeSize() );

    System.out.println( "finished : " + rootA );
    System.out.println( "finished : " + rootB );
    System.out.println( "--------------------------------" );


    AtomicInteger index = new AtomicInteger();


    int[] elementA = new int[]{1,2,4};
    int[] elementB = new int[]{1,3,4};
    ListNode list1 = makeTestNode( elementA );
    ListNode list2 = makeTestNode( elementB );


    ListNode case1 = mergeTwoLists( list1,list2 );
    ListNode case2 = mergeTwoLists( null, null );
    ListNode case3 = mergeTwoLists( null, new ListNode( 0 ) );


    System.out.printf("\n === Test Case %d : === \n",index.getAndIncrement());
    System.out.println(case1);

    System.out.printf("\n === Test Case %d : === \n",index.getAndIncrement());
    System.out.println(case2);

    System.out.printf("\n === Test Case %d : === \n",index.getAndIncrement());
    System.out.println(case3);







  }


  public static ListNode findNodeValue( ListNode node , int tar ) {
    int i = 0;
    ListNode currentNode = node;
    while ( currentNode != null && currentNode.val != tar ) {
      System.out.println( "No." + i + " : " + "tar = " + tar + " source = " + currentNode.val );
      currentNode = currentNode.next;
      i++;
    }
    return currentNode;
  }

  public static ListNode mergeTwoLists( ListNode list1 , ListNode list2 ) {

//    if ( list1 == null || list2 == null ) { //리스트가 하나 또는 두개가 빈 경우
//      return ( list1 != null ) ? list1 : list2; // 둘중 널이 아닌 리스트나 둘다 널일 경우 반환
//    }

    ListNode memo = new ListNode();
    ListNode current = memo;
    if ( list1 == null ) {
      current = list2;
      return current;
    }

    while (list1 != null && list2 != null) {

      current.next = list1.val > list2.val ? list1 : list2;
      list1= list1.next;
      list2= list2.next;
      current=current.next;
//      if (list1.val > list2.val) {
//        System.out.print("\n >>>> ");
//        System.out.println(list2);
//        current.next = list2;
//        list2 = list2.next;
//      } else {
//        current.next = list1;
//        list1 = list1.next;
//      }
//      current = current.next;
    }


  //  current.next = (list1 != null) ? list1 : list2;


    return current;
  }


  public static ListNode makeNodeList( int size ) {
    ListNode root = null;
    for ( int i = 0; i < size; i++ ) {
      ListNode node = new ListNode( makeNum() , root );
      root = node;
    }
    return root;
  }


  public static int makeNum( ) {
    return new Random().nextInt( 100 );
  }

  public static int makeSize( ) {
    return new Random().nextInt( 50 );
  }


}
