package com.unifei.barber_schedule.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.validation.ConstraintViolationException;

import java.util.stream.Collectors;

//Essa anotação define a classe como um interceptador global para exceções lançadas por controladores.
@ControllerAdvice
public class GlobalExceptionHandler {

    //O método handleConstraintViolationException lida especificamente com
    // as validações de Bean Validation (como @Email, @NotBlank).
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: " + errorMessage);
    }

    //O método handleIllegalArgumentException lida com
    // exceções lançadas quando um argumento é inválido.
    // Por exemplo, um email repetido no register.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    //O método handleHttpMessageNotReadableException lida com
    // exceções lançadas quando o corpo da solicitação não pode ser lido.
    //conversão de mensagens HTTP em objetos Java
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("JSON parsing error: " + e.getMessage());
    }

    //O método handleDataIntegrityViolationException lida com
    // exceções lançadas quando uma restrição de integridade de dados é violada.
    // Por exemplo, um email duplicado
    // uma tentativa de inserir um valor duplicado em uma coluna que possui uma restrição de unicidade
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT) // HTTP 409 Conflict
                .body("Data integrity error: " + e.getRootCause().getMessage());
    }

    //O método handleRuntimeException lida com exceções de tempo de execução
    // Por exemplo, quando um recurso solicitado não é encontrado.
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        // Log the error for internal debugging
        // logger.error("Runtime Exception: ", e);

        // Customize the message for runtime exceptions
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("The requested resource was not found. Please check the ID and try again.");
    }

    //O método handleEntityNotFound lida com exceções lançadas quando uma entidade não é encontrada.
    //Por ex quando busca appointment por cliente e nao encontra o cliente
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //O método handleRuntimeException lida com outras exceções não especificadas acima
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneralException(Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado.");
//    }
}
