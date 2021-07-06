package com.example.springboot.dao;

import com.example.springboot.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
//增加订单
    int InsertOrder(
            @Param("cid")int cid,
            @Param("phone")String phone,
            @Param("uid")int uid,
            @Param("uname")String uname,
            @Param("stuname")String stuname,
            @Param("zipurl")String zipurl,
            @Param("prepay")double prepay,
            @Param("total")double total,
            @Param("account")String account,
            @Param("con")String con,
            @Param("orderproject")String orderproject,
            @Param("studentype")String studentype
            );
    //根据销售id状态查询订单
    List<Order> SelectOrderByUid(@Param("uid")int uid, @Param("state")int state, @Param("page")int page);
//查询没有交全款的客户
    List<Order> SelectOrderByUidWk(@Param("uid")int uid, @Param("page")int page);


    int InsertOrder2(
            @Param("cid")int cid,
            @Param("phone")String phone,
            @Param("uid")int uid,
            @Param("uname")String uname,
            @Param("stuname")String stuname,
            @Param("zipurl")String zipurl,
            @Param("prepay")double prepay,
            @Param("total")double total,
            @Param("account")String account,
            @Param("con")String con,
            @Param("orderproject")String orderproject,
             @Param("studentype")String studentype
    );

    //管理员查询全部 审核的
    List<Order> SelectOrderByAdmin(@Param("state")int state,@Param("page")int page);

    //管理员查询全部 没有支付全款的人
    List<Order> SelectOrderByAdminNo(@Param("state")int state,@Param("page")int page);

    //查询已经通过的订单
    List<Order> SelectOrderByAdminYes(@Param("state")int state,@Param("page")int page);
    //根据oid拒绝订单
    int SelectOrderByOidNo(@Param("oid")int oid,@Param("rescon")String rescon);
//根据oid 改成审核通过 并且添加备注
    int SelectOrderByOidyes(@Param("oid")int oid,@Param("ordercon")String ordercon);
//修改金额
    int updateOrderByOidAndPrepay(@Param("oid")int oid,@Param("prepay")double prepay);
//修改金额和状态
    int updateOrderByOidAndPrepay2(@Param("oid")int oid,@Param("prepay")double prepay);

    //根据cid查询历史订单
    List<Order> SecletOrderByCid(@Param("cid")int cid);
}
