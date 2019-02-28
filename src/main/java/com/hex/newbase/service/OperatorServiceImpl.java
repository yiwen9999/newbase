package com.hex.newbase.service;

import com.hex.newbase.domain.Operator;
import com.hex.newbase.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 3:31 PM
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public Operator save(Operator operator) {
        return operatorRepository.save(operator);
    }

    @Override
    public Optional<Operator> findById(String id) {
        return operatorRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        operatorRepository.deleteById(id);
    }
}
