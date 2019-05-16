package com.hex.newbase.service;

import com.hex.newbase.domain.Channel;

import java.util.List;
import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 3:22 PM
 */
public interface ChannelService {

    Channel save(Channel channel);

    Optional<Channel> findById(String id);

    void delete(String id);

    List<Channel> findTopChannelList();

    List<Channel> findUsingTopChannelList(Integer state);

    Channel updateChannelStateById(String id);
}
