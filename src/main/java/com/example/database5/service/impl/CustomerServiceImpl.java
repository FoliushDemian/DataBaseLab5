package com.example.database5.service.impl;


import com.example.database5.domain.Customer;
import com.example.database5.exception.CustomerNotFoundException;
import com.example.database5.repository.CustomerRepository;
import com.example.database5.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional
    public Customer create(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Transactional
    public void update(Integer id, Customer uCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        //update
        customer.setName(uCustomer.getName());
        customer.setEmail(uCustomer.getEmail());
        customer.setPhoneNumber(uCustomer.getPhoneNumber());

        customerRepository.save(customer);
    }

    @Transactional
    public void delete(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customerRepository.delete(customer);
    }
}
