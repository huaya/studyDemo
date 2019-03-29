package com.maxlong.study.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;

import java.util.List;

/**
 * @author ���� maxlong:
 * @version ����ʱ�䣺2016��6��28�� ����4:36:02
 * ��˵��
 */
public class ZooKeeperOperator extends AbstractZooKeeper {
    /**
     * <b>function:</b>�����־�̬��znode,��֧�ֶ�㴴��.�����ڴ���/parent/child�������,��/parent.�޷�ͨ��
     *
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException
     * @author cuiran
     * @createDate 2013-01-16 15:08:38
     */
    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        /**
         * �˴����õ���CreateMode��PERSISTENT  ��ʾThe znode will not be automatically deleted upon client's disconnect. 
         * EPHEMERAL ��ʾThe znode will be deleted upon the client's disconnect. 
         */
        this.zooKeeper.create(path, data, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * <b>function:</b>��ȡ�ڵ���Ϣ
     *
     * @param path
     * @throws KeeperException
     * @throws InterruptedException
     * @author cuiran
     * @createDate 2013-01-16 15:17:22
     */
    public void getChild(String path) throws KeeperException, InterruptedException {
        try {
            List<String> list = this.zooKeeper.getChildren(path, false);
            if (list.isEmpty()) {
                System.out.println(path + "��û�нڵ�");
            } else {
                System.out.println(path + "�д��ڽڵ�");
                for (String child : list) {
                    System.out.println("�ڵ�Ϊ��" + child);
                }
            }
        } catch (KeeperException.NoNodeException e) {
            throw e;
        }
    }

    public byte[] getData(String path) throws KeeperException, InterruptedException {
        return this.zooKeeper.getData(path, false, null);
    }

    public static void main(String[] args) {
        try {
            ZooKeeperOperator zkoperator = new ZooKeeperOperator();
            zkoperator.connect("localhost");

            byte[] data = new byte[]{'a', 'b', 'c', 'd'};

            String zktest = "ZooKeeper��Java API����";
            //zkoperator.create("/zookeeper/server3", zktest.getBytes());
            //zkoperator.create("/zookeeper/server1", zktest.getBytes());
            //zkoperator.create("/zookeeper/server2", zktest.getBytes());
            zkoperator.create("/zookeeper/server6", zktest.getBytes());

            System.out.println("�ڵ㺢����Ϣ:");
            zkoperator.getChild("/zookeeper");

            zkoperator.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
 