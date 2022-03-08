package egovframework.sample.service;

public class BooksVO {
	private int id;
	private String name;
	private String author;
	private String publisher;
	private String callNum;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getCallNum() {
		return callNum;
	}
	public void setCallNum(String callNum) {
		this.callNum = callNum;
	}
	
	@Override
	public String toString() {
		return "BooksVO [id=" + id + ", name=" + name + ", author=" + author + ", publisher=" + publisher + ", callNum="
				+ callNum + "]";
	}
	

}
