package address_book;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author excite
 *
 */
public class AddressBookServlet extends HttpServlet{

	private long selialVersionUID;

	/**
	 *
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		try {
			if("003Back".equals(req.getParameter("btnBack"))){  //ADR004からADR003へ遷移
				String uuid = req.getParameter("hidUuid");
				AddressBook addressbook = new AddressBook();
				Address address = addressbook.get(uuid);
				req.setAttribute("UUID", uuid);
				req.setAttribute("NAME", address.getName());
				req.setAttribute("KANA", address.getKana());
				req.setAttribute("ADDRESS", address.getAddress());
				req.setAttribute("MEMO", address.getMemo());
				int i = 0;
				List<String> mail = address.getMailAddressList();
				req.setAttribute("mailsize", mail.size());
				for(String s:mail){
					req.setAttribute("MAIL" + i, s);
					i++;
				}
				i = 0;
				List<String> phone = address.getPhoneNumberList();
				req.setAttribute("phonesize", phone.size());
				for(String s : phone){
					req.setAttribute("PHONE" + i, s);
					i++;
				}
				getServletConfig().getServletContext().getRequestDispatcher("/ADR003.jsp").forward(req, res);
			}
			else
			{
				req.setCharacterEncoding("UTF-8");
				AddressBook addressbook = new AddressBook();
				List<Address> array = addressbook.getAll();
				Integer s = array.size();
				req.setAttribute("size", s);
				int i = 0;
				for(Address a: array){
					req.setAttribute("UUID" + i, a.getUuid());
					req.setAttribute("NAME" + i, a.getName());
					req.setAttribute("KANA" + i, a.getKana());
					req.setAttribute("MEMO" + i, a.getMemo());
					req.setAttribute("MAIL_ADDRESS" + i, a.getMailAddressList().get(i));
					req.setAttribute("PHONE_NUMBER" + i, a.getPhoneNumberList().get(i));
					i++;
				}
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 *
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res){
		try {
			req.setCharacterEncoding("UTF-8");
			if(null != req.getParameter("btnAdd")){ //登録画面へ
				getServletConfig().getServletContext().getRequestDispatcher("/ADR002.jsp").forward(req, res);
			}
			else if(null != req.getParameter("btnRegister")) //新規登録
			{
				List<String> mailAddressList = new ArrayList<String>();
				List<String> phoneNumberList = new ArrayList<String>();
				String name = req.getParameter("txtName");
				String kana = req.getParameter("txtKana");
				String address = req.getParameter("txtAddress");
				String memo = req.getParameter("txtMemo");
				String s = null;
				String t = null;
				for(int i = 1; i <= 3 ;i++){
					if("" != (s = req.getParameter("txtMailAddress" + i))){
						mailAddressList.add(s);
					}
					if("" != (t = req.getParameter("txtPhoneNumber" + i))){
						phoneNumberList.add(t);
					}
				}
				registerAddress(name, kana, mailAddressList, phoneNumberList, address, memo);
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}
			else if(null != req.getParameter("btnRefer")) //参照（ADR003へ）
			{
				String uuid = req.getParameter("hidUuid");
				AddressBook addressbook = new AddressBook();
				Address address = addressbook.get(uuid);
				req.setAttribute("UUID", uuid);
				req.setAttribute("NAME", address.getName());
				req.setAttribute("KANA", address.getKana());
				req.setAttribute("ADDRESS", address.getAddress());
				req.setAttribute("MEMO", address.getMemo());
				int i = 0;
				List<String> mail = address.getMailAddressList();
				req.setAttribute("mailsize", mail.size());
				for(String s:mail){
					req.setAttribute("MAIL" + i, s);
					i++;
				}
				i = 0;
				List<String> phone = address.getPhoneNumberList();
				req.setAttribute("phonesize", phone.size());
				for(String s : phone){
					req.setAttribute("PHONE" + i, s);
					i++;
				}
				getServletConfig().getServletContext().getRequestDispatcher("/ADR003.jsp").forward(req, res);
			}
			else if(null != req.getParameter("btnDelete"))  //削除
			{
				String uuid = req.getParameter("hidUuid");
				deleteAddress(uuid);
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}
			else if(null != req.getParameter("btnEdit"))  //更新画面へ
			{
				String uuid = req.getParameter("hidUuid");
				req.setAttribute("UUID", uuid);
				AddressBook addressbook = new AddressBook();
				Address address = addressbook.get(uuid);
				List<String> mail = address.getMailAddressList();
				List<String> phone = address.getPhoneNumberList();
				req.setAttribute("NAME", address.getName());
				req.setAttribute("KANA", address.getKana());
				req.setAttribute("MEMO", address.getMemo());
				req.setAttribute("ADDRESS", address.getAddress());
				req.setAttribute("mailsize", mail.size());
				req.setAttribute("phonesize", phone.size());
				int i = 0;
				for(String s : mail){
					req.setAttribute("MAIL" + i, s);
					i++;
				}
				i = 0;
				for(String s : phone){
					req.setAttribute("PHONE" + i, s);
					i++;
				}
				getServletConfig().getServletContext().getRequestDispatcher("/ADR004.jsp").forward(req, res);
			}
			else if(null != req.getParameter("btnUpdate"))  //更新
			{
				String uuid = req.getParameter("hidUuid");
				List<String> mail = new ArrayList<String>();
				int i = 0;
				String s = null;
				while(null  !=  (s = req.getParameter("txtMailAddress" + i))){
					mail.add(s);
					i++;
				}
				List<String> phone = new ArrayList<String>();
				i = 0;
				while(null != (s = req.getParameter("txtPhoneNumber" + i))){
					phone.add(s);
					i++;
				}
				String name = req.getParameter("txtName");
				String kana = req.getParameter("txtKana");
				String address = req.getParameter("txtAddress");
				String memo = req.getParameter("txtMemo");
				Address uaddress = new Address(uuid, name, kana, address, memo, mail, phone);
				updateAddress(uaddress);
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 入力からパラメータを受け取って要素を新規登録する
	 * @param name 名前
	 * @param kana かな
	 * @param mailAddressList メールアドレスが格納されたリスト
	 * @param phoneNumberList 電話番号が格納されたリスト
	 * @param address 住所
	 * @param memo メモ
	 * @throws SQLException
	 */
	private void registerAddress(String name, String kana, List<String> mailAddressList, List<String> phoneNumberList, String address, String memo)throws SQLException{
		Address address2 = new Address(name, kana, address, memo, mailAddressList, phoneNumberList);
		AddressBook addressbook = new AddressBook();
		addressbook.add(address2);
	}

	/**
	 * 入力からパラメータを受け取り、要素の更新を行う
	 * @param address
	 * @throws SQLException
	 */
	private void updateAddress(Address address) throws SQLException{
		AddressBook addressbook = new AddressBook();
		addressbook.update(address);
		}

	/**
	 * UUIDを受け取り、その要素を削除する
	 * @param uuid
	 * @throws SQLException
	 */
	private void deleteAddress(String uuid) throws SQLException{
		AddressBook addressbook = new AddressBook();
		addressbook.delete(uuid);
	}

}