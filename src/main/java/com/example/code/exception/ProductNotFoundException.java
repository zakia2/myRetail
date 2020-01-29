/**
 * Custom Exception to be returned when the product does not exist in the db
 */
package com.example.code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Product not found")
public class ProductNotFoundException extends RuntimeException {
}
