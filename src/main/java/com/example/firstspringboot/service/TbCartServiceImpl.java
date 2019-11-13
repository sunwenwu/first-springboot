package com.example.firstspringboot.service;

import com.example.firstspringboot.common.vo.ParamVO;
import com.example.firstspringboot.common.vo.ResultVO;
import com.example.firstspringboot.common.vo.TbCart;
import com.example.firstspringboot.dao.TbCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author liuchj
 * @create 2018-11-06-22:54.
 */
@Service
public class TbCartServiceImpl implements TbCartService{

    @Autowired
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
}
