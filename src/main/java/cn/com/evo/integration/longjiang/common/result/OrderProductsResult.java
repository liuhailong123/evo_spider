package cn.com.evo.integration.longjiang.common.result;

import cn.com.evo.integration.longjiang.common.msg.DataMsg;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ZhangLiJie
 * @Description
 * @Date: Created in 11:30 27/3/18
 * @Modified By:
 */
public class OrderProductsResult {

    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * BOSS产品编码
     */
    private String productCode;
    /**
     * 产品类型(2:服务产品 4:套餐产品)
     */
    private Integer productType;
    /**
     * 产品描述
     */
    private String productDes;
    /**
     * 价格计划ID
     */
    private Long pricePlanId;
    /**
     * 价格计划编码
     */
    private String pricePlanCode;
    /**
     * 价格计划名称
     */
    private String pricePlanName;
    /**
     * 价格计划描述
     */
    private String pricePlanDes;
    /**
     * 订购方式(1:周期产品 2:时段产品 3:均可)
     */
    private Integer orderMode;
    /**
     * 订购方式是否一致 (productType =4 时不为空)
     */
    private Integer isOrderModeSame;
    /**
     * 可订购周期数 （多个周期之间，用英文逗号隔开）
     */
    private String orderCycles;
    /**
     * 产品包列表 (productType =4 时不为空)
     */
    private List<Component> componentList;

    public OrderProductsResult() {
    }

