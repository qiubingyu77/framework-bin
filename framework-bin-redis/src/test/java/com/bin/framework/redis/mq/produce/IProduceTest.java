package com.bin.framework.redis.mq.produce;

import com.bin.framework.redis.BaseTest;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @autor qiubingyu
 * @ClassName IProduceTest
 * @date 2020/12/21
 **/
public class IProduceTest extends BaseTest {

  /*  @Test
    public void sendMessage() {
        final IProduce<User> produce = applicationContext.getBean(IProduce.class);
        produce.sendMessage("user",new User("1","qby","qby"));
    }

    @Test
    public void sendMessages() {
        final ArrayList<User> users = Lists.newArrayList(new User("2", "qby", "qby"), new User("3", "qby", "qby"), new User("4", "qby", "qby"));
        final IProduce<User> produce = applicationContext.getBean(IProduce.class);
        produce.sendMessages("user",users);
    }*/
}

@Data
@AllArgsConstructor
class User implements Serializable {
    String id;
    String username;
    String password;
}