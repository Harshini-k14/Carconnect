package com.hexaware.dao;
import com.hexaware.controller.*;
import com.hexaware.model.*;

import java.util.Date;

public interface ICustomerService {
    Customer GetCustomerById(int customerId);
    Customer GetCustomerByUsername(String username);
    void RegisterCustomer(Customer customerData);
    void UpdateCustomer(Customer customerData);
    void DeleteCustomer(int customerId);
}

