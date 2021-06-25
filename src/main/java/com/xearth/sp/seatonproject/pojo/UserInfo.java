package com.xearth.sp.seatonproject.pojo;

/**
 * @program: websocket -> UserInfo
 * @description: 用户信息
 * @author wangxudong
 * @date 2020/10/28 13:53
 */
public class UserInfo {

    private String id;

    private String nickName;

    private String password;

    public UserInfo() {
    }

    public UserInfo(String id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
