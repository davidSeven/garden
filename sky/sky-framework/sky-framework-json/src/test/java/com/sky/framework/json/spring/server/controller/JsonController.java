package com.sky.framework.json.spring.server.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.framework.json.JsonView;
import com.sky.framework.json.Match;
import com.sky.framework.json.model.Info;
import com.sky.framework.json.model.User;
import com.sky.framework.json.spring.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class JsonController {
    private static final Logger log = LoggerFactory.getLogger(JsonController.class);
    private final JsonResult json = JsonResult.instance();

    @RequestMapping(method = RequestMethod.GET, value = "/ready")
    @ResponseBody
    public String ready() {
        return "readys";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/blank")
    @ResponseBody
    public void blank() {
        //do nothing
    }

    @RequestMapping(method = RequestMethod.POST, value = "/bean")
    @ResponseBody
    public User acceptData(@RequestBody User object) {
        log.debug("POST testNoninterference()");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            System.out.println(objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return object;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/bean")
    @ResponseBody
    public void bean() {
        Info info = new Info();
        info.setRemark("备注信息");
        info.setDocument("文档信息");
        User user = new User();
        user.setName("名称");
        user.setAge(30);
        user.setBirthday(new Date());
        user.setInfo(info);
        json.use(JsonView.with(user)
                .onClass(User.class, Match.match()
                        .exclude("age")));
    }

}
