package br.com.peopleapi.peopleapi.domain.exception;

public class EntityNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(final String message) {
        super(message);
    }

}
