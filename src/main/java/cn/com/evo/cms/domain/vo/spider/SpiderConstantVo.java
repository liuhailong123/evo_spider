package cn.com.evo.cms.domain.vo.spider;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * @author rf
 * @date 2020/4/28
 * 爬虫配置子表实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SpiderConstantVo extends com.frameworks.core.vo.BaseVo {
    private static final long serialVersionUID = 1L;

    /**
     * key
     */
    private String constantKey;

    /**
     * 值
     */
    private String constantValue;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否启用
     */
    private Integer enable;

    public SpiderConstantVo() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("constantKey", constantKey)
                .append("constantValue", constantValue)
                .append("remark", remark)
                .append("enable", enable)
                .toString();
    }


    public static void main(String[] args) {
        try {
            Blob blob = new SerialBlob("12312321312321312312".getBytes("GBK"));
            String str = new String(blob.getBytes(1, (int) blob.length()));
            System.out.println(blob);
            System.out.println(str);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
