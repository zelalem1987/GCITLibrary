package com.gcit.training.library.entity;

public class Publisher implements BaseEntity{

	private int publisherId;
	private String publisherName;
	private String publisherAddress;
	private String punlisherPhone;
	
	public Publisher(){
		super();
	}
	
	public String getPunlisherPhone() {
		return punlisherPhone;
	}
	public void setPunlisherPhone(String punlisherPhone) {
		this.punlisherPhone = punlisherPhone;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublisherAddress() {
		return publisherAddress;
	}
	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}
	
	@Override
	public String toString(){
		return this.getPublisherName() + ", " + 
				this.getPublisherAddress() + ", " + this.getPunlisherPhone();
	}
	
}
