package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class PrescriptionDTO {
    private String prescId;
    private String diagnosis;
    private String vetId;


}