package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class VeterinarianDTO {
    private String vetId;
    private String name;
    private int yrsOfExperience;


}