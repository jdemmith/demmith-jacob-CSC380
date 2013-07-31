package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.sun.xml.rpc.tools.wscompile.Main;

import servlets.Service;

/**
 * Servlet implementation class REST
 */
@WebServlet("/REST/*")
public class REST extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public REST() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		URL url = this.getClass().getClassLoader().getResource("restaurants.xml");
		File rests = new File(url.getPath());	
		String xml ="";
		Scanner scan = new Scanner(rests);
		while(scan.hasNextLine()){
			xml+=scan.nextLine()+"<br/>";
		}
		request.getSession().setAttribute("xml", xml);
		
		response.sendRedirect("selection.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String restaurant = request.getParameter("restaurant");
		String item = request.getParameter("item");
		URL url = this.getClass().getClassLoader().getResource("orders.txt");
		System.out.println(url.getPath());
		File orders = new File(url.getPath());
		restaurant = restaurant.toUpperCase();
		item = item.toUpperCase();
		String order = "Restaurant: "+restaurant+ "Item: "+item;
		FileOutputStream file = new FileOutputStream(orders);
		Writer writer = new BufferedWriter(new OutputStreamWriter(file,"utf-8"));
		writer.write(order);
		writer.flush();
		writer.close();
		file.close();
		
		response.getWriter().write("Your order has been saved!!");
	}
	

}
