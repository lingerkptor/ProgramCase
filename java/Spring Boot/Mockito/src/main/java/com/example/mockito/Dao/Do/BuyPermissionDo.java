package com.example.mockito.Dao.Do;

import com.example.mockito.CustomerLevel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class BuyPermissionDo implements Serializable {
    private static final long serialVersionUID = -7697947438057874933L;

    private Long productSerial;
    private Long maxBuyCount;
    private CustomerLevel customerLevel;
}
