package com.example.firstspringboot.common.bean;

import com.example.firstspringboot.common.utils.ValidataUtil;
import com.example.firstspringboot.common.utils.ValiteUtils;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author :sunwenwu
 * @Date : 2019/11/1 18:31
 * @Description :
 */
@Data
public class ValidDemoBeanSon extends ValidDemoBean{

    @NotBlank(message = "son不能为空")
    private String son;


    @Range(min=1,message = "num不能小于0")
    private Long num;


    public static void main(String[] args) {
        /*ValidDemoBeanSon validDemoBeanSon = new ValidDemoBeanSon();

        validDemoBeanSon.setNum(1L);
        validDemoBeanSon.setSon("son");
        validDemoBeanSon.setFather("father");

        System.out.println("第一次校验:"+ValidataUtil.validate(validDemoBeanSon));

        validDemoBeanSon.setFather(null);
        System.out.println("第二次校验:"+ValidataUtil.validate(validDemoBeanSon));

        validDemoBeanSon.setSon(null);
        validDemoBeanSon.setFather("father");
        System.out.println("第三次校验:"+ValidataUtil.validate(validDemoBeanSon));

        validDemoBeanSon.setNum(0L);
        validDemoBeanSon.setSon("son");
        System.out.println("第四次校验:"+ValidataUtil.validate(validDemoBeanSon));

*/

        System.out.println(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));


    }
}
