package com.tao.protal.pojo;

/**
 * Created by 28029 on 2018/4/10.
 */
public class CartItem {
    private long id;
    private String title;
    private Integer num;
    private long price;
    private String image;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }
}
