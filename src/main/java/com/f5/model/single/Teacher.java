package com.f5.model.single;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author : wangtao
 * @date : 2018/8/23 17:58  星期四
 */

@Data
@Entity
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToMany(mappedBy = "teacherList")
    private Set<Student> students;
}
