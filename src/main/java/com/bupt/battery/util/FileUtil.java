package com.bupt.battery.util;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;

public class FileUtil {

    // 文件上传工具类服务方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String getNotFileHtml(String fileName,String message){
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        sb.append("</head><script>alert('"+message+"')</script>");
        sb.append("<body>");
        sb.append("<div id='errorInfo' ");
        sb.append(" fileName='"+fileName+"'");
        sb.append(" message='"+message+"'>");
        sb.append("11111111</div>");
        sb.append("</body>");
        sb.append("</html>");
        System.out.println(sb.toString());
        return sb.toString();
    }
}

