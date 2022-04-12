package com.example.mockito.Dao;


import com.example.mockito.Dao.Do.CustomerDo;

import java.util.Optional;

public interface CustomerRepository  {

    public int deleteByCustomerSerial(Long serial);

    public Optional<CustomerDo> findByCustomerSerial(Long serial);

    public CustomerDo save(CustomerDo customerDo);
}
