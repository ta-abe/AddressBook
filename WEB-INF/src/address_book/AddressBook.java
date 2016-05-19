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
	private String PN_MAIL_ADDRESS = null;
	private String PN_REGISTERED_DATETIME = null;
	private String PN_UPDATED_DATETIME = null;
	private String dbUrl = "jdbc:mysql://localhost:3306/sample";
	private String dbUser = "root";
	private String dbPassword = "8121";

	private List<Address> ToList(Connection conn, ResultSet rs){

		return null;
	}

	public List<Address> getAll(){
		return null;
	}

	private List<String> getMailAddressList(Connection conn, ResultSet rs) throws SQLException{
		List<String> mailAddressList = new ArrayList<String>();
		String sql = "SELECT * FROM MAIL_ADDRESS WHERE BOOK_UUID = ?";
		PreparedStatement pst = conn.prepareStatement(sql);
		rs.next();
		pst.setString(1, rs.getString("UUID"));
		ResultSet rs2 = pst.executeQuery();
		while(rs2.next()){
			String phonenumber = rs2.getString("PHONE_NUMBER");
			mailAddressList.add(phonenumber);
		}
		return mailAddressList;
	}

	private List<String> getPhoneNumberList(Connection conn, ResultSet rs) throws SQLException{
		List<String> phoneNumberList = new ArrayList<String>();
		String sql = "SELECT * FROM PHONE_NUMBER WHERE BOOK_UUID = ?";
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
		Address address = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			String sql = "SELECT * FROM ADDRESS_BOOK WHERE UUID = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, uuid);
			ResultSet rs = pst.executeQuery();
			rs.next();
			NAME = rs.getString("NAME");
			KANA = rs.getString("KANA");
			ADDRESS = rs.getString("ADDRESS");
			MEMO = rs.getString("MEMO");
			List<String> mailAddressList = getMailAddressList(conn, rs);
			List<String> phoneNumberList = getPhoneNumberList(conn, rs);
			address = new Address(uuid, NAME, KANA, ADDRESS, MEMO, mailAddressList, phoneNumberList);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

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
		for(int i = 0 ; i< array.size(); i ++){
			MA_BOOK_UUID = java.util.UUID.randomUUID().toString();
			pst2.setString(1, MA_BOOK_UUID);
			pst2.setString(2, UUID);
			pst2.setInt(3, i + 1);
			pst2.setString(4, array.get(i));
			pst2.executeUpdate();
		}


		return null;
	}

	public void update(Address address){

	}

	public void delete(String uuid){

	}
 }
