package com.hull.test.camel.simpleTest;
import org.apache.camel.main.Main;
/**
 * Created by Administrator on 2017/1/1.
 */
public class MainApp {
    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.enableHangupSupport();
        main.addRouteBuilder(new MyRouteBuilder());
        main.run(args);
    }
}

