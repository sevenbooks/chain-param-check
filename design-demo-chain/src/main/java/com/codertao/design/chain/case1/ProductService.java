package com.codertao.design.chain.case1;

import com.codertao.design.chain.case1.handler.AbstractCheckHandler;
import com.codertao.design.chain.case1.handler.CheckHandlerConfig;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    /**
     * 使用Spring注入:所有继承了AbstractCheckHandler抽象类的Spring Bean都会注入进来。Map的Key对应Bean的name,Value是name对应相应的Bean
     */
    @Resource
    private Map<String, AbstractCheckHandler> handlerMap;

    /**
     * 创建商品
     *
     * @return
     */
    public Result createProduct(ProductVO param) {

        //参数校验，使用责任链模式
        Result paramCheckResult = this.paramCheckChain(param);
        if (!paramCheckResult.isSuccess()) {
            return paramCheckResult;
        }

        //创建商品
        return this.saveProduct(param);
    }

    /**
     * 参数校验：责任链模式
     *
     * @param param
     * @return
     */
    private Result paramCheckChain(ProductVO param) {
        // 获取第一个处理器
        AbstractCheckHandler firstHandler = CheckHandlerConfig.getFirstHandler();
        //责任链：执行处理器链路
        Result executeChainResult = HandlerClient.executeChain(firstHandler, param);
        if (!executeChainResult.isSuccess()) {
            System.out.println("创建商品 失败...");
            return executeChainResult;
        }

        //处理器链路全部成功
        return Result.success();
    }


    private Result saveProduct(ProductVO param) {
        System.out.println("保存商品 成功...");
        return Result.success(param);
    }
}
