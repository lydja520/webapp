package com.ydc.webapp.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ydc.webframe.util.DateUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ydc on 17-4-16.
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable,Cloneable {

    private static final long serialVersionUID = 310141708974192892L;

    private String userId;
    private String userName;
    private String userPwd;
    private String userNick;
    private Sex userSex;
    private Date birthday;
    private String email;
    private int age;

    public enum  Sex{
        man,woman
    }

    public User() {
    }

    @javax.persistence.Id
    @Column(name = "user_id",length = 36,nullable = false,unique = true)
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "user_name",length = 64,nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_pwd",length = 128,nullable = false)
    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_sex",length = 5,nullable = false)
    public Sex getUserSex() {
        return userSex;
    }

    public String getUserNick() {
        return userNick;
    }

    @Column(name = "user_nick",length = 64)
    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public void setUserSex(Sex userSex) {
        this.userSex = userSex;
    }

    @JsonFormat(pattern = DateUtils.DATE_TIME_FORMAT)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_brithday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "user_emial")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public int getAge() {
        if (this.birthday != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            int _now = calendar.get(Calendar.YEAR);
            calendar.setTime(birthday);
            int _birthday = calendar.get(Calendar.YEAR);
            return _now - _birthday;
        }
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
