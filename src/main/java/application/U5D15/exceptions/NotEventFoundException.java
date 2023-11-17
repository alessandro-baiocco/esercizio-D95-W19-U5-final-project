package application.U5D15.exceptions;

public class NotEventFoundException extends RuntimeException {
    public NotEventFoundException(int id){

        super("l'evento con id " + id + " non è stato trovato");
    }
}
