package com.swnote.jooq.service.impl;

import static com.swnote.jooq.generator.tables.User.USER;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jooq.DSLContext;
import org.jooq.UpdateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.swnote.jooq.generator.tables.pojos.User;
import com.swnote.jooq.generator.tables.records.UserRecord;
import com.swnote.jooq.service.IUserService;

/**
 * 用户信息服务类
 * 
 * @author lzj
 * @date [2019-03-10]
 */
@Transactional
@Service
public class UserService implements IUserService {

    @Autowired
    private DSLContext dsl;

    @Override
    public void create(User user) {
        dsl.insertInto(USER, USER.USER_ID, USER.NAME, USER.INTRO)
            .values(user.getUserId(), user.getName(), user.getIntro()).execute();
    }

    @Override
    public void delete(String user_id) {
        dsl.delete(USER).where(USER.USER_ID.eq(user_id)).execute();
    }

    @Override
    public void update(User user) {
        UpdateQuery<UserRecord> update = dsl.updateQuery(USER);
        update.addValue(USER.NAME, user.getName());
        update.addValue(USER.INTRO, user.getIntro());
        update.addConditions(USER.USER_ID.eq(user.getUserId()));
        update.execute();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public User retrieve(String user_id) {
        List<User> users = dsl.select(USER.USER_ID, USER.NAME, USER.INTRO).from(USER).where(USER.USER_ID.eq(user_id))
            .fetch().into(User.class);
        
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<User> queryForList(Map<String, Object> params) {
        StringBuilder builder = new StringBuilder();
        if (params != null) {
            for (Entry<String, Object> entry : params.entrySet()) {
                if (builder.length() == 0) {
                    builder.append(entry.getKey()).append(" = ").append(entry.getValue());
                } else {
                    builder.append(" and ").append(entry.getKey()).append(" = ").append(entry.getValue());
                }
            }
        }
        
        List<User> users = dsl.select(USER.USER_ID, USER.NAME, USER.INTRO).from(USER).where(builder.toString()).fetch().into(User.class);
        return users;
    }
}