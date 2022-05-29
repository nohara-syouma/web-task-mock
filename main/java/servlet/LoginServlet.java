package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Enitity.User;
import service.UserService;
import util.ParamUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// ログインID、パスワードを取得
		String id = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		
		
		System.out.println(id);
		System.out.println(pass);
		

		// 入力値のチェック
		if (ParamUtil.isNullOrEmpty(id) && ParamUtil.isNullOrEmpty(pass)) {

			// メッセージ設定
			request.setAttribute("msgid", "IDは必須です");
			request.setAttribute("msgpass", "PASSは必須です");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else if (ParamUtil.isNullOrEmpty(pass)) {

			request.setAttribute("msgpass", "PASSは必須です");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else if (ParamUtil.isNullOrEmpty(id)) {

			request.setAttribute("msgid", "IDは必須です");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
		
		UserService userService = new UserService();
		User user = userService.authentication(id, pass);
		
		  if (user != null) {
			 
			  request.setAttribute("name", user.getUserName());
			  
	          
	            // 次画面指定
			  request.getRequestDispatcher("menu.jsp").forward(request, response);
	        } else {
	        	
	            // メッセージ設定
	            request.setAttribute("msg", "IDまたはパスワードが不正です");

	            // 次画面指定
	            request.getRequestDispatcher("index.jsp").forward(request, response);
	        }
		  
		
		  
		 
	}
}
