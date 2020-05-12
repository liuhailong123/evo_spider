/**
 * IngestAssetService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.com.evo.integration.nxsp.content.ws;

public interface IngestAssetService_PortType extends java.rmi.Remote {
    public IngestAssetResponse ingestAsset(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String ftpPath, java.lang.String adiFileName, java.lang.String notifyUrl, javax.xml.rpc.holders.IntHolder resultCode, javax.xml.rpc.holders.StringHolder resultMsg) throws java.rmi.RemoteException;
    public QueryStateResponse queryState(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String assetId, javax.xml.rpc.holders.StringHolder playId, javax.xml.rpc.holders.IntHolder stateCode, javax.xml.rpc.holders.StringHolder stateMsg) throws java.rmi.RemoteException;
    public UnpublishResponse unpublish(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String providerID, java.lang.String assetId, javax.xml.rpc.holders.IntHolder resultCode, javax.xml.rpc.holders.StringHolder resultMsg) throws java.rmi.RemoteException;
    public DeleteAssetResponse deleteAsset(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String providerID, java.lang.String assetId, javax.xml.rpc.holders.IntHolder resultCode, javax.xml.rpc.holders.StringHolder resultMsg) throws java.rmi.RemoteException;
    public PublishResponse publish(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String assetId, java.lang.String providerID, java.lang.String PPVId, java.lang.String columnId, javax.xml.rpc.holders.IntHolder resultCode, javax.xml.rpc.holders.StringHolder resultMsg) throws java.rmi.RemoteException;
    public BindProductResponse bindProduct(javax.xml.rpc.holders.StringHolder LSPID, javax.xml.rpc.holders.StringHolder AMSID, javax.xml.rpc.holders.StringHolder sequence, java.lang.String operate, java.lang.String playId, java.lang.String PPVId, javax.xml.rpc.holders.IntHolder resultCode, javax.xml.rpc.holders.StringHolder resultMsg) throws java.rmi.RemoteException;
}
