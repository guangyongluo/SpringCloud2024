package com.vilin.cloud.service;


import com.vilin.cloud.entities.Pay;
import java.util.List;

/**
 * @auther zzyy
 * @create 2023-12-21 17:28
 */
public interface PayService
{
    public int add(Pay pay);
    public int delete(Integer id);
    public int update(Pay pay);

    public Pay getById(Integer id);

    public List<Pay> getAll();

}
