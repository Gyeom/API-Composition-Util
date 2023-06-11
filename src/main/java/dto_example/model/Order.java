package dto_example.model;

import lombok.Value;

@Value
public class Order {
    int orderId;
    int userId;
    String productName;

    public int getOrderId() {
        return orderId;
    }

    public int getUserId() {
        return userId;
    }

    public String getProductName() {
        return productName;
    }
}
