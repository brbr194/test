package com.br.entity;

import javax.persistence.*;

@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private String price;
    @Column(name = "author")
    private String author;
    @Column(name = "press")
    private String press;
    @Column(name = "img")
    private String img;

    @Column(name = "typeId")
    private Integer typeId;

    @Transient
    private String typeName;

    public Book(Integer id, String name, String price, String author, String press, String img, Integer typeId, String typeName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.press = press;
        this.img = img;
        this.typeId = typeId;
        this.typeName = typeName;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 获取
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取
     * @return press
     */
    public String getPress() {
        return press;
    }

    /**
     * 设置
     * @param press
     */
    public void setPress(String press) {
        this.press = press;
    }

    /**
     * 获取
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取
     * @return typeId
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置
     * @param typeId
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String toString() {
        return "Book{id = " + id + ", name = " + name + ", price = " + price + ", author = " + author + ", press = " + press + ", img = " + img + ", typeId = " + typeId + "}";
    }
}