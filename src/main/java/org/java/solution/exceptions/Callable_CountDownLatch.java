package org.java.solution.exceptions;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.Random;

class Sum{
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
class Treads implements Callable<Integer> {
    AtomicIntegerArray ar=Sum.ar;
    Sum s;
    CountDownLatch count;
    Treads(Sum s, CountDownLatch count)
    {
        this.s=s;
        this.count=count;
    }
    public void sum ()
    {
        for (int i=Sum.ai1.getAndAdd(4);i< Sum.ai1.get(); i++)
        {
            ar.addAndGet(i, 3);
            System.out.print(Sum.ar.get(i)+" ");

        }
        System.out.println(" ");
    }
    public int min ()
    {
        int min =Sum.ar.get(0);
        for (int i=Sum.ai2.getAndAdd(4);i< Sum.ai2.get(); i++)
        {
            if (min>Sum.ar.get(i))
            min=Sum.ar.get(i);
        }
        return min;
    }
     public Integer call () throws InterruptedException {
         s.input_data();
         Thread.sleep(1000);
         System.out.println("------");
         sum();
         System.out.println("Дошли до барьера");
         count.countDown();
         count.await();
         System.out.println("Прошли барьера");
         return min();

     }
}
public class Callable_CountDownLatch {
    static final CountDownLatch count = new CountDownLatch(4);

    public static void main(String arg[]){
        Future<Integer> f, f1, f2, f3;

        Sum s=new Sum();
        ExecutorService es = Executors.newFixedThreadPool(4);
        f=es.submit(new Treads(s, count));
        f1=es.submit(new Treads(s, count));
        f2=es.submit(new Treads(s, count));
        f3=es.submit(new Treads(s, count));
        try {

            Thread.sleep(2000);
            System.out.println("\nМинимумы");
            System.out.println(f.get());
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
        catch (ExecutionException exc) {
            System.out.println(exc);
        }
        es.shutdown();
    }
}
