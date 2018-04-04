package com.maxlong.thrift.service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

import com.maxlong.thrift.config.ProductServerConfig;
import com.maxlong.thrift.demo.SharedService;
import com.maxlong.thrift.service.impl.ThriftShareServiceImpl;

/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 下午1:44:51
 * 类说明
 */
public class ThriftShareService extends SharedService{

    private TServerSocket serverTransport = null;

    public  void start(ProductServerConfig config){
        try {
            serverTransport = new TServerSocket(config.getPort());
            TProcessor processor = this.getProcesser();
            //TServer.Args.AcceptPolicy acceptPolicy = TServer.Args.AcceptPolicy.FAST_ACCEPT;;
            TServer.Args serverArgs =  new TServer.Args(serverTransport)
                    .protocolFactory(new TCompactProtocol.Factory())
                    .processor(processor);
            TServer server = new TSimpleServer(serverArgs);
            server.serve();
        } catch (TTransportException e) {
            throw  new RuntimeException(e);
        }
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    public  TProcessor getProcesser(){
        return new SharedService.Processor(new ThriftShareServiceImpl());
    };

}
 