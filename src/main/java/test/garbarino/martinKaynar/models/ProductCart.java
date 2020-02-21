package test.garbarino.martinKaynar.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name=("PRODUCT_CART"))
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name="unit_price", nullable = false)
    private Double unitPrice;
    
    @Column(name="quantity", nullable = false)
    private Long quantity;
    
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Cart cart;
    
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductStock productStock;
    
    public ProductCart() {
    	
    }
    
    public ProductCart(Double unitPrice, Long quantity) {
    	this.unitPrice=unitPrice;
    	this.quantity=quantity;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public ProductStock getProductStock() {
		return productStock;
	}

	public void setProductStock(ProductStock productStock) {
		this.productStock = productStock;
	}
	
}
