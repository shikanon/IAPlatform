package com.ue.model;

import javax.persistence.*;
import java.util.List;

/**
 * @auther: 作者 dzc
 * @description: 类说明 目录表实体类bean
 * @Date: created in 0:07 2017/11/10
 */

@Entity
@Table(name = "category", schema = "studio")
public class TCategory implements java.io.Serializable {

    /**目录ID*/
    private String id;

    /**目录名称*/
    private String name;

    /**目录等级*/
    private String level;

    /**目录类型*/
    private String type;

    /**目录上级地址*/
    private String path;

    /**默认为0*/
    private Integer hasChild;

    /**创建时间*/
    private String createTime;

    /**无参构造函数*/
    public TCategory() {

    }

    public TCategory(String id, String name, String level, String type, String path) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.type = type;
        this.path = path;
    }

    /**目录下的算法*/
    //private List<TProgram> programList;

     /**目录下的数据*/
    //private List<TProgram> programList;

    @Id
    @Column(name = "id", unique = true,nullable = false)
    public String getId() {
        return id;
    }

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "level",nullable = false)
    public String getLevel() {
        return level;
    }

    @Column(name = "type",nullable = false)
    public String getType() {
        return type;
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    @Column(name = "haschild")
    public Integer getHasChild() {
        return hasChild;
    }

    @Column(name = "createtime",nullable = false)
    public String getCreateTime() {
        return createTime;
    }

    @OneToMany(mappedBy="category",
            cascade={CascadeType.ALL}
    )
    /*public List<TProgram> getProgramList() {
        return programList;
    }

    public void setProgramList(List<TProgram> programList) {
        this.programList = programList;
    }
*/
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
