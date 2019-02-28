package com.hex.newbase.service;

import com.hex.newbase.domain.Operator;

import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 3:22 PM
 */
public interface OperatorService {

    Operator save(Operator operator);

    Optional<Operator> findById(String id);

    void delete(String id);
}
