package com.codertao.design.chain.case1.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * @author yanghelin3
 * @date 2023/1/30
 */
@Component
public class CheckHandlerConfig {

    public static Map<Integer, AbstractCheckHandler> checkHandlerBeanMap = new HashMap<>();

    @PostConstruct
    public void init() {
        checkHandlerBeanMap.put(1, new NullValueCheckHandler());
        checkHandlerBeanMap.put(2, new PriceCheckHandler());
        checkHandlerBeanMap.put(3, new StockCheckHandler());
    }

    public static AbstractCheckHandler getNextHandler(AbstractCheckHandler abstractCheckHandler) {
        // 从map中根据value取key的值
        Integer index = checkHandlerBeanMap.entrySet().stream()
                .collect(Collectors.toMap(Entry::getValue, Entry::getKey)).get(abstractCheckHandler);
        if (Objects.isNull(index)) {
            throw new UnsupportedOperationException("没有该处理器的类型: " + abstractCheckHandler);
        }

        // 取下一个handler
        return checkHandlerBeanMap.get(index + 1);
    }


    /**
     * 取第一个handler
     *
     * @return
     */
    public static AbstractCheckHandler getFirstHandler() {
        return checkHandlerBeanMap.get(1);
    }


}
