package lk.ijse.dto;

import lk.ijse.dto.tm.CartTm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class PetMediDetailDTO extends CartTm {
    private String Medicine_ID;
    private String P_ID;
    private int QTY;
}