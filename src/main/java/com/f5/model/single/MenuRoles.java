package com.f5.model.single;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author : wangtao
 * @date : 2018/9/3 15:49  星期一
 */

@Entity
@Data
public class MenuRoles implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private SystemRoles systemRoles;
}
