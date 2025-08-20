package org.acme.concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ProducerConsumerExample {
  static LinkedBlockingDeque< String > infos = new LinkedBlockingDeque<>( List.of( "1.Mares eat oats" , "2.Does eat oats" , "3.Little lambs eat ivy" , "4.A kid will eat ivy too" ) );

  public static void main( String[] args ) {


   Drop drop = new Drop();

    try ( ExecutorService vir = Executors.newVirtualThreadPerTaskExecutor() ) {

//      vir.execute( new Producer( drop , infos) );
//      vir.execute( new Consumer( drop ) );
      vir.submit(new Producer( drop , infos)  );
      vir.submit(new Consumer( drop ) );


    }
    System.out.println();

  } //end -> main


  static class Producer implements Runnable {

    private final Pub drop;

    private final LinkedBlockingDeque< String > infos ;

    public Producer( Pub drop, LinkedBlockingDeque<String>  deque) {
      this.drop = drop;
      this.infos= deque;
    }

    public void run( ) {
      while ( infos.iterator().hasNext() ) {
        System.out.printf( "\n**[ Producer ]** --( %s )--> " , infos.peek() );
        drop.put( infos.poll() );
      }
      drop.put( "DONE" );

    }
  }


  static class Consumer implements Runnable {

    private final Sub drop;

    public Consumer( Sub drop ) {
      this.drop = drop;
    }

    public void run( ) {

      Random random = new Random();
      for ( String message = drop.take(); !message.equals( "DONE" ); message = drop.take() ) {
        System.out.format( "\n<<<< [ Consumer ] >>>> MESSAGE RECEIVED:  %s   \n " , message );
        try {
          Thread.sleep( random.nextInt( 1000 ) );
        } catch ( InterruptedException e ) {
        }
      }
    }
  }

  static class Consumer2 implements Runnable {

    private final Drop drop;

    public Consumer2( Drop drop ) {
      this.drop = drop;
    }

    public void run( ) {
      Random random = new Random();
      for ( String message = drop.take(); !message.equals( "DONE" ); message = drop.take() ) {
        System.out.format( "\n<<<< [ Consumer_2 ] >>>> MESSAGE RECEIVED:  %s   \n " , message );
        try {
          Thread.sleep( random.nextInt( 1000 ) );
        } catch ( InterruptedException e ) {
        }
      }
    }
  }

}
