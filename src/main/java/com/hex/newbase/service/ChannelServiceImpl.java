package com.hex.newbase.service;

import com.hex.newbase.domain.Channel;
import com.hex.newbase.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/2/28
 * Time: 3:25 PM
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public Channel save(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public Optional<Channel> findById(String id) {
        return channelRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        channelRepository.deleteById(id);
    }
}
