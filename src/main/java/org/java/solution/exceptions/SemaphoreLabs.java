package org.java.solution.exceptions;
import java.io.IOException;
import java.util.concurrent.*;

class Threads1 implements Runnable{
    Semaphore sem;
    String name;

    Threads1 (Semaphore s, String n) {
        sem = s;
        name = n;
        new Thread(this, name).start();
    }
        public void run() {

            try {
                sem.acquire();
                System.out.println(name);
                Task1.main();
                }
            catch (InterruptedException exc)
            {
                System.out.println(exc);
            }
            System.out.println("***************");
            sem.release();
        }
}

class Threads2 implements Runnable {
    Semaphore sem;
    String name;

    Threads2 (Semaphore s, String n) {
        sem = s;
        name = n;
        new Thread(this, name).start();
    }
    public void run() {

        try{
            try {
                sem.acquire();
                System.out.println(name);
                Task2.main();
            }
            catch (InterruptedException exc)
            {
                System.out.println(exc);
            }
            System.out.println();
            System.out.println("***************");
            sem.release();
       }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

}
class Threads3 implements Runnable{
    Semaphore sem;
    String name;
    Threads3 (Semaphore s, String n) {
        sem = s;
        name = n;
        new Thread(this, name).start();
    }
    public void run() {
        try {
            sem.acquire();
            System.out.println(name);
            Task3.main();
        }
        catch (InterruptedException exc)
        {
            System.out.println(exc);
        }
        System.out.println("***************");
        sem.release();
    }

}
public class SemaphoreLabs {
    public static void main(String args[])  {
        Semaphore sem = new Semaphore(1);
        new Threads1(sem,"Task1");
        new Threads2(sem,"Task2");
        new Threads3(sem, "Task3");
    }
}