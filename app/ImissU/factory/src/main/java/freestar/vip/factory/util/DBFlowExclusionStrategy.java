package freestar.vip.factory.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

/**
 * 描述：DBFlow的数据库过滤字段 Gson
 * 作者：一颗浪星
 * 日期：2017/10/14
 * github：
 */

public class DBFlowExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        // 被跳过的字段
        // 只要是属于DBFlow数据的
        return f.getDeclaredClass().equals(ModelAdapter.class);
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        // 别跳过的Class
        return false;
    }
}
