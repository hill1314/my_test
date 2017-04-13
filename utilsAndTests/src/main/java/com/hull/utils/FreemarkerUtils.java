package com.hull.utils;

import com.hull.test.autoMethod.camel.BizException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hull on 2017/3/31.
 */
public class FreemarkerUtils {
    /** 属性对象 */
    private static Configuration cfg = new Configuration();

    /** 初始化标志 */
    private static boolean isInit;

    static {
        try {
            getConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取属性对象
     *
     * @return Properties
     * @throws Exception
     */
    public static Configuration getConfiguration() throws Exception {
        if (!isInit) {
            synchronized (cfg) {
                if (!isInit) {
                    //method1
//                    String projectPath = PropertiesUtils.getConfig("project.path");
//                    String tempPath = projectPath+"/aplus_tools/src/main/resources/META-INF/templates";
//                    cfg.setDirectoryForTemplateLoading(new File(tempPath));
                    ////method2
                    cfg.setClassForTemplateLoading(Configuration.class,"/ftl");
                    cfg.setDefaultEncoding("UTF-8"); //这个一定要设置，不然在生成的页面中 会乱码

                    isInit = true;
                }
            }
        }
        return cfg;
    }

    /**
     *  获取模板
     * @param templateName
     * @return
     */
    public static Template getTemplate(String templateName){
        Template tem;
        try {
            tem = getConfiguration().getTemplate(templateName);
            if(null == tem){
                throw new BizException("00010");
            }
        }  catch (Exception e) {
            e.printStackTrace();
            throw new BizException("00010");
        }
        return tem;
    }

    /**
     *
     * @title生成文件
     * @description
     *
     * @param filePath 目标文件路径
     * @param templateName 模板名称
     * @param map 模板需要的参数
     * @throws Exception
     */
    public static void creatFile(String filePath, String templateName, Map map){
        FileOutputStream fs = null;
        Writer out = null;
        File file = null;
        try{
            file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }else{
                file.delete();
                file.createNewFile();
            }

            //加载模板文件
            Template t1 = FreemarkerUtils.getTemplate(templateName);

            //显示生成的数据
            fs = new FileOutputStream(file);

            out = new OutputStreamWriter(fs,"UTF-8");
            t1.process(map, out);
            out.flush();
            out.close();
            fs.close();
        }catch(Exception e){
            e.printStackTrace();
            throw new BizException("00015");
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fs != null){
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 输出到控制台
     * @param templateName 模板文件名
     * @param dataMap
     */
    public static void print(String templateName,Map<String,Object> dataMap) {
        try {
            //通过Template可以将模板文件输出到相应的流
            Template temp = FreemarkerUtils.getTemplate(templateName);
            temp.process(dataMap, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
