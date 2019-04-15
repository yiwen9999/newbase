package com.hex.newbase.repository;

import com.hex.newbase.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 2:47 PM
 */
public interface OperatorRepository extends JpaRepository<Operator, String> {
    Operator findByNameAndAndPassword(String name, String password);

    Operator findByName(String name);
}
