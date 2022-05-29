package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Enitity.products;
import service.ProductsService;

@WebServlet("/search")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String search = request.getParameter("search");
		
		ProductsService ProductsService = new ProductsService();
		//products pro = userService.searchProductttt(search);
		//System.out.println(pro);
		
		 products Product = new products(null,null,search,null);
	     List<products> product = ProductsService.find(Product);
	     System.out.println(product);
	     if(product.isEmpty()) {
	    		request.setAttribute("msg", "対象のデータはありません。");
	            request.getRequestDispatcher("/index.jsp").forward(request, response);
	    		
	    	}else {
	    		request.setAttribute("productList", product);
	    		request.setAttribute("productListnum", product.size());
	    		
	            request.getRequestDispatcher("/menu.jsp").forward(request, response);
	    	} 
	}	  
}
	
	