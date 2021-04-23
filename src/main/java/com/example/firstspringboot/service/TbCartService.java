package com.example.firstspringboot.service;

import com.example.firstspringboot.common.vo.FamilyMemberReqDto;
import com.example.firstspringboot.common.vo.ParamVO;
import com.example.firstspringboot.common.vo.ResultVO;
import com.example.firstspringboot.common.vo.TbCart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuchj
 * @create 2018-11-06-22:53.
 */

public interface TbCartService {

    TbCart selectByPrimaryKey(Long id);

    ResultVO getResultData(ParamVO paramVO);

    List<ResultVO> getResultDatas(ParamVO paramVO);

    Long join(FamilyMemberReqDto bizReqDto);
}
