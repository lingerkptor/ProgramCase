package com.example.mockito.Service;


import com.example.mockito.CustomerLevel;
import com.example.mockito.Dao.Do.BuyPermissionDo;
import com.example.mockito.Dao.Do.BuyPermissionId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BuyPermissionService {
    public BuyPermissionDo save(BuyPermissionDo product);

    public List<BuyPermissionDo> findByProductSerial(Long productSerial);

    public BuyPermissionDo findByProductSerialAndCustomerLevel(Long productSerial, CustomerLevel customerLevel);

}
