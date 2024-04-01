package co.id.bcafinance.finalproject.core;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IService<T> {
    ResponseEntity<Object> save(T t, HttpServletRequest request); //001 - 010
    ResponseEntity<Object> findById(Long id, HttpServletRequest request); //011 - 020
    ResponseEntity<Object> delete(Long id, HttpServletRequest request); //021 - 030
    ResponseEntity<Object> update(Long id, T t, HttpServletRequest request); //031 - 040
    ResponseEntity<Object> find(Pageable pageable, String filterBy, String value, HttpServletRequest request);
    ResponseEntity<Object> findWithoutPage(HttpServletRequest request);
}

