package cool.food.android.bean;

import cn.bmob.v3.BmobObject;

public class FoodBean extends BmobObject {

    private String restaurantId;
    private String foodName;
    private String foodPrice;
    private String foodAvatar;

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodAvatar() {
        return foodAvatar;
    }

    public void setFoodAvatar(String foodAvatar) {
        this.foodAvatar = foodAvatar;
    }

    @Override
    public String toString() {
        return "FoodBean{" +
                "restaurantId='" + restaurantId + '\'' +
                ", foodName='" + foodName + '\'' +
                ", foodPrice='" + foodPrice + '\'' +
                ", foodAvatar='" + foodAvatar + '\'' +
                '}';
    }
}
