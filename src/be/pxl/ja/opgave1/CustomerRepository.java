package be.pxl.ja.opgave1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {
	private Map<String, Customer> customers = new HashMap<>();
	
	public CustomerRepository() {
		for (Customer customer : Customers.customers) {
			customers.put(customer.getCustomerNumber(), customer);
		}
	}
	
	public Customer getByCustomerNumber(String customerNumber) {
		return customers.getOrDefault(customerNumber, null);
	}
	
	public List<Customer> findAll() {
		/*
		 * Either return collection or return new ArrayList which is copy of customers-values
		 * actions on new ArrayList will not alter actual data in customers map
		 */
		return new ArrayList<>(customers.values());
	}
}
