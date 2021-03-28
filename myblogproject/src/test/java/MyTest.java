import com.example.myblogproject.entity.Collect;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class MyTest {


    @Test
    public void test01(){
        //192.168.175.133
        Jedis jedis = new Jedis("192.168.175.133",6379);
        System.out.println(jedis.ping()+"////////////");
        jedis.set("hello","hi");
        System.out.println(jedis.get("hello")+"******");

    }

    @Test
    public void test02(){
        Collect collect = new Collect(1,2,3);
//        log.info("collect",collect);
//        log.info("hello");
    }
}
