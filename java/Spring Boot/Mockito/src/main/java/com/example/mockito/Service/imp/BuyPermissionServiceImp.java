package com.example.mockito.Service.imp;

import com.example.mockito.CustomerLevel;
import com.example.mockito.Service.BuyPermissionService;
import com.example.mockito.Dao.Do.BuyPermissionDo;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BuyPermissionServiceImp implements BuyPermissionService {
    public final List<BuyPermissionDo> buyPermissionDoList = new Vector<>();

    @Override
    public synchronized BuyPermissionDo save(BuyPermissionDo permission) {
        if (permission == null || permission.getProductSerial() == null || permission.getCustomerLevel() == null) {
            throw new NullPointerException("permission is null");
        }

        BuyPermissionDo buyPermission = this.findByProductSerialAndCustomerLevel(permission.getProductSerial(), permission.getCustomerLevel());

        if (buyPermission != null) {
            buyPermissionDoList.remove(buyPermission);
        }

        buyPermissionDoList.add(permission);

        return permission;
    }


    //    @Override
    public int deleteByProductSerial(Long serial) {
        if (serial == null) {
            throw new NullPointerException("serial is null");
        }

        List<BuyPermissionDo> permissionList = findByProductSerial(serial);
        int count = 0;

        for (BuyPermissionDo permissionDo : permissionList) {
            count += buyPermissionDoList.remove(permissionDo) ? 1 : 0;
        }

        return count;
    }

    //    @Override
    public int deleteByProductSerialAndCustomerLevel(Long productSerial, CustomerLevel customerLevel) {
        BuyPermissionDo buyPermission = findByProductSerial(productSerial)
                .stream()
                .filter(permissionDo -> permissionDo.getProductSerial().equals(productSerial))
                .filter(permissionDo -> permissionDo.getCustomerLevel() == customerLevel)
                .findFirst().orElse(null);

        return 0;
    }

    //    @Override
    public int delete(BuyPermissionDo permission) {
        if (permission == null) {
            throw new NullPointerException("product is null");
        }

        BuyPermissionDo buyPermission = findByProductSerial(permission.getProductSerial())
                .stream()
                .filter(permissionDo -> permissionDo.getProductSerial().equals(permission.getProductSerial()))
                .filter(permissionDo -> permissionDo.getCustomerLevel() == permission.getCustomerLevel())
                .findFirst().orElse(null);

        if (buyPermission != null) {
            return buyPermissionDoList.remove(buyPermission) ? 1 : 0;
        }

        return 0;
    }

    @Override
    public List<BuyPermissionDo> findByProductSerial(Long serial) {
        if (serial == null) {
            throw new NullPointerException("serial is null");
        }

        return buyPermissionDoList.stream()
                .filter(productDo -> productDo.getProductSerial().equals(serial))
                .collect(Collectors.toList());
    }

    @Override
    public BuyPermissionDo findByProductSerialAndCustomerLevel(Long productSerial, CustomerLevel customerLevel) {
        BuyPermissionDo buyPermission = findByProductSerial(productSerial)
                .stream()
                .filter(permissionDo -> permissionDo.getProductSerial().equals(productSerial))
                .filter(permissionDo -> permissionDo.getCustomerLevel().equals(customerLevel))
                .findFirst().orElse(null);

        return buyPermission;
    }

}
