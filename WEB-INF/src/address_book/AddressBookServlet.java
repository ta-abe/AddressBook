package address_book;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddressBookServlet extends HttpServlet{

	public long selialVersionUID;

	public void doGet(HttpServletRequest req,HttpServletResponse res){

		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest req,HttpServletResponse res){

		try {
			req.setCharacterEncoding("UTF-8");
			if(null != req.getParameter("btnAdd")){
				getServletConfig().getServletContext().getRequestDispatcher("/ADR002.jsp").forward(req, res);
			}else if(null != req.getParameter("btnRefer")){
				getServletConfig().getServletContext().getRequestDispatcher("/ADR003.jsp").forward(req, res);
			}else if("001Back" .equals(req.getParameter("btnBack"))){
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}else if(null != req.getParameter("btnRegister")){
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}else if(null != req.getParameter("btnEdit")){
				getServletConfig().getServletContext().getRequestDispatcher("/ADR004.jsp").forward(req, res);
			}else if(null != req.getParameter("btnDelete")){
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}else if(null != req.getParameter("btnUpdate")){
				getServletConfig().getServletContext().getRequestDispatcher("/ADR001.jsp").forward(req, res);
			}else if("003Back".equals(req.getParameter("btnBack"))){
				getServletConfig().getServletContext().getRequestDispatcher("/ADR003.jsp").forward(req, res);
			}

		} catch (UnsupportedEncodingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	private void registerAddress(){

	}

	private void updateAddress(){

	}

	private void deleteAddress(){

	}

}
