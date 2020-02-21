package com.bison.inventory.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件上传工具类
 */
public class FileUploadUtil {


    private  static String rootPath = null;

    public static String getRootPath() {
        return rootPath = System.getProperty("user.dir");
    }

    /**
     * 单个文件上传
     * @param file 文件
     * @param path 上传路径
     * @return 全路径
     * @throws IOException
     */
    public static String uploadOne(MultipartFile file, String path) throws IOException {
        //新的文件存放路径加上新的文件名
        String newPath = rootPath + "/" +  path +"/"+file.getOriginalFilename();
        File file1 = new File(newPath);
        //判断文件目录是否存在
        if (!file1.getParentFile().exists()) {
            //创建文件存放目录
            //无论是几个/，都是创建一个文件夹
            //mkdirs(): 创建多层目录，如：E:/upload/2019
            //mkdir(): 只创建一层目录，如：E:upload
            //如果不加这一行不会创建新的文件夹，会报系统找不到路径
            file1.getParentFile().mkdirs();
        }
        //存储文件
        InputStream in  = file.getInputStream();
        FileUtils.copyInputStreamToFile(in, file1);
        //去掉目录名，保留文件总体路径，通过该路径访问图片

        return file1.getPath();
    }


    /**
     * 多文件上传
     *
     * @param files 文件
     * @param path  文件上传路径
     * @return
     * @throws IOException
     */
    public static String uploadMore(MultipartFile[] files, String path) throws IOException {
        //多文件文件名
        String uploadName = null;

        for (MultipartFile file : files) {
            //新的文件存放路径加上新的文件名
            String newPath = path +"/"+(file.getOriginalFilename());
            File file1 = new File(newPath);
            //判断文件目录是否存在
            if (!file1.getParentFile().exists()) {
                //创建文件存放目录
                //无论是几个/，都是创建一个文件夹
                //mkdirs(): 创建多层目录，如：E:/upload/2019
                //mkdir(): 只创建一层目录，如：E:upload
                //如果不加这一行不会创建新的文件夹，会报系统找不到路径
                file1.getParentFile().mkdirs();
            }
            //存储文件
            file.transferTo(file1);
            if (uploadName == null) {
                uploadName = newPath.substring(newPath.lastIndexOf(path)).replace(path, "");
            } else {
                uploadName = uploadName + "," + newPath.substring(newPath.lastIndexOf(path)).replace(path, "");
            }
        }
        return uploadName;
    }
}
