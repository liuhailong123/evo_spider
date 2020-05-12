package cn.com.evo.cms.domain.entity.cms;import com.frameworks.core.entity.BaseEntity;import javax.persistence.Entity;import javax.persistence.NamedQuery;import javax.persistence.Table;/** * @Description: 内容注入记录表 * @author: lu.xin * @create: 2019-03-28 2:35 PM **/@Entity@Table(name = "cms_content_injection")@NamedQuery(name = "ContentInjection.findAll", query = "SELECT c FROM ContentInjection c")public class ContentInjection extends BaseEntity {    /**     * 内容id     */    private String contentId;    /**     * 三方操作id     */    private String bizId;    /**     * 操作状态; 0-注入中；1-已注入     */    private Integer status;    private String videoFileUrl;    private String imagesFileUrl;    private String xmlFileUrl;}