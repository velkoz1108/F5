package com.f5.repository.single;

import com.f5.model.single.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : wangtao
 * @date : 2018/9/3 16:03  星期一
 */


public interface MenuRepository extends JpaRepository<Menu, Long> {
}
