package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Enitity.products;
import util.ParamUtil;

public class ProductsDao {
	
	private static final String SELECT_ALL = "SELECT p.product_id product_id, p.name name, p.price, c.name c_name FROM products p INNER JOIN categories c ON p.category_id = c.id";
	//private static final String SQL_SELECT_FIND = "SELECT p.product_id product_id, p.name name, p.price, c.name c_name FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE p.name LIKE ?";
	private static final String SQL_SELECT_FIND = "SELECT p.product_id product_id, p.name name, p.price, c.name c_name FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE";
	private static final String ORDER_BY = " ORDER BY product_id";
	private Connection connection;

	public ProductsDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<products> findAll() {

		List<products> list = new ArrayList<>();

		try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL + ORDER_BY)) {
			ResultSet rs = stmt.executeQuery();
			System.out.println(stmt);

			while (rs.next()) {
				list.add(new products(rs.getString("product_id"), rs.getString("c_name"), rs.getString("name"),
						rs.getInt("price")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	

    public List<products> find(products pd) {
        // WHERE句の文字列生成用
        // 指定した条件に応じて、
        // 「product_name = ?」、「price = ?」などの文字列が入る
        ArrayList<String> conditionList = new ArrayList<>();

        // プレースホルダーに埋め込む値用
        ArrayList<Object> paramList = new ArrayList<>();

        // 各検索条件の入力値用
        String productName = null;
        //Integer price = null;

        // 引数の値をセット
        if (pd != null) {
            productName = pd.getName();           
            System.out.println(productName);
        }

        // 各検索条件が全て未入力の場合、全件検索
        if (ParamUtil.isNullOrEmpty(productName)) {
            return findAll();
        }

        // 入力されている場合、
        // 「product_name = ?」をWHEREの条件として使用する
        if (!ParamUtil.isNullOrEmpty(productName)) {
            conditionList.add(" p.name LIKE ? OR c.name LIKE ?"); // WHERE句の条件に使用する文字列
            
            String find1 = "%" + productName + "%";
            paramList.add(find1); // プレースホルダーに埋め込む値
            paramList.add(find1);
        }
//
//        // todo:
//        // priceが入力されている場合、
//        // 「price = ?」をWHEREの条件として使用する
//        // 上記のproduct_nameが入力されている際の処理を参考にしてください
//        if (price != null) {
//            conditionList.add("price = ?"); // WHERE句の条件に使用する文字列
//            paramList.add(price); // プレースホルダーに埋め込む値
//        }

        // WHERE句の文字列生成
        // 「product_name = ?」、「price = ?」などの
        // 文字列の間に「 AND 」をくっつけて、一つの文字列に生成する
        // 入力パターンに応じて、下記の文字列が生成される
        // product_nameのみ入力 ⇒ 「prduct_name = ?」
        // priceのみ入力 　　　 ⇒ 「price = ?」
        // 両方入力 　　　　　　⇒ 「prduct_name = ? AND price = ?」
        String whereString = String.join(" AND ", conditionList.toArray(new String[] {}));

        List<products> list = new ArrayList<>();

        // SQL文生成
        // (ベースのSQL文 + WHERE句の文字列 + 出力順)
        String sql = SQL_SELECT_FIND + whereString + ORDER_BY;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            // プレースホルダーの値をセット
            for (int i = 0; i < paramList.size(); i++) {
                stmt.setObject(i + 1, paramList.get(i));
            }

            // todo:SQL文実行
            ResultSet rs = stmt.executeQuery();

            // 取得した件数分、処理を繰り返す
            while (rs.next()) {
                // todo:取得したデータを変数listに追加
            	list.add(new products(rs.getString("product_id"), rs.getString("c_name"), rs.getString("name"),
						rs.getInt("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
