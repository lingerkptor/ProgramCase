package com.example.mockito.Service;

import com.example.mockito.Dao.Do.BuyPermissionDo;
import com.example.mockito.Dao.Do.CustomerDo;
import com.example.mockito.Dao.Do.BillDo;
import com.example.mockito.Dao.Do.ProductDo;
import com.example.mockito.Dao.exception.CheckOutException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service("CashRegisterService")
public class CashRegisterService {

    private final ProductService productService;
    private final CustomerService customerService;
    private final BuyPermissionService buyPermissionService;
    private BillService billService;


    public BillDo checkout(Long customerSerial, Long productSerial, int buyCount) throws CheckOutException {
        ProductDo productDo = productService.findByProductSerial(productSerial)
                .orElseThrow(() -> new CheckOutException("product is not exist. productSerial: " + productSerial));

        BillDo.DetailItem item = transactionProcessing(customerSerial, productDo, buyCount);
        BillDo billDo = new BillDo();
        billDo.setCustomerSerial(customerSerial);
        billDo.addDetailItem(item);
        billDo.setTotalPrice(productDo.getPrice() * buyCount);
        billService.save(billDo);

        return billDo;
    }


    public BillDo checkout(Long customerSerial, List<BuyItemDto> buyItemList) throws CheckOutException {
        List<BillDo.DetailItem> items = new ArrayList<>();
        Integer totalCount = 0;
        for (BuyItemDto buyItem : buyItemList) {
            ProductDo productDo = productService.findByProductSerial(buyItem.getProductSerial())
                    .orElseThrow(() -> new CheckOutException("product is not exist. productSerial: " + buyItem.getProductSerial()));
            items.add(transactionProcessing(customerSerial, productDo, buyItem.getCount()));
            totalCount += productDo.getPrice() * buyItem.getCount();
        }

        BillDo billDo = new BillDo();
        billDo.setCustomerSerial(customerSerial);
        billDo.setTotalPrice(totalCount);

        for (BillDo.DetailItem item : items) {
            billDo.addDetailItem(item);
        }

        billService.save(billDo);
        return billDo;
    }

    private boolean checkWareHouseCount(ProductDo product, int buyCount) {
        return product.getCount() >= buyCount;
    }

    private boolean checkBuyCountPermission(Long productSerial, Long customSerial, int buyCount) {
        CustomerDo customerDo = customerService.findCustomer(customSerial);

        if (customerDo == null) {
            throw new NullPointerException("Customer is not exist.");
        }

        BuyPermissionDo permissionDo = buyPermissionService.findByProductSerialAndCustomerLevel(productSerial, customerDo.getCustomerLevel());

        if (permissionDo == null) {
            return true;
        }

        return permissionDo.getMaxBuyCount() >= buyCount;
    }

    private BillDo.DetailItem transactionProcessing(Long customerSerial, ProductDo productDo, int buyCount) throws CheckOutException {
        if (!this.checkWareHouseCount(productDo, buyCount)) {
            throw new CheckOutException("Inventory shortage productSerial: " + productDo.getProductSerial());
        }

        if (!this.checkBuyCountPermission(productDo.getProductSerial(), customerSerial, buyCount)) {
            throw new CheckOutException("Insufficient purchase limit. productSerial: " + productDo.getProductSerial());
        }

        productDo.setCount(productDo.getCount() - buyCount);

        return productService.save(productDo) != null ?
                BillDo.DetailItem.builder()
                        .productName(productDo.getProductName())
                        .count(buyCount)
                        .build() :
                null;
    }

    @Autowired
    public void setBillService(BillService billService) {
        this.billService = billService;
    }
}
