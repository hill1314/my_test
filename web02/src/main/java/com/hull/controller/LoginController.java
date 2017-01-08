package com.hull.controller;

import com.hull.entity.User;
import com.hull.utils.ILog;
import com.hull.utils.JacksonUtils;
import com.hull.utils.StringUtil;
import com.hull.utils.VerifyCodeUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/11/21.
 */
@Controller
public class LoginController implements ILog{

    @RequestMapping(value = "/loginView")
    public Object loginView(){
        return "login";
    }

    @RequestMapping(value = "/menu")
    public Object menu(){
        return "menu";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request,
                        String userName,String password,String userCode){
        Map<String,Object> resultMap = new HashMap<>();
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("verCode");
        //验证 验证码
        boolean codeResult = StringUtil.isEqual(userCode.toLowerCase(),code);
        if(!codeResult){
            resultMap.put("resultCode","0001");
            resultMap.put("resultMsg","verify code is wrong!");
        }else{
            //验证 用户名密码
            if("hull".equals(userName) && "123123".equals(password)){
                resultMap.put("resultCode","0000");
                resultMap.put("resultMsg","success");
            }else{
                resultMap.put("resultCode","0002");
                resultMap.put("resultMsg","user or password is wrong!");
            }
        }

        String str = "";
        try {
            str = JacksonUtils.obj2Json(resultMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    @RequestMapping("/getImgCode")
    @ResponseBody
    public void getImgCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        logger.info("generate the verify code ... ");
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        //生成图片
        int w = 160, h = 30;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);

//        simpleMethod(request,response);
    }

    /**
     *  简单生成验证码的方法
     * @param request
     * @param response
     * @throws IOException
     */
    private void simpleMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");

        int height=25;
        int width=120;
        //得到一个内存图像BufferedImage
        BufferedImage img=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //得到一个画笔
        Graphics g=img.getGraphics();
        //画边框drawRect绘制指定矩形的边框。
        g.drawRect(0, 0, width, height);
        //填充颜色
        g.setColor(Color.RED);
        g.fillRect(1, 1, width-2, height-2);
        //画干扰线
        g.setColor(Color.BLACK);
        Random r=new Random();
        for(int i=0;i<20;i++)
            g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
        //生成随机数字
        g.setColor(Color.BLUE);
        g.setFont(new Font("微软雅黑", Font.BOLD|Font.ITALIC, 20));//BOLD加粗，ITALIC斜体
        int d=15;
        StringBuffer sb=new StringBuffer();//可变字符串的利用
        for(int j=0;j<4;j++){
            String code=r.nextInt(10)+"";
            sb.append(code);
            g.drawString(code+"", d, 20);
            d+=20;
        }
        //将验证码输入到session中，用来验证
        request.getSession().setAttribute("code", sb.toString());
        //输出打web页面
        ImageIO.write(img, "jpg", response.getOutputStream());
    }


}
