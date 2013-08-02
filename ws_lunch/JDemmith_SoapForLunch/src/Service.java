

import MyPackage.Envelope;
import MyPackage.GetAllRestaurant;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Validator;

/**
 * Servlet implementation class Service
 */
@WebServlet("/Service")
public class Service extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Service() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream is = request.getInputStream();	
		try {
			JAXBContext jc = JAXBContext.newInstance("org.example.restaurants");
			Unmarshaller um = jc.createUnmarshaller();
			Envelope ev = (Envelope)um.unmarshal(is);
            String xml ="";
            if(ev.getBody().getClass().isInstance(GetAllRestaurant.class)){
                URL url = this.getClass().getClassLoader().getResource("restaurants.xml");
                File rests = new File(url.getPath());
                Scanner scan = new Scanner(rests);
                while(scan.hasNextLine()){
                    xml+=scan.nextLine();
                }
            }

            response.getWriter().write(xml);

		} 
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
