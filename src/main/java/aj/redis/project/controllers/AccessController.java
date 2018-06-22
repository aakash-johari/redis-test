package aj.redis.project.controllers;

import aj.redis.project.dao.SetValueDao;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sortedSet")
public class AccessController {
    private StatefulRedisConnection<String, String> connection;
    private RedisCommands<String, String> command;

    @Value("${sortedSet.key.expiryTime}")
    private int expiryTime;

    @Autowired
    public AccessController(StatefulRedisConnection<String, String> connection) {
        this.connection = connection;
        this.command = connection.sync();
    }

    @PostMapping
    public void values(@RequestParam("setKey") String setKey, @RequestBody SetValueDao value) {
        if (command.exists(setKey) == 1) {
            command.zadd(setKey, 1.0, value.getValue());
        } else {
            command.zadd(setKey, 1.0, value.getValue());
            command.expire(setKey, expiryTime);
        }
    }

    @GetMapping
    public List<String> values(@RequestParam("setKey") String setKey) {
        return command.zrange(setKey, 0, -1);
    }
}
