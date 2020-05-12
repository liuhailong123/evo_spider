package cn.com.evo.cms.domain.entity.book;

import cn.com.evo.cms.domain.entity.cms.Picture;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="book_picture")
@NamedQuery(name="BookPicture.findAll", query="SELECT b FROM BookPicture b")
public class BookPicture extends com.frameworks.core.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "bookId")
	private BookInfo bookInfo;

	@ManyToOne
	@JoinColumn(name = "pictureId")
	private Picture pictureInfo;

	private int enable;
	
	public BookPicture() {
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}

	public Picture getPictureInfo() {
		return pictureInfo;
	}

	public void setPictureInfo(Picture pictureInfo) {
		this.pictureInfo = pictureInfo;
	}

	
	
}