    public static List<OrderProductsResult> getUserResultForLongJiang(DataMsg ob) {
        List<OrderProductsResult> result = new ArrayList<OrderProductsResult>();
        Object obj = ob.getReturnData();
        String str = (String) obj;
        JSONArray jsonArray = JSON.parseArray(str);
        for (int i = 0; i < jsonArray.size(); i++) {
            OrderProductsResult orderProductsResult = new OrderProductsResult();
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            orderProductsResult.setProductId(jsonObject.getLong("productId"));
            orderProductsResult.setProductName(jsonObject.getString("productName"));
            orderProductsResult.setProductCode(jsonObject.getString("productCode"));
            orderProductsResult.setProductType(jsonObject.getInteger("productType"));
            orderProductsResult.setProductDes(jsonObject.getString("productDes"));
            orderProductsResult.setPricePlanId(jsonObject.getLong("pricePlanId"));
            orderProductsResult.setPricePlanCode(jsonObject.getString("pricePlanCode"));
            orderProductsResult.setPricePlanName(jsonObject.getString("pricePlanName"));
            orderProductsResult.setPricePlanDes(jsonObject.getString("pricePlanDes"));
            orderProductsResult.setOrderMode(jsonObject.getInteger("orderMode"));
            orderProductsResult.setOrderCycles(jsonObject.getString("orderCycles"));
            if (jsonObject.getInteger("productType") == 4) {
                //设置产品包列表
                List<Component> componentList = null;
                //获取Component对象的值
                JSONArray jsonArray1 = jsonObject.getJSONArray("componentList");
                for (int j = 0; j < jsonArray1.size(); j++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray1.get(j);
                    Component component = new Component();
                    component.setComponentId(jsonObject1.getLong("componentId"));
                    component.setComponentName(jsonObject1.getString("componentName"));
                    component.setComponentProdMinNum(jsonObject1.getInteger("componentProdMinNum"));
                    component.setComponentProdMaxNum(jsonObject1.getInteger("componentProdMaxNum"));
                    //获取ComponentProdInfo的值
                    JSONArray jsonArray2 = jsonObject1.getJSONArray("ComponentProdInfoList");
                    List<ComponentProdInfo> componentProdInfoList = null;
                    for (int k = 0; k < jsonArray2.size(); k++) {
                        ComponentProdInfo componentProdInfo = new ComponentProdInfo();
                        JSONObject jsonObject2 = (JSONObject) jsonArray2.get(k);
                        componentProdInfo.setProductId(jsonObject2.getLong("productId"));
                        componentProdInfo.setProductCode(jsonObject2.getString("productCode"));
                        componentProdInfo.setProductName(jsonObject2.getString("productName"));
                        componentProdInfo.setPricePlanId(jsonObject2.getLong("pricePlanId"));
                        componentProdInfo.setPricePlanCode(jsonObject2.getString("pricePlanCode"));
                        componentProdInfo.setPricePlanName(jsonObject2.getString("pricePlanName"));
                        componentProdInfo.setBusinessId(jsonObject2.getInteger("businessId"));
                        componentProdInfo.setIsRequired(jsonObject2.getInteger("isRequired"));
                        componentProdInfo.setOrderMode(jsonObject2.getInteger("orderMode"));
                        componentProdInfo.setOrderCycles(jsonObject2.getString("orderCycles"));
                        componentProdInfoList.add(componentProdInfo);
                    }
                    component.setComponentProdInfoList(componentProdInfoList);
                    componentList.add(component);
                }
                orderProductsResult.setComponentList(componentList);
                orderProductsResult.setIsOrderModeSame(jsonObject.getInteger("isOrderModeSame"));
            } else {
                orderProductsResult.setComponentList(null);
                orderProductsResult.setIsOrderModeSame(null);
            }
            result.add(orderProductsResult);
        }
        return result;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public Long getPricePlanId() {
        return pricePlanId;
    }

    public void setPricePlanId(Long pricePlanId) {
        this.pricePlanId = pricePlanId;
    }

    public String getPricePlanCode() {
        return pricePlanCode;
    }

    public void setPricePlanCode(String pricePlanCode) {
        this.pricePlanCode = pricePlanCode;
    }

    public String getPricePlanName() {
        return pricePlanName;
    }

    public void setPricePlanName(String pricePlanName) {
        this.pricePlanName = pricePlanName;
    }

    public String getPricePlanDes() {
        return pricePlanDes;
    }

    public void setPricePlanDes(String pricePlanDes) {
        this.pricePlanDes = pricePlanDes;
    }

    public Integer getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(Integer orderMode) {
        this.orderMode = orderMode;
    }

    public Integer getIsOrderModeSame() {
        return isOrderModeSame;
    }

    public void setIsOrderModeSame(Integer isOrderModeSame) {
        this.isOrderModeSame = isOrderModeSame;
    }

    public String getOrderCycles() {
        return orderCycles;
    }

    public void setOrderCycles(String orderCycles) {
        this.orderCycles = orderCycles;
    }

    public List<Component> getComponentList() {
        return componentList;
    }

    public void setComponentList(List<Component> componentList) {
        this.componentList = componentList;
    }

    public static class Component {
        /**
         * 产品包Id
         */
        private Long componentId;
        /**
         * 产品包名称
         */
        private String componentName;
        /**
         * 产品包可订购最小产品数
         */
        private Integer componentProdMinNum;
        /**
         * 产品包可订购最大产品数
         */
        private Integer componentProdMaxNum;
        /**
         * 产品包Id
         */
        private List<ComponentProdInfo> ComponentProdInfoList;

        public Component() {
        }

        public Long getComponentId() {
            return componentId;
        }

        public void setComponentId(Long componentId) {
            this.componentId = componentId;
        }

        public String getComponentName() {
            return componentName;
        }

        public void setComponentName(String componentName) {
            this.componentName = componentName;
        }

        public Integer getComponentProdMinNum() {
            return componentProdMinNum;
        }

        public void setComponentProdMinNum(Integer componentProdMinNum) {
            this.componentProdMinNum = componentProdMinNum;
        }

        public Integer getComponentProdMaxNum() {
            return componentProdMaxNum;
        }

        public void setComponentProdMaxNum(Integer componentProdMaxNum) {
            this.componentProdMaxNum = componentProdMaxNum;
        }

        public List<ComponentProdInfo> getComponentProdInfoList() {
            return ComponentProdInfoList;
        }

        public void setComponentProdInfoList(List<ComponentProdInfo> componentProdInfoList) {
            ComponentProdInfoList = componentProdInfoList;
        }
    }

    public static class ComponentProdInfo {
        /**
         * 产品Id
         */
        private Long productId;
        /**
         * BOSS产品编码
         */
        private String productCode;
        /**
         * 产品名称
         */
        private String productName;
        /**
         * 价格计划ID
         */
        private Long pricePlanId;
        /**
         * 价格计划编码
         */
        private String pricePlanCode;
        /**
         * 价格计划名称
         */
        private String pricePlanName;
        /**
         * 业务标识
         */
        private Integer businessId;
        /**
         * 是否必选
         */
        private Integer isRequired;
        /**
         * 订购方式(1:周期产品 2:时段产品 3:均可)
         */
        private Integer orderMode;
        /**
         * 可订购周期数 （多个周期之间，用英文逗号隔开）
         */
        private String orderCycles;

        public ComponentProdInfo() {

        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Long getPricePlanId() {
            return pricePlanId;
        }

        public void setPricePlanId(Long pricePlanId) {
            this.pricePlanId = pricePlanId;
        }

        public String getPricePlanCode() {
            return pricePlanCode;
        }

        public void setPricePlanCode(String pricePlanCode) {
            this.pricePlanCode = pricePlanCode;
        }

        public String getPricePlanName() {
            return pricePlanName;
        }

        public void setPricePlanName(String pricePlanName) {
            this.pricePlanName = pricePlanName;
        }

        public Integer getBusinessId() {
            return businessId;
        }

        public void setBusinessId(Integer businessId) {
            this.businessId = businessId;
        }

        public Integer getIsRequired() {
            return isRequired;
        }

        public void setIsRequired(Integer isRequired) {
            this.isRequired = isRequired;
        }

        public Integer getOrderMode() {
            return orderMode;
        }

        public void setOrderMode(Integer orderMode) {
            this.orderMode = orderMode;
        }

        public String getOrderCycles() {
            return orderCycles;
        }

        public void setOrderCycles(String orderCycles) {
            this.orderCycles = orderCycles;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "productId:" + productId +
                ", productName:'" + productName + '\'' +
                ", productCode:'" + productCode + '\'' +
                ", productType:" + productType +
                ", productDes:'" + productDes + '\'' +
                ", pricePlanId:" + pricePlanId +
                ", pricePlanCode:'" + pricePlanCode + '\'' +
                ", pricePlanName:'" + pricePlanName + '\'' +
                ", pricePlanDes:'" + pricePlanDes + '\'' +
                ", orderMode:" + orderMode +
                ", isOrderModeSame:" + isOrderModeSame +
                ", orderCycles:'" + orderCycles + '\'' +
                '}';
    }
}
