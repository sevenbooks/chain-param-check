package com.codertao.design.chain.case1;

import java.math.BigDecimal;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yanghelin3
 * @date 2023/1/30
 */
@SpringBootTest
class ProductServiceTest {

    @Resource
    ProductService productService;

    @Test
    void createProduct() {
        ProductVO param = ProductVO.builder()
                .skuId(123L)
                .skuName("测试商品")
                .imgPath("http://..")
                .price(new BigDecimal(1))
                .stock(1)
                .build();
        productService.createProduct(param);
    }



    @Test
    void createProduct_sku_failed() {
        ProductVO param = ProductVO.builder()
                .skuName("测试商品")
                .imgPath("http://..")
                .price(new BigDecimal(1))
                .stock(1)
                .build();
        productService.createProduct(param);
    }
}