package pe.gob.minjus.sisarb.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/*@RestController
@ControllerAdvice*/
@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
   @ExceptionHandler(Exception.class)
    public final ResponseEntity<ModelExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ModelExceptionResponse er = new ModelExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<ModelExceptionResponse> handleDataAccessException(DataAccessException ex, WebRequest request) {
        ModelExceptionResponse er = new ModelExceptionResponse("Error en base de datos: "+ex.getMostSpecificCause(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ModelExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {
        ModelExceptionResponse er = new ModelExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(EntityValidationCustom.class)
    public final ResponseEntity<ModelExceptionResponse> handleMethodArgumentNotValid(Exception ex, WebRequest request) {
        ModelExceptionResponse er = new ModelExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityValidationConfiguracion.class)
    public final ResponseEntity<ModelExceptionResponse> handleMethodServiceExternal(Exception ex, WebRequest request) {
        ModelExceptionResponse er = new ModelExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityWebServiceRestException.class)
    public final ResponseEntity<ModelExceptionResponse> handleMethodServiceRestTemplate(Exception ex, WebRequest request) {
        ModelExceptionResponse er = new ModelExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        AtomicInteger index = new AtomicInteger();
        String mensaje = errors.stream()
                .map(e -> (errors.size() > 1 && index.incrementAndGet() < errors.size()) ? e.getDefaultMessage() + ", " : e.getDefaultMessage())
                .collect(Collectors.joining());
        ModelExceptionResponse er = new ModelExceptionResponse("Se encontraron validaciones: " + mensaje, request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }


}
