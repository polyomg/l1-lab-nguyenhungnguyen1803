package poly.edu.lab3.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
    private String id;

    @Builder.Default
    private String fullname = "";

    @Builder.Default
    private String photo = "nguyen.jpg";

    @Builder.Default
    private Boolean gender = true;

    @Builder.Default
    private Date birthday = new Date();

    @Builder.Default
    private Double salary = 12345.6789;

    @Builder.Default
    private Integer level = 0;
}
