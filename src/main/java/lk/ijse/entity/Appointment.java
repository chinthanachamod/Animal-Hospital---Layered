package lk.ijse.entity;

import lombok.*;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
public class Appointment {
    private String appId;
    private Date date;
    private String time;
    private String petOwId;
    private String vetId;


}