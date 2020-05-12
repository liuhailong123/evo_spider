package cn.com.evo.integration.xjTvos.content.xml.model;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * @author rf
 * @date 2019/5/9
 */
public class Extend implements Serializable {
    /**
     *
     */
    private String directorEn;

    private String nameEn;

    private String actorEn;

    private String descriptionEn;

    public Extend() {
    }

    public Extend(String directorEn, String nameEn, String actorEn, String descriptionEn) {

        this.directorEn = directorEn;
        this.nameEn = nameEn;
        this.actorEn = actorEn;
        this.descriptionEn = descriptionEn;
    }

    @XmlElement(name = "vod:Director_en")
    public String getDirectorEn() {

        return directorEn;
    }

    public void setDirectorEn(String directorEn) {
        this.directorEn = directorEn;
    }

    @XmlElement(name = "vod:Name_en")
    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @XmlElement(name = "vod:Actor_en")
    public String getActorEn() {
        return actorEn;
    }

    public void setActorEn(String actorEn) {
        this.actorEn = actorEn;
    }

    @XmlElement(name = "vod:Description_en")
    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }
}
