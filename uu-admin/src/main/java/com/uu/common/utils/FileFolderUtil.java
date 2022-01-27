package com.uu.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.uu.exception.SCException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by will on 2016/7/7.
 */
@Slf4j
public class FileFolderUtil {

    public static String getFileUrl() {
        String dateStr = DateUtil.generateTimeStamp();
        dateStr = dateStr.substring(0, 8);
        return dateStr;
    }

    public static void isExist(String path) {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
    }
    public static void isExists(String path) {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
//            file.setReadable(true);
            file.mkdirs();
        }
    }

    public static void isDeletedFile(String path) {
        File file = new File(path);
        if (file.exists() && !file.isDirectory()) {
            file.delete();
            System.out.println("文件已删除");
        }
    }

    public static JSONObject upload(MultipartFile file, String read_url, String outFileRootPath) throws IOException {
        //文件夹名称
        String path = FileFolderUtil.getFileUrl();
        //文件夹路径
        String rpath = outFileRootPath + path; //服务器上
        log.info("文件上传路径：" + rpath);
        //生成文件夹
        FileFolderUtil.isExists(rpath);
        //根据uid生成名称
        String uid = GUID.getGUID();
        String temp = file.getContentType();
        String suffix = temp.substring(temp.lastIndexOf("/") + 1);

        if(WDWUtil.isExcel2007(file.getOriginalFilename())){
            suffix = "xlsx";
        }
        if(WDWUtil.isExcel2003(file.getOriginalFilename())){
            suffix = "xls";
        }
        String fileName = uid + "." + suffix;
        //根据路径和文件名生成新的空白文件
        String realPath = rpath + File.separator + fileName;
        File targetFile = new File(realPath);
        targetFile.createNewFile();

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SCException("图片保存失败");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fileUrl", read_url + path + File.separator  + fileName);
        jsonObject.put("realPath", realPath);
        return jsonObject;
    }

}
