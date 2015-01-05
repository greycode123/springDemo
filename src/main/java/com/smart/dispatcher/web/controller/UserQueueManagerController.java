package com.smart.dispatcher.web.controller;

import com.smart.common.dispatcher.queue.user.UserQueue;
import com.smart.dispatcher.service.UserQueueService;
import com.smart.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserQueueManagerController {

    @Resource(name = "userQueueService")
    private UserQueueService userQueueService;

    @RequestMapping(value = "/userQueueManager.do", method = RequestMethod.GET)
    public String searchAllConfig() {
        return "userQueueManager";
    }

    @RequestMapping(value = "/getUserQueues.do", method = RequestMethod.POST)
    public  @ResponseBody Map<String, Object> getUserQueues(ModelMap model) {
        List<User> userQueues = userQueueService.getAllUser();
        model.put("userQueues", userQueues);

        Map<String, Object> json = new HashMap<String, Object>();
        json.put("total", userQueues.size());
        json.put("rows", userQueues);

        return json;
    }

    @RequestMapping(value = "/updateUserQueue.do ", method = RequestMethod.POST)
    public void updateUserQueue(UserQueue userQueue) {
       // userQueueService.update(userQueue);
    }
}
