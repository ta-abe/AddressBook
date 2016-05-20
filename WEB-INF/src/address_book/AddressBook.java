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
		return 	ToList(conn, rs);
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

	public Address get(String uuid){
		try {
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
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
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
		List <String> array2 = address.getPhoneNumber();
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

	public void update(Address address){

	}

	public void delete(String uuid) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		String sql = "DELETE FROM ? WHERE UUID = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "ADDRESS_BOOK");
		pst.setString(2, uuid);
		pst.executeUpdate();
		pst.setString(1, "MAIL_ADDRESS");
		pst.executeUpdate();
		pst.setString(1, "PHONE_NUMBER");
		pst.executeUpdate();

	}
 }
