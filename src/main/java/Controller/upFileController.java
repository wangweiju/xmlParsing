package Controller;

import Service.FileService;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.kit.PropKit;
import com.jfinal.upload.UploadFile;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@RequestMapping("/upload")
public class upFileController extends JbootController {
    @Inject
    private FileService fileService;
    /**
     * 返回图片上传界面
     */
    public void index(){
        String AvatarPath = fileService.findById(1).getAvatar();
        setAttr("AvatarPath",AvatarPath);
        render("/upfile.html");
    }

    /**
     *图片上传
     */
    public void uploadFile() {
        JSONObject jsonObject = new JSONObject();
        UploadFile uploadFile = getFile("upload","",104857860);//maxPostSize为默认为getFile方法上传文件的最大的默认大小为10485760（10 * 1024 * 1024） 即10M
        File file = uploadFile.getFile();
        String Suffix = file.getName().substring(file.getName().lastIndexOf("."));//获取图片后缀
        String newPath = PropKit.get("uploadpath");

        File f = new File(newPath);

        //判断是否存在该路径，如果不存在的话创建路径
        if(!f.exists()){
            f.mkdirs();
        }
        //保存新图片
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream  = null;
       try{
           String imgName = RandomStringUtils.randomAlphabetic(10) +Suffix;
           File savePath = new File(newPath+imgName);
           if(!savePath.isDirectory()){//判断是否是文件夹
               savePath.createNewFile();//创建空文件
           }
           fileInputStream = new FileInputStream(file);
           fileOutputStream = new FileOutputStream(savePath);

           byte[] b = new byte[1024];
           while (fileInputStream.read(b,0,1024) != -1){
               fileOutputStream.write(b,0,1024);
           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           try{
               if(fileInputStream!=null){
                   fileInputStream.close();
               }
               if (fileOutputStream!=null){
                   fileOutputStream.close();
               }
           }catch (Exception e){
               e.printStackTrace();
           }
       }

        //删除所有图片
        if(file.exists()){
            file.delete();
        }
        jsonObject.put("code",1);
        jsonObject.put("msg","上传成功");
        renderJson(jsonObject.toJSONString());
    }

    /**
     * 使用配置文件后的快速开发
     */
    public void uploadFile2(){
        JSONObject jsonObject = new JSONObject();
       UploadFile uploadFile =  getFile("upload");
        File file  =  uploadFile.getFile();
        String fileName = file.getName();
        String filePath = file.getPath();

        /**
         * 保存上传后的图片路径，将上传后的图片路径保存到数据库
         */
        Model.File file1 = new Model.File();
        file1.setAvatar(fileName);
        fileService.save(file1);


        jsonObject.put("code",1);
        jsonObject.put("msg","上传成功");
        jsonObject.put("fileName",fileName);
        jsonObject.put("filePath",filePath);
        renderJson(jsonObject.toJSONString());
        return;
    }
}
