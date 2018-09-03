package com.f5.service.impl;

import com.f5.model.single.Menu;
import com.f5.repository.single.MenuRepository;
import com.f5.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : wangtao
 * @date : 2018/9/3 16:04  星期一
 */

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository repository;

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> all = repository.findAll();
        return all;
    }
}
