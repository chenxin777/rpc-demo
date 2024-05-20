import com.chenxin.rpc.config.RpcConfig;
import com.chenxin.rpc.utils.ConfigYmlUtils;
import org.junit.Test;

/**
 * @author fangchenxin
 * @description
 * @date
 * @modify
 */
public class ConfigTest {

    @Test
    public void test() {
        RpcConfig rpcConfig11 = ConfigYmlUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpcConfig11);
    }

}
