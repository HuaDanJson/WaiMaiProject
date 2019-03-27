package cool.food.android.base;

import android.app.Activity;

public interface BaseView<T> {

    void setPresenter(T presenter);

    Activity getActivityContext();

}
