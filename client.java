package ex1;
import java.io.*;  
import java.net.*;
import java.util.Scanner;
import java.util.Random;

/**
 * A simple client for the capitalization server.
 */
public class client {

    public static void main(String[] args) throws Exception {
        Scanner inputt = new Scanner(System.in);
		int choice =0;
		while (choice != 1 && choice != 2) {
		System.out.println("What service do you need please choose 1(for Push Msg) or 2(for Pull Msg");
		 choice = inputt.nextInt();
		}
		int port1=2020;
		int port2=2030;
		int port=0;
		if (choice == 1) 
		{   port = port1;
		    Socket socket = new Socket("localhost", port);
	        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while(true) {
	        // Consume the initial welcoming messages from the server
	        
            	System.out.println(in.readLine());
	        BufferedReader consolereader = new BufferedReader (new InputStreamReader(System.in));
	        String message = "";
	        System.out.println("Enter the message which you want to push ");
	        message = consolereader.readLine();
	        out.println(message); //send message to server
	        try {
                Thread.sleep((new Random()).nextInt(20000));
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
	        String response = in.readLine();            
	        System.out.println("Server responded with  "+ response + "\n");
	        
	        }
		}
		
		if(choice==2)
		{
		port=port2;
		//Connect to server
        Socket socket = new Socket("localhost", port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // Consume the initial welcoming messages from the server
       while(true) {
        System.out.println(in.readLine());
        
        BufferedReader consolereader = new BufferedReader (new InputStreamReader(System.in));
        String message = "";
        
        System.out.println("Enter the id of the message which you want to pull");
        message = consolereader.readLine();
        out.println(message); //send message to server
        Thread.sleep((new Random()).nextInt(10000));
        String response = in.readLine();            
        System.out.println( response + "\n");
        }
		}
        
    }
}