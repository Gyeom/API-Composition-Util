public class OrderGetter implements APICompositionUtil.Getter<Order> {

    @Override
    public Object getValue(final Order data, final String key) {
        if (key.equals("userId")) {
            return data.getUserId();
        }

        if (key.equals("orderId")) {
            return data.getOrderId();
        }

        if (key.equals("productName")) {
            return data.getProductName();
        }

        return null;
    }
}
