package cn.com.evo.cms.domain.entity.book;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cn.com.evo.admin.manage.domain.entity.Area;


/**
 * The persistent class for the book_storage database table.
 * 
 */
@Entity
@Table(name="book_storage")
@NamedQuery(name="BookStorage.findAll", query="SELECT b FROM BookStorage b")
public class BookStorage extends com.frameworks.core.entity.BaseEntity  {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "cityId")
	private Area city;

	private String name;

	private String contactManName;
	
	private String contactPhone;
	
	private String contactAddress;

//	@OneToMany(mappedBy = "bookStorage", cascade = { CascadeType.REMOVE }, orphanRemoval = true)
//	private List<BookOrder> BookOrders = new ArrayList<BookOrder>(0);

	public BookStorage() {
	}
	
	public String getContactManName() {
		return contactManName;
	}

	public void setContactManName(String contactManName) {
		this.contactManName = contactManName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public Area getCity() {
		return city;
	}

	public void setCity(Area city) {
		this.city = city;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<BookOrder> getBookOrders() {
//		return BookOrders;
//	}
//
//	public void setBookOrders(List<BookOrder> bookOrders) {
//		BookOrders = bookOrders;
//	}

}