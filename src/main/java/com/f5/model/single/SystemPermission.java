package com.f5.model.single;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : wangtao
 * @date : 2018/8/23 15:46  星期四
 * <p>
 * 全部权限
 */

@Data
@Entity
public class SystemPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permissionName;

    private Integer orderNo;

    private String remark;

    //OneToMany指定了由AdminRole这个类来维护多对一的关联关系，mappedBy="role"
    @OneToMany(mappedBy="systemPermission")
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<AdminPermission> adminPermissions;
}
