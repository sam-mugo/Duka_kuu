package backend.duka_kuu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String categoryName;

    @NotNull
    private String categoryDescription;

    @NotNull
    @Size(max = 255)
    private String categoryImage;

}
