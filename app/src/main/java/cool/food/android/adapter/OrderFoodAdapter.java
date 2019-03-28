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
import cool.food.android.bean.FoodBean;


public class OrderFoodAdapter extends BaseRVAdapter<FoodBean, OrderFoodAdapter.ReaderAdapterHolder> {

    @Override
    protected ReaderAdapterHolder doCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ReaderAdapterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_order_food_adapter, viewGroup, false));
    }

    @Override
    protected void bindItemData(ReaderAdapterHolder viewHolder, FoodBean foodBean, int position) {
        viewHolder.bindView(foodBean, position);
    }

    public class ReaderAdapterHolder extends RecyclerView.ViewHolder implements IViewHolder<FoodBean> {

        @BindView(R.id.iv_food_avatar) ImageView mFoodAvatar;
        @BindView(R.id.tv_food_name) TextView mFoodName;
        @BindView(R.id.tv_food_price) TextView mFoodPrice;
        @BindView(R.id.iv_subtract) ImageView mSubtractFood;
        @BindView(R.id.iv_add_food) ImageView mAddFood;
        @BindView(R.id.tv_buy_count) TextView mBuyCount;

        public ReaderAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindView(final FoodBean foodBean, int position) {
            mFoodName.setText(foodBean.getFoodName());
            mFoodPrice.setText("价格：" + foodBean.getFoodPrice());
            int butCount = foodBean.getBuyCount();
            mBuyCount.setText(butCount <= 0 ? String.valueOf(0) : String.valueOf(butCount));
            try {
                Glide.with(CCApplication.getInstance())
                        .load(foodBean.getFoodAvatar())
                        .fitCenter()
                        .dontAnimate()
                        .into(mFoodAvatar);
            } catch (Exception e) {

            }
            mSubtractFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int butCount = foodBean.getBuyCount();
                    if (butCount <= 0) {
                        foodBean.setBuyCount(0);
                    } else {
                        foodBean.setBuyCount(butCount - 1);
                    }
                    mBuyCount.setText(String.valueOf(foodBean.getBuyCount()));
                }
            });

            mAddFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int butCount = foodBean.getBuyCount();
                    if (butCount <= -1) {
                        foodBean.setBuyCount(0);
                    } else {
                        foodBean.setBuyCount(butCount + 1);
                    }
                    mBuyCount.setText(String.valueOf(foodBean.getBuyCount()));
                }
            });
        }
    }
}

