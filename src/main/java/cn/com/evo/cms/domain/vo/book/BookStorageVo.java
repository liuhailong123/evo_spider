package cn.com.evo.cms.domain.vo.book;

public class BookStorageVo extends com.frameworks.core.vo.BaseVo {
	
	private static final long serialVersionUID = 1L;
	
	private String  cityname;

	private String name;
	
	private String contactManName;
	
	private String contactPhone;
	
	private String contactAddress;
	
	public BookStorageVo() {
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

	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}