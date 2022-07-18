package backend.duka_kuu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SubcategoryDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String subcategoryName;

    @NotNull
    private String subcategoryDescription;

    @NotNull
    @Size(max = 255)
    private String subcategoryImage;

    @NotNull
    private Long category;

}
