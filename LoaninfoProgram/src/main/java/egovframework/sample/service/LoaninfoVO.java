package egovframework.sample.service;

import java.sql.Date;

public class LoaninfoVO {
	
	private String id;
	private String u_id;
	private int b_id; // 책 등록 번호
	private String b_name;
	private String b_author;
	private String b_publisher;
	private String b_callnum;
	private Date loanDate;
	private Date returnDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public String getB_author() {
		return b_author;
	}
	public void setB_author(String b_author) {
		this.b_author = b_author;
	}
	public String getB_publisher() {
		return b_publisher;
	}
	public void setB_publisher(String b_publisher) {
		this.b_publisher = b_publisher;
	}
	public String getB_callnum() {
		return b_callnum;
	}
	public void setB_callnum(String b_callnum) {
		this.b_callnum = b_callnum;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	@Override
	public String toString() {
		return "LoaninfoVO [id=" + id + ", u_id=" + u_id + ", b_id=" + b_id + ", b_name=" + b_name + ", b_author="
				+ b_author + ", b_publisher=" + b_publisher + ", b_callnum=" + b_callnum + ", loanDate=" + loanDate
				+ ", returnDate=" + returnDate + "]";
	}

	
}
