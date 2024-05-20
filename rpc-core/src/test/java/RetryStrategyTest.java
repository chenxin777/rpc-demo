import com.chenxin.rpc.fault.retry.NoRetryStrategy;
import com.chenxin.rpc.fault.retry.RetryStrategy;
import com.chenxin.rpc.model.RpcResponse;
import org.junit.Test;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class RetryStrategyTest {

    RetryStrategy retryStrategy = new NoRetryStrategy();

    @Test
    public void doRetry() {
        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception ex) {
            System.out.println("重试多次失败");
            ex.printStackTrace();
        }
    }
}
