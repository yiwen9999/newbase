package com.hex.newbase.controller;

import com.hex.newbase.converter.Operator2OperatorVOConverter;
import com.hex.newbase.domain.Operator;
import com.hex.newbase.domain.Result;
import com.hex.newbase.enums.ResultEnum;
import com.hex.newbase.service.OperatorService;
import com.hex.newbase.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * 文件服务回调接口
 * User: hexuan
 * Date: 2019/3/18
 * Time: 4:29 PM
 */
@RestController
public class FileController {

    @Autowired
    private OperatorService operatorService;

    /**
     * 保存operator的icon信息
     *
     * @param operatorId 操作账号id
     * @param fileId     文件id
     * @param filePath   文件路径
     * @return
     */
    @PostMapping("/saveOperatorIcon")
    public Result saveOperatorIcon(String operatorId, String fileId, String filePath) {
        return updateOperatorIconInfo(operatorId, fileId, filePath);
    }

    /**
     * 删除operator的icon信息
     *
     * @param operatorId 操作账号id
     * @return
     */
    @PostMapping("/deleteOperatorIcon")
    public Result deleteOperatorIcon(String operatorId) {
        return updateOperatorIconInfo(operatorId, null, null);
    }

    /**
     * 更新operator的icon信息
     *
     * @param operatorId 操作账号id
     * @param iconId     头像图片id
     * @param iconPath   头像图片路径
     * @return
     */
    private Result updateOperatorIconInfo(String operatorId, String iconId, String iconPath) {
        if (StringUtils.isBlank(operatorId)) {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
        Optional<Operator> operatorOptional = operatorService.findById(operatorId);
        Operator operator;
        if (operatorOptional.isPresent()) {
            operator = operatorOptional.get();
            operator.setIconId(iconId);
            operator.setIconPath(iconPath);
            operator = operatorService.save(operator);
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
        return ResultUtil.success(Operator2OperatorVOConverter.converter(operator));
    }

}
