package com.example.firstspringboot;

import com.example.firstspringboot.common.vo.ParamVO;
import com.example.firstspringboot.common.vo.ResultVO;
import com.example.firstspringboot.service.TbCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstspringbootApplicationTests {

    @Resource
    private TbCartService tbCartService;


    @Test
    public void contextLoads() {

        System.out.println("test = " );
    }

    @Test
    public void tes() {
        ParamVO paramVO = new ParamVO();
        paramVO.setInputParam("sww原始入参2");
/*

        ResultVO resultData = tbCartService.getResultData(paramVO);

        System.out.println("================返回："+resultData);
*/



        List<ResultVO> resultDatas = tbCartService.getResultDatas(paramVO);

        System.out.println("================返回list："+resultDatas);


    }



}
