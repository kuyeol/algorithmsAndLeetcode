package org.acme.sort;

import org.acme.ListNode;

import java.util.LinkedList;
import java.util.Random;

public class ListNodeMergeSort {

  @SuppressWarnings("null")

  public static void main( String[] args ) {


    ListNode rootA = makeNodeList( makeSize() );
    ListNode rootB = makeNodeList( makeSize() );

    System.out.println( "finished : " + rootA );
    System.out.println( "finished : " + rootB );
    System.out.println( "--------------------------------" );

    ListNode test = mergeTwoLists( rootA , new ListNode( 0 ) );

    @SuppressWarnings("all") ListNode test2 = mergeTwoLists( null , null );

//    System.out.println(test);
    //  System.out.println(test2);

    ListNode find = findNodeValue( rootA , 4 );
    System.out.println( find );

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
    if ( list1 == null || list2 == null ) {
      return ( list1 != null ) ? list1 : list2;
    }

    //리스트가 없는경우 종료조건
    ListNode memo = new ListNode();
    ListNode current = memo;


    while ( list1 != null && list2 != null ) {
      if ( list1.val < list2.val ) {
        current = list1;
      }

      return current.next = list1;
    }


    return memo.next;
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
