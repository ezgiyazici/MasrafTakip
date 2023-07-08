package ezgiyazici.masraftakip.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="kisi")
public class KisiEntity extends BaseEntity  implements Serializable {

    @NotBlank
    @Column(name = "name",columnDefinition = "varchar(255)")
    private String name;

    @NotBlank
    @Column(name = "surname",columnDefinition = "varchar(255)")
    private String surname;

}
