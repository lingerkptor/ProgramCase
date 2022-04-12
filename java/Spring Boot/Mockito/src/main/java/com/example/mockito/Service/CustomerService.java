package com.example.mockito.Service;

import com.example.mockito.Dao.CustomerRepository;
import com.example.mockito.Dao.Do.CustomerDo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDo findCustomer(Long customSerial) {
        return customerRepository.findByCustomerSerial(customSerial).orElse(null);
    }

}
