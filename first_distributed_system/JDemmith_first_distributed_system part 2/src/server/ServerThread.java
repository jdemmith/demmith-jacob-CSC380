package server;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
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
	        String input;
	        MathLogic logic = new MathLogic();
	        Class c = logic.getClass();
	        Class[] params = null;
	        Method currentMethod = null;
	        Method[] methods = c.getDeclaredMethods();
	        ArrayList<String> methodNames = new ArrayList<String>();
	        String question = "What would you like to do?";
	        for(int i = 0;i<methods.length;i++){
	        	methodNames.add(methods[i].getName().toUpperCase());
	        	question+="@"+methods[i].getName();
	        }
	        out.println(question);
	        while ((input = in.readLine()) != null) {
	        	if(methodNames.contains(input.toUpperCase())){
		        	for(Method i:methods){
		        		if(i.getName().equalsIgnoreCase(input)){
		        			params = i.getParameterTypes();
		        			currentMethod = i;
		        			String goOut = "Please enter in the following parameters separated by commas:";
		        			for(int d=0;d<params.length;d++){
		        				goOut+=params[d].getName();
		        				if(d!=params.length-1){
		        					goOut+=",";
		        				}
		        			}
		        			out.println(goOut);
		        		}
		        	}
	        	}
	        	else{
	        		String[] userInput = input.split(",");
	        		ArrayList<Object> list = new ArrayList<Object>();
	        		for(int i=0;i<userInput.length;i++){
	        			if(params[i].getName().equals("int")){
	        				list.add(Integer.parseInt(userInput[i]));
	        			}
	        			else if(params[i].getName().equals("double")){
	        				list.add(Double.parseDouble(userInput[i]));
	        			}
	        			else if(params[i].getName().equals("float")){
	        				list.add(Float.parseFloat(userInput[i]));
	        			}
	        			else if(params[i].getName().equals("long")){
	        				list.add(Long.parseLong(userInput[i]));
	        			}
	        			else if(params[i].getName().equals("short")){
	        				list.add(Short.parseShort(userInput[i]));
	        			}
	        			else if(params[i].getName().equals("byte")){
	        				list.add(Byte.parseByte(userInput[i]));
	        			}
	        			else if(params[i].getName().equals("boolean")){
	        				list.add(Boolean.parseBoolean(userInput[i]));
	        			}
	        			else if(params[i].getName().equals("java.lang.String")){
	        				list.add(userInput[i]);
	        			}
	        		}
	        		out.println(currentMethod.invoke(logic, list.toArray()));
	        	}
	        }
		    out.close();
		    in.close();
		    client.close();
	    	}
    	catch(IOException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex){
    		System.out.println("Something went wrong with a stream.");
    	}
    }
}