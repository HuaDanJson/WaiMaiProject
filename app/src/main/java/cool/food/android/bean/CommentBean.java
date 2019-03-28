package cool.food.android.bean;

import cn.bmob.v3.BmobObject;

public class CommentBean extends BmobObject {

    private String restaurantId;
    private String value;//内容
    private String senderUserName;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "restaurantId='" + restaurantId + '\'' +
                ", value='" + value + '\'' +
                ", senderUserName='" + senderUserName + '\'' +
                '}';
    }
}
