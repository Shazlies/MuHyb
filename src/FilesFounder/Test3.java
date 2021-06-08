/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FilesFounder;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class Test3 {

    public static void main(String[] args) {
        boolean x = false;

        ExecutorService ex2 = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            ex2.execute(new Task());

        }
        ex2.shutdown();

        try {
            ex2.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex1) {
            System.out.println(ex1.toString());
        }
    }

    static class Task implements Runnable {
        boolean task1 = false;
        boolean task2 = false;
        boolean task3 = false;
        boolean task4 = false;
        
        public Task() {
            
        }
        private Lock lock = new ReentrantLock();
        @Override
        public void run() {
            
            lock.lock();
            firstThread();
            lock.unlock();
            lock.lock();
            secondThread();
            lock.unlock();
            lock.lock();
            thirdThread();
            lock.unlock();
            lock.lock();
            fourthThread();
            lock.unlock();

        }
        
        
        public void firstThread(){
            System.out.println("first thread");
            System.out.println(Thread.currentThread().getName());
        }
        public void secondThread(){
            System.out.println("second thread");
            System.out.println(Thread.currentThread().getName());
        }
        public void thirdThread(){
            System.out.println("third thread");
            System.out.println(Thread.currentThread().getName());
        }
        public void fourthThread(){
            System.out.println("fourth thread");
            System.out.println(Thread.currentThread().getName());
        }
        

    }
}
