package cool.food.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cool.food.android.R;
import cool.food.android.base.BaseRVAdapter;
import cool.food.android.base.CCApplication;
import cool.food.android.base.IViewHolder;
import cool.food.android.bean.RestaurantBean;


public class RestaurantAdapter extends BaseRVAdapter<RestaurantBean, RestaurantAdapter.ReaderAdapterHolder> {

    @Override
    protected ReaderAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ReaderAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_restaurant_adapter, viewGroup, false));
    }

    @Override
    protected void bindItemData(ReaderAdapterHolder viewHolder, RestaurantBean restaurantBean, int position) {
        viewHolder.bindView(restaurantBean, position);
    }

    public class ReaderAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<RestaurantBean> {

        @BindView(R.id.iv_restaurant_avatar)
        ImageView mRestaurantAvatar;
        @BindView(R.id.tv_restaurant_name)
        TextView mRestaurantName;
        @BindView(R.id.tv_restaurant_address)
        TextView mRestaurantAddress;

        public ReaderAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(RestaurantBean restaurantBean, int position) {
            mRestaurantName.setText(restaurantBean.getName());
            mRestaurantAddress.setText(restaurantBean.getAddress());
            try {
                Glide.with(CCApplication.getInstance())
                        .load(restaurantBean.getAvatar())
                        .fitCenter()
                        .dontAnimate()
                        .into(mRestaurantAvatar);
            } catch (Exception e) {

            }
        }
    }
}

