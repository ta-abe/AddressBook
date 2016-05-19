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
		}
		catch (UnsupportedEncodingException e)
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
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	private void updateAddress(){

	}

	private void deleteAddress(){

	}

}
