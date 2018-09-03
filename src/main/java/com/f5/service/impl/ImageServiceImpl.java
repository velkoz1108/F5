package com.f5.service.impl;

import com.f5.model.single.ImageIcon;
import com.f5.repository.single.ImageIconRepository;
import com.f5.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : wangtao
 * @date : 2018/9/3 11:36  星期一
 */

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageIconRepository repository;

    @Override
    public List<ImageIcon> findAll() {
        return repository.findAll();
    }
}
