package ezgiyazici.masraftakip.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class KisiDto implements Serializable {

    public Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;
}
