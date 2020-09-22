package tradeit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	@NotNull
	private String name;

	private String model;

	private String yearModel;

	private double price;

	private double tradeValue;

	private Date purchasedDate;

	private Category category;
	
//	public Item() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public Item(String name, double price) {
//		this.name = name;
//		this.price = price;
//	}
	
	
	public  CarValue getCarValue() {
		if(this.getPrice() <= 500000) 
			return CarValue.LOW;
		else if(this.getPrice() > 500000 && this.getPrice() <= 1000000)
			return CarValue.MEDIUM;
		else if(this.getPrice() > 1000000 && this.getPrice() <= 3000000)
			return CarValue.HIGH;
		else 
			return CarValue.LUXURY;
	}
	

}
