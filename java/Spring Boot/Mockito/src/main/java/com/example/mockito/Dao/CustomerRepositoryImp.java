package com.example.mockito.Dao;

import com.example.mockito.Dao.CustomerRepository;
import com.example.mockito.Dao.Do.CustomerDo;
import com.example.mockito.Dao.exception.CustomerIsExist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CustomerRepositoryImp implements CustomerRepository {
    public final List<CustomerDo> customerDoList = new LinkedList<>();
    private long lastSerial = 0L;

    @Override
    public synchronized CustomerDo save(CustomerDo customer) {
        if (this.findByCustomerSerial(customer.getCustomerSerial()).isPresent()) {
            throw new CustomerIsExist();
        }

        if (customer.getCustomerSerial() == null) {
            customer.setCustomerSerial(++lastSerial);
        } else {
            this.findByCustomerSerial(customer.getCustomerSerial())
                    .ifPresent(customerDoList::remove);
        }

        customerDoList.add(customer);
        return customer;
    }


    @Override
    public int deleteByCustomerSerial(Long serial) {
        Optional<CustomerDo> customerDo = findByCustomerSerial(serial);
        if (customerDo.isPresent()) {
            synchronized (customerDoList) {
                return customerDoList.remove(customerDo.get()) ? 1 : 0;
            }
        }
        return 0;
    }


    @Override
    public Optional<CustomerDo> findByCustomerSerial(Long serial) {
        synchronized (customerDoList) {
            return customerDoList.stream()
                    .filter(customerDo -> customerDo.getCustomerSerial().equals(serial))
                    .findFirst();
        }
    }

}
