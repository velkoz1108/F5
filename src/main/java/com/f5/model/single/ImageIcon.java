package com.f5.model.single;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : wangtao
 * @date : 2018/9/3 11:37  星期一
 */
@Data
@Entity
public class ImageIcon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "icon_name")
    private String iconName;

    @Column(name = "file_path")
    private String filePath;

    private Integer type = 0;

    @Column(name = "index_no")
    private Integer indexNo = 0;

    @Column(name = "create_date")
    private Date createDate = new Date();

    @Column(name = "modify_date")
    private Date modifyDate = new Date();
}
