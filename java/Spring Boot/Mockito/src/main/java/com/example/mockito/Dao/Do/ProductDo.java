package com.example.mockito.Dao.Do;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table
public class ProductDo implements Serializable {
    private static final long serialVersionUID = 3288933297230524159L;

    /**
     * 產品批號
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSerial;
    /**
     * 產品名稱
     */
    private String productName;
    /**
     * 產品價格
     */
    private Integer price;
    /**
     * 產品數量
     */
    private Long count;
}
