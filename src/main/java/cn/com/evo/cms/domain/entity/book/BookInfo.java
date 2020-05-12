package cn.com.evo.cms.domain.entity.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the cms_book_info database table.
 *
 */
@Entity
@Table(name="book_info")
@NamedQuery(name="BookInfo.findAll", query="SELECT b FROM BookInfo b")
public class BookInfo extends com.frameworks.core.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	private String number;

	private String ageTag;

	private String author;

	private String clcClassify;

	private String engineer;

	private String info;

	private String name;

	private String price;

	private String publishDate;

	private String smallClassify;

	private String suitGroup;

	private String supplier;

	private String repertoryCount;

	private String putCount;

	private String columnsIds;

	private String columnsNames;

	private String dbScore; //豆瓣评分

	private Integer isStart; //是否启用 (0:未启用  1:已启用)

	private String tags; //图书标签

	private String sectionId; //专题ID

	private String sectionName; //专题名称

	@OneToMany(mappedBy = "bookInfo", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
	private List<BookPicture> allPictures = new ArrayList<BookPicture>(0);

	public BookInfo() {
	}

	public List<BookPicture> getAllPictures() {
		return allPictures;
	}

	public void setAllPictures(List<BookPicture> allPictures) {
		this.allPictures = allPictures;
	}

	public String getColumnsIds() {
		return columnsIds;
	}

	public void setColumnsIds(String columnsIds) {
		this.columnsIds = columnsIds;
	}

	public String getColumnsNames() {
		return columnsNames;
	}

	public void setColumnsNames(String columnsNames) {
		this.columnsNames = columnsNames;
	}

	public String getRepertoryCount() {
		return repertoryCount;
	}

	public void setRepertoryCount(String repertoryCount) {
		this.repertoryCount = repertoryCount;
	}

	public String getPutCount() {
		return putCount;
	}

	public void setPutCount(String putCount) {
		this.putCount = putCount;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAgeTag() {
		return this.ageTag;
	}

	public void setAgeTag(String ageTag) {
		this.ageTag = ageTag;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getClcClassify() {
		return this.clcClassify;
	}

	public void setClcClassify(String clcClassify) {
		this.clcClassify = clcClassify;
	}

	public String getEngineer() {
		return this.engineer;
	}

	public void setEngineer(String engineer) {
		this.engineer = engineer;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getSmallClassify() {
		return this.smallClassify;
	}

	public void setSmallClassify(String smallClassify) {
		this.smallClassify = smallClassify;
	}

	public String getSuitGroup() {
		return this.suitGroup;
	}

	public void setSuitGroup(String suitGroup) {
		this.suitGroup = suitGroup;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getDbScore() {
		return dbScore;
	}

	public void setDbScore(String dbScore) {
		this.dbScore = dbScore;
	}

	public Integer getIsStart() {
		return isStart;
	}

	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

}