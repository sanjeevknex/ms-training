package com.edureka.ms.training.forex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by sanjeev.singh1 on 30/06/18.
 */
@RestController
@Slf4j
public class ForexController {


    @Autowired
    ExchangeValueRepository repository;

    @Autowired
    Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to){
        log.info("Inside get Exchange Value");
        ExchangeValue byFromAndTo = repository.findByFromAndTo(from, to);

        byFromAndTo.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return byFromAndTo;
    }

    @GetMapping("/hello")
    public String getExchangeValue(){
        return "hello";
    }

}
