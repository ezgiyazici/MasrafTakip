package ezgiyazici.masraftakip.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name="masraf")
public class MasrafEntity extends BaseEntity  implements Serializable {

    @NotNull
    @Column(name = "masraf_adi",columnDefinition = "varchar(255)")
    private String masraf_adi;

    @NotNull
    @Column(name = "masraf_miktari",columnDefinition = "integer")
    private int masraf_miktari;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private KisiEntity kisi;
}
