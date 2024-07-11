package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class MedicineDTO {
    private String medId;
    private String description;
    private int qty;
    private Double price;


}