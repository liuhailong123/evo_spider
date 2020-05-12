package cn.com.evo.integration.guangxi.content.xml.model;import cn.com.evo.integration.common.ConstantFactory;import cn.com.evo.integration.guangxi.common.ConstantEnum;import com.frameworks.utils.DateUtil;import javax.xml.bind.annotation.XmlAttribute;import javax.xml.bind.annotation.XmlType;/** * @Description: * @author: lu.xin * @create: 2019-05-14 11:23 AM **/@XmlType(propOrder = {        "assetClass",        "assetId",        "assetName",        "creationDate",        "product",        "versionMajor",        "versionMinor",        "verb",        "description",        "provider",        "providerId",})public class AMS {    private String assetClass;    private String assetId;    private String assetName;    private String creationDate;    private String product = "VOD";    private String versionMajor = "1";    private String versionMinor = "0";    private String verb = "";    private String description = "";    private String provider = ConstantFactory.map.get(ConstantEnum.provider.getKey());    private String providerId = ConstantFactory.map.get(ConstantEnum.provider_id.getKey());    public AMS() {    }    public AMS(String assetClass, String assetId, String assetName) {        this.assetClass = assetClass;        this.assetId = assetId;        this.assetName = assetName;    }    /**     * @return     */    public static AMS createAMS(String assetClass, String assetId, String assetName) {        AMS ams = new AMS(assetClass, assetId, assetName);        ams.setCreationDate(DateUtil.getDateTime(DateUtil.DATE_PATTERN.YYYY_MM_DD));        return ams;    }    @XmlAttribute(name = "Asset_Class")    public String getAssetClass() {        return assetClass;    }    @XmlAttribute(name = "Product")    public String getProduct() {        return product;    }    @XmlAttribute(name = "Version_Major")    public String getVersionMajor() {        return versionMajor;    }    @XmlAttribute(name = "Version_Minor")    public String getVersionMinor() {        return versionMinor;    }    @XmlAttribute(name = "Verb")    public String getVerb() {        return verb;    }    @XmlAttribute(name = "Asset_ID")    public String getAssetId() {        return assetId;    }    @XmlAttribute(name = "Asset_Name")    public String getAssetName() {        return assetName;    }    @XmlAttribute(name = "Creation_Date")    public String getCreationDate() {        return creationDate;    }    @XmlAttribute(name = "Description")    public String getDescription() {        return description;    }    @XmlAttribute(name = "Provider")    public String getProvider() {        return provider;    }    @XmlAttribute(name = "Provider_ID")    public String getProviderId() {        return providerId;    }    public void setAssetClass(String assetClass) {        this.assetClass = assetClass;    }    public void setProduct(String product) {        this.product = product;    }    public void setVersionMajor(String versionMajor) {        this.versionMajor = versionMajor;    }    public void setVersionMinor(String versionMinor) {        this.versionMinor = versionMinor;    }    public void setVerb(String verb) {        this.verb = verb;    }    public void setAssetId(String assetId) {        this.assetId = assetId;    }    public void setAssetName(String assetName) {        this.assetName = assetName;    }    public void setCreationDate(String creationDate) {        this.creationDate = creationDate;    }    public void setDescription(String description) {        this.description = description;    }    public void setProvider(String provider) {        this.provider = provider;    }    public void setProviderId(String providerId) {        this.providerId = providerId;    }}