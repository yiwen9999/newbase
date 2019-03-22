package com.hex.newbase.controller;

import com.alibaba.fastjson.JSONObject;
import com.hex.newbase.domain.Operator;
import com.hex.newbase.domain.Result;
import com.hex.newbase.enums.ResultEnum;
import com.hex.newbase.form.FileListForm;
import com.hex.newbase.service.OperatorService;
import com.hex.newbase.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * User: hexuan
 * Date: 2019/3/18
 * Time: 4:29 PM
 */
@RestController
public class FileController {

    @Autowired
    private OperatorService operatorService;

    @Value("${web.file-server-path}")
    private String fileServerPath;

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
        if (operatorOptional.isPresent()) {
            Operator operator = operatorOptional.get();
            operator.setIconId(iconId);
            operator.setIconPath(iconPath);
            operatorService.save(operator);
        } else {
            return ResultUtil.error(ResultEnum.ERROR_PARAM.getCode(), ResultEnum.ERROR_PARAM.getMsg());
        }
        return ResultUtil.success();
    }

    @PostMapping("/testClient")
    public Result testClient(MultipartFile file) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());

        FileSystemResource fileSystemResource = new FileSystemResource(file.getOriginalFilename());

        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("fileName", file.getOriginalFilename());
        param.add("jarFile", fileSystemResource);

        JSONObject jsonObject = restTemplate.postForObject(fileServerPath + "/saveFile", param, JSONObject.class);


        return ResultUtil.success(jsonObject);
    }

    @GetMapping("/testClient2")
    public Object testClient2() {
        FileSystemResource fileSystemResource = new FileSystemResource(new File("/Users/hexuan/Desktop/WechatIMG5.jpeg"));
        RestTemplate rest = new RestTemplate();
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("jarFile", fileSystemResource);
        param.add("fileName", "WechatIMG5.jpeg");
        JSONObject jsonObject = rest.postForObject(fileServerPath + "/testFile", param, JSONObject.class);
        return jsonObject;
    }

    @PostMapping("/testSaveFile")
    public Result testSaveFile(String fileId, String filePath, String operatorId) {
        System.out.println(fileId);
        System.out.println(filePath);
        System.out.println(operatorId);
        return ResultUtil.success();
    }

    @PostMapping("/testSaveFileList")
    public Result testSaveFileList(FileListForm fileListForm, String operatorId, String msg) {
        System.out.println(fileListForm.getFileIdList().toString());
        System.out.println(fileListForm.getFilePathList().toString());
        System.out.println(operatorId);
        System.out.println(msg);
        return ResultUtil.success();
    }
}
