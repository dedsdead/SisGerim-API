package sisgerim.backend.domain.pessoa.corretor;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDTO(@NotBlank String email, @NotBlank String senha) {}
