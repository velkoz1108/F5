package com.f5.model.single;

import lombok.Data;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;

/**
 * @author : wangtao
 * @date : 2018/8/23 17:53  星期四
 */

@Data
@Entity
public class AdminPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    //    ManyToOne关联到Admin
//    @ManyToOne
//    @JoinColumn(name = "admin_id")
//    private Admin admin;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id")
    private SystemPermission systemPermission;
}
