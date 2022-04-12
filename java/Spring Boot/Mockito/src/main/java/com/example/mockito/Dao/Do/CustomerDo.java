package com.example.mockito.Dao.Do;

import com.example.mockito.CustomerLevel;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table
public class CustomerDo implements Serializable {
    private static final long serialVersionUID = -6721657946717871035L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerSerial;

    private String customerName;

    private CustomerLevel customerLevel;
}
