package com.kulongtai.mpstore.controller;

import com.kulongtai.mpstore.common.FileUploadUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 上传公共方法 tjh 2019/01/08
 * </p>
 *
 * @author wanghk
 * @since 2019-01-04
 */
@Controller
@Api(description = "图片上传接口")
@RequestMapping("/api/file")
public class UploadController {
    @Autowired
    FileUploadUtil fileUploadUtil;



 /*
    @RequestMapping("/upload")
    @ApiOperation(value = "上传图片")
    @ResponseBody
    public FileUploadResult business(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return fileUploadUtil.fileUpload(file);
    }


    @PostMapping(value = "fileUpload")
    public void fileUpload(MultipartHttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        //告知浏览器编码方式;防止乱码
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        String token = request.getParameter("token");
        if (!StringUtils.isNotBlank(token)) {
            writer.println(getError("没有权限"));
            return;
        }

        HashMap<String, String> extMap = new HashMap<>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        long maxSize = 1024L*1024L;
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
            FileUploadResult fileUploadResult = fileUtils.fileUpload(item);
            if (fileUploadResult.isSuccess()) {
                String downUrl = fileUtils.getDownloadUrl(fileUploadResult.getFileUrl());
                JSONObject obj = new JSONObject();
                obj.put("error", 0);
                obj.put("url", downUrl);
                writer.println(obj.toJSONString());
            } else {
                writer.println(getError(fileUploadResult.getMessage()));
            }
            return;
        }
    }

    private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toJSONString();
    }
*/
}
