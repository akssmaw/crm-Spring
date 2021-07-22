package com.example.springboot.controller;

import com.example.springboot.services.OrderServices;
import com.example.springboot.services.PrepayServices;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "prepay")
public class PrepayCon {
    @Autowired
    private OrderServices orderServices;
    @Autowired
   private PrepayServices prepayServices;
    @ApiOperation("增加付款击记录 并且修改order表单的金额 没有全款")
    @PostMapping("/insertPrepay/{oid}/{pcon}/{account}/{prepay}/{ordprepay}")
    public Object insertPrepay(@PathVariable int oid,@PathVariable String pcon,@PathVariable String account,@PathVariable double prepay,@PathVariable double ordprepay) {

        System.out.println(ordprepay);
        System.out.println(prepay);


        orderServices.updateOrderByOidAndPrepay(oid,ordprepay+prepay);
        return prepayServices.insertPrepay(oid,pcon,account,prepay) ;

    }
    @ApiOperation("增加付款击记录 并且修改order表单的金额 表示已经选择了全款")
    @PostMapping("/insertPrepay2/{oid}/{pcon}/{account}/{ordtotal}")
    public Object insertPrepay2(@PathVariable int oid,@PathVariable String pcon,@PathVariable String account,@PathVariable double ordtotal) {





        orderServices.updateOrderByOidAndPrepay2(oid,ordtotal);
        return prepayServices.insertPrepay(oid,pcon,account,ordtotal) ;

    }
    @ApiOperation("查询")
    @PostMapping("/selectPrepay/{oid}")
    public Object selectPrepay(@PathVariable int oid) {


        return  prepayServices.selectPrepay(oid);

    }
}
