package com.example.mockito.Service;


import com.example.mockito.Dao.Do.BillDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillService {

    public BillDo save(BillDo billDo);

    public long count();

    public List<BillDo> findByCustomerSerial(Long customerSerial);
}
