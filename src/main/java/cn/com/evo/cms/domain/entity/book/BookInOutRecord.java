package cn.com.evo.cms.domain.entity.book;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cn.com.evo.admin.manage.domain.entity.Account;


/**
 * The persistent class for the book_in_out_record database table.
 * 
 */
@Entity
@Table(name="book_in_out_record")
@NamedQuery(name="BookInOutRecord.findAll", query="SELECT b FROM BookInOutRecord b")
public class BookInOutRecord extends com.frameworks.core.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "adminId")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "bookId")
	private BookInfo bookInfo;

	private int count;

	private String name;

	private String number;

	private int repertoryCountAfter;

	private int repertoryCountBefore;
	
	private String remark;

	public BookInOutRecord() {
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getRepertoryCountAfter() {
		return this.repertoryCountAfter;
	}

	public void setRepertoryCountAfter(int repertoryCountAfter) {
		this.repertoryCountAfter = repertoryCountAfter;
	}

	public int getRepertoryCountBefore() {
		return this.repertoryCountBefore;
	}

	public void setRepertoryCountBefore(int repertoryCountBefore) {
		this.repertoryCountBefore = repertoryCountBefore;
	}

}