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
            System.err.println("Cant connect to that port.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get Streams.");
            System.exit(1);
        }
 
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        
 
        while ((fromServer = in.readLine()) != null) {
        	String[] strings = fromServer.split("@");
        	String serverAnswer = strings[0];
        	for(int i=1;i<strings.length;i++){
        		serverAnswer+= "\n"+strings[i];
        	}
            System.out.println("Server: " + serverAnswer);
             
            fromUser = stdIn.readLine();
            out.println(fromUser);
        }
 
        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }
}
