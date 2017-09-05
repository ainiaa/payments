package com.a91coding.payments.model;

import java.util.List;

public class User extends BaseModel {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private Byte status;

    private String salt;

    private List hasRoleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null)
            return false;

        return true;
    }

    public List getHasRoleList() {
        return hasRoleList;
    }

    public void setHasRoleList(List hasRoleList) {
        this.hasRoleList = hasRoleList;
    }
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}