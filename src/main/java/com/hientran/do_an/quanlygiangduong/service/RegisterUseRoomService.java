package com.hientran.do_an.quanlygiangduong.service;

import com.hientran.do_an.quanlygiangduong.repository.RegisterUseRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUseRoomService {
    @Autowired
    private RegisterUseRoomRepository registerUseRoomRepository;

}
