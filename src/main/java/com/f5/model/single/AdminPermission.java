package com.f5.model.single;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : wangtao
 * @date : 2018/8/23 17:53  星期四
 */

@Data
@Entity
public class AdminPermission implements Serializable {
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
