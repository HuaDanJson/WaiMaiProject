package cool.food.android.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cool.food.android.R;
import cool.food.android.base.BaseActivity;
import cool.food.android.base.TabEntity;
import cool.food.android.fragment.CollectionFragment;
import cool.food.android.fragment.MeFragment;
import cool.food.android.fragment.OrderFoodFragment;
import cool.food.android.fragment.ShoppingCartFragment;
import cool.food.android.utils.ToastHelper;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout) CommonTabLayout mCommonTabLayout;
    @BindView(R.id.ll_show_fragment) LinearLayout activityMain;

    private OrderFoodFragment mOrderFoodFragment;
    private ShoppingCartFragment mShoppingCartFragment;
    private CollectionFragment mCollectionFragment;
    private MeFragment mMeFragment;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String[] mTitles = {"点餐", "购物车", "收藏", "我的"};
    private int[] mIconUnselectIds = {R.drawable.icon_message_unpress, R.drawable.icon_shopping_un_press, R.drawable.icon_discover_unpress, R.drawable.icon_me_unpress};
    private int[] mIconSelectIds = {R.drawable.icon_message_press, R.drawable.icon_shopping_press, R.drawable.icon_discover_press, R.drawable.icon_me_press};
    private long firstBack = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //第一：默认初始化
        Bmob.initialize(this, "e8e83fe1a4be56bac297cd001fb36757");
        initTab();
        initFragment();
        getPermission();
    }

    public void getPermission() {
        PermissionUtils.permission(PermissionConstants.PHONE)
                .rationale(new PermissionUtils.OnRationaleListener() {
                    @Override
                    public void rationale(final ShouldRequest shouldRequest) {
                        shouldRequest.again(true);
                    }
                })
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {

                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever,
                                         List<String> permissionsDenied) {
                        if (!permissionsDeniedForever.isEmpty()) {
                            PermissionUtils.launchAppDetailsSettings();
                        }
                    }
                }).request();
    }

    private void initTab() {
        for (int i = 0; i < mIconSelectIds.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mCommonTabLayout.setTabData(mTabEntities);
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    public void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;

        if (mOrderFoodFragment == null) {
            mOrderFoodFragment = new OrderFoodFragment();
            transaction.add(R.id.ll_show_fragment, mOrderFoodFragment);
        }

        if (mShoppingCartFragment == null) {
            mShoppingCartFragment = new ShoppingCartFragment();
            transaction.add(R.id.ll_show_fragment, mShoppingCartFragment);
        }

        if (mCollectionFragment == null) {
            mCollectionFragment = new CollectionFragment();
            transaction.add(R.id.ll_show_fragment, mCollectionFragment);
        }

        if (mMeFragment == null) {
            mMeFragment = new MeFragment();
            transaction.add(R.id.ll_show_fragment, mMeFragment);
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        mCommonTabLayout.setCurrentTab(currentTabPosition);
    }

    private void SwitchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.show(mOrderFoodFragment);
                transaction.hide(mCollectionFragment);
                transaction.hide(mMeFragment);
                transaction.hide(mShoppingCartFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.show(mShoppingCartFragment);
                transaction.hide(mCollectionFragment);
                transaction.hide(mOrderFoodFragment);
                transaction.hide(mMeFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.show(mCollectionFragment);
                transaction.hide(mOrderFoodFragment);
                transaction.hide(mMeFragment);
                transaction.hide(mShoppingCartFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                transaction.show(mMeFragment);
                transaction.hide(mCollectionFragment);
                transaction.hide(mOrderFoodFragment);
                transaction.hide(mShoppingCartFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstBack < 2000) {
            super.onBackPressed();
        } else {
            firstBack = System.currentTimeMillis();
            ToastHelper.showShortMessage(R.string.back_btn_exit_pop);
        }
    }

}