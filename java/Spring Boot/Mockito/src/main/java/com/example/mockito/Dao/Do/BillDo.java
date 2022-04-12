package com.example.mockito.Dao.Do;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

@Data
public class BillDo implements Serializable {
    private static final long serialVersionUID = -273627141529477827L;

    private Long billSerial;

    private Long customerSerial;

    private List<DetailItem> buyList = new Vector<>();

    @Setter
    @Getter
    private Integer totalPrice = 0;

    public void addDetailItem(DetailItem item) {
        buyList.add(item);
    }

    public List<DetailItem> getBuyList() {
        return buyList;
    }

    @Data
    @Builder
    public static class DetailItem {
        private String productName;
        private Integer count;
    }
}
