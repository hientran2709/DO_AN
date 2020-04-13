package com.hientran.do_an.quanlygiangduong.web.rest;

import com.hientran.do_an.quanlygiangduong.client.dto.AddNewPhongRequest;
import com.hientran.do_an.quanlygiangduong.client.dto.AddNewPhongResponse;
import com.hientran.do_an.quanlygiangduong.service.PhongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phong")
public class PhongResource {
    @Autowired
    private PhongService service;

    @PostMapping("/addnew")
    public AddNewPhongResponse addnew(AddNewPhongRequest request){

        AddNewPhongResponse response = service.addNewPhong(request);

        return response;
    }
}
