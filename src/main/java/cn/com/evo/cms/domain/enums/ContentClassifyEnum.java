package cn.com.evo.cms.domain.enums;/** * @Description: 内容类型枚举类 * @author: lu.xin * @create: 2019年01月08日18:12:35 **/public enum ContentClassifyEnum {    movie("电影", 1),    episode("剧集", 2),    episode_child("剧集子集", 3),    error("错误", -1);    String name;    int index;    ContentClassifyEnum(String name, int index) {        this.name = name;        this.index = index;    }    public int getIndex() {        return index;    }    public String getName() {        return name;    }    public static ContentClassifyEnum val(Integer index) {        for (ContentClassifyEnum s : values()) {    //values()方法返回enum实例的数组            if (index == s.getIndex()) {                return s;            }        }        return null;    }}