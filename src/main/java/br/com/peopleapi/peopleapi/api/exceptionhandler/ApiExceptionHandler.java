package br.com.peopleapi.peopleapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.peopleapi.peopleapi.domain.exception.BusinessException;
import br.com.peopleapi.peopleapi.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;

/**
 * O polimorfismo ocorre nas anotações @ExceptionHandler que são usadas para
 * lidar com exceções específicas. Essas anotações permitem que métodos
 * diferentes sejam executados dependendo do tipo de exceção lançada.
 */
@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
            final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {

        List<Field> fields = ex.getBindingResult().getAllErrors().stream()
                .map(error -> new Field(((FieldError) error).getField(),
                        messageSource.getMessage(error, LocaleContextHolder.getLocale())))
                .collect(Collectors.toList());

        Problem problem = buildProblem(ex, status, "One or more invalid fields. Fill in correctly.");
        problem.setFields(fields);
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFound(final EntityNotFoundException ex, final WebRequest request) {
        HttpStatusCode httpStatus = HttpStatus.NOT_FOUND;

        Problem problem = buildProblem(ex, httpStatus, null);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handlerBusiness(final BusinessException ex, final WebRequest request) {
        HttpStatusCode httpStatus = HttpStatus.BAD_REQUEST;

        Problem problem = buildProblem(ex, httpStatus, null);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), httpStatus, request);
    }

    private Problem buildProblem(final Exception ex, final HttpStatusCode httpStatus, final String title) {
        Problem problem = new Problem();
        problem.setStatus(httpStatus.value());
        problem.setDateTime(OffsetDateTime.now());
        problem.setTitle(title != null ? title : ex.getMessage());
        return problem;
    }
}
