public class UserOrderSetter implements MergeUtils.Setter<UserOrder> {
    @Override
    public void setValue(UserOrder data, String key, Object value) {
        if (key.equals("orderId")) {
            data.setOrderId((Integer) value);
            return;
        }

        if (key.equals("productName")) {
            data.setProductName((String) value);
            return;
        }

        throw new IllegalArgumentException("Unexpected field: " + key);
    }
}
