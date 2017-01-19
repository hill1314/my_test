package com.hull.test.autoMethod.camel;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hull on 2017/1/19.
 */
public class DataObject implements java.io.Serializable {
    /**
     * @Fields serialVersionUID : UUID
     */
    private static final long serialVersionUID = 1L;
    /** 输出信息 */
    //private static final String MSG = "\u7C7B\u578B\u8F6C\u6362\u51FA\u73B0\u9519\u8BEF,\u5BF9\u8C61";

    /**
     * @Fields data : 内部数据结构
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    /**
     * <p>Title: </p>
     * <p>Description: 构造方法 </p>
     */
    public DataObject() {
    }

    public Object get(String name) {
        return data.get(name);
    }

    public void set(String name, Object value) {
        data.put(name, value);
    }

    /**
     * @description 初始化方法
     * @version
     * @title
     * @author wulei_wb wulei_wb@newchinalife.com
     */
    protected void init() {

    }

    /**
     * @description
     * @version
     * @title
     * @author liuliang liuliang_wb@newchinalife.com
     * @param name String
     * @return DataObject
     */
    @SuppressWarnings("unchecked")
    public DataObject getDataObject(String name) {
        Object obj = data.get(name);
        if (obj == null) {
            return null;
        }
        if (obj instanceof DataObject) {
            return (DataObject) obj;
        } else if (obj instanceof Map) {
            DataObject d = new DataObject();
            d.setData((Map<String, Object>) obj);
            return d;
        } else {
//            throw new RuntimeException(ExceptionMessageHelper.getExMsg("[" + name + "] + MSG + [" + obj.getClass()
//                    + "]"));
            throw new RuntimeException();
        }
    }

    public void setInt(String name, int value) {
        data.put(name, value);
    }

    public Integer getInt(String name) {
        return (Integer) data.get(name);
    }

    public void setInteger(String name, int value) {
        data.put(name, value);
    }

    public Integer getInteger(String name) {
        return (Integer) data.get(name);
    }

    public void setFloat(String name, float value) {
        data.put(name, value);
    }

    public float getFloat(String name) {
        return (Float) data.get(name);
    }

    public void setLong(String name, long value) {
        data.put(name, value);
    }

    public Long getLong(String name) {
        return (Long) data.get(name);
    }

    public void setDouble(String name, double value) {
        data.put(name, value);
    }

    public Double getDouble(String name) {
        return (Double) data.get(name);
    }

    public void setBigDecimal(String name, BigDecimal value) {
        data.put(name, value);
    }

    public BigDecimal getBigDecimal(String name) {
        return (BigDecimal) data.get(name);
    }

    public void setSqlDate(String name, java.sql.Date value) {
        data.put(name, value);
    }

    public java.sql.Date getSqlDate(String name) {
        return (java.sql.Date) data.get(name);
    }

    public void setTimestamp(String name, java.sql.Timestamp value) {
        data.put(name, value);
    }

    public java.sql.Timestamp getTimestamp(String name) {
        return (java.sql.Timestamp) data.get(name);
    }

    public void setUtilDate(String name, java.util.Date value) {
        data.put(name, value);
    }

    public java.util.Date getUtilDate(String name) {
        return (java.util.Date) data.get(name);
    }

    public void setDataObject(String name, Object value) {
        data.put(name, value);
    }

    public void setDataObject(String name, DataObject value) {
        data.put(name, value);
    }

    public String getString(String name) {
        return (String) data.get(name);
    }

    public void setString(String name, String value) {
        data.put(name, value);
    }

    public void setArray(String name, Object[] values) {
        data.put(name, values);
    }

    public Object[] getArray(String name) {
        return (Object[]) data.get(name);
    }

    public Boolean getBoolean(String name) {
        return (Boolean) get(name);
    }

    public void setBoolean(String name, Boolean value) {
        set(name, value);
    }

    public void setList(String name, List<?> value) {
        set(name, value);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data.putAll(data);
    }

    public Collection<?> getValues() {
        return this.data.values();
    }

}
