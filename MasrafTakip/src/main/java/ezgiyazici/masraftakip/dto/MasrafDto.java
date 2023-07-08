package ezgiyazici.masraftakip.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class MasrafDto {

    @NotEmpty
    private  Long id;

    @NotEmpty
    private String masraf_adi;

    @NotEmpty
    private Long masraf_miktari;

    @NotEmpty
    private KisiDto kisi;
}
