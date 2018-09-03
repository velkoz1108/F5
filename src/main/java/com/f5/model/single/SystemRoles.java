package com.f5.model.single;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author : wangtao
 * @date : 2018/8/23 15:45  星期四
 * <p>
 * 全部角色
 */

@Data
@Entity
public class SystemRoles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    private Integer orderNo;

}
