package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private static CustomerService instance;
    private CustomerService(){}

    public static CustomerService getInstance(){
        if(instance == null){
            instance = new CustomerService();
        }
        return instance;
    }


    Map<String, Customer> allCustomers = new HashMap<String, Customer>();



    public  void addCustomer(String email, String firstName, String lastName){
        allCustomers.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail){
        if(allCustomers.containsKey(customerEmail)){
            return allCustomers.get(customerEmail);
        }else {
            System.out.println("No customer found.");
            return null;
        }
    }

    public Collection<Customer> getAllCustomers(){
        if (allCustomers.values().isEmpty()){
            System.out.println("No customer found.");
        }
        return allCustomers.values();

    }
}
