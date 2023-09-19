package sisgerim.backend.domain.caracteristica;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;

public record CaracteristicaListRequest(@NotEmpty List<Caracteristica> caracteristicas) {}
