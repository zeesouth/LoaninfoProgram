package egovframework.sample.service;

public class UsersVO {
	private String id;
	private String name;
	private String phoneNum;
	private String address;
	private String info;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "UsersVO [id=" + id + ", name=" + name + ", phoneNum=" + phoneNum + ", address=" + address + ", info="
				+ info + "]";
	}
	
	

	
	
	
}
