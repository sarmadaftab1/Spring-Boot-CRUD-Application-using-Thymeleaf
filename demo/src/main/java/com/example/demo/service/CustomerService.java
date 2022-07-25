package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Customer> getAllCustomerSalman(){
        return customerRepository.findAll()
                .stream()
                .toList();
    }
    public void createCustomer(Customer customer)
    {
        customerRepository.save(customer);
    }

    public Customer UpdateCustomerById (long cust_id){
        Customer customer = new Customer();
        customerRepository.save(customer);
        return customer;
    }

    public Customer getCustomerById( Long cust_id){
         Optional<Customer> res= customerRepository.findById(cust_id);
         if(res.isPresent())
         {
             return res.get();
         }
         else return null;
    }

    public void deleteCustomer(long cust_id) {
        customerRepository.deleteById(cust_id);
    }
}
