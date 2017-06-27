package com.gupao.homework.designPatterns.d5_strategy;

/**
 * Created by Administrator on 2017/5/22.
 */
public class China {
    //持有一个具体策略的对象
    private IRecoverStrategy strategy;

    /**
     * 构造函数，传入一个具体策略对象
     * @param strategy    具体策略对象
     */
    public China(IRecoverStrategy strategy){
        this.strategy = strategy;
    }

    /**
     * 策略方法
     */
    public void recoverTaiwan(){
        strategy.doRecover("Taiwan");
    }

}
