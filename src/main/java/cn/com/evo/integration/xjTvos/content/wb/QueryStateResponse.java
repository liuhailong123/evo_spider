/**
 * QueryStateResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.xjTvos.content.wb;

public class QueryStateResponse  implements java.io.Serializable {
    private java.lang.String LSPID;

    private java.lang.String AMSID;

    private java.lang.String playId;

    private java.lang.String sequence;

    private int stateCode;

    private java.lang.String stateMsg;

    public QueryStateResponse() {
    }

    public QueryStateResponse(
           java.lang.String LSPID,
           java.lang.String AMSID,
           java.lang.String playId,
           java.lang.String sequence,
           int stateCode,
           java.lang.String stateMsg) {
           this.LSPID = LSPID;
           this.AMSID = AMSID;
           this.playId = playId;
           this.sequence = sequence;
           this.stateCode = stateCode;
           this.stateMsg = stateMsg;
    }


    /**
     * Gets the LSPID value for this QueryStateResponse.
     * 
     * @return LSPID
     */
    public java.lang.String getLSPID() {
        return LSPID;
    }


    /**
     * Sets the LSPID value for this QueryStateResponse.
     * 
     * @param LSPID
     */
    public void setLSPID(java.lang.String LSPID) {
        this.LSPID = LSPID;
    }


    /**
     * Gets the AMSID value for this QueryStateResponse.
     * 
     * @return AMSID
     */
    public java.lang.String getAMSID() {
        return AMSID;
    }


    /**
     * Sets the AMSID value for this QueryStateResponse.
     * 
     * @param AMSID
     */
    public void setAMSID(java.lang.String AMSID) {
        this.AMSID = AMSID;
    }


    /**
     * Gets the playId value for this QueryStateResponse.
     * 
     * @return playId
     */
    public java.lang.String getPlayId() {
        return playId;
    }


    /**
     * Sets the playId value for this QueryStateResponse.
     * 
     * @param playId
     */
    public void setPlayId(java.lang.String playId) {
        this.playId = playId;
    }


    /**
     * Gets the sequence value for this QueryStateResponse.
     * 
     * @return sequence
     */
    public java.lang.String getSequence() {
        return sequence;
    }


    /**
     * Sets the sequence value for this QueryStateResponse.
     * 
     * @param sequence
     */
    public void setSequence(java.lang.String sequence) {
        this.sequence = sequence;
    }


    /**
     * Gets the stateCode value for this QueryStateResponse.
     * 
     * @return stateCode
     */
    public int getStateCode() {
        return stateCode;
    }


    /**
     * Sets the stateCode value for this QueryStateResponse.
     * 
     * @param stateCode
     */
    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }


    /**
     * Gets the stateMsg value for this QueryStateResponse.
     * 
     * @return stateMsg
     */
    public java.lang.String getStateMsg() {
        return stateMsg;
    }


    /**
     * Sets the stateMsg value for this QueryStateResponse.
     * 
     * @param stateMsg
     */
    public void setStateMsg(java.lang.String stateMsg) {
        this.stateMsg = stateMsg;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof QueryStateResponse)) return false;
        QueryStateResponse other = (QueryStateResponse) obj;
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
            ((this.playId==null && other.getPlayId()==null) || 
             (this.playId!=null &&
              this.playId.equals(other.getPlayId()))) &&
            ((this.sequence==null && other.getSequence()==null) || 
             (this.sequence!=null &&
              this.sequence.equals(other.getSequence()))) &&
            this.stateCode == other.getStateCode() &&
            ((this.stateMsg==null && other.getStateMsg()==null) || 
             (this.stateMsg!=null &&
              this.stateMsg.equals(other.getStateMsg())));
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
        if (getPlayId() != null) {
            _hashCode += getPlayId().hashCode();
        }
        if (getSequence() != null) {
            _hashCode += getSequence().hashCode();
        }
        _hashCode += getStateCode();
        if (getStateMsg() != null) {
            _hashCode += getStateMsg().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(QueryStateResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">QueryStateResponse"));
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
        elemField.setFieldName("playId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PlayId"));
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
        elemField.setFieldName("stateCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StateCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stateMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "StateMsg"));
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
