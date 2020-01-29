/**
 * Custom Exception to be returned when the product title info is not returned by the api
 */
package com.example.code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Product title not found")
public class ProductInfoNotFoundException extends RuntimeException {
}
