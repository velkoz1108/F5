package com.f5.model.single;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
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
    @Column(name = "last_access_time")
    private Date lastAccessTime;
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

//    cascade属性的可能值有
//all: 所有情况下均进行关联操作，即save-update和delete。
//none: 所有情况下均不进行关联操作。这是默认值。
//save-update: 在执行save/update/saveOrUpdate时进行关联操作。
//delete: 在执行delete 时进行关联操作。
//all-delete-orphan: 当一个节点在对象图中成为孤儿节点时，删除该节点。
//比如在一个一对多的关系中，Student包含多个book，当在对象关系中删除一个book时，
//此book即成为孤儿节点。

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "admin_id")
//    Set<AdminRoles> adminRoles;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    Set<AdminPermission> adminPermissions;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    Set<AdminRoles> adminRoles;
}
