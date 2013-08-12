package service;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jdemmith
 * Date: 8/6/13
 * Time: 5:51 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService(endpointInterface = "service.LunchService", serviceName = "LunchService")
public class LunchServiceImpl implements LunchService {

    public static final Service sv= new Service();

    static{
        Service.Restaurant r = new Service.Restaurant();
        Service.Restaurant.Item i = new Service.Restaurant.Item();
        i.setName("Sub");
        r.setName("Subway");
        r.getItem().add(i);
        Service.Restaurant r1 = new Service.Restaurant();
        Service.Restaurant.Item i1 = new Service.Restaurant.Item();
        i1.setName("Pizza");
        r1.setName("Jimmy John's");
        r1.getItem().add(i1);
        sv.getRestaurant().add(r);
        sv.getRestaurant().add(r1);
    }


    @Override
    public List<Service.Restaurant> getRestaurants() {
          return  sv.getRestaurant();
    }

    @Override
    public void order(@WebParam(name = "Restaurant") String restName,@WebParam(name = "Item") String name) {
        System.out.println("The customer wants a "+name+" from "+restName+".");
    }
}
