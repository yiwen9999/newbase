package com.hex.newbase.repository;

import com.hex.newbase.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 2:51 PM
 */
public interface ChannelRepository extends JpaRepository<Channel, String> {
    List<Channel> findAllByParentChannelIsNullOrderBySort();

    List<Channel> findAllByParentChannelIsNullAndStateOrderBySort(Integer state);
}
