package cn.com.evo.cms.domain.entity.book;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the cms_book_child_info database table.
 * 
 */
@Entity
@Table(name = "book_child_info")
@NamedQuery(name = "BookChildInfo.findAll", query = "SELECT b FROM BookChildInfo b")
public class BookChildInfo extends com.frameworks.core.entity.BaseEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookId")
	private BookInfo bookInfo;

	private int depreciationRate;

	private int number;

	private int status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "storageId")
	private BookStorage bookStorage;

	public BookChildInfo() {
	}

	public BookStorage getBookStorage() {
		return bookStorage;
	}

	public void setBookStorage(BookStorage bookStorage) {
		this.bookStorage = bookStorage;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}

	public int getDepreciationRate() {
		return this.depreciationRate;
	}

	public void setDepreciationRate(int depreciationRate) {
		this.depreciationRate = depreciationRate;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}