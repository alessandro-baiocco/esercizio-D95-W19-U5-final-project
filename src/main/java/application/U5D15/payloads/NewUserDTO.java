package application.U5D15.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Date;

public record NewUserDTO(
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String name,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String lastName,
        @NotEmpty(message = "Il nome utente è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String userName,
        @NotEmpty(message = "la password è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il nome deve essere compreso tra 3 e 30 caratteri")
        String password,
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email) {
}
