public class UserOrderGetter implements APICompositionUtil.Getter<UserOrder> {

    @Override
    public Object getValue(final UserOrder data, final String key) {
        if (key.equals("id")) {
            return data.getId();
        }

        return null;
    }
}
