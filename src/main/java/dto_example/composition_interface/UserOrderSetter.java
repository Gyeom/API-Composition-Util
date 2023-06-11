package dto_example.composition_interface;

import dto_example.model.UserOrder;
import util.APICompositionUtil;

public class UserOrderSetter implements APICompositionUtil.Setter<UserOrder> {
    @Override
    public void setValue(UserOrder data, String key, Object value) {
        if (key.equals("orderId")) {
            data.changeOrderId((Integer) value);
            return;
        }

        if (key.equals("productName")) {
            data.changeProductName((String) value);
            return;
        }

        throw new IllegalArgumentException("Unexpected field: " + key);
    }
}
