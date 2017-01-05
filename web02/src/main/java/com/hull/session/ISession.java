package com.hull.session;

public interface ISession {

    /**
     * 删除所有Session数据
     */
    public void removeAll();

    /**
     * 删除Session属性
     *
     * @param _attrName 属性名称
     */
    public void removeAttr(String _attrName);

    /**
     * 设置属性值
     *
     * @param attrName 属性名称
     * @param value    属性值
     */
    public void setAttr(String attrName, Object value);

    /**
     * 获取session中的Object对象
     *
     * @param attrName 属性名称
     * @return 属性值
     */
    public Object getObject(String attrName);

    /**
     * 获取Session的Key值
     *
     * @return Session主键
     */
    public String getSessionKey();

}
