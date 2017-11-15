package com.huage.crawler.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("ntype")
public class NewType {
    @Id
    private Integer id;
    @Column
    private String name;
}
