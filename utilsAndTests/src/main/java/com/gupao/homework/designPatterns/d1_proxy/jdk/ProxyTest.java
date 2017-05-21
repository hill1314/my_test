package com.gupao.homework.designPatterns.d1_proxy.jdk;

/**
 * Created by Administrator on 2017/5/21.
 */
public class ProxyTest {
    public static void main(String[] args) {
        FinancingProxy proxy = new FinancingProxy();
        Investor investor = (Investor) proxy.setInvestor(new RichMan("Qian BaiWang"));
        investor.buy("A");
        investor.sell("B");
    }
}
