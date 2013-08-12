package Servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Servlet implementation class REST
 */
@WebServlet("/REST")
public class REST extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public REST() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xml ="";
        System.out.println("I MADE IT!!");
		try{
			URL url = this.getClass().getClassLoader().getResource("Servlets/restaurants.xml");
			File rests = new File(url.getPath());	
			Scanner scan = new Scanner(rests);
			JAXBContext jb = JAXBContext.newInstance("Models");
			Unmarshaller um = jb.createUnmarshaller();
			Models.Service rest = (Models.Service)um.unmarshal(rests);
			while(scan.hasNextLine()){
				xml+=scan.nextLine();
			}
            System.out.println(xml);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
        response.setContentType("text/xml");
        response.setContentLength(xml.length());
		response.getWriter().write(xml);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
		URL url = this.getClass().getClassLoader().getResource("Servlets/restaurants.xml");
		File rests = new File(url.getPath());
		JAXBContext jb = JAXBContext.newInstance("Models");
		Unmarshaller um = jb.createUnmarshaller();
		Models.Service rest = (Models.Service)um.unmarshal(input);
		System.out.println("The customer would like to order a "+rest.getRestaurant().get(0).getItem().get(0).getName()+" from: "+rest.getRestaurant().get(0).getName());
		}
		catch(Exception e){
            System.out.println("Error was thrown");
			System.out.println(e.getMessage());
		}
	}
	

}
