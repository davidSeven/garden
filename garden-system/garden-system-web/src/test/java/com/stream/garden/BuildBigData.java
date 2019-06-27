package com.stream.garden;

import com.stream.garden.framework.api.exception.ApplicationException;
import com.stream.garden.framework.jdbc.util.SnowflakeIdWorker;
import com.stream.garden.system.group.model.Group;
import com.stream.garden.system.group.service.IGroupService;
import com.stream.garden.system.group.service.IGroupUserService;
import com.stream.garden.system.role.model.Role;
import com.stream.garden.system.role.service.IRoleGroupService;
import com.stream.garden.system.role.service.IRoleService;
import com.stream.garden.system.user.model.User;
import com.stream.garden.system.user.service.IUserRoleService;
import com.stream.garden.system.user.service.IUserService;
import com.stream.garden.util.ChineseName;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.plaf.metal.MetalIconFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author garden
 * @date 2019-06-22 11:20
 */
public class BuildBigData extends BaseTest {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleGroupService roleGroupService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IGroupService groupService;
    @Autowired
    private IGroupUserService groupUserService;

    private char[] codes = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};

    private Set<String> roleNameSet = new HashSet<>();
    private Set<String> groupNameSet = new HashSet<>();
    private int roleCode = 1;
    private int userCode = 1;
    private int groupCode = 1;

    @Test
    public void build() throws ApplicationException {

        List<Role> roleList = new ArrayList<>();
        for (int i = 0; i < 30000; i++) {
            Role role = new Role();
            role.setId(String.valueOf(SnowflakeIdWorker.generateId()));
            role.setName(getRoleName());
            role.setCode(getRoleCode());
            role.setState("1");
            roleList.add(role);
        }
        roleService.insertBatch(roleList);

        List<Group> groupList = new ArrayList<>();
        for (int i = 0; i < 30000; i++) {
            Group group = new Group();
            group.setId(String.valueOf(SnowflakeIdWorker.generateId()));
            group.setName(getGroupName());
            group.setCode(getGroupCode());
            group.setState("1");
            groupList.add(group);
        }
        groupService.insertBatch(groupList);

        for (int i = 0; i < 10; i++) {
            List<User> userList = new ArrayList<>();
            for (int j = 0; j < 30000; j++) {
                User user = new User();
                user.setId(String.valueOf(SnowflakeIdWorker.generateId()));
                user.setName(getUserName());
                user.setCode(getUserCode());
                user.setState("1");
                userList.add(user);
            }
            userService.insertBatch(userList);
        }


    }

    private String getRoleCode() {
        String code = String.format("%010d", roleCode);
        roleCode++;
        return code;
    }

    private String getGroupCode() {
        String code = String.format("%010d", groupCode);
        groupCode++;
        return code;
    }

    private String getUserCode() {
        String code = String.format("%010d", userCode);
        userCode++;
        return code;
    }

    private String getRoleName() {
        return getRandomCode(16);
    }

    private String getGroupName() {
        return getRandomCode(12);
    }

    private String getUserName() {
        return ChineseName.getChineseName();
    }

    /**
     * 随机创建编码
     *
     * @return 随机编码
     */
    public String getRandomCode(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(codes[(int) (Math.random() * codes.length)]);
        }
        return sb.toString();
    }
}
