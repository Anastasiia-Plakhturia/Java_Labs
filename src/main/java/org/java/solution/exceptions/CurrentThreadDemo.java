package org.java.solution.exceptions;

import java.io.IOException;


class Starttasks  {
    String name; // name of thread
    int flag = 0;



    // This is the entry point for thread.
    public synchronized void task1()  {

               while (flag!=0)
                   try {
                  wait();
               }
         catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }
        this.flag = 1;
        Task1.main();
        System.out.println( "One exiting.");
        System.out.println("------------------");
        notify();
    }
    public synchronized void task2() throws IOException {


        while (flag!=1)
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.out.println(name + "Interrupted");
            }
        this.flag = 2;
        Task2.main();
        System.out.println(" ");
        System.out.println("Two exiting.");
        System.out.println("------------------");
        notify();
    }
    public synchronized void task3()  {

        while (flag!=2)
            try {
                wait();
            }
            catch (InterruptedException e) {
                System.out.println(name + "Interrupted");
            }
        this.flag =0;
        Task3.main();
        System.out.println("Three exiting.");
        System.out.println("------------------");
        notify();
    }
}
class Thread1 implements Runnable {
    Thread t;
    Starttasks tasks;
    Thread1(Starttasks tasks){
        this.tasks = tasks;
        t = new Thread (this, "Task1");
        t.start();
    }

    public void run(){
        tasks.task1();
    }
}

class Thread2 implements Runnable {
    Starttasks tasks;
    Thread t;
    Thread2(Starttasks tasks){
        this.tasks = tasks;
        t = new Thread (this, "Task2");
        t.start();
    }

    public void run() {
        try {
        tasks.task2();
        }
        catch (IOException exc){System.out.println("Ошибка task2");}
    }
}
class Thread3 implements Runnable {
    Starttasks tasks;
    Thread t;
    Thread3(Starttasks tasks){
        this.tasks = tasks;
        t = new Thread (this, "Task3");
        t.start();
    }

    public void run(){
        tasks.task3();
    }
}

class CurrentThreadDemo  {
    public static void main(String args[])  {
        Starttasks t = new Starttasks();
        Thread1 obj1 = new Thread1(t);
        Thread2 obj2 = new Thread2(t);
        Thread3 obj3 = new Thread3(t);
       try {
            obj1.t.join();
            obj2.t.join();
            obj3.t.join();
        }
        catch (InterruptedException e){
            System.out.println("exeption");
        }
        System.out.println("Все потоки выполнены");
        System.out.println("Main thread exiting.");
    }
}