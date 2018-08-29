package com.f5.model.single;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : wangtao
 * @date : 2018/8/23 17:45  星期四
 */

@Data
@Entity
public class AdminRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    //    ManyToOne关联到Admin
//    @ManyToOne
//    @JoinColumn(name = "admin_id")
//    private Admin admin;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private SystemRoles systemRoles;
}
