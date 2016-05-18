package address_book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class AddressBook {
	private String UUID = null;
	private String NAME = null;
	private String ADDRESS = null;
	private String MEMO = null;
	private String REGISTERED_DATETIME = null;
	private String UPDATED_DATETIME = null;
	private String MA_UUID = null;
	private String MA_BOOK_UUID = null;
	private String MA_SORT_ORDER = null;
	private String MA_MAIL_ADDRESS = null;
	private String MA_REGISTERED_DATETIME = null;
	private String MA_UPDATED_DATETIME = null;
	private String PN_UUID = null;
	private String PN_BOOK_UUID = null;
	private String PN_SORT_ORDER = null;
	private String PN_MAIL_ADDRESS = null;
	private String PN_REGISTERED_DATETIME = null;
	private String PN_UPDATED_DATETIME = null;
	private String dbUrl = "jdbc:mysql://localhost:3306/sample";
	private String dbUser = "root";
	private String dbPassword = "8121";
	
	private List<Address> ToList(Connection conn , ResultSet rs){
		
		
		return null;
	}
	
	public List<Address> getAll(){
		return null;
	}
	
	private getMailAddressList(Connection conn,ResultSet rs){
		
	}
}
