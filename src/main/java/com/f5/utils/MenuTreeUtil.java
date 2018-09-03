package com.f5.utils;

import com.alibaba.fastjson.JSON;
import com.f5.model.single.Menu;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : wangtao
 * @date : 2018/9/3 16:18  星期一
 */

@Slf4j
public class MenuTreeUtil {
    public static List<Menu> buildTree(List<Menu> list) {
        List<Menu> result = new ArrayList<Menu>();
        Map<Long, Menu> dtoMap = new ConcurrentHashMap<>();
        for (Menu node : list) {
            dtoMap.put(node.getId(), node);
        }
        for (Map.Entry<Long, Menu> entry : dtoMap.entrySet()) {
            Menu node = entry.getValue();
            if (node.getParentMenu() == null) {
                result.add(node);
            } else {
                if (dtoMap.get(node.getParentMenu()) != null) {
                    dtoMap.get(node.getParentMenu()).getChildMenu().add(node);
                }
            }
        }
        return result;
    }
}
