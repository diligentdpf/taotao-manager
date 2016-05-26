package diao.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import diao.taotao.common.util.JsonUtils;
import diao.taotao.common.util.PictureResult;
import diao.taotao.service.PictureService;
@Controller
@RequestMapping("/pic")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @RequestMapping("/upload")
    @ResponseBody
    public String uploda(MultipartFile uploadFile) throws Exception {
        Map<String,Object> map=null;
        // 调用service上传图片
        map = pictureService.uploadFile(uploadFile);
        // 返回上传结果
        String json=JsonUtils.objectToJson(map);
        return json;

    }
}
