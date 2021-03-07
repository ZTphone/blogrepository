import com.example.myblogproject.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class MyTest {



    @Test
    public void test01(){
        //192.168.175.133
        Jedis jedis = new Jedis("192.168.175.133",6379);
        System.out.println(jedis.ping()+"////////////");
        jedis.set("hello","hi");
        System.out.println(jedis.get("hello")+"******");

    }
}
