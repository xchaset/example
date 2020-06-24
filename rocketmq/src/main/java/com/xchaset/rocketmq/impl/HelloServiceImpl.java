package com.xchaset.rocketmq.impl;

import com.xchaset.rocketmq.HelloService;

public class HelloServiceImpl implements HelloService {

    private String nameServer;

    private String host;

    public HelloServiceImpl(String nameServer, String host) {
        this.nameServer = nameServer;
        this.host = host;
    }

    @Override
    public void sayHello() {
        System.out.println(nameServer + host);
    }
}
