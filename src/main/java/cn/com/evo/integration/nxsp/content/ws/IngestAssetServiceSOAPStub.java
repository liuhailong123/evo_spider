/**
 * IngestAssetServiceSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.nxsp.content.ws;

public class IngestAssetServiceSOAPStub extends org.apache.axis.client.Stub implements cn.com.evo.integration.nxsp.content.ws.IngestAssetService_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[6];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("IngestAsset");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LSPID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AMSID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Sequence"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "FtpPath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AdiFileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "NotifyUrl"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultMsg"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("QueryState");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LSPID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AMSID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Sequence"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AssetId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PlayId"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "StateCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "StateMsg"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Unpublish");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LSPID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AMSID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Sequence"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProviderID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AssetId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultMsg"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("DeleteAsset");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LSPID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AMSID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Sequence"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProviderID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AssetId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultMsg"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Publish");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LSPID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AMSID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Sequence"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AssetId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ProviderID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PPVId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ColumnId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultMsg"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("BindProduct");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "LSPID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "AMSID"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Sequence"), org.apache.axis.description.ParameterDesc.INOUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "Operate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PlayId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "PPVId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultCode"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "ResultMsg"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

    }

    public IngestAssetServiceSOAPStub() throws org.apache.axis.AxisFault {
        this(null);
    }

    public IngestAssetServiceSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        this(service);
        super.cachedEndpoint = endpointURL;
    }

    public IngestAssetServiceSOAPStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.1");
    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public IngestAssetResponse ingestAsset(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String ftpPath, java.lang.String adiFileName, java.lang.String notifyUrl, javax.xml.rpc.holders.IntHolder resultCode, javax.xml.rpc.holders.StringHolder resultMsg) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:IngestAsset"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {
            java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LSPID.value, AMSID.value, sequence.value, ftpPath, adiFileName, notifyUrl});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                System.out.println("打印注入媒资返回参数");
                extractAttachments(_call);
                java.util.Map _output;
                _output = _call.getOutputParams();
                System.out.println(_output);
                try {
                    LSPID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "LSPID"));
                    System.out.println(LSPID.value);
                } catch (java.lang.Exception _exception) {
                    LSPID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "LSPID")), java.lang.String.class);
                }
                try {
                    AMSID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "AMSID"));
                    System.out.println(AMSID.value);
                } catch (java.lang.Exception _exception) {
                    AMSID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AMSID")), java.lang.String.class);
                }
                try {
                    sequence.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "Sequence"));
                    System.out.println(sequence.value);
                } catch (java.lang.Exception _exception) {
                    sequence.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Sequence")), java.lang.String.class);
                }
                try {
                    resultCode.value = ((java.lang.Integer) _output.get(new javax.xml.namespace.QName("", "ResultCode"))).intValue();
                    System.out.println(resultCode.value);
                } catch (java.lang.Exception _exception) {
                    resultCode.value = ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResultCode")), int.class)).intValue();
                }
                try {
                    resultMsg.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "ResultMsg"));
                    System.out.println(resultMsg.value);
                } catch (java.lang.Exception _exception) {
                    resultMsg.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResultMsg")), java.lang.String.class);
                }

                IngestAssetResponse response = new IngestAssetResponse(LSPID.value, AMSID.value, sequence.value, resultCode.value, resultMsg.value);
                return response;
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public QueryStateResponse queryState(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String assetId, javax.xml.rpc.holders.StringHolder playId, javax.xml.rpc.holders.IntHolder stateCode, javax.xml.rpc.holders.StringHolder stateMsg) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:QueryState"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LSPID.value, AMSID.value, sequence.value, assetId});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                System.out.println("打印查询状态返回参数");
                extractAttachments(_call);
                java.util.Map _output;
                _output = _call.getOutputParams();
                System.out.println(_output);
                try {
                    LSPID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "LSPID"));
                    System.out.println(LSPID.value);
                } catch (java.lang.Exception _exception) {
                    LSPID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "LSPID")), java.lang.String.class);
                }
                try {
                    AMSID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "AMSID"));
                    System.out.println(AMSID.value);
                } catch (java.lang.Exception _exception) {
                    AMSID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AMSID")), java.lang.String.class);
                }
                try {
                    sequence.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "Sequence"));
                    System.out.println(sequence.value);
                } catch (java.lang.Exception _exception) {
                    sequence.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Sequence")), java.lang.String.class);
                }
                try {
                    playId.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "PlayId"));
                    System.out.println(playId.value);
                } catch (java.lang.Exception _exception) {
                    playId.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "PlayId")), java.lang.String.class);
                }
                try {
                    stateCode.value = ((java.lang.Integer) _output.get(new javax.xml.namespace.QName("", "StateCode"))).intValue();
                    System.out.println(stateCode.value);
                } catch (java.lang.Exception _exception) {
                    stateCode.value = ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "StateCode")), int.class)).intValue();
                }
                try {
                    stateMsg.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "StateMsg"));
                    System.out.println(stateMsg.value);
                } catch (java.lang.Exception _exception) {
                    stateMsg.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "StateMsg")), java.lang.String.class);
                }
                QueryStateResponse response = new QueryStateResponse(LSPID.value,AMSID.value,sequence.value,playId.value, stateCode.value, stateMsg.value);
                return response;
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public UnpublishResponse unpublish(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String providerID, java.lang.String assetId, javax.xml.rpc.holders.IntHolder resultCode, javax.xml.rpc.holders.StringHolder resultMsg) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:Unpublish"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LSPID.value, AMSID.value, sequence.value, providerID, assetId});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                System.out.println("打印取消发布返回参数");
                extractAttachments(_call);
                java.util.Map _output;
                _output = _call.getOutputParams();
                System.out.println(_output);
                try {
                    LSPID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "LSPID"));
                    System.out.println(LSPID.value);
                } catch (java.lang.Exception _exception) {
                    LSPID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "LSPID")), java.lang.String.class);
                }
                try {
                    AMSID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "AMSID"));
                    System.out.println(AMSID.value);
                } catch (java.lang.Exception _exception) {
                    AMSID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AMSID")), java.lang.String.class);
                }
                try {
                    sequence.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "Sequence"));
                    System.out.println(sequence.value);
                } catch (java.lang.Exception _exception) {
                    sequence.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Sequence")), java.lang.String.class);
                }
                try {
                    resultCode.value = ((java.lang.Integer) _output.get(new javax.xml.namespace.QName("", "ResultCode"))).intValue();
                    System.out.println(resultCode.value);
                } catch (java.lang.Exception _exception) {
                    resultCode.value = ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResultCode")), int.class)).intValue();
                }
                try {
                    resultMsg.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "ResultMsg"));
                    System.out.println(resultMsg.value);
                } catch (java.lang.Exception _exception) {
                    resultMsg.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResultMsg")), java.lang.String.class);
                }
                UnpublishResponse response = new UnpublishResponse(LSPID.value, AMSID.value, sequence.value, resultCode.value, resultMsg.value);
                return response;
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public DeleteAssetResponse deleteAsset(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String providerID, java.lang.String assetId, javax.xml.rpc.holders.IntHolder resultCode, javax.xml.rpc.holders.StringHolder resultMsg) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:DeleteAsset"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LSPID.value, AMSID.value, sequence.value, providerID, assetId});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                System.out.println("打印删除媒资返回参数");
                extractAttachments(_call);
                java.util.Map _output;
                _output = _call.getOutputParams();
                System.out.println(_output);
                try {
                    LSPID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "LSPID"));
                    System.out.println(LSPID.value);
                } catch (java.lang.Exception _exception) {
                    LSPID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "LSPID")), java.lang.String.class);
                }
                try {
                    AMSID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "AMSID"));
                    System.out.println(AMSID.value);
                } catch (java.lang.Exception _exception) {
                    AMSID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AMSID")), java.lang.String.class);
                }
                try {
                    sequence.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "Sequence"));
                    System.out.println(sequence.value);
                } catch (java.lang.Exception _exception) {
                    sequence.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Sequence")), java.lang.String.class);
                }
                try {
                    resultCode.value = ((java.lang.Integer) _output.get(new javax.xml.namespace.QName("", "ResultCode"))).intValue();
                    System.out.println(resultCode.value);
                } catch (java.lang.Exception _exception) {
                    resultCode.value = ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResultCode")), int.class)).intValue();
                }
                try {
                    resultMsg.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "ResultMsg"));
                    System.out.println(resultMsg.value);
                } catch (java.lang.Exception _exception) {
                    resultMsg.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResultMsg")), java.lang.String.class);
                }
                DeleteAssetResponse response = new DeleteAssetResponse(LSPID.value, AMSID.value, sequence.value, resultCode.value, resultMsg.value);
                return response;
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public PublishResponse publish(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String assetId, java.lang.String providerID, java.lang.String PPVId, java.lang.String columnId, javax.xml.rpc.holders.IntHolder resultCode, javax.xml.rpc.holders.StringHolder resultMsg) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:Publish"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LSPID.value, AMSID.value, sequence.value, assetId, providerID, PPVId, columnId});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                System.out.println("打印发布返回参数");
                extractAttachments(_call);
                java.util.Map _output;
                _output = _call.getOutputParams();
                System.out.println(_output);
                try {
                    LSPID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "LSPID"));
                    System.out.println(LSPID.value);
                } catch (java.lang.Exception _exception) {
                    LSPID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "LSPID")), java.lang.String.class);
                }
                try {
                    AMSID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "AMSID"));
                    System.out.println(AMSID.value);
                } catch (java.lang.Exception _exception) {
                    AMSID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AMSID")), java.lang.String.class);
                }
                try {
                    sequence.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "Sequence"));
                    System.out.println(sequence.value);
                } catch (java.lang.Exception _exception) {
                    sequence.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Sequence")), java.lang.String.class);
                }
                try {
                    resultCode.value = ((java.lang.Integer) _output.get(new javax.xml.namespace.QName("", "ResultCode"))).intValue();
                    System.out.println(resultCode.value);
                } catch (java.lang.Exception _exception) {
                    resultCode.value = ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResultCode")), int.class)).intValue();
                }
                try {
                    resultMsg.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "ResultMsg"));
                    System.out.println(resultMsg.value);
                } catch (java.lang.Exception _exception) {
                    resultMsg.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResultMsg")), java.lang.String.class);
                }
                PublishResponse response = new PublishResponse(LSPID.value, AMSID.value, sequence.value, resultCode.value, resultMsg.value);
                return response;
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

    public BindProductResponse bindProduct(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String operate, java.lang.String playId, java.lang.String PPVId, javax.xml.rpc.holders.IntHolder resultCode, javax.xml.rpc.holders.StringHolder resultMsg) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://IngestAssetService.homed.ipanel.cn", "ing:BindProduct"));

        setRequestHeaders(_call);
        setAttachments(_call);
        try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {LSPID.value, AMSID.value, sequence.value, operate, playId, PPVId});

            if (_resp instanceof java.rmi.RemoteException) {
                throw (java.rmi.RemoteException)_resp;
            }
            else {
                System.out.println("打印绑定产品返回参数");
                extractAttachments(_call);
                java.util.Map _output;
                _output = _call.getOutputParams();
                System.out.println(_output);
                try {
                    LSPID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "LSPID"));
                    System.out.println(LSPID.value);
                } catch (java.lang.Exception _exception) {
                    LSPID.value = (java.lang. String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "LSPID")), java.lang.String.class);
                }
                try {
                    AMSID.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "AMSID"));
                    System.out.println(AMSID.value);
                } catch (java.lang.Exception _exception) {
                    AMSID.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "AMSID")), java.lang.String.class);
                }
                try {
                    sequence.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "Sequence"));
                    System.out.println(sequence.value);
                } catch (java.lang.Exception _exception) {
                    sequence.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "Sequence")), java.lang.String.class);
                }
                try {
                    resultCode.value = ((java.lang.Integer) _output.get(new javax.xml.namespace.QName("", "ResultCode"))).intValue();
                    System.out.println(resultCode.value);
                } catch (java.lang.Exception _exception) {
                    resultCode.value = ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResultCode")), int.class)).intValue();
                }
                try {
                    resultMsg.value = (java.lang.String) _output.get(new javax.xml.namespace.QName("", "ResultMsg"));
                    System.out.println(resultMsg.value);
                } catch (java.lang.Exception _exception) {
                    resultMsg.value = (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_output.get(new javax.xml.namespace.QName("", "ResultMsg")), java.lang.String.class);
                }
                BindProductResponse response = new BindProductResponse(LSPID.value, AMSID.value, sequence.value, resultCode.value, resultMsg.value);
                return response;
            }
        } catch (org.apache.axis.AxisFault axisFaultException) {
            throw axisFaultException;
        }
    }

}
