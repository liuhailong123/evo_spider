package cn.com.evo.integration.xjdx.content.model;

/**
 * @author rf
 * @date 2019/6/4
 */
@SuppressWarnings("all")
public class ObjectPropertys {
    /**
     * 节目类型 0-电影
     */
    private String Type;
    /**
     * 提供商名称
     */
    private String Provide;
    /**
     * 内容中文名称
     */
    private String Name;
    /**
     * 所属父级名称
     */
    private String SeriesName;
    /**
     * 英文名称
     */
    private String EnglishName;
    /**
     * 授权开始时间
     */
    private String LicensingWindowStart;
    /**
     * 授权结束时间
     */
    private String LicensingWindowEnd;
    /**
     * 授权类型 0-独家、1-非独家
     */
    private String AuthType;
    /**
     * 授权渠道 0-数字电视、1-互联网站、2-移动互联网
     */
    private String AuthChannel;
    /**
     * 公映许可证号
     */
    private String PublicLicenseNo;
    /**
     * 引进许可证号
     */
    private String IssuanceLicenseNo;
    /**
     * 试听节目备案号
     */
    private String ImportLicenseNo;
    /**
     * 视听节目备案号
     */
    private String AudiovisualProgramNo;
    /**
     * IMBD号
     */
    private String IMDBNo;
    /**
     * 节目订购编号
     */
    private String OrderNumber;
    /**
     * 原名
     */
    private String OriginalName;
    /**
     * 索引名称供界面排序
     */
    private String SortName;
    /**
     * 搜索关键字
     */
    private String SearchName;
    /**
     * program的默认类别(Gene)
     */
    private String Genre;
    /**
     * 主演
     */
    private String ActorDisplay;
    /**
     * 主演搜索关键字
     */
    private String ActorSearchName;
    /**
     * 导演
     */
    private String WriterDisplay;
    /**
     * 导演搜索关键字
     */
    private String WriterSearchName;
    /**
     * 制片国家地区
     */
    private String OriginalCountry;
    /**
     * 制作公司
     */
    private String Originalcompany;
    /**
     * 播出平台
     */
    private String PlayPlat;
    /**
     * 首播平台
     */
    private String PremierePlat;
    /**
     * 编剧
     */
    private String ScreenWriter;
    /**
     * 语言
     */
    private String Language;
    /**
     * 上映年份
     */
    private String ReleaseYear;
    /**
     * 首播日期
     */
    private String OrgAirDate;
    /**
     * 新到天数
     */
    private String DisplayAsNew;
    /**
     * 剩余天数
     */
    private String DisplayAsLastChance;
    /**
     * 拷贝保护标志 0-无拷贝保护 1-有拷贝保护
     */
    private String Macrovision;
    /**
     * 节目简介
     */
    private String Description;
    /**
     * 节目定价
     */
    private String PriceTaxIn;
    /**
     * 状态标志 0-失效，-1生效
     */
    private String Status;
    /**
     * 1-VOD
     */
    private String SourceType;
    /**
     * 0-普通VOD  1-子集内容
     */
    private String SeriesFlag;
    /**
     * 子集编号 (当SeriesFlag=1)
     */
    private String SeriesItemNo;
    /**
     * 关键字
     */
    private String Keywords;
    /**
     * 关联标签
     */
    private String Tags;
    /**
     * 票房数据
     */
    private String BoxOffice;
    /**
     * 豆瓣评分
     */
    private String Rate;
    /**
     * 保留字段
     */
    private String Reserve1;
    /**
     * 保留字段
     */
    private String Reserve2;
    /**
     * 保留字段
     */
    private String Reserve3;
    /**
     * 保留字段
     */
    private String Reserve4;
    /**
     * 保留字段
     */
    private String Reserve5;
    /**
     * 存储分发策略要求：
     * 0. 厂商CDN可不要存储本节目（在海量存储中保存，具体视频路径在Movie.OCSURL）
     * 1. 厂商CDN存储本节目
     * >2. 自定义策略（具体对应策略在厂商系统中定义，可以做到部分节点覆盖，或者后拉视频文件…）
     */
    private String StorageType;
    /**
     * 关联内容唯一表示
     */
    private String RMediaCode;
    /**
     * 0-成功
     */
    private String Result;
    /**
     * 错误描述
     */
    private String ErrorDescription;
    /**
     * 节目清晰度标识 0-标清 1-高清 3-4K
     */
    private String DefinitionFlag;

    //片源相关
    /**
     *
     */
    private String FileURL;
    /**
     *
     */
    private String SourceDRMType;
    /**
     *
     */
    private String DestDRMType;
    /**
     *
     */
    private String AudioType;
    /**
     *
     */
    private String ScreenFormat;
    /**
     *
     */
    private String ClosedCaptioning;
    /**
     *
     */
    private String OCSURL;
    /**
     *
     */
    private String Duration;
    /**
     *
     */
    private String VideoType;
    /**
     *
     */
    private String AudioFormat;
    /**
     *
     */
    private String Resolution;
    /**
     *
     */
    private String VideoProfile;
    /**
     *
     */
    private String SystemLayer;
    /**
     *
     */
    private String ServiceType;

