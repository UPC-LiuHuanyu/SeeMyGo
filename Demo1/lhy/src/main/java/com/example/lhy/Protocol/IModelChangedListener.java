package com.example.lhy.Protocol;

import com.alibaba.fastjson.JSON;

public abstract class IModelChangedListener<T> {

    Class<T> type;

    public IModelChangedListener(Class t) {
        this.type = t;
    }

    public void onDataChanged(String jsonStr) {
        if (type == null) {
            onChangeUI(null);
        } else if (type == String.class) {
            onChangeUI((T) jsonStr);
        } else {
            T result = JSON.parseObject(jsonStr, type);
            onChangeUI(result);
        }
    }

    public abstract void onChangeUI(T t);

}
