package com.hull.test.camel.myTest;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/1/1.
 */
public class FileConvertProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            InputStream body = exchange.getIn().getBody(InputStream.class);
            BufferedReader in = new BufferedReader(new InputStreamReader(body));
            StringBuffer strbf = new StringBuffer("");
            String str = null;
            str = in.readLine();
            while (str != null) {
                System.out.println(str);
                strbf.append(str + " ");
                str = in.readLine();
            }
            exchange.getOut().setHeader(Exchange.FILE_NAME, "converted.txt");
            // set the output to the file
            exchange.getOut().setBody(strbf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
