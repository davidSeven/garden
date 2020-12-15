package com.sky.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.system.api.model.OnlineUser;
import com.sky.system.constant.OnlineUserConstant;
import com.sky.system.dao.OnlineUserDao;
import com.sky.system.service.OnlineUserService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @date 2020-12-15 015 9:20
 */
@Service
public class OnlineUserServiceImpl extends ServiceImpl<OnlineUserDao, OnlineUser> implements OnlineUserService {

    @Override
    public void addOnlineUser(OnlineUser onlineUser) {
        // 过期时间 = 访问时间 + 租期
        if (Objects.nonNull(onlineUser.getLastVisitDate()) && Objects.nonNull(onlineUser.getLeaseTime())) {
            onlineUser.setExpireDate(DateUtils.addSeconds(onlineUser.getLastVisitDate(), Math.toIntExact(onlineUser.getLeaseTime())));
        }
        onlineUser.setState(OnlineUserConstant.STATE_ONLINE);
        this.save(onlineUser);
    }

    @Async
    @Override
    public void logoutOnlineUser(OnlineUser onlineUser) {
        LambdaUpdateWrapper<OnlineUser> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(OnlineUser::getLoginToken, onlineUser.getLoginToken());
        OnlineUser updateOnlineUser = new OnlineUser();
        updateOnlineUser.setState(OnlineUserConstant.STATE_OFFLINE);
        updateOnlineUser.setLogoutDate(new Date());
        this.update(updateOnlineUser, updateWrapper);
    }

    @Override
    public void visitOnlineUser(OnlineUser onlineUser) {
        // 更新了最后访问时间从新计算过期时间
        OnlineUser updateOnlineUser = new OnlineUser();
        updateOnlineUser.setLastVisitDate(onlineUser.getLastVisitDate());
        updateOnlineUser.setLoginToken(onlineUser.getLoginToken());
        this.baseMapper.visitOnlineUser(updateOnlineUser);
    }
}
