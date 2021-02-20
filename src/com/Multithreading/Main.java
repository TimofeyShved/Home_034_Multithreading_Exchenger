package com.Multithreading;

import java.util.concurrent.Exchanger;

public class Main {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>(); // класс для передачи данными между потоками

        new Mike(exchanger); // наши потоки с общим Exchanger
        new Anket(exchanger);
    }

    static class Mike extends Thread{
        Exchanger<String> exchanger;

        public Mike(Exchanger<String> exchanger){ // запихиваем Exchanger
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run(){
            try {
                exchanger.exchange("Привет"); // запихиваем в Exchanger, новую строчку
                sleep(3000);
                exchanger.exchange("Пока");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Anket extends Thread{
        Exchanger<String> exchanger;

        public Anket(Exchanger<String> exchanger){
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run(){
            try {
                System.out.println(exchanger.exchange(null)); // принимаем Exchanger и выводим на экран
                String string = exchanger.exchange(null);
                System.out.println(string);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
