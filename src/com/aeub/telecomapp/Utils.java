package com.aeub.telecomapp;

public class Utils {
    public static <T extends Object> T tryLoop(Callable<T> tryScanFunc, String errorMessage) {
        while(true){
            try{
                return tryScanFunc.call();
            }catch (Exception ex){
                System.out.println(errorMessage);
            }
        }
    }

    public interface Callable<T> {
        public T call() throws Exception;
    }
}
