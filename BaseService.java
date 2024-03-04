package edu.syr.textbooks.service;

// Use of abstract class
abstract class BaseService {
    long startTime, endTime;
    void startTimer(){
        startTime = System.nanoTime();
    }
    void endTimer(String methodName){
        endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Method "+methodName +" takes: "+duration+" Seconds");
    }
}

