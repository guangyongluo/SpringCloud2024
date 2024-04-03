package com.vilin.cloud.controller;

import com.vilin.cloud.entities.Pay;
import com.vilin.cloud.entities.PayDTO;
import com.vilin.cloud.resp.ResultData;
import com.vilin.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {

  @Resource
  private PayService payService;

  @PostMapping("/pay/add")
  @Operation(summary = "新增",description = "新增支付流水方法,json串做参数")
  public ResultData<String> addPay(@RequestBody PayDTO payDto) {
    Pay pay = new Pay();
    BeanUtils.copyProperties(payDto, pay);
    int result = payService.add(pay);
    return ResultData.success("插入成功：" + result + "个支付款");
  }

  @DeleteMapping("/pay/delete/{id}")
  @Operation(summary = "删除",description = "删除支付流水方法")
  public ResultData<String> deletePay(@PathVariable("id") Integer id) {
    int result = payService.delete(id);
    return ResultData.success("删除成功：" + result + "个支付款");
  }

  @PutMapping("/pay/update")
  @Operation(summary = "修改",description = "修改支付流水方法")
  public ResultData<String> updatePay(@RequestBody PayDTO payDto){
    Pay pay = new Pay();
    BeanUtils.copyProperties(payDto, pay);
    int result = payService.update(pay);
    return ResultData.success("成功修改：" + result + "个支付款");
  }

  @GetMapping("/pay/get/{id}")
  @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
  public ResultData<String> getPay(@PathVariable("id") Integer id){
    Pay pay = payService.getById(id);
    return ResultData.success("查询结果：" + pay.toString());
  }

  @GetMapping("/pay/get/all")
  public ResultData<String> getAll(){
    List<Pay> pays = payService.getAll();
    return ResultData.success(Arrays.toString(pays.stream().toArray()));
  }

  @GetMapping("/pay/get/env")
  public ResultData<String> getEnvInfo(@Value("${vilin.message}") String envString, @Value("${server.port}") String port){
    return ResultData.success("port: " + port +", message:" + envString);
  }

}
