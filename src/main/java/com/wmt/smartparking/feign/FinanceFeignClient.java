package com.wmt.smartparking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author wmtumanday
 */
@FeignClient(name ="smart-finance")
public interface FinanceFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/smart/finance/test")
    String test();

}
