package cn.com.evo.cms.domain.vo.book;

public class BookInOutRecordVo extends com.frameworks.core.vo.BaseVo {
	
	private static final long serialVersionUID = 1L;
	
	private String  accountId;
	
	private String  accountName;

	private String bookInfoId;

	private int count;

	private String name;

	private String number;

	private int repertoryCountAfter;

	private int repertoryCountBefore;
	
	public BookInOutRecordVo() {
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBookInfoId() {
		return bookInfoId;
	}

	public void setBookInfoId(String bookInfoId) {
		this.bookInfoId = bookInfoId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getRepertoryCountAfter() {
		return repertoryCountAfter;
	}

	public void setRepertoryCountAfter(int repertoryCountAfter) {
		this.repertoryCountAfter = repertoryCountAfter;
	}

	public int getRepertoryCountBefore() {
		return repertoryCountBefore;
	}

	public void setRepertoryCountBefore(int repertoryCountBefore) {
		this.repertoryCountBefore = repertoryCountBefore;
	}
	
	
}