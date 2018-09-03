package com.f5.model.single;

import lombok.Data;
import org.thymeleaf.expression.Lists;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author : wangtao
 * @date : 2018/9/3 15:49  星期一
 */

@Entity
@Data
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long pid;

    @ManyToOne
    @JoinColumn(name = "pid")
    private Menu parentMenu;

    @Column(name = "menu_type")
    private Integer menuType;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "icon_style")
    private String iconStyle;

    @Column(name = "href_url")
    private String hrefUrl;

    @Column(name = "order_no")
    private Integer orderNo;


    //子目录

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private List<Menu> childMenu = new ArrayList<>();


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
    Set<MenuRoles> menuRoles;
}
