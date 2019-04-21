package ex1;
import java.io.*;  
import java.net.*; 

public class server {

    /**
     * Application method to run the server runs in an infinite loop
     * listening on port 9898.  When a connection is requested, it
     * spawns a new thread to do the servicing and immediately returns
     * to listening.  The server keeps a unique client number for each
     * client that connects just to show interesting System.out.println
     * messages.  It is certainly not necessary to do this.
     */
	public static  String[] messageBox;
	
	
	
    public static void main(String[] args) throws Exception {
        System.out.println("The server is running.");
        int clientNumber = 0;
        
        messageBox=new String[10];
        for(int i=0;i<10;i++)
        {
        	messageBox[i]="";
        }
        ServerSocket listener = new ServerSocket(2020);
        ServerSocket listener2= new ServerSocket(2030);
        try {
            while (true) {
                /* the server waits here till a client reaches at accept() method. (a.k.a. a client created a socket at port 9898)
                just a client reached; it enters Capitalizer constructor and starts the Capitalizer thread*/
                new clientconnect(listener.accept(), clientNumber++).start(); 
                new clientconnect(listener2.accept(), clientNumber++).start();
                
            }
        } finally {
            listener.close();
            listener2.close();
        }
        
    }

    /**
     * A private thread to handle capitalization requests on a particular
     * socket.  The client terminates the System.out.println by sending a single line
     * containing only a period.
     */
        private static class clientconnect extends Thread {
        private Socket socket;
        private int clientNumber;
        

        public clientconnect(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
          
            System.out.println("New connection with client# " + clientNumber + " at " + socket);
        }

        /**
         * Services this thread's client by first sending the
         * client a welcome message then repeatedly reading strings
         * and sending back the capitalized version of the string.
         */
        public void run() {
            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                
                
              while(true) {
            	// Send a welcome message to the client.
            	  out.println("Hello, you are client #" + clientNumber + ".");
              if(socket.getLocalPort()==2020) // This client want to push message
              {
            	  String input = in.readLine();
            	  if (input == null ) {
                      break;
            	  }
            	  for(int i=0;i<10;i++) {
            	  if(messageBox[i]=="") {
            	     messageBox[i]=input;
            	     out.println("Message "+input+" is added successfully");
            	     break;
                        } 
              else {if(i==9) {out.println("Message Box is FULL can't ADD message ");}}
            		  
            		  }
               }
            	  
              else  // this client want to pull message
              {
            	  String input = in.readLine();
            	  if (input == null ) {
                      break;}
            	 
            	  out.println("The message is "+messageBox[Integer.parseInt(input)]);
              }
            
                }
               
            } catch (IOException e) {
                System.out.println("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close a socket, what's going on?");
                }
                System.out.println("Connection with client# " + clientNumber + " closed");
            }
        }

    }
}