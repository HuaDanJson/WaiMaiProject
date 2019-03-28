package cool.food.android.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.blankj.utilcode.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cool.food.android.R;
import cool.food.android.adapter.RestaurantAdapter;
import cool.food.android.bean.RestaurantBean;
import cool.food.android.utils.ActivityUtil;

//订餐
public class OrderFoodFragment extends Fragment implements View.OnTouchListener {

    @BindView(R.id.rlv_restaurant) RecyclerView mRecyclerView;

    private List<RestaurantBean> mRestaurantBeanList = new ArrayList<>();
    private RestaurantAdapter mRestaurantAdapter;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_food, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getRestaurantList();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    public void getRestaurantList() {
        BmobQuery<RestaurantBean> query = new BmobQuery<>();
        query.setLimit(50).order("createdAt")
                .findObjects(new FindListener<RestaurantBean>() {
                    @Override
                    public void done(List<RestaurantBean> restaurantBeanList, BmobException e) {
                        if (e == null) {
                            LogUtils.d("OrderFoodFragment BmobQuery success:" + restaurantBeanList);
                            mRestaurantBeanList = restaurantBeanList;
                            if (mRestaurantAdapter == null) {
                                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                mRestaurantAdapter = new RestaurantAdapter();
                                mRestaurantAdapter.setOnItemClickListener(mBookClickListener);
                                mRestaurantAdapter.setDataSilently(mRestaurantBeanList);
                                mRecyclerView.setAdapter(mRestaurantAdapter);
                            } else {
                                mRestaurantAdapter.setData(mRestaurantBeanList);
                            }

                        } else {
                            LogUtils.d("OrderFoodFragment BmobQuery failed : " + e);
                        }
                    }
                });
    }

    private AdapterView.OnItemClickListener mBookClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            if (mRestaurantAdapter == null) { return; }
            RestaurantBean restaurantBean = mRestaurantAdapter.getItem(position);
            ActivityUtil.startRestaurantActivity(OrderFoodFragment.this, restaurantBean);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
