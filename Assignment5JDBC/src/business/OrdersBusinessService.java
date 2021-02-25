package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;
import beans.Orders;

/**
 * Session Bean implementation class OrdersBusinessService
 */
@Stateless
@Local(OrdersBusinessInterface.class)
@Alternative
public class OrdersBusinessService implements OrdersBusinessInterface {
	
	List<Order> orders = new ArrayList<Order>();
	
    /**
     * Default constructor. 
     */
    public OrdersBusinessService() {
		orders.add(new Order("00044", "AMD Ryzen 5 2600", (float) 159.99, 1));
		orders.add(new Order("03532", "AMD Ryzen 5 2600X", (float) 189.99, 1));
		orders.add(new Order("87583", "AMD Ryzen 5 3600", (float) 194.99, 1));
		orders.add(new Order("34756", "AMD RYZEN 5 3600X", (float) 210.99, 1));
		orders.add(new Order("03461", "AMD-Ryzen 7 5800X", (float) 524.99, 1));
		orders.add(new Order("99166", "AMD Ryzen 9 3900XT", (float) 644.99, 1));
		orders.add(new Order("85756", "NVIDIA GeForce RTX 3070", (float) 999.99, 1));
		orders.add(new Order("84780", "NVIDIA Geforce RTX 3080", (float) 1799.99, 1));
		orders.add(new Order("99246", "NVIDIA GeForce RTX 3090", (float) 2999.99, 1));
		orders.add(new Order("39131", "NVIDIA Tesla K80", (float) 509.99, 1));
		orders.add(new Order("03689", "NVIDIA Quadro P6000", (float) 5799.99, 1));
    }

	@Override
	public void test() {
		// TODO 
		System.out.println(">>>Hello from the OrdersBusinessService");
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
			this.orders = orders;
	}

}
