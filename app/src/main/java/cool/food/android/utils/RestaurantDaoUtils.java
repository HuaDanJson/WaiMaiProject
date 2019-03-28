package cool.food.android.utils;

import android.content.Context;

import java.util.List;

import cool.android.greendao.DaoManager;
import cool.android.greendao.RestaurantBeanDao;
import cool.food.android.bean.RestaurantBean;

public class RestaurantDaoUtils {

    private RestaurantBeanDao mRestaurantBeanDao;

    private static RestaurantDaoUtils mRestaurantDaoUtils = null;

    public RestaurantDaoUtils(Context context) {
        mRestaurantBeanDao = DaoManager.getInstance(context).getNewSession().getRestaurantBeanDao();
    }

    public static RestaurantDaoUtils getInstance() {
        return mRestaurantDaoUtils;
    }

    public static void Init(Context context) {
        if (mRestaurantDaoUtils == null) {
            mRestaurantDaoUtils = new RestaurantDaoUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     *
     * @param
     * @return
     */
    public void insertOneData(RestaurantBean restaurantBean) {
        mRestaurantBeanDao.insertOrReplace(restaurantBean);
    }

    /**
     * 完成对数据库中插入多条数据操作
     *
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<RestaurantBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            mRestaurantBeanDao.insertOrReplaceInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据操作
     *
     * @param dbUserInvestment
     * @return
     */
    public boolean deleteOneData(RestaurantBean dbUserInvestment) {
        boolean flag = false;
        try {
            mRestaurantBeanDao.delete(dbUserInvestment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中删除一条数据 ByKey操作
     *
     * @return
     */
    public boolean deleteOneDataByKey(long id) {
        boolean flag = false;
        try {
            mRestaurantBeanDao.deleteByKey(id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中批量删除数据操作
     *
     * @return
     */
    public boolean deleteManData(List<RestaurantBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            mRestaurantBeanDao.deleteInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库中数据全部删除
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            mRestaurantBeanDao.deleteAll();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 完成对数据库更新数据操作
     *
     * @return
     */
    public boolean updateData(RestaurantBean dbUserInvestment) {
        boolean flag = false;
        try {
            mRestaurantBeanDao.update(dbUserInvestment);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库批量更新数据操作
     *
     * @return
     */
    public boolean updateManData(List<RestaurantBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            mRestaurantBeanDao.updateInTx(dbUserInvestmentList);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对数据库查询数据操作
     *
     * @return
     */
    public RestaurantBean queryOneData(long id) {
        return mRestaurantBeanDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     *
     * @return
     */
    public List<RestaurantBean> queryAllData() {
        return mRestaurantBeanDao.loadAll();
    }
}
