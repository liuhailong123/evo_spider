package cn.com.evo.cms.domain.vo.book;

public class BookChildInfoVo extends com.frameworks.core.vo.BaseVo {
	
	private static final long serialVersionUID = 1L;
	
    private BookInfoVo bookInfo;	

	private int depreciationRate;

	private int number;

	private int status;
	
	private String bookStorageName;
	
	public BookChildInfoVo() {
	}

	public String getBookStorageName() {
		return bookStorageName;
	}

	public void setBookStorageName(String bookStorageName) {
		this.bookStorageName = bookStorageName;
	}

	public BookInfoVo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfoVo bookInfo) {
		this.bookInfo = bookInfo;
	}

	public int getDepreciationRate() {
		return depreciationRate;
	}

	public void setDepreciationRate(int depreciationRate) {
		this.depreciationRate = depreciationRate;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}