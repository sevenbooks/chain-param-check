package com.codertao.design.chain.case1.handler;

import com.codertao.design.chain.case1.ProductVO;
import com.codertao.design.chain.case1.Result;
import java.util.Objects;
import org.springframework.stereotype.Component;


/**
 * 抽象类处理器
 */
@Component
public abstract class AbstractCheckHandler {

    /**
     * 处理器执行方法
     *
     * @param param
     * @return
     */
    public abstract Result handle(ProductVO param);


    /**
     * 下一个处理器
     *
     * @return
     */
    private AbstractCheckHandler getNextHandler() {
        return CheckHandlerConfig.getNextHandler(this);
    }


    /**
     * 链路传递
     *
     * @param param
     * @return
     */
    protected Result next(ProductVO param) {
        AbstractCheckHandler nextHandler = getNextHandler();
        //下一个链路没有处理器了，直接返回
        if (Objects.isNull(nextHandler)) {
            return Result.success();
        }

        //执行下一个处理器
        return nextHandler.handle(param);
    }

}
