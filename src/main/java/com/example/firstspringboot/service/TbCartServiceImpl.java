package com.example.firstspringboot.service;

import com.example.firstspringboot.common.vo.*;
import com.example.firstspringboot.dao.FamilyMapper;
import com.example.firstspringboot.dao.FamilyMemberCopyMapper;
import com.example.firstspringboot.dao.TbCartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author liuchj
 * @create 2018-11-06-22:54.
 */
@Slf4j
@Service
public class TbCartServiceImpl implements TbCartService{

    @Resource
    private TbCartMapper tbCartMapper;

    @Override
    public TbCart selectByPrimaryKey(Long id) {

        return tbCartMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultVO getResultData(ParamVO paramVO) {
        System.out.println("================入参："+paramVO);

        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("sww原始返回");
        return resultVO;
    }

    @Override
    public List<ResultVO> getResultDatas(ParamVO paramVO) {
        System.out.println("================入参："+paramVO);

        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("sww原始返回111111");

        ResultVO resultVO2 = new ResultVO();
        resultVO2.setMessage("sww原始返回222222");
        return Arrays.asList(resultVO,resultVO2);
    }


    @Resource
    private FamilyMapper familyMapper;

    @Resource
    private FamilyMemberCopyMapper familyMemberCopyMapper;

    public volatile static Boolean flag = false;

    @Override
//    @Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
    @Transactional(rollbackFor = Exception.class)
    public Long join(FamilyMemberReqDto bizReqDto)  {

        try {
            String name = Thread.currentThread().getName();

            System.out.println(name+":----开始获取锁--FamilyId："+bizReqDto.getFamilyId());
            familyMapper.selectByFamilyIdForLock(bizReqDto.getFamilyId());
            if (!flag) {
//                Thread.sleep(10 * 1000);
                flag = true;
            }

            System.out.println(name+"----成功获取锁--FamilyId："+bizReqDto.getFamilyId());

            FamilyMemberCopy copy = new FamilyMemberCopy();
            copy.setFamilyId(bizReqDto.getFamilyId());
            copy.setUserId(bizReqDto.getUserId());
            FamilyMemberCopy dataMember = familyMemberCopyMapper.selectByPrimaryKeySelective(copy);

            if (dataMember != null) {
                System.out.println(name+":存在");
                return -1L;
            }

            FamilyMemberCopy member = new FamilyMemberCopy();
            member.setFamilyId(bizReqDto.getFamilyId());
            member.setUserId(bizReqDto.getUserId());
            member.setJoinStatus("0");
            member.setOwner("N");
            familyMemberCopyMapper.insertSelective(member);
            System.out.println("保存成功："+member.getId());

            return member.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
