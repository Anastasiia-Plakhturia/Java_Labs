package org.java.solution.exceptions;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class ExchangerLabs {
    public static void main(String args[]) {
        Exchanger<String> exgr = new Exchanger<>();
        new Labs1("Task1", exgr);
        new Labs2("Task2", exgr);
    }
}
class Labs1 implements Runnable{
    String name;
    String str;
    Exchanger<String> ex;
    Labs1 (String n, Exchanger<String> exch) {
        name = n;
        ex=exch;
        str = new String();
        new Thread(this, name).start();

    }
    public void run() {

        try {
            Task1.Runner creator = new Task1.Runner();
            List<String> resultList = creator.createResultList();
            resultList.forEach(System.out::println);
            str = resultList.get(0);
            ex.exchange(str);
        }
        catch (InterruptedException exc)
        {
            System.out.println(exc);
        }
        System.out.println("***************");
    }

}

class Labs2 implements Runnable {
    String name;
    Exchanger<String> ex;
    String str;

    Labs2 (String n, Exchanger<String> exch ) {
        name = n;
        ex=exch;
        new Thread(this, name).start();
    }
    public void run() {

        try{
            try {
                str = ex.exchange(new String());
                Task2.Validator validator = new Task2.Validator();
                validator.validate(str);

            }
            catch (InterruptedException exc)
            {
                System.out.println(exc);
            }
            System.out.println();
            System.out.println("***************");
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

}
