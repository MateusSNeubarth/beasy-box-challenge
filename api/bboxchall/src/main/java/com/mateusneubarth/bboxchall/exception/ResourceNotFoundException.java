package com.mateusneubarth.bboxchall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

/**
 * Exceção lançada quando um recurso não é encontrado no sistema. Retorna
 * automaticamente status HTTP 404 (NOT FOUND) quando lançada.
 */
@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;  // Nome do recurso que não foi encontrado (ex: "Usuário")
    private final String fieldName;    // Nome do campo usado na busca (ex: "id")
    private final Object fieldValue;   // Valor do campo usado na busca (ex: 123L)

    /**
     * Construtor da exceção
     *
     * @param resourceName Nome do recurso que não foi encontrado
     * @param fieldName Nome do campo usado na busca
     * @param fieldValue Valor do campo usado na busca
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        // Formata a mensagem de erro: "Usuário não encontrado com id: 123"
        super(String.format("%s não encontrado com %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
