package ec.edu.dto;

import java.util.Date;
import java.util.List;

import ec.edu.model.Role;
import ec.edu.model.Screens;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieSetupDto {
    private Integer movieSetupId;

    private int movieId;
    private int screenId;
    private int userId;
    private Date showDate;
    private String showtime;

   
}

