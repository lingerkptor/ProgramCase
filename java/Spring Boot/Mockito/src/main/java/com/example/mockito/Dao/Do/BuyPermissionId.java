package com.example.mockito.Dao.Do;

import com.example.mockito.CustomerLevel;
import lombok.Data;

import java.io.Serializable;

@Data
public class BuyPermissionId implements Serializable {
    private static final long serialVersionUID = 3143169151520075440L;

    private Long productSerial;
    private CustomerLevel customerLevel;
}
