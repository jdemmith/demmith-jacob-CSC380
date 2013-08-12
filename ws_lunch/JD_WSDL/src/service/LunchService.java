package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jdemmith
 * Date: 8/6/13
 * Time: 5:48 PM
 * To change this template use File | Settings | File Templates.
 */
@WebService(name="LunchService",targetNamespace = "http://localhost/LunchService")
public interface LunchService {

    @WebMethod(operationName="getRestaurants")
    public @WebResult(name="RestaurantList") List<Service.Restaurant> getRestaurants();

    @WebMethod(operationName = "order")
    public void order(@WebParam(name="Restaurant")String restName,@WebParam(name="item")String name);
}
