package firstprogram;
import mpi.*;
import java.io.*;
public class helloworld {


public static void main(String args[]) throws Exception {
	long start = System.currentTimeMillis();

 MPI.Init(args);
int me = MPI.COMM_WORLD.Rank();
 int size = MPI.COMM_WORLD.Size();
 String in="abdo";
 
 if(me==0)
 {
	  File file = new File("1.txt");   
	  BufferedReader br = new BufferedReader(new FileReader(file)); 
	  int i=0;
	  int number[]=new int[10];
	  boolean found1=false;
	  String st; 
	  while ((st = br.readLine()) != null) 
	    { i=i+1;
		  if(st.equals(in)) {
			  System.out.println("The word is found at line "+i+" In file 1.txt");
			  found1=true;   
		  }
		}
	  if(found1==true)
		  {
		  int j;
	
		  for( j=0;j<10;j++)
		  {
		  number[j]=1;
		  }
		  MPI.COMM_WORLD.Send(number, 1, 1,MPI.INT, 1, 1);
		  }
	  else
	  {
		  int j;
			
		  for( j=0;j<10;j++)
		  {
		  number[j]=-1;
		  }
		  
		  MPI.COMM_WORLD.Send(number, 1, 1,MPI.INT, 1, 1);
	  }

 }
 else {
	 File file = new File("2.txt");   
 
 BufferedReader br = new BufferedReader(new FileReader(file)); 
 int i=0;
 int number1[]=new int[10];
 boolean found1=false;
 String st; 
 while ((st = br.readLine()) != null) 
   {  
	  i=i+1;
	  if(st.equals(in)) {
		  System.out.println("The word is found at line "+i+" In file 2.txt");
		  found1=true;  
		 
	  }
	}
 if(found1==false)
	  {
	  
	  MPI.COMM_WORLD.Recv(number1, 1, 1,MPI.INT, 0, 1);
	  if(number1[1]==-1) {System.out.println("Word Not found");}
	  }
 }
long finish = System.currentTimeMillis();
long timeElapsed = finish - start;
	System.out.println("Elapsed Time= "+timeElapsed+" ns");
 MPI.Finalize();

	
}
} 