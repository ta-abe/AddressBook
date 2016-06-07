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

	/**
	 * 引数のResultSetからAddressクラスのオブジェクトを作成し
	 * それをListに入れて返却する
	 * @param conn
	 * @param rs
	 * @return AddressのListを返す
	 * @throws SQLException
	 */
	private  List<Address> ToList(Connection conn, ResultSet rs) throws SQLException{
		List<Address> array = new ArrayList<Address>();
		List<String> mailaddresslist = new ArrayList<String>();
		List<String> phonenumberlist = new ArrayList<String>();
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs2 = null;
		try {
			while(rs.next()){
				UUID = rs.getString("ADDRESS_BOOK.UUID");
				NAME = rs.getString("ADDRESS_BOOK.NAME");
				KANA = rs.getString("ADDRESS_BOOK.KANA");
				ADDRESS = rs.getString("ADDRESS_BOOK.ADDRESS");
				MEMO = rs.getString("ADDRESS_BOOK.MEMO");
				REGISTERED_DATETIME = rs.getString("ADDRESS_BOOK.REGISTERED_DATETIME");
				UPDATED_DATETIME = rs.getString("ADDRESS_BOOK.UPDATED_DATETIME");

				String sql = "SELECT * FROM MAIL_ADDRESS WHERE MAIL_ADDRESS.BOOK_UUID = ? ORDER BY MAIL_ADDRESS.SORT_ORDER ASC";
				pst = conn.prepareStatement(sql);
				pst.setString(1, UUID);
				rs2 = pst.executeQuery();
				MA_MAIL_ADDRESS = "";
				if(true == rs2.next()){
					MA_MAIL_ADDRESS = rs2.getString("MAIL_ADDRESS");
				}
				rs2.close();

				sql = "SELECT * FROM PHONE_NUMBER WHERE PHONE_NUMBER.BOOK_UUID = ? ORDER BY PHONE_NUMBER.SORT_ORDER ASC";
				pst2 = conn.prepareStatement(sql);
				pst2.setString(1, UUID);
				rs2 = pst2.executeQuery();
				PN_PHONE_NUMBER = "";
				if(true == rs2.next()){
					PN_PHONE_NUMBER = rs2.getString("PHONE_NUMBER");
				}
				rs2.close();

				mailaddresslist.add(MA_MAIL_ADDRESS);
				phonenumberlist.add(PN_PHONE_NUMBER);
				Address address = new Address(UUID, NAME, KANA, ADDRESS, MEMO, mailaddresslist, phonenumberlist);
				array.add(address);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pst != null){
				pst.close();
			}
			if(pst2 != null){
				pst2.close();
			}
			if(rs2 != null){
				rs2.close();
			}
		}
		return array;
	}

	/**
	 * 登録されているすべての要素をListに入れて返す
	 * @return Addressクラスのオブジェクトが格納されたList
	 * @throws SQLException
	 */
	public  List<Address> getAll() throws SQLException{

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		List<Address> address = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			String sql = "SELECT * FROM ADDRESS_BOOK ORDER BY REGISTERED_DATETIME DESC";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			address = ToList(conn, rs);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pst != null){
				pst.close();
			}
			if(rs != null){
				rs.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		return address;
	}

	/**
	 * 引数のResultSetからメールアドレスのListを作成する
	 * @param conn
	 * @param rs
	 * @return メールアドレスが格納されたList
	 * @throws SQLException
	 */
	private List<String> getMailAddressList(Connection conn, ResultSet rs) throws SQLException{
		List<String> mailAddressList = new ArrayList<String>();
		PreparedStatement pst = null;
		ResultSet rs2 = null;
		try{
			String sql = "SELECT * FROM MAIL_ADDRESS WHERE BOOK_UUID = ? ORDER BY SORT_ORDER ASC";
			pst = conn.prepareStatement(sql);
			rs.next();
			pst.setString(1, rs.getString("UUID"));
			rs2 = pst.executeQuery();
			while(rs2.next()){
				String mailaddress = rs2.getString("MAIL_ADDRESS");
				mailAddressList.add(mailaddress);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pst != null){
				pst.close();
			}
			if(rs2 != null){
				rs2.close();
			}
		}
		return mailAddressList;
	}

	/**
	 * 引数のResultSetから電話番号のListを作成する
	 * @param conn
	 * @param rs
	 * @return 電話番号が格納されたList
	 * @throws SQLException
	 */
	private List<String> getPhoneNumberList(Connection conn, ResultSet rs) throws SQLException{
		List<String> phoneNumberList = new ArrayList<String>();
		PreparedStatement pst = null;
		ResultSet rs2 = null;
		try {
			String sql = "SELECT * FROM PHONE_NUMBER WHERE BOOK_UUID = ? ORDER BY SORT_ORDER ASC";
			pst = conn.prepareStatement(sql);
			rs.next();
			pst.setString(1, rs.getString("UUID"));
			rs2 = pst.executeQuery();
			while(rs2.next()){
				String phonenumber = rs2.getString("PHONE_NUMBER");
				phoneNumberList.add(phonenumber);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pst != null){
				pst.close();
			}
			if(rs2 != null){
				rs2.close();
			}
		}

		return phoneNumberList;
	}

	/**
	 * 引数に指定したUUIDに対応する要素を取得しAddressクラスのオブジェクトにして返す
	 * して返す
	 * @param uuid
	 * @return Address
	 * @throws SQLException
	 */
	public Address get(String uuid) throws SQLException{
		Address address = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			String sql = "SELECT * FROM ADDRESS_BOOK WHERE UUID = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, uuid);
			rs = pst.executeQuery();
			List<String> mailAddressList = getMailAddressList(conn, rs);
			rs.beforeFirst();
			List<String> phoneNumberList = getPhoneNumberList(conn, rs);
			rs.absolute(1);
			NAME = rs.getString("NAME");
			KANA = rs.getString("KANA");
			ADDRESS = rs.getString("ADDRESS");
			MEMO = rs.getString("MEMO");
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
		finally
		{
			if(pst != null){
				pst.close();
			}
			if(rs != null){
				rs.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		return address;

	}

	/**
	 * 入力された値のAddressオブジェクトを受け取り、要素を登録する
	 * @param address
	 * @return
	 * @throws SQLException
	 */
	public Address add(Address address) throws SQLException{
		Connection conn = null;
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO ADDRESS_BOOK(UUID, NAME, KANA, ADDRESS, MEMO, REGISTERED_DATETIME) VALUES(?, ?, ?, ?, ?, CAST(NOW() AS DATETIME))";
			pst = conn.prepareStatement(sql);
			pst.setString(1, UUID = address.getUuid());
			pst.setString(2, NAME = address.getName());
			pst.setString(3, KANA = address.getKana());
			pst.setString(4, ADDRESS = address.getAddress());
			pst.setString(5, MEMO = address.getMemo());
			pst.executeUpdate();

			String sql2 = "INSERT INTO MAIL_ADDRESS(UUID, BOOK_UUID, SORT_ORDER, MAIL_ADDRESS, REGISTERED_DATETIME) VALUES(?, ?, ?, ?, CAST(NOW() AS DATETIME))";
			pst2 = conn.prepareStatement(sql2);
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
			pst3 = conn.prepareStatement(sql3);
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
			conn.commit();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			conn.rollback();
		}
		finally
		{
			if(pst != null){
				pst.close();
			}
			if(pst2 != null){
				pst2.close();
			}
			if(pst3 != null){
				pst3.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		return null;
	}

	/**
	 * 更新したい要素のAddressオブジェクトを受け取り、要素を更新する
	 * @param address
	 * @throws SQLException
	 */
	public void update(Address address) throws SQLException {
		String uuid = address.getUuid();
		String name = address.getName();
		String kana = address.getKana();
		String ad = address.getAddress();
		String memo = address.getMemo();
		List<String> mail = address.getMailAddressList();
		List<String> phone = address.getPhoneNumberList();
		Connection conn = null;
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		PreparedStatement pst4 = null;
		PreparedStatement pst5 = null;
		PreparedStatement pst6 = null;
		PreparedStatement pst7 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			String sql = "UPDATE ADDRESS_BOOK SET NAME = ?, KANA = ?, ADDRESS = ?, MEMO = ?, UPDATED_DATETIME = CAST(NOW() AS DATETIME)  WHERE UUID = ?";
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, kana);
			pst.setString(3, ad);
			pst.setString(4, memo);
			pst.setString(5, uuid);
			pst.executeUpdate();

			String sql2 = "SELECT * FROM MAIL_ADDRESS WHERE BOOK_UUID = ? ORDER BY SORT_ORDER ASC";//メールアドレスのUUID取得
			pst2 = conn.prepareStatement(sql2);
			pst2.setString(1, uuid);
			rs = pst2.executeQuery();
			List<String> mauuidlist = new ArrayList<String>();
			while(rs.next()){
				mauuidlist.add(rs.getString("UUID"));
			}

			String sql3 = "UPDATE MAIL_ADDRESS SET MAIL_ADDRESS = ?, UPDATED_DATETIME = CAST(NOW() AS DATETIME) where UUID = ?";
			pst3 = conn.prepareStatement(sql3);
			for(int i = 0 ; i < mauuidlist.size() ; i++){
				pst3.setString(1,mail.get(i));
				pst3.setString(2, mauuidlist.get(i));
				pst3.executeUpdate();
			}
			int mailsize = mail.size();
			if(false == "null".equals(mail.get(mailsize - 1)) && false == "".equals(mail.get(mailsize - 1))){
				String sql4 = "INSERT INTO MAIL_ADDRESS(UUID, BOOK_UUID, SORT_ORDER, MAIL_ADDRESS, REGISTERED_DATETIME) VALUES(?, ?, ?, ?, CAST(NOW() AS DATETIME))";
				pst4 = conn.prepareStatement(sql4);
				String newuuid =  java.util.UUID.randomUUID().toString();
				pst4.setString(1, newuuid);
				pst4.setString(2, uuid);
				pst4.setInt(3, mailsize);
				pst4.setString(4, mail.get(mailsize - 1));
				pst4.executeUpdate();
			}

			String sql5 = "SELECT * FROM PHONE_NUMBER WHERE BOOK_UUID = ? ORDER BY SORT_ORDER ASC";//電話番号のUUID取得
			pst5 = conn.prepareStatement(sql5);
			pst5.setString(1, uuid);
			rs2 = pst5.executeQuery();
			List<String> phuuidlist = new ArrayList<String>();
			while(rs2.next()){
				phuuidlist.add(rs2.getString("UUID"));
			}

			String sql6 = "UPDATE PHONE_NUMBER SET PHONE_NUMBER = ?, UPDATED_DATETIME = CAST(NOW() AS DATETIME) where UUID = ?";
			pst6 = conn.prepareStatement(sql6);
			for(int i = 0 ; i < phuuidlist.size() ; i++){
				pst6.setString(1,phone.get(i));
				pst6.setString(2, phuuidlist.get(i));
				pst6.executeUpdate();
			}
			int phonesize = phone.size();
			if(false == "null".equals(phone.get(phonesize - 1)) && false == "".equals(phone.get(phonesize - 1)) ){
				String sql7 = "INSERT INTO PHONE_NUMBER(UUID, BOOK_UUID, SORT_ORDER, PHONE_NUMBER, REGISTERED_DATETIME) VALUES(?, ?, ?, ?, CAST(NOW() AS DATETIME))";
				pst7 = conn.prepareStatement(sql7);
				String newuuid =  java.util.UUID.randomUUID().toString();
				pst7.setString(1, newuuid);
				pst7.setString(2, uuid);
				pst7.setInt(3, phonesize);
				pst7.setString(4, phone.get(phonesize - 1));
				pst7.executeUpdate();
			}
			conn.commit();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			conn.rollback();
		}
		finally
		{
			if(conn != null){
				conn.close();
			}
			if(pst != null){
				pst.close();
			}
			if(pst2 != null){
				pst2.close();
			}
			if(pst3 != null){
				pst3.close();
			}
			if(pst4 != null){
				pst4.close();
			}
			if(pst5 != null){
				pst5.close();
			}
			if(pst6 != null){
				pst6.close();
			}
			if(pst7 != null){
				pst7.close();
			}
			if(rs != null){
				rs.close();
			}
			if(rs2 != null){
				rs2.close();
			}

		}
	}

	/**
	 * 削除したい要素のUUIDを受け取り、その要素を削除する
	 * @param uuid
	 * @throws SQLException
	 */
	public void delete(String uuid) throws SQLException{
		Connection conn = null;
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			conn.setAutoCommit(false);

			String sql = "DELETE FROM ADDRESS_BOOK WHERE UUID = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, uuid);
			pst.executeUpdate();

			String sql2 = "DELETE FROM MAIL_ADDRESS WHERE BOOK_UUID = ?";
			pst2 = conn.prepareStatement(sql2);
			pst2.setString(1, uuid);
			pst2.executeUpdate();

			String sql3 = "DELETE FROM PHONE_NUMBER WHERE BOOK_UUID = ?";
			pst3 = conn.prepareStatement(sql3);
			pst3.setString(1, uuid);
			pst3.executeUpdate();
			conn.commit();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			conn.rollback();
		}
		finally{
			if(pst != null){
				pst.close();
			}
			if(pst2 != null){
				pst2.cancel();
			}
			if(pst3 != null){
				pst3.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}
 }
