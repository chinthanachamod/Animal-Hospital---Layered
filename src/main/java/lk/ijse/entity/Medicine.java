package lk.ijse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class Medicine {
    private String medId;
    private String description;
    private int qty;
    private Double price;


}