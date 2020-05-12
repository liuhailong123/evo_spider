/**
 * IngestAsset.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.xjTvos.content.wb;

public class IngestAsset  implements java.io.Serializable {
    private java.lang.String LSPID;

    private java.lang.String AMSID;

    private java.lang.String sequence;

    private java.lang.String ftpPath;

    private java.lang.String adiFileName;

    private java.lang.String notifyUrl;

    public IngestAsset() {
    }

    public IngestAsset(
           java.lang.String LSPID,
           java.lang.String AMSID,
           java.lang.String sequence,
           java.lang.String ftpPath,
           java.lang.String adiFileName,
           java.lang.String notifyUrl) {
           this.LSPID = LSPID;
           this.AMSID = AMSID;
           this.sequence = sequence;
           this.ftpPath = ftpPath;
           this.adiFileName = adiFileName;
           this.notifyUrl = notifyUrl;
    }


    /**
     * Gets the LSPID value for this IngestAsset.
     * 
     * @return LSPID
     */
    public java.lang.String getLSPID() {
        return LSPID;
    }


    /**
     * Sets the LSPID value for this IngestAsset.
     * 
     * @param LSPID
     */
    public void setLSPID(java.lang.String LSPID) {
        this.LSPID = LSPID;
    }


    /**
     * Gets the AMSID value for this IngestAsset.
     * 
     * @return AMSID
     */
    public java.lang.String getAMSID() {
        return AMSID;
    }


    /**
     * Sets the AMSID value for this IngestAsset.
     * 
     * @param AMSID
     */
    public void setAMSID(java.lang.String AMSID) {
        this.AMSID = AMSID;
    }


    /**
     * Gets the sequence value for this IngestAsset.
     * 
     * @return sequence
     */
    public java.lang.String getSequence() {
        return sequence;
    }


    /**
     * Sets the sequence value for this IngestAsset.
     * 
     * @param sequence
     */
    public void setSequence(java.lang.String sequence) {
        this.sequence = sequence;
    }


    /**
     * Gets the ftpPath value for this IngestAsset.
     * 
     * @return ftpPath
     */
    public java.lang.String getFtpPath() {
        return ftpPath;
    }


    /**
     * Sets the ftpPath value for this IngestAsset.
     * 
     * @param ftpPath
     */
    public void setFtpPath(java.lang.String ftpPath) {
        this.ftpPath = ftpPath;
    }


    /**
     * Gets the adiFileName value for this IngestAsset.
     * 
     * @return adiFileName
     */
    public java.lang.String getAdiFileName() {
        return adiFileName;
    }


    /**
     * Sets the adiFileName value for this IngestAsset.
     * 
     * @param adiFileName
     */
    public void setAdiFileName(java.lang.String adiFileName) {
        this.adiFileName = adiFileName;
    }


    /**
     * Gets the notifyUrl value for this IngestAsset.
     * 
     * @return notifyUrl
     */
    public java.lang.String getNotifyUrl() {
        return notifyUrl;
    }


    /**
     * Sets the notifyUrl value for this IngestAsset.
     * 
     * @param notifyUrl
     */
    public void setNotifyUrl(java.lang.String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IngestAsset)) return false;
        IngestAsset other = (IngestAsset) obj;
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
            ((this.ftpPath==null && other.getFtpPath()==null) || 
             (this.ftpPath!=null &&
              this.ftpPath.equals(other.getFtpPath()))) &&
            ((this.adiFileName==null && other.getAdiFileName()==null) || 
             (this.adiFileName!=null &&
              this.adiFileName.equals(other.getAdiFileName()))) &&
            ((this.notifyUrl==null && other.getNotifyUrl()==null) || 
             (this.notifyUrl!=null &&
              this.notifyUrl.equals(other.getNotifyUrl())));
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
        if (getFtpPath() != null) {
            _hashCode += getFtpPath().hashCode();
        }
        if (getAdiFileName() != null) {
            _hashCode += getAdiFileName().hashCode();
        }
        if (getNotifyUrl() != null) {
            _hashCode += getNotifyUrl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IngestAsset.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", ">ing:IngestAsset"));
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
        elemField.setFieldName("ftpPath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FtpPath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adiFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AdiFileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notifyUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NotifyUrl"));
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
