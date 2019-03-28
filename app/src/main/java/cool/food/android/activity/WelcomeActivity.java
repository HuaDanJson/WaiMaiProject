package cool.food.android.activity;

import android.os.Bundle;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cool.food.android.R;
import cool.food.android.base.BaseActivity;
import cool.food.android.bean.CurrentUser;
import cool.food.android.utils.CurrentUserHelper;


public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //第一：默认初始化
        Bmob.initialize(this, "e8e83fe1a4be56bac297cd001fb36757");
        if (BmobUser.isLogin()) {
            CurrentUser currentUser = BmobUser.getCurrentUser(CurrentUser.class);
            CurrentUserHelper.getInstance().updateCurrentUser(currentUser);
            toActivity(MainActivity.class);
        } else {
            toActivity(LoginActivity.class);
            finish();
        }
    }
}
