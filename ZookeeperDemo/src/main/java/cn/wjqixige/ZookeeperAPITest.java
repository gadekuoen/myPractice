package cn.wjqixige;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

public class ZookeeperAPITest {

    @Test
    public void watchZnode() throws Exception {
        //1. 定制一个重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,1);
        //2. 获取客户端
        String connectionStr = "192.168.137.100:2181,192.168.137.110:2181,192.168.137.120:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, 8000, 8000, retryPolicy);
        //3. 启动客户端
        client.start();
        //4. 创建一个TreeCache对象，指定要监控的节点路径
        TreeCache treeCache = new TreeCache(client, "/hello");
        //5. 自定义一个监听器
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                ChildData data = event.getData();
                if ( data != null){
                    switch ( event.getType()){
                        case NODE_ADDED:
                            System.out.println("监控到有新增节点！");
                            break;
                        case NODE_REMOVED:
                            System.out.println("监控到有节点被溢出！");
                            break;
                        case NODE_UPDATED:
                            System.out.println("监控到有节点被更新！");
                        default:
                            break;
                    }
                }
            }
        });
        //6.开始监听
        treeCache.start();

        Thread.sleep(100000);
    }

    @Test
    public void createZnode() throws Exception {
        //1. 定制一个重试策略 param1: 重试间隔事件  param2：重试最大次数
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,1);
        //2. 获取一个客户端对象 param1:连接zk服务器列表；param2:
        String connectionStr = "192.168.137.100:2181,192.168.137.110:2181,192.168.137.120:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, 1000, 1000, retryPolicy);
        //3. 开启客户端
        client.start();
        //4. 创建节点
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/hello2","world".getBytes());
        //5. 关闭客户端
        client.close();
    }

    @Test
    public void createZnode2() throws Exception{
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000,1);
        CuratorFramework client = CuratorFrameworkFactory.newClient("node01:2181,node02:2181,node03:2181",3000,3000,retryPolicy);
        client.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/wujiang","world".getBytes());
        Thread.sleep(5000);
        client.close();
    }

    @Test
    public void setZnodeData() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 1);
        String connectionStr = "192.168.137.100:2181,192.168.137.110:2181,192.168.137.120:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, 1000, 1000, retryPolicy);
        client.start();
        client.setData().forPath("/hello","zookeeper".getBytes());
        client.close();
    }

    @Test
    public void getZnodeData() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 1);
        String connectionStr = "192.168.137.100:2181,192.168.137.110:2181,192.168.137.120:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionStr, 1000, 1000, retryPolicy);
        client.start();
        byte[] bytes = client.getData().forPath("/hello");
        System.out.println(new String(bytes));
        client.close();
    }
}
