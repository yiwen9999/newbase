package com.hex.newbase.converter;

import com.hex.newbase.domain.Channel;
import com.hex.newbase.enums.StateEnum;
import com.hex.newbase.util.EnumUtil;
import com.hex.newbase.vo.ChannelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


public class Channel2ChannelVOConverter {
    public static ChannelVO converter(Channel channel) {
        ChannelVO channelVO = new ChannelVO();
        BeanUtils.copyProperties(channel, channelVO);
        channelVO.setStateStr(EnumUtil.getCode(channelVO.getState(), StateEnum.class).getMsg());
        if (!CollectionUtils.isEmpty(channel.getChildChannelList())) {
            channelVO.setChildChannelVOList(converter(channel.getChildChannelList()));
        }
        if (!CollectionUtils.isEmpty(channel.getUsingChildChannelList())) {
            channelVO.setUsingChannelVOList(converter(channel.getUsingChildChannelList()));
        }
        if (null != channel.getParentChannel()) {
            channelVO.setParentChannelId(channel.getParentChannel().getId());
            channelVO.setParentChannelName(channel.getParentChannel().getName());
        }
        if (null != channel.getCreator())
            channelVO.setCreatorVO(Operator2OperatorVOConverter.converter(channel.getCreator()));
        return channelVO;
    }

    public static List<ChannelVO> converter(List<Channel> channelList) {
        List<ChannelVO> channelVOList = new ArrayList<>();
        ChannelVO channelVO;
        for (Channel channel : channelList) {
            channelVO = converter(channel);
            channelVOList.add(channelVO);
        }
        return channelVOList;
    }
}
