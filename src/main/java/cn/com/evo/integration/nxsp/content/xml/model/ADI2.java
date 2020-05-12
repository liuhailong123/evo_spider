package cn.com.evo.integration.nxsp.content.xml.model;

import cn.com.evo.integration.common.utils.BeanToXml;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rf
 * @date 2019/5/8
 */
@XmlRootElement(name = "adi:ADI2")
@XmlType(propOrder = {"openGroupAssets", "replaceGroupAsset", "dropGroupAsset"
, "addMetadataAsset", "replaceMetadataAsset", "removeMetadataAsset",
        "acceptContentAsset", "replaceContentAsset", "destroyContentAsset",
"associateContents", "associateGroups"})
public class ADI2 implements Serializable {

    private String xmlns = "http://www.cablelabs.com/VODSchema/default";

    private String adi = "http://www.cablelabs.com/VODSchema/adi";

    private String vod = "http://www.cablelabs.com/VODSchema/vod";

    /**
     *
     */
    private List<GroupAsset> openGroupAssets;
    private List<GroupAsset> replaceGroupAsset;
    private List<GroupAsset> dropGroupAsset;

    private List<MetadataAsset> addMetadataAsset;
    private List<MetadataAsset> replaceMetadataAsset;
    private List<MetadataAsset> removeMetadataAsset;

    private List<ContentAsset> acceptContentAsset;
    private List<ContentAsset> replaceContentAsset;
    private List<ContentAsset> destroyContentAsset;

    private List<AssociateContent> associateContents;

    private List<AssociateGroup> associateGroups;

    public ADI2() {
    }

    @XmlElement(name = "adi:AssociateContent")
    public List<AssociateContent> getAssociateContents() {
        return associateContents;
    }

    public void setAssociateContents(List<AssociateContent> associateContents) {
        this.associateContents = associateContents;
    }

    @XmlElement(name = "adi:AssociateGroup")
    public List<AssociateGroup> getAssociateGroups() {
        return associateGroups;
    }

    public void setAssociateGroups(List<AssociateGroup> associateGroups) {
        this.associateGroups = associateGroups;
    }

    @XmlElement(name = "adi:OpenGroupAsset")
    public List<GroupAsset> getOpenGroupAssets() {

        return openGroupAssets;
    }

    public void setOpenGroupAssets(List<GroupAsset> openGroupAssets) {
        this.openGroupAssets = openGroupAssets;
    }

    @XmlElement(name = "adi:ReplaceGroupAsset")
    public List<GroupAsset> getReplaceGroupAsset() {
        return replaceGroupAsset;
    }

    public void setReplaceGroupAsset(List<GroupAsset> replaceGroupAsset) {
        this.replaceGroupAsset = replaceGroupAsset;
    }
    @XmlElement(name = "adi:DropGroupAsset")
    public List<GroupAsset> getDropGroupAsset() {
        return dropGroupAsset;
    }

    public void setDropGroupAsset(List<GroupAsset> dropGroupAsset) {
        this.dropGroupAsset = dropGroupAsset;
    }
    @XmlElement(name = "adi:AddMetadataAsset")
    public List<MetadataAsset> getAddMetadataAsset() {
        return addMetadataAsset;
    }

    public void setAddMetadataAsset(List<MetadataAsset> addMetadataAsset) {
        this.addMetadataAsset = addMetadataAsset;
    }
    @XmlElement(name = "adi:ReplaceMetadataAsset")
    public List<MetadataAsset> getReplaceMetadataAsset() {
        return replaceMetadataAsset;
    }

    public void setReplaceMetadataAsset(List<MetadataAsset> replaceMetadataAsset) {
        this.replaceMetadataAsset = replaceMetadataAsset;
    }
    @XmlElement(name = "adi:RemoveMetadataAsset")
    public List<MetadataAsset> getRemoveMetadataAsset() {
        return removeMetadataAsset;
    }

    public void setRemoveMetadataAsset(List<MetadataAsset> removeMetadataAsset) {
        this.removeMetadataAsset = removeMetadataAsset;
    }
    @XmlElement(name = "adi:AcceptContentAsset")
    public List<ContentAsset> getAcceptContentAsset() {
        return acceptContentAsset;
    }

    public void setAcceptContentAsset(List<ContentAsset> acceptContentAsset) {
        this.acceptContentAsset = acceptContentAsset;
    }
    @XmlElement(name = "adi:ReplaceContentAsset")
    public List<ContentAsset> getReplaceContentAsset() {
        return replaceContentAsset;
    }

    public void setReplaceContentAsset(List<ContentAsset> replaceContentAsset) {
        this.replaceContentAsset = replaceContentAsset;
    }
    @XmlElement(name = "adi:DestroyContentAsset")
    public List<ContentAsset> getDestroyContentAsset() {
        return destroyContentAsset;
    }

    public void setDestroyContentAsset(List<ContentAsset> destroyContentAsset) {
        this.destroyContentAsset = destroyContentAsset;
    }

    @XmlAttribute(name = "xmlns")
    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    @XmlAttribute(name = "xmlns:adi")
    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    @XmlAttribute(name = "xmlns:vod")
    public String getVod() {
        return vod;
    }

    public void setVod(String vod) {
        this.vod = vod;
    }
}
