package server;

import java.net.*;
import java.io.*;
 
public class ServerThread extends Thread {
	private Socket client = null;
	
	public ServerThread(Socket socket){
		this.client=socket;
	}
    public void run(){ 
    	try{
	        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        String input="";
	        String output="";
	        MathLogic logic = new MathLogic();
	        out.println("What would you like to do?1-Add 2-Subtract 3-Exit");
	 
	        while ((input = in.readLine()) != null) {
	        	String[] strings = input.split(",");
	        	if(input.contains("Add")){
	             output = ""+logic.add(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
	        	}
	        	else if(input.contains("Subtract")){
	                output = ""+logic.subtract(Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
	           	}
	             out.println(output);
	 	        out.println("What would you like to do?1-Add 2-Subtract 3-Exit");
	             if (input.contains("Bye."))
	                break;
	        }
		        out.close();
		        in.close();
				client.close();
	    	}
    	catch(IOException ex){
    		System.out.println("Something went wrong with a stream.");
    	}
    }
}