package org.acme.concurrency;


import java.util.concurrent.atomic.AtomicInteger;

public class Drop implements Pub, Sub {
  // Message sent from producer to consumer.
  private String message;

  /**
   * True if consumer should wait
   * for producer to send message,
   * false if producer should wait for
   * consumer to retrieve message.
   */
  private boolean empty = true;

  AtomicInteger c = new AtomicInteger();

  @Override
  public synchronized String take( ) {

    while ( empty ) {
      try {
        wait(); // Wait until message is available.
      } catch ( InterruptedException e ) {
        System.out.println( e.getMessage() );
      }

    }
    if ( message.equals( "DONE" ) ) {
      System.out.println( "\n## [Entity Methods ]  Message Finished ##\n" );
    } else {
      System.out.printf( "\n## [Entity Methods ]  Message is take : %s ##\n" , !empty );

    }
    empty = true; // Toggle status.
    notifyAll();  // Notify producer that status has changed.
    return message;
  }

  @Override
  public synchronized void put( String message ) {

    while ( !empty ) {  // Wait until message has been retrieved.
      try {
        System.out.println( "\n## [Entity Methods ] [ " + c.getAndIncrement() + " : in ] ##" );
        wait();
      } catch ( InterruptedException e ) {
        System.out.println( e.getMessage() );
      }
    }
    empty = false;  // Toggle status.
    this.message = message;  // Store message
    System.out.printf( "\n## [Entity Methods ] [ %d : Out ] Notify  ##  -> put : %s \n" , c.getAndIncrement() , message );
    notifyAll(); // Notify consumer that status has changed.
  }


}

interface Pub {
  void put( String m );
}

interface Sub {
  String take( );

}
