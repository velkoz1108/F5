package com.f5.model.single;

import javax.persistence.*;

/**
 * @author : wangtao
 * @date : 2018/8/29 14:10  星期三
 */

@Entity
@Table(name = "t_id_card")
public class IDCard {
    private int id;
    private String no;
    private Person person;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @OneToOne //　--->　　OnetoOne指定了一对一的关联关系，一对一中随便指定一方来维护映射关系，这里选择IDCard来进行维护
    @JoinColumn(name = "pid") //--->　　指定外键的名字 pid
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
