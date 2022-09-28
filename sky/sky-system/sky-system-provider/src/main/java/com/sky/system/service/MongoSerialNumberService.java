package com.sky.system.service;

import com.sky.system.document.DocumentSerialNumber;

import java.util.List;

public interface MongoSerialNumberService {

    String generateNumber(String code);

    DocumentSerialNumber getById(Long id);

    void removeById(Long id);

    long remove();

    List<DocumentSerialNumber> findAll();
}
