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

public class AddressBookServlet extends HttpServlet{

	@SuppressWarnings("unused")
	private long selialVersionUID;

	public void doGet(HttpServletRequest req, HttpServletResponse res){
		try {
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
				req.setAttribute("PHONE_NUMBER" + i, a.getPhoneNumber().get(i));
				i++;
			}
			getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req , res);
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
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

	public void doPost(HttpServletRequest req, HttpServletResponse res){
		try {
			req.setCharacterEncoding("UTF-8");
			if(null != req.getParameter("btnAdd")){
				getServletConfig().getServletContext().getRequestDispatcher("/ADR002.jsp").forward(req, res);
			}
			else if(null != req.getParameter("btnRefer"))
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
				List<String> phone = address.getPhoneNumber();
				req.setAttribute("phonesize", phone.size());
				for(String s : phone){
					req.setAttribute("PHONE" + i, s);
					i++;
				}
				getServletConfig().getServletContext().getRequestDispatcher("/ADR003.jsp").forward(req, res);
			}
			else if("001Back" .equals(req.getParameter("btnBack")))
			{
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}
			else if(null != req.getParameter("btnRegister"))
			{
				List<String> mailAddressList = new ArrayList<String>();
				List<String> phoneNumberList = new ArrayList<String>();
				String name = req.getParameter("txtName");
				String kana = req.getParameter("txtKana");
				String address = req.getParameter("txtAddress");
				String memo = req.getParameter("txtMemo");
				int i = 1;
				String s = null;
				String t = null;
				while(null != (s = req.getParameter("txtMailAddress" + i))){
					mailAddressList.add(s);
					i++;
				}
				i = 1;
				while(null != (t = req.getParameter("txtPhoneNumber" + i))){
					phoneNumberList.add(t);
					i++;
				}
				registerAddress(name, kana, mailAddressList, phoneNumberList, address, memo);
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}
			else if(null != req.getParameter("btnEdit"))
			{
				getServletConfig().getServletContext().getRequestDispatcher("/ADR004.jsp").forward(req, res);
			}
			else if(null != req.getParameter("btnDelete"))
			{
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}
			else if(null != req.getParameter("btnUpdate"))
			{
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}
			else if("003Back".equals(req.getParameter("btnBack")))
			{
				getServletConfig().getServletContext().getRequestDispatcher("/ADR003.jsp").forward(req, res);
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

	}

	private void registerAddress(String name, String kana, List<String> mailAddressList, List<String> phoneNumberList, String address, String memo){
		Address address2 = new Address(name, kana, address, memo, mailAddressList, phoneNumberList);
		AddressBook addressbook = new AddressBook();
		try {
			addressbook.add(address2);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	private void updateAddress(){

	}

	private void deleteAddress(){

	}

}
