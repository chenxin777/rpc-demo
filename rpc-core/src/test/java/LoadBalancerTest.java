import com.chenxin.rpc.loadbalancer.ConsistentHashLoadBalancer;
import com.chenxin.rpc.loadbalancer.LoadBalancer;
import com.chenxin.rpc.model.ServiceMetaInfo;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class LoadBalancerTest {

    final LoadBalancer loadBalancer = new ConsistentHashLoadBalancer();
    @Test
    public void select() {
        // 请求参数
        HashMap<String, Object> requestParams = new HashMap<>();
        requestParams.put("methodName", "apple");
        // 服务列表
        ServiceMetaInfo serviceMetaInfo1 = new ServiceMetaInfo();
        serviceMetaInfo1.setServiceName("myService1");
        serviceMetaInfo1.setServiceVersion("1.0");
        serviceMetaInfo1.setServiceHost("localhost");
        serviceMetaInfo1.setServicePort(1234);
        ServiceMetaInfo serviceMetaInfo2 = new ServiceMetaInfo();
        serviceMetaInfo2.setServiceName("myService2");
        serviceMetaInfo2.setServiceVersion("2.0");
        serviceMetaInfo2.setServiceHost("chen");
        serviceMetaInfo2.setServicePort(80);
        ServiceMetaInfo serviceMetaInfo3 = new ServiceMetaInfo();
        serviceMetaInfo3.setServiceName("myService3");
        serviceMetaInfo3.setServiceVersion("3.0");
        serviceMetaInfo3.setServiceHost("xin");
        serviceMetaInfo3.setServicePort(90);
        List<ServiceMetaInfo> serviceMetaInfoList = Arrays.asList(serviceMetaInfo1, serviceMetaInfo2, serviceMetaInfo3);
        // 连续调用3次
        ServiceMetaInfo serviceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        System.out.println("第一次：" + serviceMetaInfo);
        Assert.assertNotNull(serviceMetaInfo);
        serviceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        System.out.println("第二次：" + serviceMetaInfo);
        Assert.assertNotNull(serviceMetaInfo);
        serviceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        System.out.println("第三次：" + serviceMetaInfo);
        Assert.assertNotNull(serviceMetaInfo);
    }
}
