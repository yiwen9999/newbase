package com.hex.newbase.controller;

import com.hex.newbase.converter.Channel2ChannelVOConverter;
import com.hex.newbase.domain.Channel;
import com.hex.newbase.domain.Operator;
import com.hex.newbase.domain.Result;
import com.hex.newbase.enums.ResultEnum;
import com.hex.newbase.exception.HexException;
import com.hex.newbase.form.ChannelForm;
import com.hex.newbase.service.ChannelService;
import com.hex.newbase.util.HexUtil;
import com.hex.newbase.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/4/16
 * Time: 9:26 PM
 */
@CrossOrigin
@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    /**
     * 新增&修改频道
     *
     * @param channelForm
     * @param bindingResult
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/saveChannel")
    public Result saveChannel(@Valid ChannelForm channelForm, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        HexUtil.validateBindResult(bindingResult);
        Channel channel = new Channel();
        if (StringUtils.isNotBlank(channelForm.getId())) {
            Optional<Channel> channelOptional = channelService.findById(channelForm.getId());
            if (channelOptional.isPresent()) {
                channel = channelOptional.get();
            } else {
                throw new HexException(ResultEnum.ERROR_PARAM);
            }
        }
        BeanUtils.copyProperties(channelForm, channel);
        if (StringUtils.isNotBlank(channelForm.getParentChannelId())) {
            Optional<Channel> parentChannelOptional = channelService.findById(channelForm.getParentChannelId());
            if (parentChannelOptional.isPresent()) {
                channel.setParentChannel(parentChannelOptional.get());
            }
        }
        /**
         * id为空说明新增，保存创建人
         */
        if (StringUtils.isBlank(channelForm.getId())) {
            Operator operator = HexUtil.getOperator(httpServletRequest);
            if (null != operator) {
                channel.setCreator(operator);
            }
        }

        return ResultUtil.success(Channel2ChannelVOConverter.converter(channelService.save(channel)));
    }

    /**
     * 根据id删除频道
     *
     * @param channelId
     * @return
     */
    @GetMapping("/deleteChannelById")
    public Result deleteChannelById(String channelId) {
        channelService.delete(channelId);
        return ResultUtil.success();
    }

    /**
     * 根据id浏览频道
     *
     * @param channelId
     * @return
     */
    @GetMapping("/findChannelById")
    public Result findChannelById(String channelId) {
        Optional<Channel> channelOptional = channelService.findById(channelId);
        if (!channelOptional.isPresent()) {
            throw new HexException(ResultEnum.ERROR_PARAM);
        }
        return ResultUtil.success(Channel2ChannelVOConverter.converter(channelOptional.get()));
    }

    /**
     * 获取所有在用顶级频道集合
     *
     * @return
     */
    @GetMapping("/findUsingTopChannelList")
    public Result findUsingTopChannelList() {
        return ResultUtil.success(Channel2ChannelVOConverter.converter(channelService.findUsingTopChannelList(new Integer(2))));
    }

    /**
     * 获取所有顶级频道集合
     *
     * @return
     */
    @GetMapping("/findTopChannelList")
    public Result findTopChannelList() {
        return ResultUtil.success(Channel2ChannelVOConverter.converter(channelService.findTopChannelList()));
    }

    /**
     * 根据id停启用频道
     * @return
     */
    @GetMapping("/updateChannelState")
    public Result updateChannelState(String channelId){
        return ResultUtil.success(Channel2ChannelVOConverter.converter(channelService.updateChannelStateById(channelId)));
    }
}
