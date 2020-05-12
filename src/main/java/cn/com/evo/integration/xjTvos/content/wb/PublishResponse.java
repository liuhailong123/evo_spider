/**
 * PublishResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.xjTvos.content.wb;

public class PublishResponse  implements java.io.Serializable {
    private java.lang.String LSPID;

    private java.lang.String AMSID;

    private java.lang.String sequence;

    private int resultCode;

    private java.lang.String resultMsg;

    public PublishResponse() {
    }

    public PublishResponse(
           java.lang.String LSPID,
           java.lang.String AMSID,
           java.lang.String sequence,
           int resultCode,
           java.lang.String resultMsg) {
           this.LSPID = LSPID;
           this.AMSID = AMSID;
           this.sequence = sequence;
           this.resultCode = resultCode;
           this.resultMsg = resultMsg;
    }


    /**
     * Gets the LSPID value for this PublishResponse.
     * 
     * @return LSPID
     */
    public java.lang.String getLSPID() {
        return LSPID;
    }


    /**
     * Sets the LSPID value for this PublishResponse.
     * 
     * @param LSPID
     */
    public void setLSPID(java.lang.String LSPID) {
        this.LSPID = LSPID;
    }


    /**
     * Gets the AMSID value for this PublishResponse.
     * 
     * @return AMSID
     */
    public java.lang.String getAMSID() {
        return AMSID;
    }


    /**
     * Sets the AMSID value for this PublishResponse.
     * 
     * @param AMSID
     */
    public void setAMSID(java.lang.String AMSID) {
        this.AMSID = AMSID;
    }


    /**
     * Gets the sequence value for this PublishResponse.
     * 
     * @return sequence
     */
    public java.lang.String getSequence() {
        return sequence;
    }


    /**
     * Sets the sequence value for this PublishResponse.
     * 
     * @param sequence
     */
    public void setSequence(java.lang.String sequence) {
        this.sequence = sequence;
    }


    /**
     * Gets the resultCode value for this PublishResponse.
     * 
     * @return resultCode
     */
    public int getResultCode() {
        return resultCode;
    }


    /**
     * Sets the resultCode value for this PublishResponse.
     * 
     * @param resultCode
     */
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }


    /**
     * Gets the resultMsg value for this PublishResponse.
     * 
     * @return resultMsg
     */
    public java.lang.String getResultMsg() {
        return resultMsg;
    }


    /**
     * Sets the resultMsg value for this PublishResponse.
     * 
     * @param resultMsg
     */
    public void setResultMsg(java.lang.String resultMsg) {
        this.resultMsg = resultMsg;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PublishResponse)) return false;
        PublishResponse other = (PublishResponse) obj;
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
            this.resultCode == other.getResultCode() &&
            ((this.resultMsg==null && other.getResultMsg()==null) || 
             (this.resultMsg!=null &&
              this.resultMsg.equals(other.getResultMsg())));
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
        _hashCode += getResultCode();
        if (getResultMsg() != null) {
            _hashCode += getResultMsg().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PublishResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">PublishResponse"));
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
        elemField.setFieldName("resultCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ResultCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ResultMsg"));
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
