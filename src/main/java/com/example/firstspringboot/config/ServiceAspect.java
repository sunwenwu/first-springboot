package com.example.firstspringboot.config;

import com.example.firstspringboot.common.aop.HandleField;
import com.example.firstspringboot.common.vo.ParamVO;
import com.example.firstspringboot.common.vo.ResultVO;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Author :sunwenwu
 * @Date : 2019/11/11 18:50
 * @Description :
 */
@Configuration
@Aspect
public class ServiceAspect {

    private final String ExpGetResultDataPonit = "execution(* com.example.firstspringboot.service.*.*(..))";
//    private final String ExpGetResultDataPonitDao = "execution(* com.example.firstspringboot.dao.TbCartMapper.*(..))";
/*

    //定义切入点,拦截servie包其子包下的所有类的所有方法
//    @Pointcut("execution(* com.haiyang.onlinejava.complier.service..*.*(..))")
    //拦截指定的方法,这里指只拦截TestService.getResultData这个方法
    @Pointcut(ExpGetResultDataPonit)
    public void excuteService() {

    }

    //执行方法前的拦截方法
//    @Before("excuteService()")
    public void doBeforeMethod(JoinPoint joinPoint) {
        System.out.println("我是前置通知，我将要执行一个方法了");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        for (Object argItem : obj) {
            System.out.println("---->now-->argItem:" + argItem);
            if (argItem instanceof ParamVO) {
                ParamVO paramVO = (ParamVO) argItem;
                paramVO.setInputParam("666666");
            }
            System.out.println("---->after-->argItem:" + argItem);
        }
    }

    */
/**
     * 后置返回通知
     * 这里需要注意的是:
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     *//*

//    @AfterReturning(value = ExpGetResultDataPonit, returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint, Object keys) {
        System.out.println("第一个后置返回通知的返回值：" + keys);
        if (keys instanceof ResultVO) {
            ResultVO resultVO = (ResultVO) keys;
            String message = resultVO.getMessage();
            resultVO.setMessage("通过AOP把值修改了 " + message);
        }
        System.out.println("修改完毕-->返回方法为:" + keys);
    }


    */
/**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     *//*

//    @After("excuteService()")
    public void doAfterAdvice(JoinPoint joinPoint) {

        System.out.println("后置通知执行了!!!!");
    }



*/

    /**
     * 环绕通知：
     * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around(ExpGetResultDataPonit)
//    @Around(ExpGetResultDataPonitDao)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("环绕通知的目标方法名：" + proceedingJoinPoint.getSignature().getName());
        processInputArg(proceedingJoinPoint.getArgs());
        try {//obj之前可以写目标方法执行前的逻辑
            Object obj = proceedingJoinPoint.proceed();//调用执行目标方法
            processOutPutObj(obj);
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    /**
     * 处理返回对象
     */
    private void processOutPutObj(Object obj) {
//        System.out.println("OBJ 原本为：" + obj.toString());
        if (obj instanceof ResultVO) {
            ResultVO resultVO = (ResultVO) obj;
            resultVO.setMessage("哈哈，我把值修改了" + resultVO.getMessage());
            System.out.println(resultVO);
        }
        System.out.println("obj instanceof List:"+(obj instanceof List));
        if (obj instanceof List) {

            List list = (List)obj;
            for(Object o:list){
                convert(o,false);
            }
        }
    }

    /**
     * 处理输入参数
     *
     * @param args 入参列表
     */
    private void processInputArg(Object[] args) {
        for (Object arg : args) {
//            System.out.println("ARG原来为:" + arg);
           /* if (arg instanceof ParamVO) {
                ParamVO paramVO = (ParamVO) arg;
                paramVO.setInputParam("654321");
            }*/

            convert(arg,true);

        }
    }

    //flag  true:加密  false：解密
    private void convert(Object arg,boolean flag) {
        Field[] fields = arg.getClass().getDeclaredFields();
        for (Field field : fields) {

            HandleField annotation = field.getAnnotation(HandleField.class);
            if (annotation != null ) {
                field.setAccessible(true);

                try{
                    Object o = field.get(arg);
                    if (o == null) {
                        continue;
                    }
                    if (String.class.isAssignableFrom(field.getType())) {
                        if (StringUtils.isEmpty((String)o)) {
                            continue;
                        }
                        if (flag) {
                            field.set(arg,"注解-加密-测试");
                        } else {
                            field.set(arg,"注解-解密-测试");
                        }
                    }
                }catch (Exception e){

                }
            }
        }
    }


    public static void main(String[] args) throws Exception{
        String key = "6666667777778888";
        String src = "62270001353803265190000000000000";
        String encrypt = encrypt("18518051445", key);
        String encrypt2 = encrypt(src, key);
        System.out.println(encrypt+":"+encrypt.length());
        System.out.println(encrypt2+":"+encrypt2.length());

        System.out.println(decrypt("6666667777778888",key));


        System.out.println(encrypt("18899997777",key).length());
        System.out.println(encrypt("18899998888",key).length());
        System.out.println(encrypt("18899996666",key).length());


        System.out.println(encrypt("1889999666618899996666188999966661889999666618899996666188999966",key).length());
        System.out.println(encrypt("1889999666618899996666188999966661889999666618899996666188999977",key).length());
        System.out.println(encrypt("1889999666618899996666188999966661818899996666188999988",key).length());




    }

    public static String encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位"+sKey.length());
            return null;
        }
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return  new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }


    // 解密
    public static String decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }


}