    /**
     *更新时间（YYMMDD）  SeriesType =1时必填
     */
    private String UpdateDate;

    /**
     * 含税定价
     */
    private String Price;

    /**
     * VolumnCount 总集数
     */
    private String VolumnCount;

    /**
     * 收视数据
     */
    private String ViewingData;

    /**
     * 移动许可证
     */
    private String MobileLicense;

    private String FileSize;

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String fileSize) {
        this.FileSize = fileSize;
    }

    public String getMobileLicense() {
        return MobileLicense;
    }

    public void setMobileLicense(String mobileLicense) {
        MobileLicense = mobileLicense;
    }

    public String getViewingData() {
        return ViewingData;
    }

    public void setViewingData(String viewingData) {
        ViewingData = viewingData;
    }

    public String getVolumnCount() {
        return VolumnCount;
    }

    public void setVolumnCount(String volumnCount) {
        VolumnCount = volumnCount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }

    public String getFileURL() {
        return FileURL;
    }

    public void setFileURL(String fileURL) {
        FileURL = fileURL;
    }

    public String getSourceDRMType() {
        return SourceDRMType;
    }

    public void setSourceDRMType(String sourceDRMType) {
        SourceDRMType = sourceDRMType;
    }

    public String getDestDRMType() {
        return DestDRMType;
    }

    public void setDestDRMType(String destDRMType) {
        DestDRMType = destDRMType;
    }

    public String getAudioType() {
        return AudioType;
    }

    public void setAudioType(String audioType) {
        AudioType = audioType;
    }

    public String getScreenFormat() {
        return ScreenFormat;
    }

    public void setScreenFormat(String screenFormat) {
        ScreenFormat = screenFormat;
    }

    public String getClosedCaptioning() {
        return ClosedCaptioning;
    }

    public void setClosedCaptioning(String closedCaptioning) {
        ClosedCaptioning = closedCaptioning;
    }

    public String getOCSURL() {
        return OCSURL;
    }

    public void setOCSURL(String OCSURL) {
        this.OCSURL = OCSURL;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getVideoType() {
        return VideoType;
    }

    public void setVideoType(String videoType) {
        VideoType = videoType;
    }

    public String getAudioFormat() {
        return AudioFormat;
    }

    public void setAudioFormat(String audioFormat) {
        AudioFormat = audioFormat;
    }

    public String getResolution() {
        return Resolution;
    }

    public void setResolution(String resolution) {
        Resolution = resolution;
    }

    public String getVideoProfile() {
        return VideoProfile;
    }

    public void setVideoProfile(String videoProfile) {
        VideoProfile = videoProfile;
    }

    public String getSystemLayer() {
        return SystemLayer;
    }

    public void setSystemLayer(String systemLayer) {
        SystemLayer = systemLayer;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getProvide() {
        return Provide;
    }

    public void setProvide(String provide) {
        Provide = provide;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSeriesName() {
        return SeriesName;
    }

    public void setSeriesName(String seriesName) {
        SeriesName = seriesName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public String getLicensingWindowStart() {
        return LicensingWindowStart;
    }

    public void setLicensingWindowStart(String licensingWindowStart) {
        LicensingWindowStart = licensingWindowStart;
    }

    public String getLicensingWindowEnd() {
        return LicensingWindowEnd;
    }

    public void setLicensingWindowEnd(String licensingWindowEnd) {
        LicensingWindowEnd = licensingWindowEnd;
    }

    public String getAuthType() {
        return AuthType;
    }

    public void setAuthType(String authType) {
        AuthType = authType;
    }

    public String getAuthChannel() {
        return AuthChannel;
    }

    public void setAuthChannel(String authChannel) {
        AuthChannel = authChannel;
    }

    public String getPublicLicenseNo() {
        return PublicLicenseNo;
    }

    public void setPublicLicenseNo(String publicLicenseNo) {
        PublicLicenseNo = publicLicenseNo;
    }

    public String getIssuanceLicenseNo() {
        return IssuanceLicenseNo;
    }

    public void setIssuanceLicenseNo(String issuanceLicenseNo) {
        IssuanceLicenseNo = issuanceLicenseNo;
    }

    public String getImportLicenseNo() {
        return ImportLicenseNo;
    }

    public void setImportLicenseNo(String importLicenseNo) {
        ImportLicenseNo = importLicenseNo;
    }

    public String getAudiovisualProgramNo() {
        return AudiovisualProgramNo;
    }

    public void setAudiovisualProgramNo(String audiovisualProgramNo) {
        AudiovisualProgramNo = audiovisualProgramNo;
    }

    public String getIMDBNo() {
        return IMDBNo;
    }

    public void setIMDBNo(String IMDBNo) {
        this.IMDBNo = IMDBNo;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getOriginalName() {
        return OriginalName;
    }

    public void setOriginalName(String originalName) {
        OriginalName = originalName;
    }

    public String getSortName() {
        return SortName;
    }

    public void setSortName(String sortName) {
        SortName = sortName;
    }

    public String getSearchName() {
        return SearchName;
    }

    public void setSearchName(String searchName) {
        SearchName = searchName;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getActorDisplay() {
        return ActorDisplay;
    }

    public void setActorDisplay(String actorDisplay) {
        ActorDisplay = actorDisplay;
    }

    public String getActorSearchName() {
        return ActorSearchName;
    }

    public void setActorSearchName(String actorSearchName) {
        ActorSearchName = actorSearchName;
    }

    public String getWriterDisplay() {
        return WriterDisplay;
    }

    public void setWriterDisplay(String writerDisplay) {
        WriterDisplay = writerDisplay;
    }

    public String getWriterSearchName() {
        return WriterSearchName;
    }

    public void setWriterSearchName(String writerSearchName) {
        WriterSearchName = writerSearchName;
    }

    public String getOriginalCountry() {
        return OriginalCountry;
    }

    public void setOriginalCountry(String originalCountry) {
        OriginalCountry = originalCountry;
    }

    public String getOriginalcompany() {
        return Originalcompany;
    }

    public void setOriginalcompany(String originalcompany) {
        Originalcompany = originalcompany;
    }

    public String getPlayPlat() {
        return PlayPlat;
    }

    public void setPlayPlat(String playPlat) {
        PlayPlat = playPlat;
    }

    public String getPremierePlat() {
        return PremierePlat;
    }

    public void setPremierePlat(String premierePlat) {
        PremierePlat = premierePlat;
    }

    public String getScreenWriter() {
        return ScreenWriter;
    }

    public void setScreenWriter(String screenWriter) {
        ScreenWriter = screenWriter;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getReleaseYear() {
        return ReleaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        ReleaseYear = releaseYear;
    }

    public String getOrgAirDate() {
        return OrgAirDate;
    }

    public void setOrgAirDate(String orgAirDate) {
        OrgAirDate = orgAirDate;
    }

    public String getDisplayAsNew() {
        return DisplayAsNew;
    }

    public void setDisplayAsNew(String displayAsNew) {
        DisplayAsNew = displayAsNew;
    }

    public String getDisplayAsLastChance() {
        return DisplayAsLastChance;
    }

    public void setDisplayAsLastChance(String displayAsLastChance) {
        DisplayAsLastChance = displayAsLastChance;
    }

    public String getMacrovision() {
        return Macrovision;
    }

    public void setMacrovision(String macrovision) {
        Macrovision = macrovision;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPriceTaxIn() {
        return PriceTaxIn;
    }

    public void setPriceTaxIn(String priceTaxIn) {
        PriceTaxIn = priceTaxIn;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String sourceType) {
        SourceType = sourceType;
    }

    public String getSeriesFlag() {
        return SeriesFlag;
    }

    public void setSeriesFlag(String seriesFlag) {
        SeriesFlag = seriesFlag;
    }

    public String getSeriesItemNo() {
        return SeriesItemNo;
    }

    public void setSeriesItemNo(String seriesItemNo) {
        SeriesItemNo = seriesItemNo;
    }

    public String getKeywords() {
        return Keywords;
    }

    public void setKeywords(String keywords) {
        Keywords = keywords;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String tags) {
        Tags = tags;
    }

    public String getBoxOffice() {
        return BoxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        BoxOffice = boxOffice;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getReserve1() {
        return Reserve1;
    }

    public void setReserve1(String reserve1) {
        Reserve1 = reserve1;
    }

    public String getReserve2() {
        return Reserve2;
    }

    public void setReserve2(String reserve2) {
        Reserve2 = reserve2;
    }

    public String getReserve3() {
        return Reserve3;
    }

    public void setReserve3(String reserve3) {
        Reserve3 = reserve3;
    }

    public String getReserve4() {
        return Reserve4;
    }

    public void setReserve4(String reserve4) {
        Reserve4 = reserve4;
    }

    public String getReserve5() {
        return Reserve5;
    }

    public void setReserve5(String reserve5) {
        Reserve5 = reserve5;
    }

    public String getStorageType() {
        return StorageType;
    }

    public void setStorageType(String storageType) {
        StorageType = storageType;
    }

    public String getRMediaCode() {
        return RMediaCode;
    }

    public void setRMediaCode(String RMediaCode) {
        this.RMediaCode = RMediaCode;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getErrorDescription() {
        return ErrorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        ErrorDescription = errorDescription;
    }

    public String getDefinitionFlag() {
        return DefinitionFlag;
    }

    public void setDefinitionFlag(String definitionFlag) {
        DefinitionFlag = definitionFlag;
    }
}
