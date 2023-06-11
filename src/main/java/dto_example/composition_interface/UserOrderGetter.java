package dto_example.composition_interface;

import dto_example.model.UserOrder;
import util.APICompositionUtil;

public class UserOrderGetter implements APICompositionUtil.Getter<UserOrder> {

    @Override
    public Object getValue(final UserOrder data, final String key) {
        if (key.equals("id")) {
            return data.getId();
        }

        return null;
    }
}
