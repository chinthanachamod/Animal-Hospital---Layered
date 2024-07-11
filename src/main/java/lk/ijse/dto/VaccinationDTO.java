package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class VaccinationDTO {
    private String vaccId;
    private String description;
    private String dosage;


}