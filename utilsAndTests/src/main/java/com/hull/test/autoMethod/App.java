package com.hull.test.autoMethod;

import com.hull.test.autoMethod.cfg.ParamCfg;

import java.util.ArrayList;

/**
 * Created by hull on 2017/1/19.
 */
public class App {

    public static void main(String[] args) {
        ArrayList<ParamCfg> list = getCfgList();
    }

    private static ArrayList<ParamCfg> getCfgList() {
        ArrayList<ParamCfg> list = new ArrayList<ParamCfg>();
        ParamCfg cfg = new ParamCfg();
        cfg.setStgCod("xulie1");
        cfg.setExceNum(1);
        cfg.setMethodName("method1");
        cfg.setMethodRmk("method1");
        cfg.setParamNum(1);
        cfg.setParamType("String");
        cfg.setParamName("param");
        cfg.setPmCfCod("@hello");
        cfg.setClassName("MyServerImpl");
        list.add(cfg);
        cfg.setExceNum(2);
        cfg.setMethodName("method2");
        cfg.setMethodRmk("method2");
        cfg.setParamNum(1);
        cfg.setParamType("int");
        cfg.setParamName("p1");
        cfg.setPmCfCod("@1");
        list.add(cfg);
        cfg.setParamNum(2);
        cfg.setParamType("int");
        cfg.setParamName("p2");
        cfg.setPmCfCod("@2");
        list.add(cfg);
        return list;
    }

}
