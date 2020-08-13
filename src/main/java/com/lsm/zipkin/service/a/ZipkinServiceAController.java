package com.lsm.zipkin.service.a;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
public class ZipkinServiceAController {

    private static final Logger LOG = Logger.getLogger(ZipkinServiceAController.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/a")
    public String callHome() {
        LOG.info("calling trace zipkin-service-b");
        return restTemplate.getForObject("http://localhost:8773/b", String.class);
    }

    @RequestMapping("/info")
    public String info() {
        LOG.info("i'm zipkin-service-a");
        return "i'm zipkin-service-a";

    }

    /**
     * 必须加这个,否则无法追踪
     *
     * @return
     */
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}