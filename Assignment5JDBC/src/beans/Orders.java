/**
 * 
 */
package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Nick
 *
 */

@ManagedBean(name="orders")
@ViewScoped
public class Orders {
	private List<Order> orders = new ArrayList<Order>();
	
	public Orders() {
		
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void printAll() {
		System.out.println(">>>>[Orders on file: START]>>>>");
		for(Order order:orders) {
			System.out.println(order.toString());
		}
		System.out.println(">>>>[Orders on file: END]>>>>");
	}
	
	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
}
