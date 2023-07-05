package com.sanley.coronavirus.service;

import com.sanley.coronavirus.entity.CheckOut;

import java.util.List;
public interface CheckOutService {
    void add(CheckOut checkOut);

    List<CheckOut> findAll(int page, int size);

    List<CheckOut> findByName(String name);
}
