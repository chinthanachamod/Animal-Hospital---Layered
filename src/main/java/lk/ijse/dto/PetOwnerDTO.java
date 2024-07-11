package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class PetOwnerDTO {
    private String petOwId;
    private String name;
    private String contactNo;


}