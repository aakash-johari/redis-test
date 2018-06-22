package aj.redis.project.controllers;

import aj.redis.project.dao.KeyValueDao;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kv")
public class RedisController {
    private StatefulRedisConnection<String, String> connection;

    private RedisCommands<String, String> command;

    @Autowired
    public RedisController(StatefulRedisConnection<String, String> connection) {
        this.connection = connection;
        this.command = connection.sync();
    }

    @PostMapping
    public void setValue(@RequestBody KeyValueDao keyValue) {
        command.set(keyValue.getKey(), keyValue.getValue());
    }

    @GetMapping
    public String getValue(@RequestParam("key") String key) {
        return command.get(key);
    }
}
