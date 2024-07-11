package lk.ijse.dto;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class AppointmentDTO {
    private String appId;
    private Date date;
    private String time;
    private String petOwId;
    private String vetId;


}