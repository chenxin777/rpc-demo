package com.chenxin.example.provider;

import com.chenxin.example.common.service.UserService;
import com.chenxin.rpc.bootstrap.ProviderBoostrap;
import com.chenxin.rpc.model.ServiceRegisterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class ProviderExample {

    public static void main(String[] args) {
        // 要注册的服务
        List<ServiceRegisterInfo> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);
        // 服务提供者初始化
        ProviderBoostrap.init(serviceRegisterInfoList);
    }
}
