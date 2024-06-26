package com.vilin.cloud.service.impl;

import com.vilin.cloud.service.PayService;
import com.vilin.cloud.entities.Pay;
import com.vilin.cloud.mapper.PayMapper;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @auther zzyy
 * @create 2023-12-21 17:30
 */
@Service
public class PayServiceImpl implements PayService
{
    @Resource
    private PayMapper payMapper;

    @Override
    public int add(Pay pay)
    {
        return payMapper.insertSelective(pay);
    }

    @Override
    public int delete(Integer id)
    {
        return payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Pay pay)
    {
        return payMapper.updateByPrimaryKeySelective(pay);
    }

    @Override
    public Pay getById(Integer id)
    {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> getAll()
    {
        return payMapper.selectAll();
    }
}
