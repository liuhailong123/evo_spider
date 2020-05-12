package cn.com.evo.integration.guangxi.pay.dto.pay.dto.query;import cn.com.evo.integration.common.ConstantFactory;import cn.com.evo.integration.guangxi.common.ConstantEnum;import com.frameworks.utils.SignUtil;import com.google.common.collect.Maps;import org.apache.commons.lang3.StringUtils;import javax.xml.bind.annotation.XmlElement;import javax.xml.bind.annotation.XmlType;import java.util.Map;/** * @Description: 查询产品信息接口请求封装 * @author: lu.xin * @create: 2019-05-20 5:45 PM **/@XmlType(propOrder = {        "method",        "stbId",        "productId",        "partner",        "sign",})public class QueryProductRequest {    /**     * 固定值     */    private String method = "queryProdInfo";    private String stbId;    private String productId = ConstantFactory.map.get(ConstantEnum.product_id.getKey());    private String partner = ConstantFactory.map.get(ConstantEnum.partner.getKey());    private String sign;    public QueryProductRequest() {    }    public QueryProductRequest(String stbId, String productId) {        this.stbId = stbId;        if (StringUtils.isNotBlank(productId)) {            this.productId = productId;        }        String payKey = ConstantFactory.map.get(ConstantEnum.pay_key.getKey());        this.sign = SignUtil.buildSign(toMap(), payKey);    }    public Map<String, String> toMap() {        Map<String, String> map = Maps.newHashMap();        map.put("method", this.method);        map.put("stbId", this.stbId);        map.put("productId", this.productId);        map.put("partner", this.partner);        return map;    }    public String toParams() {        String params = "method=" + this.method + "&stbId=" + this.stbId +                "&productId=" + this.productId + "&partner=" + this.partner + "&sign=" + this.sign;        return params;    }    @XmlElement(name = "method")    public String getMethod() {        return method;    }    @XmlElement(name = "stbId")    public String getStbId() {        return stbId;    }    @XmlElement(name = "productId")    public String getProductId() {        return productId;    }    @XmlElement(name = "partner")    public String getPartner() {        return partner;    }    @XmlElement(name = "sign")    public String getSign() {        return sign;    }    public void setMethod(String method) {        this.method = method;    }    public void setStbId(String stbId) {        this.stbId = stbId;    }    public void setProductId(String productId) {        this.productId = productId;    }    public void setPartner(String partner) {        this.partner = partner;    }    public void setSign(String sign) {        this.sign = sign;    }}