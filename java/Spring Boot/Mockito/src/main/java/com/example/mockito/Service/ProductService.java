package com.example.mockito.Service;


import com.example.mockito.Dao.Do.ProductDo;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public int deleteByProductSerial(Long serial);

    public void delete(ProductDo product);

    public Optional<ProductDo> findByProductSerial(Long serial);

    public List<ProductDo> findAll();

    public ProductDo save(ProductDo productDo);
}
