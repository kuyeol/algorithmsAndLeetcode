package org.acme.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeLockExample {


  public static void main( String[] args ) {

    final Friend alphonse = new Friend( "Alphonse" );
    final Friend gaston = new Friend( "Gaston" );

    try ( var virtual = Executors.newVirtualThreadPerTaskExecutor() ) {

        virtual.execute( new BowLoop( alphonse , gaston ) );
        virtual.execute( new BowLoop( gaston , alphonse ) );

    }

  }


  static class BowLoop implements Runnable {
    AtomicInteger atomicInteger = new AtomicInteger(10);
    private final Friend bower;
    private final Friend bowee;

    public BowLoop( Friend bower , Friend bowee ) {
      this.bower = bower;
      this.bowee = bowee;
    }

    public  void run( ) {
      while ( atomicInteger.get() >= 0 ) {
        System.out.println("\n[RUN] Count is : "+ atomicInteger.get() );

        try {
          Thread.sleep( 1,1);
        } catch ( InterruptedException e ) {
          throw new RuntimeException( e );
        }


        bowee.bow( bower , atomicInteger.getAndDecrement() );
        if ( atomicInteger.get() <= 0  ) {
          System.out.println("\n\t ========================== 종료 ======================== \n "+atomicInteger.get());
          break;
        }
      }

    }
  }

  static class Friend {

    private final String name;
    private final Lock lock = new ReentrantLock();

    public Friend( String name ) {
      this.name = name;
    }

    public String getName( ) {
      return this.name;
    }


    public  boolean impendingBow( Friend bower )  {

      boolean myLock = false;
      boolean yourLock = false;

      try {
        myLock = lock.tryLock();
        yourLock = bower.lock.tryLock();

      } finally {
        if ( !( myLock && yourLock ) ) {
          if ( myLock ) {
            lock.unlock();
          }
          if ( yourLock ) {
            bower.lock.unlock();
          }
        }
      }
      return myLock && yourLock;
    }

    public void bow( Friend bower ,int count ) {
        if ( impendingBow( bower ) && count <= 0) {
          System.out.println( "[ bow ] counter : " + count );
          System.out.flush();
          try {

            // System.out.format( "%s: %s has" + " bowed to me!%n" , this.name , bower.getName() );
            System.out.format( "[ %s ] : %s (가) 나에게 인사를 했습니다!%n" , this.name , bower.getName() );
            System.out.flush();
            bower.bowBack( this );
          } finally {
            lock.unlock();
            bower.lock.unlock();
          }
        } else {
          //        System.out.format( "%s: %s started" + " to bow to me, but saw that" + " I was already bowing to" + " him.%n" , this.name , bower.getName() );
          System.out.format( "## [ %s ] : %s (가) 나에게 인사를 하려 했지만, " + "내가 이미 그에게 인사 중임을 보고 멈췄습니다.%n" , this.name , bower.getName() );
          System.out.flush();
        }

    }

    public synchronized void bowBack( Friend bower ) {
//      System.out.format( "%s: %s has" + " bowed back to me!%n" , this.name , bower.getName() );
      System.out.format( "[ %s ]: %s (가) 나에게 인사를 되돌려 주었습니다!%n" , this.name , bower.getName() );
      System.out.flush();
    }
  }

}
