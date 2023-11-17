package application.U5D15.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record NewEventDTO(
        @NotEmpty(message = "Il titolo è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il titolo deve essere compreso tra 3 e 30 caratteri")
        String title,
        @Size(min=1 , max=100, message = "la descrizione deve essere massimo 100 caratteri ed averne almeno 1")
        String description,
        @NotNull(message = "il numero massimo di partecipanti è un campo obbligatorio")
        int numberMax,
        @NotNull(message = "la data è un campo obbligatorio")
        @Future(message = "la data deve essere nel futuro")
        LocalDateTime data,
        @NotEmpty(message = "il luogo è un campo obbligatorio!")
        @Size(min = 3, max=30, message = "Il luogo deve essere compreso tra 3 e 30 caratteri")
        String place) { }
