package com.f5.model.single;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : wangtao
 * @date : 2018/8/23 13:10  星期四
 * <p>
 * 后台管理用户
 */
@Data
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean rememberMe;
//
//    @ManyToMany
//    @JoinTable(name = "admin_permission",
//            joinColumns = @JoinColumn(name = "admin_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id") )
//    Set<SystemPermission> permissionList;
//
//    @ManyToMany
//    @JoinTable(name = "admin_role",
//            joinColumns = @JoinColumn(name = "admin_id",referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")  )
//    Set<SystemRoles> rolesList;

    //--->　　OneToMany关联到了AdminRole这个类，由AdminRole这个类来维护多对一的关系，mappedBy="admin"
    @OneToMany(mappedBy = "admin")
    @LazyCollection(LazyCollectionOption.FALSE)
    Set<AdminRoles> adminRoles;

    @OneToMany(mappedBy = "admin")
    @LazyCollection(LazyCollectionOption.FALSE)
    Set<AdminPermission> adminPermissions;
}
