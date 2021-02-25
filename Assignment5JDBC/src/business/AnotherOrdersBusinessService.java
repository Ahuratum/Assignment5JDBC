package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

/**
 * Session Bean implementation class AnotherOrdersBusinessService
 */
@Stateless
@Local(OrdersBusinessInterface.class)
@Alternative
public class AnotherOrdersBusinessService implements OrdersBusinessInterface {

	List<Order> orders = new ArrayList<Order>();
	
    /**
     * Default constructor. 
     */
    public AnotherOrdersBusinessService() {
		orders.add(new Order("60135", "MicroSD - 1GB", (float) 0.99, 1));
		orders.add(new Order("04366", "MicroSD - 2GB", (float) 1.59, 1));
		orders.add(new Order("25673", "MicroSD - 4GB", (float) 2.99, 1));
		orders.add(new Order("11955", "MicroSD - 8GB", (float) 3.49, 1));
		orders.add(new Order("42112", "MicroSD - 16GB", (float) 4.99, 1));
		orders.add(new Order("33830", "MicroSD - 32GB", (float) 8.99, 1));
		orders.add(new Order("42271", "MicroSD - 64GB", (float) 15.99, 1));
		orders.add(new Order("15740", "MicroSD - 128GB", (float) 22.99, 1));
		orders.add(new Order("64159", "MicroSD - 256GB", (float) 39.99, 1));
		orders.add(new Order("31995", "MicroSD - 512GB", (float) 48.99, 1));
		orders.add(new Order("36426", "MicroSD - 1TB", (float) 55.99, 1));
    }

	@Override
	public void test() {
		// TODO 
		System.out.println(">>>Hello from the AnotherOrdersBusinessService");
		
	}

	@Override
	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
