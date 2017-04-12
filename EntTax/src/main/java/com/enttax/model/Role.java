package com.enttax.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

@Alias("Role")
public class Role {
    private String rid;

    private String rname;

    private Date rupdatetime;

    private Short rmark;

    private List<Permission> permissions;//一个角色对应多个权限

    private List<Staff> staffs;//一个角色对应多个用户

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public Date getRupdatetime() {
        return rupdatetime;
    }

    public void setRupdatetime(Date rupdatetime) {
        this.rupdatetime = rupdatetime;
    }

    public Short getRmark() {
        return rmark;
    }

    public void setRmark(Short rmark) {
        this.rmark = rmark;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }
}