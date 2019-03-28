package cool.food.android.activity;

import android.os.Bundle;

import cool.food.android.R;
import cool.food.android.base.BaseActivity;
import cool.food.android.bean.RestaurantBean;

public class RestaurantActivity extends BaseActivity {

    private RestaurantBean mRestaurantBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
    }
}
