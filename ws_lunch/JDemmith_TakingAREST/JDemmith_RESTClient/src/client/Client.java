package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import Models.Service;
import Models.Service.Restaurant;
import Models.Service.Restaurant.Item;

public class Client {
Scanner scan;
	public Client() throws IOException, JAXBException {
		URLConnection urlConnect = null;
		scan = new Scanner(System.in);
		URL url = new URL("http://localhost:8080/REST");
		urlConnect = url.openConnection();
		urlConnect.setReadTimeout(100000);
		urlConnect.connect();
		String xml ="";
		BufferedReader input = new BufferedReader(new InputStreamReader(urlConnect.getInputStream()));
		
		JAXBContext jb = JAXBContext.newInstance("Models");
		Unmarshaller um = jb.createUnmarshaller();
		Service rest = (Service)um.unmarshal(input);
		input.close();
		System.out.println("Please choose a restaurant.");
		for(int i=0;i<rest.getRestaurant().size();i++){
			System.out.println(i+": "+rest.getRestaurant().get(i).getName());
		}
		int restInput=scan.nextInt();
		Restaurant r = rest.getRestaurant().get(restInput);
		System.out.println("Please pick the item you want.");
		for(int i=0;i<r.getItem().size();i++){
			System.out.println(i+": "+r.getItem().get(i).getName());
		}
		restInput = scan.nextInt();
		Item item = r.getItem().get(restInput);
		xml="<Service><Restaurant><Name>"+r.getName()+"</Name><Item><Name>"+item.getName()+"</Name></Item></Restaurant></Service>";
		HttpURLConnection postConnect = (HttpURLConnection) url.openConnection();
		postConnect.setRequestMethod("POST");      
        postConnect.setDoOutput(true);
        postConnect.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        postConnect.setRequestProperty( "Content-Length", "" + xml.length());
        postConnect.connect();

        PrintWriter pw = new PrintWriter(postConnect.getOutputStream());
        pw.write(xml);
        pw.flush();
        pw.close();
        postConnect.getResponseMessage();
	}

}
