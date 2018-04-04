package com.maxlong.thrift.client;
/**
 * @author 作者 maxlong:
 * @version 创建时间：2016年6月24日 下午6:06:00
 * 类说明
 */
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.maxlong.thrift.demo.RequestStruct;
import com.maxlong.thrift.demo.ResponseStruct;
import com.maxlong.thrift.demo.SharedService;

/**
 * Created by amosli on 14-8-12.
 */
public class HelloClientDemo {

  public static final String SERVER_IP = "localhost";
  public static final int SERVER_PORT = 5568;
  public static final int TIMEOUT = 30000;

  /**
   * @param args
   */
  public static void main(String[] args) {
    HelloClientDemo client = new HelloClientDemo();
    client.startClient("amosli");

  }

  /**
   * @param userName
   */
  public void startClient(String userName) {
    TTransport transport = null;
    try {
      transport = new TSocket(SERVER_IP, SERVER_PORT, TIMEOUT);
      TProtocol protocol = new TCompactProtocol(transport);
      SharedService.Client client = new SharedService.Client(protocol);
      transport.open();
      RequestStruct request = new RequestStruct();
      request.setRequestId("direct://maxlong_camelTestService");
      request.setRequestData("direct://maxlong_camelTestService");
      ResponseStruct result = client.SendReceive(request);
      System.out.println("Thrift client result =: " + result);
    } catch (TTransportException e) {
      e.printStackTrace();
    } catch (TException e) {
      e.printStackTrace();
    } finally {
      if (null != transport) {
        transport.close();
      }
    }
  }

}