/**
 * BindProduct.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.xjTvos.content.wb;

public class BindProduct  implements java.io.Serializable {
    private java.lang.String LSPID;

    private java.lang.String AMSID;

    private java.lang.String sequence;

    private java.lang.String operate;

    private java.lang.String playId;

    private java.lang.String PPVId;

    public BindProduct() {
    }

    public BindProduct(
           java.lang.String LSPID,
           java.lang.String AMSID,
           java.lang.String sequence,
           java.lang.String operate,
           java.lang.String playId,
           java.lang.String PPVId) {
           this.LSPID = LSPID;
           this.AMSID = AMSID;
           this.sequence = sequence;
           this.operate = operate;
           this.playId = playId;
           this.PPVId = PPVId;
    }


    /**
     * Gets the LSPID value for this BindProduct.
     * 
     * @return LSPID
     */
    public java.lang.String getLSPID() {
        return LSPID;
    }


    /**
     * Sets the LSPID value for this BindProduct.
     * 
     * @param LSPID
     */
    public void setLSPID(java.lang.String LSPID) {
        this.LSPID = LSPID;
    }


    /**
     * Gets the AMSID value for this BindProduct.
     * 
     * @return AMSID
     */
    public java.lang.String getAMSID() {
        return AMSID;
    }


    /**
     * Sets the AMSID value for this BindProduct.
     * 
     * @param AMSID
     */
    public void setAMSID(java.lang.String AMSID) {
        this.AMSID = AMSID;
    }


    /**
     * Gets the sequence value for this BindProduct.
     * 
     * @return sequence
     */
    public java.lang.String getSequence() {
        return sequence;
    }


    /**
     * Sets the sequence value for this BindProduct.
     * 
     * @param sequence
     */
    public void setSequence(java.lang.String sequence) {
        this.sequence = sequence;
    }


    /**
     * Gets the operate value for this BindProduct.
     * 
     * @return operate
     */
    public java.lang.String getOperate() {
        return operate;
    }


    /**
     * Sets the operate value for this BindProduct.
     * 
     * @param operate
     */
    public void setOperate(java.lang.String operate) {
        this.operate = operate;
    }


    /**
     * Gets the playId value for this BindProduct.
     * 
     * @return playId
     */
    public java.lang.String getPlayId() {
        return playId;
    }


    /**
     * Sets the playId value for this BindProduct.
     * 
     * @param playId
     */
    public void setPlayId(java.lang.String playId) {
        this.playId = playId;
    }


    /**
     * Gets the PPVId value for this BindProduct.
     * 
     * @return PPVId
     */
    public java.lang.String getPPVId() {
        return PPVId;
    }


    /**
     * Sets the PPVId value for this BindProduct.
     * 
     * @param PPVId
     */
    public void setPPVId(java.lang.String PPVId) {
        this.PPVId = PPVId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BindProduct)) return false;
        BindProduct other = (BindProduct) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.LSPID==null && other.getLSPID()==null) || 
             (this.LSPID!=null &&
              this.LSPID.equals(other.getLSPID()))) &&
            ((this.AMSID==null && other.getAMSID()==null) || 
             (this.AMSID!=null &&
              this.AMSID.equals(other.getAMSID()))) &&
            ((this.sequence==null && other.getSequence()==null) || 
             (this.sequence!=null &&
              this.sequence.equals(other.getSequence()))) &&
            ((this.operate==null && other.getOperate()==null) || 
             (this.operate!=null &&
              this.operate.equals(other.getOperate()))) &&
            ((this.playId==null && other.getPlayId()==null) || 
             (this.playId!=null &&
              this.playId.equals(other.getPlayId()))) &&
            ((this.PPVId==null && other.getPPVId()==null) || 
             (this.PPVId!=null &&
              this.PPVId.equals(other.getPPVId())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getLSPID() != null) {
            _hashCode += getLSPID().hashCode();
        }
        if (getAMSID() != null) {
            _hashCode += getAMSID().hashCode();
        }
        if (getSequence() != null) {
            _hashCode += getSequence().hashCode();
        }
        if (getOperate() != null) {
            _hashCode += getOperate().hashCode();
        }
        if (getPlayId() != null) {
            _hashCode += getPlayId().hashCode();
        }
        if (getPPVId() != null) {
            _hashCode += getPPVId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BindProduct.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">BindProduct"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LSPID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LSPID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AMSID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AMSID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sequence");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Sequence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("operate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Operate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("playId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PlayId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PPVId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PPVId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
