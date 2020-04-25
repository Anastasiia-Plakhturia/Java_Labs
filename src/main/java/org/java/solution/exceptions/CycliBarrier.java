package org.java.solution.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class Threads implements Runnable
{
    ReentrantLock locker;
    ArrayList<Double> per;
    CyclicBarrier cycl;
    double a,b;


    Threads (CyclicBarrier cycl,  ArrayList<Double> per, ReentrantLock locker)
    {
        this.locker=locker;
        this.per=per;
        this.cycl=cycl;
        new Thread(this);
    }

    public void run(){
        System.out.println("Thread Start");
        try {
            input_data();
        double area=a*b;
        Thread.sleep(4000);
            per.add(area);
            System.out.println("Площадь: "+area);
            cycl.await();
        } catch (InterruptedException | IOException e) {
            System.out.println(e);
        } catch (BrokenBarrierException e) {
            System.out.println(e);
        }
    }

    public void input_data() throws IOException {
        locker.lock();
        System.out.println("Введите числа:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        a=Double.parseDouble(reader.readLine());
        b=Double.parseDouble(reader.readLine());
        locker.unlock();
    }
}

class MinPer implements Runnable{
    ArrayList<Double> per ;
    MinPer( ArrayList<Double> per)
    {

        this.per = per ;
    }
    public void run()
    {
        System.out.println("Минимальная площадь");
        double min=per.get(0);
            for(int i=0; i<per.size();i++){
                if (min>per.get(i))
                    min=per.get(i);
            }
            System.out.print(min);
    }
}
public class CycliBarrier {
    static ArrayList<Double> per = new ArrayList<Double>() ;

    public static void main (String args []) {
        ReentrantLock locker = new ReentrantLock();
        CyclicBarrier cycl = new CyclicBarrier(3, new MinPer(per));
        ExecutorService es = Executors.newFixedThreadPool(3);

        es.execute(new Threads(cycl, per, locker));
        es.execute(new Threads(cycl, per, locker));
        es.execute(new Threads(cycl, per, locker));
        es.shutdown();
    }
}