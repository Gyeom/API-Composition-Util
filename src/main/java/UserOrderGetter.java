public class UserOrderGetter implements MergeUtils.Getter<UserOrder> {

    @Override
    public Object getValue(final UserOrder data, final String key) {
        if (key.equals("id")) {
            return data.getId();
        }

        return null;
    }
}
