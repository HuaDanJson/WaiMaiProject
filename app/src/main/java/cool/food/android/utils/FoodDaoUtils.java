package cool.food.android.utils;

import android.content.Context;

import java.util.List;

import cool.android.greendao.DaoManager;
import cool.android.greendao.FoodBeanDao;
import cool.food.android.bean.FoodBean;

public class FoodDaoUtils {

    private FoodBeanDao mFoodBeanDao;

    private static FoodDaoUtils mFoodDaoUtils = null;

    public FoodDaoUtils(Context context) {
        mFoodBeanDao = DaoManager.getInstance(context).getNewSession().getFoodBeanDao();
    }

    public static FoodDaoUtils getInstance() {
        return mFoodDaoUtils;
    }

    public static void Init(Context context) {
        if (mFoodDaoUtils == null) {
            mFoodDaoUtils = new FoodDaoUtils(context);
        }
    }

    /**
     * 完成对数据库中插入一条数据操作
     *
     * @param
     * @return
     */
    public void insertOneData(FoodBean weiBoBean) {
        mFoodBeanDao.insertOrReplace(weiBoBean);
    }

    /**
     * 完成对数据库中插入多条数据操作
     *
     * @param dbUserInvestmentList
     * @return
     */
    public boolean insertManyData(List<FoodBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            mFoodBeanDao.insertOrReplaceInTx(dbUserInvestmentList);
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
    public boolean deleteOneData(FoodBean dbUserInvestment) {
        boolean flag = false;
        try {
            mFoodBeanDao.delete(dbUserInvestment);
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
            mFoodBeanDao.deleteByKey(id);
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
    public boolean deleteManData(List<FoodBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            mFoodBeanDao.deleteInTx(dbUserInvestmentList);
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
            mFoodBeanDao.deleteAll();
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
    public boolean updateData(FoodBean dbUserInvestment) {
        boolean flag = false;
        try {
            mFoodBeanDao.update(dbUserInvestment);
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
    public boolean updateManData(List<FoodBean> dbUserInvestmentList) {
        boolean flag = false;
        try {
            mFoodBeanDao.updateInTx(dbUserInvestmentList);
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
    public FoodBean queryOneData(long id) {
        return mFoodBeanDao.load(id);
    }

    /**
     * 完成对数据库查询所有数据操作
     *
     * @return
     */
    public List<FoodBean> queryAllData() {
        return mFoodBeanDao.loadAll();
    }

}
