public class Order {
    private final int orderId;
    private final int userId;
    private final String productName;

    public Order(int orderId, int userId, String productName) {
        this.orderId = orderId;
        this.userId = userId;
        this.productName = productName;
    }

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
