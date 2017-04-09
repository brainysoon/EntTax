package com.enttax.shiro;

import com.enttax.model.Permission;
import com.enttax.model.Role;
import com.enttax.model.Staff;
import com.enttax.service.permissionService.PermissService;
import com.enttax.util.constant.ConstantStr;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcyanxi on 17-4-9.
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private PermissService permissService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

//        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        String currentUsername = (String) super.getAvailablePrincipal(principals);
        List<String> roleList = new ArrayList<String>();
        List<String> permissionList = new ArrayList<String>();
//      //从数据库中获取当前登录用户的详细信息
        Staff staff = permissService.selectByUserName(currentUsername);
        if (null != staff) {

            //实体类User中包含有用户角色的实体类信息

            if (null != staff.getRoles() && staff.getRoles().size() > 0) {

                //获取当前登录用户的角色

                for (Role role : staff.getRoles()) {

                    roleList.add(role.getRname());

                    //实体类Role中包含有角色权限的实体类信息

                    if (null != role.getPermissions() && role.getPermissions().size() > 0) {

                        //获取权限

                        for (Permission pmss : role.getPermissions()) {

                            if (!StringUtils.isEmpty(pmss.getPname())) {

                                permissionList.add(pmss.getPname());

                            }

                        }

                    }

                }

            }

        } else {

            throw new AuthorizationException();

        }
        return null;

    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String passwd = String.valueOf(token.getCredentials());
        String userName = String.valueOf(token.getPrincipal());

        Staff staff = permissService.login(userName, passwd);

        if (null != staff) {

            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userName, passwd, this.getName());

            this.setSession(ConstantStr.STAFFINFO, staff);

            return authcInfo;

        } else {

            return null;

        }

    }


    private void setSession(Object key, Object value) {

        Subject currentUser = SecurityUtils.getSubject();

        if (null != currentUser) {

            Session session = currentUser.getSession();

            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");

            if (null != session) {

                session.setAttribute(key, value);

            }

        }

    }


}
