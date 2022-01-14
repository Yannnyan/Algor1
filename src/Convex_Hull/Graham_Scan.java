package Convex_Hull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Graham_Scan {
    // take 3 by 3 matrix calculate the determinant
    private static int determinant(int[][] matrix){
        int sum=0;
        int x1, y1,x2,y2;
        for (int i = 0; i < matrix.length; i++) {
            x1 = matrix[1][(i+1)%3]; y1 = matrix[2][(i+1)%3];
            x2 = matrix[1][(i+2)%3]; y2 = matrix[2][(i+2)%3];
            int[][] mat2x2= make_matrix(new Point(x1,y1),new Point(x2,y2));
            sum+= mat2x2[0][0]*mat2x2[1][1] - mat2x2[0][1]*mat2x2[1][0];
        }

        return sum;
    }
    private static int[][] make_matrix(Point p1, Point p2){
        int[][] mat = new int[2][2];
        mat[0][0] = p1.x; mat[0][1] = p2.x;
        mat[1][0] = p1.y; mat[1][1] = p2.y;
        return mat;
    }
    private static int[][] make_matrix(Point p1, Point p2, Point p3){
        int[][] mat = new int[3][3];
        for (int i = 0; i < 3; i++) {
            mat[0][i] = 1;
        }
        mat[1][0] = p1.x; mat[1][1] = p2.x; mat[1][2] = p3.x;
        mat[2][0] = p1.y; mat[2][1] = p2.y; mat[2][2] = p3.y;
        return mat;
    }
    public static int comparator(Point q, Point p1, Point p2){
        int[][] matrix = make_matrix(p1,q,p2);
        double deter = determinant(matrix);
        if( deter < 0)
            return -1;
        else if (deter > 0)
            return 1;
        else
            return 0;

    }
    public static ArrayList graham_scan(Point[] points){
        if(points.length <=2){
            return null;
        }
        int index = -1;double valmin = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            if(points[i].y < valmin){
                valmin = points[i].y;
                index = i;
            }
        }
        Point minimal_point = points[index];

        quicksort(points,comparator,minimal_point); // sort the list with the comparator
        Stack<Point> stack = new Stack<>();
        stack.add(points[0]);
        stack.add(points[1]);


        for (int i = 3; i < points.length; i++) {
            while(comparator(stack.elementAt(1),stack.elementAt(0),points[i]) < 0){ // while makes a left turn pop
                stack.pop();
            }
            stack.push(points[i]);
        }
        ArrayList<Point> ret = (ArrayList<Point>) stack.stream().toList();
        return ret;
    }
}
