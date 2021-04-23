package com.example.firstspringboot;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.firstspringboot.common.vo.*;
import com.example.firstspringboot.dao.FamilyMapper;
import com.example.firstspringboot.dao.FamilyMemberCopyMapper;
import com.example.firstspringboot.dao.FamilyMemberCopyPlusMapper;
import com.example.firstspringboot.dao.MemberInfoCopyPlusMapper;
import com.example.firstspringboot.service.TbCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstspringbootApplicationTests {

    @Resource
    private TbCartService tbCartService;

    @Resource
    private FamilyMapper familyMapper;

    @Resource
    private FamilyMemberCopyMapper familyMemberCopyMapper;

    @Resource
    private FamilyMemberCopyPlusMapper familyMemberCopyPlusMapper;

    @Resource
    private MemberInfoCopyPlusMapper memberInfoCopyPlusMapper;


    @Test
    public void testMybatisPlus() throws Exception {
        FamilyMemberCopy copy = familyMemberCopyPlusMapper.selectById(1056);

        System.out.println(JSON.toJSONString(copy));
    }

    @Test
    public void testMybatisPlusM() throws Exception {
        MemberInfoCopy memberInfoCopy = memberInfoCopyPlusMapper.selectById(2);

        System.out.println(JSON.toJSONString(memberInfoCopy));

        MemberInfoCopy m = new MemberInfoCopy();
        m.setId(2L);
        m.setUserId("plus 更新");
        Wrapper wrapper = new UpdateWrapper(m);

//        memberInfoCopyPlusMapper.delete(wrapper);

        memberInfoCopyPlusMapper.updateById(m);

        MemberInfoCopy memberInfoCopy2 = memberInfoCopyPlusMapper.selectById(2);
        System.out.println(JSON.toJSONString(memberInfoCopy2));
    }










    @Test
    public void setTbCartService() throws Exception {

        ExecutorService threadPool=new ThreadPoolExecutor(20,20,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(500),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());




        for (int i=1;i<1;i++) {

            for (int j=0;j<20;j++) {
                int finalI = i;
                threadPool.execute(()->{

                    FamilyMemberReqDto bizReqDto = new FamilyMemberReqDto();
                    bizReqDto.setFamilyId("FAMILY731181616069066646");
                    bizReqDto.setUserId("xxxxxuser12349xxxxxx--"+ finalI);

                   /* System.out.println("开始获取锁--FamilyId："+bizReqDto.getFamilyId());
                    familyMapper.selectByFamilyIdForLock(bizReqDto.getFamilyId());
                    System.out.println("成功获取锁--FamilyId："+bizReqDto.getFamilyId());*/
                    System.out.println(tbCartService.join(bizReqDto));
                });
            }
            System.out.println("===============================================================");
            System.out.println("===============================================================");
            System.out.println("===============================================================");
            Thread.sleep(5 * 1000);
        }

        Thread.sleep(1000 * 1000);
    }

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
