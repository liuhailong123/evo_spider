package cn.com.evo.cms.domain.entity.cms;/** * @Description: * @author: lu.xin * @create: 2018-04-12 下午2:58 **/import cn.com.evo.cms.domain.enums.ColumnsAuxiliaryType;import java.util.List;/** * 树对象 * * @author shilinxiao */public class Tree {    private String id;    private String pId;    private String name;    private Integer level;    private String siteId;    //1站点 2应用 3栏目 4专题 5剧集    private Integer type;    private String iconSkin;    private List<Tree> child;    public Tree(String id, String pId, String name, Integer level) {        this.id = id;        this.pId = pId;        this.name = name;        this.level = level;    }    public Tree(String id, String pId, String name, Integer level, String siteId, Integer type) {        this.id = id;        this.pId = pId;        this.name = name;        this.level = level;        this.siteId = siteId;        this.type = type;        this.iconSkin = setIconByType(type);    }    private String setIconByType(Integer type) {        ColumnsAuxiliaryType auxiliaryType = ColumnsAuxiliaryType.val(type);        String icon = "treeIconDefault";        switch (auxiliaryType) {            case root:                icon = "treeIconRoot";                break;            case webSite:                icon = "treeIconWebSite";                break;            case app:                icon = "treeIconApp";                break;            case column:                icon = "treeIconColumn";                break;            case episode:                icon = "treeIconEpisode";                break;            case section:                icon = "treeIconSection";                break;            default:                break;        }        return icon;    }    public Integer getType() {        return type;    }    public void setType(Integer type) {        this.type = type;    }    public String getId() {        return id;    }    public void setId(String id) {        this.id = id;    }    public String getpId() {        return pId;    }    public void setpId(String pId) {        this.pId = pId;    }    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }    public Integer getLevel() {        return level;    }    public void setLevel(Integer level) {        this.level = level;    }    public List<Tree> getChild() {        return child;    }    public void setChild(List<Tree> child) {        this.child = child;    }    public String getSiteId() {        return siteId;    }    public void setSiteId(String siteId) {        this.siteId = siteId;    }    public String getIconSkin() {        return iconSkin;    }    public void setIconSkin(String iconSkin) {        this.iconSkin = iconSkin;    }}