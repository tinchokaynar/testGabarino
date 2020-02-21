package test.garbarino.martinKaynar.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CART")
public class Cart {
	
	public enum Status {
		NEW,READY,PROCESSED,FAILED
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;
    
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate  = LocalDateTime.now();
    
    @OneToMany(mappedBy = "cart", cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductCart> products = new ArrayList<ProductCart>();
    
    @Column(name = "total", nullable = false)
    private Double total = 0.0;
    
    @Column(name = "status", nullable = false)
    private Status status = Status.NEW;
    
    public Cart() {
    	
    }
    
    public Cart(String fullName, String email, LocalDateTime creationDate, List<ProductCart> products) {
    	this.fullName=fullName;
    	this.creationDate=creationDate;
    	this.email=email;
    	this.products=products;
    	this.status=Status.NEW;
    	if(products != null && !products.isEmpty()) {
    		this.total=products.stream().map(p -> p.getUnitPrice()*p.getQuantity()).reduce(0.0,(p1,p2) -> p1 + p2);
    	}
    	
    }

	public Long getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public List<ProductCart> getProducts() {
		return products;
	}

	public Double getTotal() {
		return total;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public void setProducts(List<ProductCart> products) {
		this.products = products;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
    
	public boolean isReady() {
		return this.status.equals(Cart.Status.READY);
	}
    
}
