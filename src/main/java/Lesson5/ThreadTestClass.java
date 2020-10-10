package Lesson5;

import java.util.Arrays;

public class ThreadTestClass {

    static final int size = 10_000_000;
    static final int halfSize = size / 2;

    volatile float[] array = new float[size];


    public long calculateSizeValuesWithoutThreads(){

        Arrays.fill(array, 1); // set all array values to 1

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long stopTime = System.currentTimeMillis();

        // print 10 middle array values to console for check correctness with second method
        for (int i = halfSize-5; i < halfSize+5; i++) {
            System.out.println(array[i]);
        }

        return stopTime - startTime; // returns the execution time of this method
    }

    public long calculateSizeValuesWith2Threads() throws InterruptedException {

        Arrays.fill(array, 1);

        float[] arr1 = new float[halfSize];
        float[] arr2 = new float[halfSize];

        //region variables for calculate threads execution time
        final long[] startTime1 = {0};
        final long[] stopTime1 = {0};
        final long[] startTime2 = {0};
        final long[] stopTime2 = {0};
        //endregion

        long startTime = System.currentTimeMillis();

        //region split initial array into 2 arrays
        System.arraycopy(array, 0, arr1,0, halfSize);
        System.arraycopy(array, halfSize, arr2,0, halfSize);
        //endregion

        Thread thread1 = new Thread(()->{
            startTime1[0] = System.currentTimeMillis();
            for (int i = 0; i < halfSize; i++) {
                arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            stopTime1[0] = System.currentTimeMillis();
        });
        thread1.start();


        Thread thread2 = new Thread(()->{
            startTime2[0] = System.currentTimeMillis();
            for (int i = 0; i < halfSize; i++) {
                int j = i + halfSize;
                arr2[i] = (float)(arr2[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
            }
            stopTime2[0] = System.currentTimeMillis();
        });
        thread2.start();

        thread1.join(); //wait till thread1 end
        thread2.join(); //wait till thread2 end

        //region combine 2 arrays into one initial array
        System.arraycopy(arr1, 0, array, 0, halfSize);
        System.arraycopy(arr2, 0, array, halfSize, halfSize);
        //endregion

        long stopTime = System.currentTimeMillis();

        // print 10 middle array values to console for check correctness with first method
        for (int i = halfSize-5; i < halfSize+5; i++) {
            System.out.println(array[i]);
        }

        //region print execution time of threads
        System.out.println("\nFirst thread time: " + (stopTime1[0]- startTime1[0]));    // returns the execution time of thread 1
        System.out.println("Second thread time: " + (stopTime2[0]- startTime2[0]));     // returns the execution time of thread 2
        //endregion

        return stopTime - startTime; // returns the execution time of this method
    }

}
