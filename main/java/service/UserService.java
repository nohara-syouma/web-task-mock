package service;

import java.sql.Connection;
import java.util.List;

import Enitity.User;
import Enitity.products;
import dao.UserDao;
import util.DbUtil;

public class UserService {
	
	 List<products> reslt =null;

    public User authentication(String id, String pass) {
        try (Connection conn = DbUtil.getConnection()) {
            UserDao userDao = new UserDao(conn);
            User user = userDao.findByIdAndPass(id, pass);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}