package address_book;

import java.util.ArrayList;
import java.util.List;

public class Addressa {
	private String uuid = null;
	private String name = null;
	private String kana = null;
	private List<String> mailAddressList = new ArrayList<String>();
	private List<String> phoneNumberList = new ArrayList<String>();
	private String address = null;
	private String memo = null;


	Address(String uuid,String name, String kana,String address,String memo,List<String> mailAddressList,List<String> phoneNumberList){
		this.uuid = uuid;
		this.name = name;
		this.kana = kana;
		this.address = address;
		this.memo = memo;
		this.mailAddressList = mailAddressList;
		this.phoneNumberList = phoneNumberList;

	}

	Address(String name, String kana,String address,String memo,List<String> mailAddressList,List<String> phoneNumberList){
		this.uuid = java.util.UUID.randomUUID().toString();
		this.name = name;
		this.kana = kana;
		this.address = address;
		this.memo = memo;
		this.mailAddressList = mailAddressList;
		this.phoneNumberList = phoneNumberList;


	}

	public String getUuid(){
		return uuid;
	}

	public String getName(){
		return name;
	}

	public String getKana(){
		return kana;
	}

	public List<String> getMailAddressList(){
		return mailAddressList;
	}

	public List<String> getPhoneNumber(){
		return phoneNumberList;
	}

	public String getAddress(){
		return address;
	}

	public String getMemo(){
		return memo;
	}
}





