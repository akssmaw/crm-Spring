package com.example.springboot.services.Impl;

import com.example.springboot.dao.OrderMapper;
import com.example.springboot.entity.Order;
import com.example.springboot.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImpl implements OrderServices {
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public int InsertOrder(int cid, String phone, int uid, String uname, String stuname, String zipurl, double prepay, double total, String account, String con, String orderproject,String studentype) {
        return orderMapper.InsertOrder(cid, phone, uid, uname, stuname, zipurl, prepay, total, account, con, orderproject,studentype);
    }

    @Override
    public List<Order> SelectOrderByUid(int uid, int state, int page) {
        return orderMapper.SelectOrderByUid(uid, state, 10*(page-1));
    }

    @Override
    public List<Order> SelectOrderByUidWk(int uid, int page) {
        return orderMapper.SelectOrderByUidWk(uid, 10*(page-1));
    }

    @Override
    public int InsertOrder2(int cid, String phone, int uid, String uname, String stuname, String zipurl, double prepay, double total, String account, String con, String orderproject,String studentype) {
        return orderMapper.InsertOrder2(cid, phone, uid, uname, stuname, zipurl, prepay, total, account, con, orderproject,studentype);
    }


    @Override
    public List<Order> SelectOrderByAdmin(int state, int page) {
        return orderMapper.SelectOrderByAdmin(state, 10*(page-1));
    }

    @Override
    public List<Order> SelectOrderByAdminNo(int state, int page) {
        return orderMapper.SelectOrderByAdminNo(state, 10*(page-1));
    }

    @Override
    public List<Order> SelectOrderByAdminYes(int state, int page) {
        return orderMapper.SelectOrderByAdminYes(state, page);
    }

    @Override
    public int SelectOrderByOidNo(int oid, String rescon) {
        return orderMapper.SelectOrderByOidNo(oid, rescon);
    }

    @Override
    public int SelectOrderByOidyes(int oid, String ordercon) {
        return orderMapper.SelectOrderByOidyes(oid, ordercon);
    }

    @Override
    public int updateOrderByOidAndPrepay(int oid, double prepay) {
        return orderMapper.updateOrderByOidAndPrepay(oid, prepay);
    }

    @Override
    public int updateOrderByOidAndPrepay2(int oid, double prepay) {
        return orderMapper.updateOrderByOidAndPrepay2(oid, prepay);
    }

    @Override
    public List<Order> SecletOrderByCid(int cid) {
        return orderMapper.SecletOrderByCid(cid);
    }
}
