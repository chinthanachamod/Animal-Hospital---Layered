package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data

public class PetDTO {
    private String petId;
    private int age;
    private String weight;
    private String breed;
    private String petOwId;

}