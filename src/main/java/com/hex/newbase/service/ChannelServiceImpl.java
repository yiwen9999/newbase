package com.hex.newbase.service;

import com.hex.newbase.domain.Channel;
import com.hex.newbase.enums.ResultEnum;
import com.hex.newbase.enums.StateEnum;
import com.hex.newbase.exception.HexException;
import com.hex.newbase.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<Channel> findTopChannelList() {
        return channelRepository.findAllByParentChannelIsNullOrderBySort();
    }

    @Override
    public List<Channel> findUsingTopChannelList(Integer state) {
        return channelRepository.findAllByParentChannelIsNullAndStateOrderBySort(state);
    }

    @Override
    public Channel updateChannelStateById(String id) {
        Optional<Channel> channelOptional = channelRepository.findById(id);
        if (!channelOptional.isPresent()) {
            throw new HexException(ResultEnum.ERROR_PARAM);
        }
        if (channelOptional.get().getState() == StateEnum.START.getCode()) {
            channelOptional.get().setState(StateEnum.STOP.getCode());
        } else if (channelOptional.get().getState() == StateEnum.STOP.getCode()) {
            channelOptional.get().setState(StateEnum.START.getCode());
        }
        return channelRepository.save(channelOptional.get());
    }
}
