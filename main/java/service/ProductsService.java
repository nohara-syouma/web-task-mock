package service;

import java.sql.Connection;
import java.util.List;

import Enitity.products;
import dao.ProductsDao;
import util.DbUtil;

public class ProductsService {
	
	List<products> reslt =null;
	
	public List<products> findAllll() {
        try (Connection conn = DbUtil.getConnection()) {
        	ProductsDao ProductsDao = new ProductsDao(conn);
            reslt =ProductsDao.findAll();

            return reslt;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

	public List<products> find(products pd){
		try (Connection conn = DbUtil.getConnection()) {
			ProductsDao ProductsDao = new ProductsDao(conn);
        	reslt = ProductsDao.find(pd);
            return reslt;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
		
	}

}
