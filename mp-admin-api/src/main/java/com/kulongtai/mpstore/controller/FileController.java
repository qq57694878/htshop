package com.kulongtai.mpstore.controller;

import com.kulongtai.mpstore.common.R;
import com.xiaoleilu.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Administrator on 2019/5/23 0023.
 */
@Controller
@Slf4j
public class FileController {

    @Value("${mpstore.service-url}")
    private String serverUrl;

    @Value("${mpstore.file.upload-path}")
    private String uploadPath;

    @GetMapping("/api/media/**")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        String contentPath = request.getContextPath();
        String filePath = uri.replaceAll("/api/media", "");
        //获得需下载的文件
        File file = new File(uploadPath + filePath);
        uri = uri.replace('\\', '/');
        String fileName = uri.substring(uri.lastIndexOf("/") + 1);
        //设置response 参数

        String contentType = Files.probeContentType(Paths.get(file.getPath()));
        response.setContentType(contentType);
        if (!isAllow(contentType)) {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "utf-8"));
        }
        InputStream is = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        IOUtils.copy(is, os);
        is.close();
        os.close();
    }

    public boolean isAllow(String contentType) {
        List<String> array = Arrays.asList("image/bmp", "image/gif", "image/jpg", "image/jpeg", "image/png", "image/tiff", "image/webp");
        return array.stream().anyMatch(item -> item.equals(contentType));
    }

    @ResponseBody
    @PostMapping(value = "/api/upload")
    public R fileUpload(MultipartHttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        List<String> list = new ArrayList<>();
        List<MultipartFile> files = request.getFiles("file");
        Iterator<MultipartFile> itr = files.iterator();
        while (itr.hasNext()) {
            MultipartFile item = itr.next();
            String fileName = item.getOriginalFilename();
            String extName = getFileExtName(fileName);
            String newFileName = createFileName(extName);
            //存储的文件
            File file = new File(uploadPath + File.separator + newFileName);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            IOUtils.copy(item.getInputStream(), new FileOutputStream(file));
            list.add(getDownloadUrl(File.separator + newFileName));
        }
        return new R(list.size() > 1 ? list : list.get(0));
    }

    @PostMapping(value = "/api/public/upload4kind")
    public void upload4kind(MultipartHttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        //告知浏览器编码方式;防止乱码
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        HashMap<String, String> extMap = new HashMap<>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        long maxSize = 1024L * 1024L;
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        if (!ServletFileUpload.isMultipartContent(request)) {
            writer.println(getError("请选择文件"));
            return;
        }
        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }
        if (!extMap.containsKey(dirName)) {
            writer.println(getError("目录名不正确"));
            return;
        }
        List<MultipartFile> files = request.getFiles("imgFile");
        Iterator<MultipartFile> itr = files.iterator();
        while (itr.hasNext()) {
            MultipartFile item = itr.next();
            String fileName = item.getOriginalFilename();
            // 检查文件大小
            if (item.getSize() > maxSize) {
                writer.println(getError("上传文件大小超过限制"));
                return;
            }
            // 检查扩展名
            String fileExt = fileName.substring(
                    fileName.lastIndexOf('.') + 1).toLowerCase();
            if (!Arrays.asList(extMap.get(dirName).split(","))
                    .contains(fileExt)) {
                writer.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
                return;
            }
            //上传文件
            String extName = getFileExtName(fileName);
            String newFileName = createFileName(extName);
            //存储的文件
            File file = new File(uploadPath + File.separator + newFileName);
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            IOUtils.copy(item.getInputStream(), new FileOutputStream(file));
            String downUrl = getDownloadUrl(File.separator + newFileName);
            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", downUrl);
            writer.println(obj.toJSONString(1));
        }
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString(1);
    }
  /*  public Object upload4kind(MultipartHttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        //告知浏览器编码方式;防止乱码
        long maxSize = 10*1024L*1024L;
        if (!ServletFileUpload.isMultipartContent(request)) {
            return getError("请选择文件");
        }

        List<MultipartFile> files = request.getFiles("imgFile");
        Iterator<MultipartFile> itr = files.iterator();
        List<JSONObject> list = new ArrayList<>();
        while (itr.hasNext()) {
            MultipartFile item = itr.next();
            String fileName = item.getOriginalFilename();
            // 检查文件大小
            if (item.getSize() > maxSize) {
                return getError("上传文件大小超过限制");
            }

            String extName = getFileExtName(fileName);
            String newFileName =createFileName(extName);
            //存储的文件
            File file  =new File(uploadPath+File.separator+newFileName);
            File fileParent = file.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            IOUtils.copy(item.getInputStream(),new FileOutputStream(file));

            JSONObject obj = new JSONObject();
            obj.put("error", 0);
            obj.put("url", getDownloadUrl(File.separator+newFileName));
            list.add(obj);
        }
        return list.size()>1?list:list.get(0);
    }
*/


    private String getDownloadUrl(String filePath) {
        log.info("getDownloadUrl:" + serverUrl + "/api/media" + filePath);
        return serverUrl + "/api/media" + filePath;
    }


    public String getFileExtName(String fileName) {
        String ext = "";
        String[] ss = fileName.split("\\.");
        if (ss.length == 2) {
            ext = ss[1];
        }
        return ext;
    }

    public String createFileName(String ext) {
        String part1 = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
        String part2 = RandomStringUtils.randomAlphanumeric(3);
        return part1 + part2 + "." + ext;
    }

    /**
     * 文件扩展名是否允许
     *
     * @param fileType
     * @param ext
     * @return
     */
    public boolean isAllowedFileExt(String fileType, String ext) {
        HashMap<String, String> extMap = new HashMap<>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        return Arrays.asList(extMap.get(fileType).split(",")).contains(ext);
    }
}
