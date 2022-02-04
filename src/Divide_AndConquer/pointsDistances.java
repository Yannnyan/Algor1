package Divide_AndConquer;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class pointsDistances {


    //given an array of points, find pair of points that satisfies the minimum distance out of all points


    // the idea is to divide the array into two parts each time and then ast who are the minimum distance
    // in the left and right subarray, then two points received from the right array





    public static int comparator21(Point point1, Point point2){
        if(point1.y < point2.y)
            return -1;
        else if(point1.y == point2.y)
            return 0;
        else return 1;
    }
    public static int comparator2(Point point1, Point point2){
        if(point1.x < point2.x)
            return -1;
        else if(point1.x == point2.x)
            return 0;
        else return 1;

    }
    public static void swap(Point[] array, int i, int j){
        Point temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    // performs an insertion sort
    public static void sortArray(Point[] array,boolean xory){
        int indexmin;
        Point minimum;
        for (int i = 0; i < array.length; i++) {
            minimum = array[i];
            indexmin = i;
            for (int j = i+1; j < array.length; j++) {
               if(xory == false){
                   if(comparator2(minimum, array[j]) != -1) {
                       minimum = array[j];
                       indexmin = j;
                   }
               }
               else{
                   if(comparator21(minimum, array[j]) != -1) {
                       minimum = array[j];
                       indexmin = j;
                   }
               }
            }
            swap(array,indexmin,i);
        }


    }
    public static void pairDistance(Point[] points){

        int n = points.length;
        Point[] xsorted = new Point[points.length];
        Point[] ysorted = new Point[points.length];
        for (int i = 0; i < xsorted.length; i++) {
            xsorted[i] = points[i]; ysorted[i] = points[i];
        }
        sortArray(xsorted,false);
        sortArray(ysorted,true);




    }




}
