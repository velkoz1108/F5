package com.f5.web;

import com.f5.mapper.secondary.DicItemMapper;
import com.f5.model.secondary.DicItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author : wangtao
 * @date : 2018/9/7 17:55  星期五
 */

@RestController
public class DictController {
    @Autowired
    private DicItemMapper dicItemMapper;

    @RequestMapping("/dict")
    @ResponseBody
    public DicItem getOne() {
        return dicItemMapper.selectByExample(null).get(0);
    }
}
