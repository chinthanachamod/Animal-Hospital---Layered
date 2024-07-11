package lk.ijse.dto.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class CartTm {
    private String Medicine_IDC;
    private String P_IDC;
    private int QTYC;
    private JFXButton btnRemoveC;

}