package com.f5.model.single;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

/**
 * @author : wangtao
 * @date : 2018/8/23 15:45  星期四
 * <p>
 * 全部角色
 */

@Data
@Entity
public class SystemRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;

    private Integer orderNo;

}
