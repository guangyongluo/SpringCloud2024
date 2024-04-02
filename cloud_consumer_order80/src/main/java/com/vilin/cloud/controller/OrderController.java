package com.vilin.cloud.controller;

import com.vilin.cloud.entities.PayDTO;
import com.vilin.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @auther zzyy
 * @create 2023-12-22 22:33
 */
@RestController
public class OrderController
{
//    public static final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码
    public static final String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称

    @Resource
    private RestTemplate restTemplate;

    @PostMapping(value = "/consumer/pay/add")
    private ResultData addOrder(@RequestBody PayDTO payDTO)
    {
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @DeleteMapping(value = "/consumer/pay/delete/{id}")
    private void deleteOrder(@PathVariable("id") Integer id){
        restTemplate.delete(PaymentSrv_URL + "/pay/delete/{1}", id);
    }

    @PutMapping(value = "/consumer/pay/update")
    private void updateOrder(@RequestBody PayDTO payDTO){
        restTemplate.put(PaymentSrv_URL + "/pay/update", payDTO);
    }

    @GetMapping(value = "/consumer/pay/get/{id}")
    private ResultData getPayInfo(@PathVariable("id") Integer id)
    {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/"+id,ResultData.class,id);
    }

    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul()
    {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }

    @GetMapping(value = "/consumer/pay/get/env")
    private ResultData<String> getEnvByConsul(){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/env", ResultData.class);
    }


    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }

}
