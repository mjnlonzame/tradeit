package tradeit.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tradeit.service.CarValueCategorizer;
import tradeit.service.ShoesValueCategorizer;
import tradeit.service.ValueCategorizer;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotNull
    private String name;

    private String model;

    private String yearModel;

    @NotNull
    private double price;

    private double tradeValue;


    @PastOrPresent
    private LocalDate purchasedDate;

    private Category category;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @JsonIgnore
    public ValueCategorizer getValueCategorizerFactory() {
        if (Category.CAR.equals(this.category)) {
            return new CarValueCategorizer();
        } else if (Category.SHOES.equals(this.category)) {
            return new ShoesValueCategorizer();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return
                Double.compare(item.price, price) == 0 &&
                        Objects.equals(name, item.name) &&
                        Objects.equals(model, item.model) &&
                        Objects.equals(yearModel, item.yearModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, model, yearModel, price);
    }
}
