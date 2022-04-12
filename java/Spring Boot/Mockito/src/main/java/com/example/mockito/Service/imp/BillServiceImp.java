package com.example.mockito.Service.imp;


import com.example.mockito.Service.BillService;
import com.example.mockito.Dao.Do.BillDo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

@Component
public class BillServiceImp implements BillService {
    private List<BillDo> billList = new Vector<>();
    private Long lastSerial = 0L;

    @Override
    public BillDo save(BillDo billDo) {
        if (billDo == null) {
            throw new NullPointerException("bill is null");
        }

        if (billDo.getBillSerial() == null) {
            billDo.setBillSerial(++lastSerial);
        } else {
            billList.stream()
                    .filter(bill -> bill.getBillSerial().equals(billDo.getBillSerial()))
                    .findFirst()
                    .ifPresent(bill -> billList.remove(bill));
        }

        billList.add(billDo);

        return billDo;
    }

    @Override
    public long count() {
        return billList.size();
    }

    @Override
    public List<BillDo> findByCustomerSerial(Long customerSerial) {
        return billList.stream()
                .filter(billDo -> billDo.getCustomerSerial().equals(customerSerial))
                .collect(Collectors.toList());
    }
}
