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
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cool.food.android.R;
import cool.food.android.adapter.RestaurantAdapter;
import cool.food.android.bean.RestaurantBean;
import cool.food.android.utils.ActivityUtil;
import cool.food.android.utils.RestaurantDaoUtils;

//收藏
public class CollectionFragment extends Fragment implements View.OnTouchListener {

    @BindView(R.id.rv_note_activity)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_empty)
    TextView mEmpty;

    private RestaurantAdapter mRestaurantAdapter;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
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
        getCollectionData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getCollectionData();
        }
    }

    public void getCollectionData() {
        List<RestaurantBean> restaurantBeanList = RestaurantDaoUtils.getInstance().queryAllData();
        initRecyclerView(restaurantBeanList);
    }

    private AdapterView.OnItemClickListener mBookClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
            if (mRestaurantAdapter == null) { return; }
            RestaurantBean restaurantBean = mRestaurantAdapter.getItem(position);
            ActivityUtil.startRestaurantActivity(CollectionFragment.this, restaurantBean);
        }
    };

    public void initRecyclerView(List<RestaurantBean> list) {
        if (mRecyclerView == null) {return;}
        if (list == null || list.isEmpty()) {
            mEmpty.setVisibility(View.VISIBLE);
        } else {
            mEmpty.setVisibility(View.GONE);
        }
        if (mRestaurantAdapter == null) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRestaurantAdapter = new RestaurantAdapter();
            mRestaurantAdapter.setOnItemClickListener(mBookClickListener);
            mRestaurantAdapter.setDataSilently(list);
            mRecyclerView.setAdapter(mRestaurantAdapter);
        } else {
            mRestaurantAdapter.setData(list);
        }
    }
}
