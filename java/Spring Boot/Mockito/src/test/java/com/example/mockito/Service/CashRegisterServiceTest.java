package com.example.mockito.Service;

import com.example.mockito.CustomerLevel;
import com.example.mockito.Dao.Do.BillDo;
import com.example.mockito.Dao.Do.BuyPermissionDo;
import com.example.mockito.Dao.Do.CustomerDo;
import com.example.mockito.Dao.Do.ProductDo;
import com.example.mockito.Dao.exception.CheckOutException;
import com.example.mockito.Service.imp.BillServiceImp;
import com.example.mockito.util.Foo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CashRegisterServiceTest {

    @InjectMocks
    @Autowired
    private CashRegisterService cashRegisterService;

    @MockBean
    private ProductService productService;

    @MockBean
    private CustomerService customerService;

    @MockBean // @Mock +  @InjectMocks +MockitoAnnotations.openMocks(this);
    private BuyPermissionService buyPermissionService;

    //@SpyBean // @Spy + @InjectMocks +MockitoAnnotations.openMocks(this);
    @Spy
    private BillService billServiceImp = new BillServiceImp();

    @BeforeEach
    public void setup() {
        // 針對每個test case 新建一個mock
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 測試庫存不足
     */
    @Test
    public void testSellProductThrowInventoryShortage() {
        // given
        {
            CustomerDo customerDo = new CustomerDo();
            {
                customerDo.setCustomerLevel(CustomerLevel.GOLD);
                customerDo.setCustomerSerial(0L);
                customerDo.setCustomerName("0");
            }
            BDDMockito.given(customerService.findCustomer(0L))
                    .willReturn(customerDo);

            ProductDo productDo = new ProductDo();
            {
                productDo.setProductName("0");
                productDo.setProductSerial(0L);
                productDo.setCount(100L);
                productDo.setPrice(1000);

            }
            BDDMockito.given(productService.findByProductSerial(0L)).willReturn(Optional.of(productDo));
        }

        // when 庫存100 ，購買數量1000
        Executable when = () -> cashRegisterService.checkout(0L, 0L, 1000);

        // then
        assertThrows(CheckOutException.class, when);
        BDDMockito.then(billServiceImp)
                .should(never())
                .save(any());
    }

    /**
     * 測試顧客允許的最大購買量不足 最多100，但是購買1000
     */
    @Test
    public void testSellProductAllowBuyNotEnough() {

        // mock config
        {
            CustomerDo customerDo = new CustomerDo();
            {
                customerDo.setCustomerLevel(CustomerLevel.SILVER);
                customerDo.setCustomerSerial(1L);
                customerDo.setCustomerName("1");
            }
            Mockito.when(customerService.findCustomer(1L)).thenReturn(customerDo);
            ProductDo productDo = new ProductDo();
            {
                productDo.setProductName("0");
                productDo.setProductSerial(0L);
                productDo.setCount(100L);
                productDo.setPrice(1000);

            }
            Mockito.when(productService.findByProductSerial(0L)).thenReturn(Optional.of(productDo));
            BuyPermissionDo buyPermissionDo = new BuyPermissionDo();
            {
                buyPermissionDo.setCustomerLevel(CustomerLevel.SILVER);
                buyPermissionDo.setMaxBuyCount(100L);
                buyPermissionDo.setProductSerial(0L);
            }
            Mockito.when(buyPermissionService.findByProductSerialAndCustomerLevel(0L, CustomerLevel.SILVER))
                    .thenReturn(buyPermissionDo);
        }

        // verify 庫存1000 ，購買數量1000
        Assertions.assertThrows(CheckOutException.class,
                () -> cashRegisterService.checkout(1L, 0L, 1000));
        Mockito.verify(productService, Mockito.times(1)).findByProductSerial(0L);
        Mockito.verify(billServiceImp, Mockito.times(0)).save(Mockito.any());
    }

    /**
     * 成功取得帳單
     */
    @Test
    public void testSellProduct() {
        // mock config
        {
            CustomerDo customerDo = new CustomerDo();
            {
                customerDo.setCustomerLevel(CustomerLevel.SILVER);
                customerDo.setCustomerSerial(1L);
                customerDo.setCustomerName("1");
            }
            Mockito.when(customerService.findCustomer(1L)).thenReturn(customerDo);
            ProductDo productDo = new ProductDo();
            {
                productDo.setProductName("0");
                productDo.setProductSerial(0L);
                productDo.setCount(100L);
                productDo.setPrice(1000);

            }
            Mockito.when(productService.findByProductSerial(0L)).thenReturn(Optional.of(productDo));
            BuyPermissionDo buyPermissionDo = new BuyPermissionDo();
            {
                buyPermissionDo.setCustomerLevel(CustomerLevel.GENERAL);
                buyPermissionDo.setMaxBuyCount(10L);
                buyPermissionDo.setProductSerial(0L);
            }
            Mockito.when(buyPermissionService.findByProductSerialAndCustomerLevel(0L, CustomerLevel.GENERAL))
                    .thenReturn(buyPermissionDo);
            Mockito.when(productService.save(productDo)).thenReturn(productDo);
        }
        // verify 庫存1000 ，購買數量1000
        Integer buyCount = 10;
        BillDo billDo = new BillDo();
        billDo.setBillSerial(1L);
        billDo.setCustomerSerial(1L);
        billDo.setTotalPrice(1000 * buyCount);
        BillDo.DetailItem detailItem = BillDo.DetailItem
                .builder()
                .productName("0")
                .count(buyCount)
                .build();
        billDo.setBuyList(List.of(detailItem));

        assertEquals(billDo,
                cashRegisterService.checkout(1L, 0L, buyCount));
        // mock verify
        Mockito.verify(productService, Mockito.times(1)).findByProductSerial(0L);
        Mockito.verify(productService, Mockito.times(1)).save(Mockito.any());

        // spy verify
        Mockito.verify(billServiceImp, Mockito.times(1)).save(Mockito.notNull());
        assertEquals(1, billServiceImp.count());
    }
}