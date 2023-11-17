package application.U5D15.payloads;

import jakarta.validation.constraints.NotNull;

public record UserIdDTO(
        @NotNull(message = "l'id utente non può essere null")
        int id
) {
}
