package com.example.mockito.Service.imp;

import com.example.mockito.Dao.Do.ProductDo;
import com.example.mockito.Service.ProductService;
import com.example.mockito.Dao.exception.ProductIsExist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class ProductServiceImp implements ProductService {
    public final List<ProductDo> productDoList = new Vector<>();
    private long lastSerial = 0L;

    public synchronized ProductDo save(ProductDo product) throws ProductIsExist {
        if (product == null) {
            throw new NullPointerException("product is null");
        }

        if (productDoList.contains(product)) {
            return product;
        }

        if (product.getProductSerial() == null) {
            product.setProductSerial(++lastSerial);
        } else {
            findByProductSerial(product.getProductSerial()).ifPresent(productDoList::remove);
        }
        productDoList.add(product);

        return product;
    }

    @Override
    public synchronized int deleteByProductSerial(Long serial) {
        if (serial == null) {
            throw new NullPointerException("serial is null");
        }
        Optional<ProductDo> productDo = findByProductSerial(serial);
        if (productDo.isPresent()) {
            return productDoList.remove(productDo.get()) ? 1 : 0;
        }
        return 0;
    }


    public synchronized void delete(ProductDo product) {
        if (product == null) {
            throw new NullPointerException("product is null");
        }
        findByProductSerial(product.getProductSerial())
                .map(productDo -> deleteByProductSerial(productDo.getProductSerial()))
                .orElse(0);
    }

    @Override
    public synchronized Optional<ProductDo> findByProductSerial(Long serial) {
        if (serial == null) {
            throw new NullPointerException("serial is null");
        }
        synchronized (productDoList) {
            return productDoList.stream()
                    .filter(productDo -> productDo.getProductSerial().equals(serial))
                    .findFirst();
        }
    }

    @Override
    public List<ProductDo> findAll() {
        return productDoList;
    }
}
