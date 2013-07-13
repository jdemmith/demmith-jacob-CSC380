package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import java.io.*;
import java.net.*;
 
public class Client {
    public static void main(String[] args) throws IOException {
 
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
 
        try {
            socket = new Socket("localhost", 4444);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Cant connec to that port.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get Streams.");
            System.exit(1);
        }
 
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        
 
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equals("Bye."))
                break;
             
            fromUser = stdIn.readLine();
            System.out.println(fromUser);
        if (fromUser != null) {
        	if(fromUser.equals("1")){
        		System.out.println("Select your first number.");
        		String param1 = stdIn.readLine();
        		System.out.println("Select your second number.");
        		String param2 = stdIn.readLine();
        		out.println("Add,"+param1+","+param2);    
        	}
        	else if(fromUser.equals("2")){
        		System.out.println("Select your first number.");
        		String param1 = stdIn.readLine();
        		System.out.println("Select your second number.");
        		String param2 = stdIn.readLine();
        		out.println("Subtract,"+param1+","+param2);        		
        	}
        	else if(fromUser.equals("3")){
        		out.println("Bye");
        	}
        }
        }
 
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
