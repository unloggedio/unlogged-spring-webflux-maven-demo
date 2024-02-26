package org.unlogged.springwebfluxdemo.service.flow1;

public class BaseServiceAbstract<RR, SR, MR> {

    protected RR redisRepository;
    protected SR sqlRepository;
    protected MR mongoRepo;
}
