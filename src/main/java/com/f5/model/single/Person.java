package com.f5.model.single;

import javax.persistence.*;

/**
 * @author : wangtao
 * @date : 2018/8/29 14:10  星期三
 */


@Entity
@Table(name = "t_person")
public class Person {
    private int id;
    private String name;
    private IDCard card;

    @OneToOne(mappedBy = "person") //--->　　指定了OneToOne的关联关系，mappedBy同样指定由对方来进行维护关联关系
    public IDCard getCard() {
        return card;
    }

    public void setCard(IDCard card) {
        this.card = card;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
