package com.bin.framework.redis.ops;

import com.bin.framework.redis.BaseTest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

/**
 * @autor qiubingyu
 * @ClassName ValueOpsTest
 * @date 2020/12/24
 **/
public class ValueOpsTest extends BaseTest {

    ValueOps<User> ops;

    @Before
    public void before() {
        ops = applicationContext.getBean(ValueOps.class);
    }


    @Test
    public void size() {
        final long qby = ops.size("qby");
        System.out.println(qby);
    }

    @Test
    public void get() {
        final User qby = ops.get("qby");
        System.out.println(qby);
    }

    @Test
    public void getAndDelete() {
    }

    @Test
    public void trySet() {
    }

    @Test
    public void testTrySet() {
    }

    @Test
    public void compareAndSet() {
    }

    @Test
    public void getAndSet() {
    }

    @Test
    public void set() {
        final ValueOps<User> bean = applicationContext.getBean(ValueOps.class);
        bean.set("qby", new User("QBY", "QBY"));
    }

    @Test
    public void testSet() {
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class User implements Serializable {
    String username;
    String password;
}