package cn.com.evo.cms.web.api.vo;

import java.io.Serializable;

/**
 * @author rf
 * @date 2020/5/9
 */
public class CataloguArticleApiVo implements Serializable {
    private String id;

    private String title;

    private String cover;

    private String author;

    private Integer readNum;

    private String dateTime;

    private String price;

    public CataloguArticleApiVo() {
    }

    public CataloguArticleApiVo(String id, String title, String cover, String author, Integer readNum, String dateTime, String price) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.author = author;
        this.readNum = readNum;
        this.dateTime = dateTime;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
