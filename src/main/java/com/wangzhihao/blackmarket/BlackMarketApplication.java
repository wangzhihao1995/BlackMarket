package com.wangzhihao.blackmarket;

import com.didispace.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
@SpringBootApplication
@EnableSwagger2Doc
public class BlackMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackMarketApplication.class, args);
    }
}
