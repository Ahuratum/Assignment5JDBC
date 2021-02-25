package controllers;

import java.io.IOException;
import java.sql.*;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import beans.User;
import business.MyTimerService;
import business.OrdersBusinessInterface;
/**
 * @author Nick
 *
 */
@ManagedBean
@ViewScoped
public class FormController {
	
	@Inject
	OrdersBusinessInterface services;
	
//	@EJB
//	MyTimerService timer;
	
	// Get all the orders from the database 
	private void getAllOrders() throws SQLException {
		// Connection to DB
		String dbURL = "jdbc:postgresql://localhost:5432/testapp";
		String user = "postgres";
		String password = "root";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DriverManager.getConnection(dbURL, user, password);
			System.out.println(">>>>[STATUS: SUCCESS]>>>> Connected to " + dbURL + " >>>>");
			
			// Create statement and execut result set 
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from testapp.orders");
			
			while(rs.next()) {
				System.out.println(">>>>[Order]>>>> ID: " + rs.getInt("id") 
						+ ", OrderNumber: " + rs.getInt("order_no") 
						+ ", ProductName: " + rs.getString("product_name") 
						+ ", Price: " + rs.getFloat("price")
						+ ", Quantity: " + rs.getInt("quantity"));
			}
		} catch (SQLException e) {
			System.out.println(">>>>[STATUS: FAIL]>>>> Not connected to " + dbURL + " >>>>");
			e.printStackTrace();
		}finally {
			// Close statement
			statement.close();
			if(statement.isClosed()) {
				System.out.println(">>>>[STATUS: SUCCESS]>>>> Statement has been closed >>>>");
			}
			// Result Set close 
			rs.close();
			if(rs.isClosed()) {
				System.out.println(">>>>[STATUS: SUCCESS]>>>> ResultSet has been closed >>>>");
			}
			connection.close();
			if (connection.isClosed()) {
				System.out.println(">>>>[STATUS: SUCCESS]>>>> Disconnected from " + dbURL + " >>>>");
			}
		}
	}
	
	// New submit button to handle database data retrieval 
	public void onSubmitJDBC() {
		try {
			getAllOrders();
			System.out.println(">>>>[STATUS: SUCCESS]>>>> Orders pulled from database >>>>");
		} catch (SQLException e) {
			System.out.println(">>>>[STATUS: FAIL]>>>> Orders not pulled from database >>>>");
			e.printStackTrace();
		}
		try {
			insertOrder();
			System.out.println(">>>>[STATUS: SUCCESS]>>>> Orders table updated >>>>");
		} catch (SQLException e) {
			System.out.println(">>>>[STATUS: FAIL]>>>> Orders table not updated  >>>>");
			e.printStackTrace();
		}
		try {
			getAllOrders();
			System.out.println(">>>>[STATUS: SUCCESS]>>>> Orders pulled from database >>>>");
		} catch (SQLException e) {
			System.out.println(">>>>[STATUS: FAIL]>>>> Orders not pulled from database >>>>");
			e.printStackTrace();
		}
	}
	
	// Add orders to the database
	private void insertOrder() throws SQLException {
		// Connection to DB
		String dbURL = "jdbc:postgresql://localhost:5432/testapp";
		String user = "postgres";
		String password = "root";
		
		Connection connection = null;
		Statement statement = null;
		int rowsAffected = 0;
		
		try {
			connection = DriverManager.getConnection(dbURL, user, password);
			System.out.println(">>>>[STATUS: SUCCESS]>>>> Connected to " + dbURL + " >>>>");
			
			// Create statement and execut result set 
			statement = connection.createStatement();
			rowsAffected = statement.executeUpdate("insert into testapp.orders(order_no, product_name, price, quantity) values('001122334455', 'new item', 25.00, 100)");
			
			if(rowsAffected == 0) {
				System.out.println(">>>>[STATUS: FAIL]>>>> " + rowsAffected + " rows updated >>>>");
			}
			else if (rowsAffected == 1) {
				System.out.println(">>>>[STATUS: SUCCESS]>>>> " + rowsAffected + " row updated >>>>");
			}else {
				System.out.println(">>>>[STATUS: SUCCESS]>>>> " + rowsAffected + " rows updated >>>>");
			}
			
		} catch (SQLException e) {
			System.out.println(">>>>[STATUS: FAIL]>>>> Not connected to " + dbURL + " >>>>");
			e.printStackTrace();
		}finally {
			// Close statement
			statement.close();
			if(statement.isClosed()) {
				System.out.println(">>>>[STATUS: SUCCESS]>>>> Statement has been closed >>>>");
			}
			connection.close();
			if (connection.isClosed()) {
				System.out.println(">>>>[STATUS: SUCCESS]>>>> Disconnected from " + dbURL + " >>>>");
			}
		}
	}	
	
	// Old Assignments-------------------------------------------------------------------------
	public String onSubmit() {
		// Get user data from form 
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		// Attach the user data to the request 
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "TestResponse.xhtml";
	}
	
	public String onFlash(User user) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
		System.out.println("onFlash Clicked");
		return "TestResponse2.xhtml?redirect=true";
	}
	
	public String onReturn() {
		//FacesContext context = FacesContext.getCurrentInstance();
		//FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
		//FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		System.out.println("onReturn Clicked");
		return "TestForm.xhtml?faces-redirect=true";
	}
	
	public String onSubmitBusinessInterface() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		services.test();
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		return "TestResponse.xhtml";
	}
	
	public OrdersBusinessInterface getService() {
		return services;
	}
}
