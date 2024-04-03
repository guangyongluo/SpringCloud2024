package com.vilin.cloud.controller;

import com.vilin.cloud.apis.PayFeignApi;
import com.vilin.cloud.entities.PayDTO;
import com.vilin.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  @Resource
  private PayFeignApi payFeignApi;

  @PostMapping(value = "/feign/pay/add")
  private ResultData addOrder(@RequestBody PayDTO payDTO) {
    return payFeignApi.addPayByFeign(payDTO);
  }

  @DeleteMapping(value = "/feign/pay/delete/{id}")
  private ResultData<String> deleteOrder(@PathVariable("id") Integer id) {
    return payFeignApi.deletePayByFeign(id);
  }

  @PutMapping(value = "/feign/pay/update")
  private ResultData<String> updateOrder(@RequestBody PayDTO payDTO) {
    return payFeignApi.updatePayByFeign(payDTO);
  }

  @GetMapping(value = "/feign/pay/get/{id}")
  private ResultData getPayInfo(@PathVariable("id") Integer id) {
    return payFeignApi.getPayByFeign(id);
  }

  @GetMapping(value = "/feign/pay/get/env")
  private ResultData<String> getEnvByConsul() {
    return payFeignApi.getEnvByFeign();
  }


}
