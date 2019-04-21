package mpjlab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import mpi.*;
public class HelloWorld {
    
    public static String mainPath = "/mnt/D/TA/CMP(N)306 Advanced Programming/2018/Labs/Lab 5/";
    public static void main(String args[]) throws Exception {
          MPI.Init(args);
        int me = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        System.out.println("Hi from <"+me+">");
        MPI.Finalize();
		 
    }

    
}
