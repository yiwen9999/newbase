package com.hex.newbase.form;

import lombok.Data;

import java.util.List;

/**
 * User: hexuan
 * Date: 2019/3/21
 * Time: 2:32 PM
 */
@Data
public class FileListForm {
    /**
     * 上传文件id集合
     */
    private List<String> fileIdList;

    /**
     * 上传文件路径集合
     */
    private List<String> filePathList;
}
