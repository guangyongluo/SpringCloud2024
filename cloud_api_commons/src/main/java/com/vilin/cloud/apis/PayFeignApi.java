package com.vilin.cloud.apis;

import com.vilin.cloud.entities.PayDTO;
import com.vilin.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("cloud-payment-service")
public interface PayFeignApi {

  @PostMapping("/pay/add")
  ResultData<String> addPayByFeign(@RequestBody PayDTO payDto);

  @DeleteMapping("/pay/delete/{id}")
  ResultData<String> deletePayByFeign(@PathVariable("id") Integer id);

  @PutMapping("/pay/update")
  ResultData<String> updatePayByFeign(@RequestBody PayDTO payDTO);

  @GetMapping("/pay/get/{id}")
  ResultData<String> getPayByFeign(@PathVariable("id") Integer id);

  @GetMapping("/pay/get/env")
  ResultData<String> getEnvByFeign();

}
