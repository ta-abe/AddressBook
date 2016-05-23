package address_book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	private String UUID = null;
	private String NAME = null;
	private String KANA = null;
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
	private String PN_PHONE_NUMBER = null;
	private String PN_REGISTERED_DATETIME = null;
	private String PN_UPDATED_DATETIME = null;
	private String dbUrl = "jdbc:mysql://localhost:3306/sample";
	private String dbUser = "root";
	private String dbPassword = "8121";

	/*public static void main(String args[]) throws ClassNotFoundException, SQLException{
		getAll();
	}*/

	//test用

	private  List<Address> ToList(Connection conn, ResultSet rs) throws SQLException{
		List<Address> array = new ArrayList<Address>();
		int i = 0;
		List<String> mailaddresslist = new ArrayList<String>();
		List<String> phonenumberlist = new ArrayList<String>();
		while(rs.next()){
			UUID = rs.getString("ADDRESS_BOOK.UUID");
			NAME = rs.getString("ADDRESS_BOOK.NAME");
			KANA = rs.getString("ADDRESS_BOOK.KANA");
			ADDRESS = rs.getString("ADDRESS_BOOK.ADDRESS");
			MEMO = rs.getString("ADDRESS_BOOK.MEMO");
			REGISTERED_DATETIME = rs.getString("ADDRESS_BOOK.REGISTERED_DATETIME");
			UPDATED_DATETIME = rs.getString("ADDRESS_BOOK.UPDATED_DATETIME");
			MA_UUID = rs.getString("MAIL_ADDRESS.UUID");
			MA_BOOK_UUID = rs.getString("MAIL_ADDRESS.BOOK_UUID");
			MA_SORT_ORDER = rs.getString("MAIL_ADDRESS.SORT_ORDER");
			MA_MAIL_ADDRESS = rs.getString("MAIL_ADDRESS.MAIL_ADDRESS");
			MA_REGISTERED_DATETIME = rs.getString("MAIL_ADDRESS.REGISTERED_DATETIME");
			MA_UPDATED_DATETIME = rs.getString("MAIL_ADDRESS.UPDATED_DATETIME");
			PN_UUID = rs.getString("PHONE_NUMBER.UUID");
			PN_BOOK_UUID = rs.getString("PHONE_NUMBER.BOOK_UUID");
			PN_SORT_ORDER = rs.getString("PHONE_NUMBER.SORT_ORDER");
			PN_PHONE_NUMBER = rs.getString("PHONE_NUMBER.PHONE_NUMBER");
			PN_REGISTERED_DATETIME = rs.getString("PHONE_NUMBER.REGISTERED_DATETIME");
			PN_UPDATED_DATETIME = rs.getString("PHONE_NUMBER.UPDATED_DATETIME");
			mailaddresslist.add(MA_MAIL_ADDRESS);
			phonenumberlist.add(PN_PHONE_NUMBER);
			Address address = new Address(UUID, NAME, KANA, ADDRESS, MEMO, mailaddresslist, phonenumberlist);
			array.add(address);
		}
		return array;
	}

	public  List<Address> getAll() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		String sql = "SELECT * FROM ADDRESS_BOOK LEFT JOIN MAIL_ADDRESS ON ADDRESS_BOOK.UUID = MAIL_ADDRESS.BOOK_UUID LEFT JOIN PHONE_NUMBER ON ADDRESS_BOOK.UUID =  PHONE_NUMBER.BOOK_UUID HAVING MAIL_ADDRESS.SORT_ORDER = 1  AND PHONE_NUMBER.SORT_ORDER = 1 ORDER BY ADDRESS_BOOK.REGISTERED_DATETIME DESC";
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		return ToList(conn, rs);
	}

	private List<String> getMailAddressList(Connection conn, ResultSet rs) throws SQLException{
		List<String> mailAddressList = new ArrayList<String>();
		String sql = "SELECT * FROM MAIL_ADDRESS WHERE BOOK_UUID = ? ORDER BY SORT_ORDER ASC";
		PreparedStatement pst = conn.prepareStatement(sql);
		rs.next();
		pst.setString(1, rs.getString("UUID"));
		ResultSet rs2 = pst.executeQuery();
		while(rs2.next()){
			String mailaddress = rs2.getString("MAIL_ADDRESS");
			mailAddressList.add(mailaddress);
		}
		return mailAddressList;
	}

	private List<String> getPhoneNumberList(Connection conn, ResultSet rs) throws SQLException{
		List<String> phoneNumberList = new ArrayList<String>();
		String sql = "SELECT * FROM PHONE_NUMBER WHERE BOOK_UUID = ? ORDER BY SORT_ORDER ASC";
		PreparedStatement pst = conn.prepareStatement(sql);
		rs.next();
		pst.setString(1, rs.getString("UUID"));
		ResultSet rs2 = pst.executeQuery();
		while(rs2.next()){
			String phonenumber = rs2.getString("PHONE_NUMBER");
			phoneNumberList.add(phonenumber);
		}
		return phoneNumberList;
	}

	public Address get(String uuid) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		String sql = "SELECT * FROM ADDRESS_BOOK WHERE UUID = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, uuid);
		ResultSet rs = pst.executeQuery();
		List<String> mailAddressList = getMailAddressList(conn, rs);
		rs.beforeFirst();
		List<String> phoneNumberList = getPhoneNumberList(conn, rs);
		rs.absolute(1);
		NAME = rs.getString("NAME");
		KANA = rs.getString("KANA");
		ADDRESS = rs.getString("ADDRESS");
		MEMO = rs.getString("MEMO");
		Address address = new Address(uuid, NAME, KANA, ADDRESS, MEMO, mailAddressList, phoneNumberList);
		return address;
	}

	public Address add(Address address) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		String sql = "INSERT INTO ADDRESS_BOOK(UUID, NAME, KANA, ADDRESS, MEMO, REGISTERED_DATETIME) VALUES(?, ?, ?, ?, ?, CAST(NOW() AS DATETIME))";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, UUID = address.getUuid());
		pst.setString(2, NAME = address.getName());
		pst.setString(3, KANA = address.getKana());
		pst.setString(4, ADDRESS = address.getAddress());
		pst.setString(5, MEMO = address.getMemo());
		pst.executeUpdate();
		String sql2 = "INSERT INTO MAIL_ADDRESS(UUID, BOOK_UUID, SORT_ORDER, MAIL_ADDRESS, REGISTERED_DATETIME) VALUES(?, ?, ?, ?, CAST(NOW() AS DATETIME))";
		PreparedStatement pst2 = conn.prepareStatement(sql2);
		List<String> array = address.getMailAddressList();
		MA_BOOK_UUID = UUID;
		for(int i = 0 ; i< array.size(); i ++){
			MA_UUID = java.util.UUID.randomUUID().toString();
			pst2.setString(1, MA_UUID);
			pst2.setString(2, MA_BOOK_UUID);
			pst2.setInt(3, i + 1);
			pst2.setString(4, array.get(i));
			pst2.executeUpdate();
		}
		String sql3 = "INSERT INTO PHONE_NUMBER(UUID, BOOK_UUID, SORT_ORDER, PHONE_NUMBER, REGISTERED_DATETIME) VALUES(?, ?, ?, ?, CAST(NOW() AS DATETIME))";
		PreparedStatement pst3 = conn.prepareStatement(sql3);
		List <String> array2 = address.getPhoneNumberList();
		PN_BOOK_UUID = UUID;
		for(int i = 0 ; i< array2.size(); i ++){
			PN_UUID = java.util.UUID.randomUUID().toString();
			pst3.setString(1, PN_UUID);
			pst3.setString(2, PN_BOOK_UUID);
			pst3.setInt(3, i + 1);
			pst3.setString(4, array2.get(i));
			pst3.executeUpdate();
		}
		return null;
	}

	public void update(Address address) throws ClassNotFoundException, SQLException{
		String uuid = address.getUuid();
		String name = address.getName();
		String kana = address.getKana();
		String ad = address.getAddress();
		String memo = address.getMemo();
		List<String> mail = address.getMailAddressList();
		List<String> phone = address.getPhoneNumberList();

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		String sql = "UPDATE ADDRESS_BOOK SET NAME = ?, KANA = ?, ADDRESS = ?, MEMO = ?, UPDATED_DATETIME = CAST(NOW() AS DATETIME)  WHERE UUID = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, name);
		pst.setString(2, kana);
		pst.setString(3, ad);
		pst.setString(4, memo);
		pst.setString(5, uuid);
		pst.executeUpdate();

		String sql2 = "SELECT * FROM MAIL_ADDRESS WHERE BOOK_UUID = ? ORDER BY SORT_ORDER ASC";
		PreparedStatement pst2 = conn.prepareStatement(sql2);
		pst2.setString(1, uuid);
		ResultSet rs = pst2.executeQuery();
		List<String> mauuidlist = new ArrayList<String>();
		while(rs.next()){
			mauuidlist.add(rs.getString("UUID"));
		}

		String sql3 = "UPDATE MAIL_ADDRESS SET MAIL_ADDRESS = ?, UPDATED_DATETIME = CAST(NOW() AS DATETIME) where UUID = ?";
		PreparedStatement pst3 = conn.prepareStatement(sql3);
		for(int i = 0 ; i < mauuidlist.size() ; i++){
			pst3.setString(1,mail.get(i));
			pst3.setString(2, mauuidlist.get(i));
			pst3.executeUpdate();
		}
		int mailsize = mail.size();
		if(false == "null".equals(mail.get(mailsize - 1))){
			String sql4 = "INSERT INTO MAIL_ADDRESS(UUID, BOOK_UUID, SORT_ORDER, MAIL_ADDRESS, REGISTERED_DATETIME) VALUES(?, ?, ?, ?, CAST(NOW() AS DATETIME))";
			PreparedStatement pst4 = conn.prepareStatement(sql4);
			String newuuid =  java.util.UUID.randomUUID().toString();
			pst4.setString(1, newuuid);
			pst4.setString(2, uuid);
			pst4.setInt(3, mailsize);
			pst4.setString(4, mail.get(mailsize - 1));
			pst4.executeUpdate();
		}


		String sql5 = "SELECT * FROM PHONE_NUMBER WHERE BOOK_UUID = ? ORDER BY SORT_ORDER ASC";
		PreparedStatement pst5 = conn.prepareStatement(sql5);
		pst5.setString(1, uuid);
		ResultSet rs2 = pst5.executeQuery();
		List<String> phuuidlist = new ArrayList<String>();
		while(rs2.next()){
			phuuidlist.add(rs2.getString("UUID"));
		}

		String sql6 = "UPDATE PHONE_NUMBER SET PHONE_NUMBER = ?, UPDATED_DATETIME = CAST(NOW() AS DATETIME) where UUID = ?";
		PreparedStatement pst6 = conn.prepareStatement(sql6);
		for(int i = 0 ; i < phuuidlist.size() ; i++){
			pst6.setString(1,phone.get(i));
			pst6.setString(2, phuuidlist.get(i));
			pst6.executeUpdate();
		}
		int phonesize = phone.size();
		if(false == "null".equals(phone.get(phonesize - 1))){
			String sql7 = "INSERT INTO PHONE_NUMBER(UUID, BOOK_UUID, SORT_ORDER, PHONE_NUMBER, REGISTERED_DATETIME) VALUES(?, ?, ?, ?, CAST(NOW() AS DATETIME))";
			PreparedStatement pst7 = conn.prepareStatement(sql7);
			String newuuid =  java.util.UUID.randomUUID().toString();
			pst7.setString(1, newuuid);
			pst7.setString(2, uuid);
			pst7.setInt(3, phonesize);
			pst7.setString(4, phone.get(phonesize - 1));
			pst7.executeUpdate();
		}
	}

	public void delete(String uuid) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		String sql = "DELETE FROM ADDRESS_BOOK WHERE UUID = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, uuid);
		pst.executeUpdate();

		String sql2 = "DELETE FROM MAIL_ADDRESS WHERE BOOK_UUID = ?";
		PreparedStatement pst2 = conn.prepareStatement(sql2);
		pst2.setString(1, uuid);
		pst2.executeUpdate();

		String sql3 = "DELETE FROM PHONE_NUMBER WHERE BOOK_UUID = ?";
		PreparedStatement pst3 = conn.prepareStatement(sql3);
		pst3.setString(1, uuid);
		pst3.executeUpdate();
	}
 }
