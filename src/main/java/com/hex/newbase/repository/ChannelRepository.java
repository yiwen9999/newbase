package com.hex.newbase.repository;

import com.hex.newbase.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 2:51 PM
 */
public interface ChannelRepository extends JpaRepository<Channel, String> {
}
