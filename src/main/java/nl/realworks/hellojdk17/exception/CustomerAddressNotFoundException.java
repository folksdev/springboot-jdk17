package nl.realworks.hellojdk17.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerAddressNotFoundException extends RuntimeException {
    public CustomerAddressNotFoundException(String s) {
        super(s);
    }
}
