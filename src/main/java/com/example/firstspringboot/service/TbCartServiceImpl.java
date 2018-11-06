package com.example.firstspringboot.service;

import com.example.firstspringboot.common.vo.TbCart;
import com.example.firstspringboot.dao.TbCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
