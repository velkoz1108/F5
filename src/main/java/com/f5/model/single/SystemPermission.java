package com.f5.model.single;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author : wangtao
 * @date : 2018/8/23 15:46  星期四
 * <p>
 * 全部权限
 */

@Data
@Entity
public class SystemPermission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permissionName;

    private Integer orderNo;

    private String remark;

//
//    @OneToOne
//    @JoinColumn(name = "id")
//    private AdminPermission adminPermission;
}
