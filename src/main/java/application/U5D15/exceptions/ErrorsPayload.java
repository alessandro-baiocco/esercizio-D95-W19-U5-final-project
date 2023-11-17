package application.U5D15.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@Setter
@Getter
public class ErrorsPayload {
        private String message;
        private Date timestamp;
}