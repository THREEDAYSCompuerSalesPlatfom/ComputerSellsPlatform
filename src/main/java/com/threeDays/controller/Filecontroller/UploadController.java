package com.threeDays.controller.Filecontroller;

import com.threeDays.POJO.BigGoods;
import com.threeDays.service.BigGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;

/**
 * 前端用表单上传图片
 * <form action="upload" method="post" enctype="multipart/form-data">
 * 选择图片:<input type="file" name="file" accept="image/*" /> <br>
 * <input type="submit" value="立刻上传">
 * </form>
 */
@Controller
public class UploadController {
    String parentDir="D:"+File.separator+File.separator+"goods"+File.separator;//图片储存的目录
    @Autowired
    private BigGoodsService bigGoodsService;

    /**
     * 上传首页展示图(这方法用一次过后再用功能为更新首页图)
     */
    @PostMapping("/image/uploadindex") // 等价于 @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadindex(@RequestParam("goodsid") BigInteger goods_id, @RequestParam("file") MultipartFile file, Model m, HttpServletRequest request) {//1. 接受上传的文件  @RequestParam("file") MultipartFile file
        try {
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            //同时辅以sellerid为文件名，以&分割
            String fileName = "index";
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名

            String destFileName = parentDir + goods_id + File.separator + fileName;
            System.out.println(destFileName);
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
            File destFile = new File(destFileName);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            //5.把浏览器上传的文件复制到希望的位置
            file.transferTo(destFile);
            //6.把文件名放在model里，以便后续显示用
            m.addAttribute("fileName", fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }

        return "sucess";
    }

    /**
     * 获取首页展示图
     */
    @RequestMapping(value = "/image/getIxdexImage")
    public void getIxdexImage(@RequestParam("goodsid") BigInteger goods_id, HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
           // String destFileName="D:"+File.separator+File.separator+"goods"+File.separator+goods_id+File.separator+"index";
           String destFileName = parentDir+ goods_id + File.separator + "index";
            File file = new File(destFileName);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 前端用表单上传图片
     * <form action="upload" method="post" enctype="multipart/form-data">
     * 选择图片:<input type="file" name="file" accept="image/*" /> <br>
     * <input type="submit" value="立刻上传">
     * </form>
     *
     * (这方法用一次过后再用功能为更新此图)
     */
    @PostMapping("/image/upload") // 等价于 @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("goodsid") BigInteger goods_id, @RequestParam("file") MultipartFile file, Model m, HttpServletRequest request) {//1. 接受上传的文件  @RequestParam("file") MultipartFile file
        try {
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            //同时辅以sellerid为文件名，以&分割
            String fileName = System.currentTimeMillis() + "&" + bigGoodsService.getBigGoods(goods_id).getSellerId();
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
                 String destFileName=parentDir+goods_id+File.separator;
            //String destFileName = request.getServletContext().getRealPath("") + "goods" + File.separator + goods_id + File.separator + fileName;
            System.out.println(destFileName);
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
            File destFile = new File(destFileName);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            //5.把浏览器上传的文件复制到希望的位置
            file.transferTo(destFile);
            //6.把文件名放在model里，以便后续显示用
            m.addAttribute("fileName", fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }

        return "sucess";
    }


    /**
     * 输入商品id返回图片名字列表
     */
    @PostMapping("/image/AllImageNames") // 等价于 @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String[] AllImageNames(@RequestParam("goodsid") BigInteger goods_id, HttpServletRequest request) {
        BigInteger seller_id = (BigInteger) request.getSession().getAttribute("Seller_id");
//        if(bigGoodsService.getBigGoods(goods_id).getSellerId().equals(seller_id)){
//            return null;
//        }
        String destFileName = parentDir+ goods_id + File.separator;
        File file = new File(destFileName);
        return file.list();

    }

    /**
     * 输入商品id和图片名字获得图片
     */
    @RequestMapping(value = "/image/get")
    public void getImage(@RequestParam("goodsid") BigInteger goods_id, @RequestParam("filename") String name, HttpServletRequest request, HttpServletResponse response) {
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            String destFileName =parentDir+ goods_id + File.separator + name;
            File file = new File(destFileName);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 按图片名删除图片
     * true当且仅当文件或目录被成功删除时; false否则
     */
    @RequestMapping(value = "/image/deleteImage")
    @ResponseBody
    public boolean deleteImage(@RequestParam("goodsid")BigInteger biggoodsid, @RequestParam("filename")String filename, HttpServletRequest request) {
        String destFileName = parentDir+ biggoodsid + File.separator + filename;
        System.out.println(destFileName);
        File file = new File(destFileName);
        return file.delete();
    }


}