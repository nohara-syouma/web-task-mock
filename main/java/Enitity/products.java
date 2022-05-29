package Enitity;

public class products{
	private String product_id;
	private String category_id;
	private String name;
	private Integer price;
	
	public products() {
    }
	
	public products(String product_id,String category_id,String name,Integer price) {
		this.product_id = product_id;
		this.category_id = category_id;
		this.name = name;
		this.price = price;
    }
	
	
	
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}