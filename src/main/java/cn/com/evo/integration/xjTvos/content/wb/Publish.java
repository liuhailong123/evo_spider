/**
 * Publish.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.xjTvos.content.wb;

public class Publish  implements java.io.Serializable {
    private java.lang.String LSPID;

    private java.lang.String AMSID;

    private java.lang.String sequence;

    private java.lang.String assetId;

    private java.lang.String providerID;

    private java.lang.String PPVId;

    private java.lang.String columnId;

    public Publish() {
    }

    public Publish(
           java.lang.String LSPID,
           java.lang.String AMSID,
           java.lang.String sequence,
           java.lang.String assetId,
           java.lang.String providerID,
           java.lang.String PPVId,
           java.lang.String columnId) {
           this.LSPID = LSPID;
           this.AMSID = AMSID;
           this.sequence = sequence;
           this.assetId = assetId;
           this.providerID = providerID;
           this.PPVId = PPVId;
           this.columnId = columnId;
    }


    /**
     * Gets the LSPID value for this Publish.
     * 
     * @return LSPID
     */
    public java.lang.String getLSPID() {
        return LSPID;
    }


    /**
     * Sets the LSPID value for this Publish.
     * 
     * @param LSPID
     */
    public void setLSPID(java.lang.String LSPID) {
        this.LSPID = LSPID;
    }


    /**
     * Gets the AMSID value for this Publish.
     * 
     * @return AMSID
     */
    public java.lang.String getAMSID() {
        return AMSID;
    }


    /**
     * Sets the AMSID value for this Publish.
     * 
     * @param AMSID
     */
    public void setAMSID(java.lang.String AMSID) {
        this.AMSID = AMSID;
    }


    /**
     * Gets the sequence value for this Publish.
     * 
     * @return sequence
     */
    public java.lang.String getSequence() {
        return sequence;
    }


    /**
     * Sets the sequence value for this Publish.
     * 
     * @param sequence
     */
    public void setSequence(java.lang.String sequence) {
        this.sequence = sequence;
    }


    /**
     * Gets the assetId value for this Publish.
     * 
     * @return assetId
     */
    public java.lang.String getAssetId() {
        return assetId;
    }


    /**
     * Sets the assetId value for this Publish.
     * 
     * @param assetId
     */
    public void setAssetId(java.lang.String assetId) {
        this.assetId = assetId;
    }


    /**
     * Gets the providerID value for this Publish.
     * 
     * @return providerID
     */
    public java.lang.String getProviderID() {
        return providerID;
    }


    /**
     * Sets the providerID value for this Publish.
     * 
     * @param providerID
     */
    public void setProviderID(java.lang.String providerID) {
        this.providerID = providerID;
    }


    /**
     * Gets the PPVId value for this Publish.
     * 
     * @return PPVId
     */
    public java.lang.String getPPVId() {
        return PPVId;
    }


    /**
     * Sets the PPVId value for this Publish.
     * 
     * @param PPVId
     */
    public void setPPVId(java.lang.String PPVId) {
        this.PPVId = PPVId;
    }


    /**
     * Gets the columnId value for this Publish.
     * 
     * @return columnId
     */
    public java.lang.String getColumnId() {
        return columnId;
    }


    /**
     * Sets the columnId value for this Publish.
     * 
     * @param columnId
     */
    public void setColumnId(java.lang.String columnId) {
        this.columnId = columnId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Publish)) return false;
        Publish other = (Publish) obj;
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
            ((this.assetId==null && other.getAssetId()==null) || 
             (this.assetId!=null &&
              this.assetId.equals(other.getAssetId()))) &&
            ((this.providerID==null && other.getProviderID()==null) || 
             (this.providerID!=null &&
              this.providerID.equals(other.getProviderID()))) &&
            ((this.PPVId==null && other.getPPVId()==null) || 
             (this.PPVId!=null &&
              this.PPVId.equals(other.getPPVId()))) &&
            ((this.columnId==null && other.getColumnId()==null) || 
             (this.columnId!=null &&
              this.columnId.equals(other.getColumnId())));
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
        if (getAssetId() != null) {
            _hashCode += getAssetId().hashCode();
        }
        if (getProviderID() != null) {
            _hashCode += getProviderID().hashCode();
        }
        if (getPPVId() != null) {
            _hashCode += getPPVId().hashCode();
        }
        if (getColumnId() != null) {
            _hashCode += getColumnId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Publish.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">Publish"));
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
        elemField.setFieldName("assetId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("providerID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProviderID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PPVId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PPVId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("columnId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ColumnId"));
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
