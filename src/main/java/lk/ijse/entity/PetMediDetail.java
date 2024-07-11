package lk.ijse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class PetMediDetail extends CartTm {
    private String Medicine_ID;
    private String P_ID;
    private int QTY;
}