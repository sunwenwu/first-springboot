package com.example.firstspringboot.demo;

import com.alibaba.fastjson.JSON;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Author :sunwenwu
 * @Date : 2021/2/4 11:32
 * @Description :
 */
public class TestEnvProperties {

    public static void main(String[] args) {
        Set<String> requiredProperties = new LinkedHashSet<>();
        requiredProperties.add("6666");

        System.out.println("env:  "+JSON.toJSONString(System.getenv()));


        //-Dsww=sunwenwu  在下面这个方法里获取
        System.out.println("Properties:  "+JSON.toJSONString(System.getProperties()));


        PlaceholderResolver resolver = pp -> System.out.println("ss"+pp);

        resolver.resolvePlaceholder("我就试试pp");


        System.out.println(JSON.toJSONString(requiredProperties));
        Collections.addAll(requiredProperties, "sss");
        System.out.println(JSON.toJSONString(requiredProperties));


        MyTestFI mf = requiredProperties::add;

        mf.addStr("666rr");

        System.out.println(JSON.toJSONString(requiredProperties));


        System.out.println("===========================================");
        TestEnvProperties tt =  new TestEnvProperties();
        tt.run();
    }


    public void run(){
        System.out.println("run....");

        MyTestFI mf = this::runStr;
        mf.addStr("mf");

        MyTestFI mmff = (ss)-> runStr(ss);
        mmff.addStr("mmff");

        MyTestFI2 mmff22 = (ss,bb)-> runStr(ss);
        mmff22.addStr("sss","sss22");

        MyTestFI2 mmff33 = (ss,bb)-> runStr(bb);
        mmff33.addStr("sss","sss22");

        //参数个数和类型一样就 直接 this::runStr
        //参数个数不一样  (ss,bb)-> runStr(ss)  or   (ss,bb)-> runStr(bb)  需要指定调用的方法用的哪个入参

    }

    public void runStr(String str){

        System.out.println("runStr...."+str);

    }

    @FunctionalInterface
    public interface PlaceholderResolver {

        /**
         * Resolve the supplied placeholder name to the replacement value.
         * @param placeholderName the name of the placeholder to resolve
         * @return the replacement value, or {@code null} if no replacement is to be made
         */
        @Nullable
        void resolvePlaceholder(String placeholderName);
    }


    @FunctionalInterface
    public interface MyTestFI {

        @Nullable
        void addStr(String str);
    }

    @FunctionalInterface
    public interface MyTestFI2 {

        @Nullable
        void addStr(String str,String str2);
    }
}
