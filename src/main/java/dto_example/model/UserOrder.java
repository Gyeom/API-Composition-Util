package dto_example.model;

import lombok.Getter;

@Getter
public class UserOrder {
    private final int id;
    private final String name;
    private String productName;
    private Integer orderId;

    public UserOrder(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void changeProductName(final String productName) {
        this.productName = productName;
    }

    public void changeOrderId(final Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "dto_example.model.UserOrder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productName='" + productName + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
