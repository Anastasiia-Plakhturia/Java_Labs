package org.java.solution.multithreading;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.Phaser;

public class Phaser_1 {

    public static void main(String arg[]) throws InterruptedException {
        Future<Integer> f, f1, f2, f3;
        Phaser ph =new Phaser();
        _Sum s=new _Sum();
        ExecutorService es = Executors.newFixedThreadPool(4);
        es.execute(new Tread(s, ph));
        es.execute(new Tread(s, ph));
        es.execute(new Tread(s, ph));
        es.execute(new Tread(s, ph));

        int k=ph.getPhase();
        ph.arriveAndAwaitAdvance();
        System.out.println("\nФаза " + k+ " выполнена");

        k=ph.getPhase();
        ph.arriveAndAwaitAdvance();
        System.out.println("Фаза " + k+ " выполнена");

        k=ph.getPhase();
        ph.arriveAndAwaitAdvance();
        System.out.println("Фаза " + k+ " выполнена");
        ph.arriveAndDeregister();
        es.shutdown();
    }
}
class _Sum{
    Random rand = new Random();
    static AtomicIntegerArray ar=new AtomicIntegerArray(16);
    static AtomicInteger ai = new AtomicInteger(0);
    static AtomicInteger ai1 = new AtomicInteger(0);
    static AtomicInteger ai2 = new AtomicInteger(0);

    public  void input_data (){
        for (int i=ai.getAndAdd(4); i<ai.get(); i++)
        {
            ar.set(i,rand.nextInt(10));
            System.out.print(ar.get(i)+" ");
        }
    }
}
class Tread implements Runnable {
    AtomicIntegerArray ar=_Sum.ar;
    _Sum s;
    Phaser ph;
    Tread(_Sum s, Phaser p )
    {
        this.s=s;
        this.ph=p;
        this.ph.register();
    }
    public void sum ()
    {
        for (int i=_Sum.ai1.getAndAdd(4);i< _Sum.ai1.get(); i++)
        {
            ar.addAndGet(i, 3);
            System.out.print(_Sum.ar.get(i)+" ");

        }
        System.out.println("");
    }
    public void min ()
    {
        int min =_Sum.ar.get(_Sum.ai2.get());
        for (int i=_Sum.ai2.getAndAdd(4);i< _Sum.ai2.get(); i++)
        {
            if (min>_Sum.ar.get(i))
                min=_Sum.ar.get(i);
        }
        System.out.println("min: "+min);

    }
    public void run ()  {
        try {
        s.input_data();
        Thread.sleep(200);
        ph.arriveAndAwaitAdvance();
        Thread.sleep(1000);
        sum();
        Thread.sleep(200);
        ph.arriveAndAwaitAdvance();
        Thread.sleep(2000);
        min();
        Thread.sleep(500);
        ph.arriveAndAwaitAdvance();
        } catch (InterruptedException e) {
            System.out.println(e);
        }


    }
}