package address_book;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author excite
 *
 */
public class Address {
	private String uuid = null;
	private String name = null;
	private String kana = null;
	private List<String> mailAddressList = new ArrayList<String>();
	private List<String> phoneNumberList = new ArrayList<String>();
	private String address = null;
	private String memo = null;

	/**
	 * 要素を受け取りフィールドにセットする
	 * @param uuid
	 * @param name
	 * @param kana
	 * @param address
	 * @param memo
	 * @param mailAddressList
	 * @param phoneNumberList
	 */
	Address(String uuid, String name, String kana, String address, String memo, List<String> mailAddressList, List<String> phoneNumberList){
		this.uuid = uuid;
		this.name = name;
		this.kana = kana;
		this.address = address;
		this.memo = memo;
		this.mailAddressList = mailAddressList;
		this.phoneNumberList = phoneNumberList;
	}

	/**
	 *要素を受け取りフィールドにセットする
	 * @param name
	 * @param kana
	 * @param address
	 * @param memo
	 * @param mailAddressList
	 * @param phoneNumberList
	 */
	Address(String name, String kana,String address,String memo,List<String> mailAddressList,List<String> phoneNumberList){
		this.uuid = java.util.UUID.randomUUID().toString();
		this.name = name;
		this.kana = kana;
		this.address = address;
		this.memo = memo;
		this.mailAddressList = mailAddressList;
		this.phoneNumberList = phoneNumberList;
	}

	/**
	 *
	 * @return setされたUUIDを返す
	 */
	public String getUuid(){
		return uuid;
	}

	/**
	 *
	 * @return setされた名前を返す
	 */
	public String getName(){
		return name;
	}

	/**
	 *
	 * @return setされたかなを返す
	 */
	public String getKana(){
		return kana;
	}

	/**
	 *
	 * @return setされたメールアドレスのリストを返す
	 */
	public List<String> getMailAddressList(){
		return mailAddressList;
	}

	/**
	 *
	 * @return setされた電話番号のリストを返す
	 */
	public List<String> getPhoneNumberList(){
		return phoneNumberList;
	}

	/**
	 *
	 * @return setされた住所を返す
	 */
	public String getAddress(){
		return address;
	}

	/**
	 *
	 * @return setされたメモを返す
	 */
	public String getMemo(){
		return memo;
	}
}
