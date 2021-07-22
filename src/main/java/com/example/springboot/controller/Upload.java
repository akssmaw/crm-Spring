package com.example.springboot.controller;

import com.example.springboot.services.OrderServices;
import com.example.springboot.services.SetuserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
@Api(tags = "上传订单")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "Upload")
public class Upload {
   private Date dNow = new Date( );
    private SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

    @Autowired
    private OrderServices orderServices;

    @Autowired
    private SetuserServices setuserServices;
    @ApiOperation("上传订单 提交是888")
    @PostMapping("/UploadOrder")
    @Transactional
    public Object UploadOrder(@RequestParam("file") MultipartFile file,
                         @RequestParam("cid") int cid,
                         @RequestParam("phone")String phone,
                         @RequestParam("uid")int uid,
                         @RequestParam("uname")String uname,
                         @RequestParam("stuname")String stuname,
                         @RequestParam("prepay")double prepay,
                         @RequestParam("total")double total,
                         @RequestParam("account")String account,
                         @RequestParam("con")String con,
                          @RequestParam("orderproject")String orderproject,
                              @RequestParam("studentype")String studentype
    ) {

        Long timechuo = Calendar.getInstance().getTimeInMillis();

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get("F:\\upload\\"+ft.format(dNow)+"-"+timechuo+"-"+file.getOriginalFilename());
            Files.write(path,bytes);
            Thread.sleep(500);
            String zipurl= path.getFileName().toString();

            System.out.println(bytes.length);

            setuserServices.UpdateSetUserByCidAndUid(cid,uid,888);

            if(prepay==total){
                System.out.println(prepay);
                System.out.println(total);
                System.out.println("金额相等");
                return orderServices.InsertOrder2(cid, phone, uid, uname, stuname, zipurl, prepay, total, account, con,orderproject,studentype) ;

            }else {
                System.out.println(prepay);
                System.out.println(total);
                System.out.println("金额不相等");
                return orderServices.InsertOrder(cid, phone, uid, uname, stuname, zipurl, prepay, total, account, con,orderproject,studentype) ;

            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            throw new IllegalArgumentException("程序报错了");
        }


    }

    @ApiOperation("根据销售id状态查询订单 ")
    @PostMapping("/SelectOrderByUid/{uid}/{state}/{page}")
    public Object SelectOrderByUid(@PathVariable int uid, @PathVariable int state,@PathVariable int page ) {


        return  orderServices.SelectOrderByUid(uid, state, page);

    }

    @ApiOperation("根据销售id查询有多少客户没有交 尾款 ")
    @PostMapping("/SelectOrderByUidWk/{uid}/{page}")
    public Object SelectOrderByUidWk(@PathVariable int uid,@PathVariable int page ) {


        return  orderServices.SelectOrderByUidWk(uid, page);

    }

    @ApiOperation("管理员查询")
    @PostMapping("/SelectOrderByAdmin/{state}/{page}")
    public Object SelectOrderByAdmin(@PathVariable int state,@PathVariable int page ) {


        return  orderServices.SelectOrderByAdmin(state, page);

    }

    @ApiOperation("拒绝订单")
    @PostMapping("/SelectOrderByOidNo/{oid}/{rescon}")
    public Object SelectOrderByOidNo(@PathVariable int oid,@PathVariable String rescon ) {


        return  orderServices.SelectOrderByOidNo(oid, rescon);

    }

    @ApiOperation("查询没有付全款的人")
    @PostMapping("/SelectOrderByAdminNo/{state}/{page}")
    public Object SelectOrderByAdminNo(@PathVariable int state,@PathVariable int page ) {


        return  orderServices.SelectOrderByAdminNo(state, page);

    }

    @ApiOperation("查询已经通过的用户")
    @PostMapping("/SelectOrderByAdminYes/{state}/{page}")
    public Object SelectOrderByAdminYes(@PathVariable int state,@PathVariable int page ) {


        return  orderServices.SelectOrderByAdminYes(state, page);

    }
    @ApiOperation("通过订单")
    @PostMapping("/SelectOrderByOidyes/{oid}/{ordercon}")
    public Object SelectOrderByOidyes(@PathVariable int oid,@PathVariable String ordercon ) {


        return  orderServices.SelectOrderByOidyes(oid, ordercon);

    }
    @ApiOperation("根据cid查询订单 ")
    @PostMapping("/SecletOrderByCid/{cid}")
    public Object SecletOrderByCid(@PathVariable int cid ) {


        return  orderServices.SecletOrderByCid(cid);

    }
}
