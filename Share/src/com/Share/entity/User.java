package com.Share.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

/**
 * User entity. 
 */
public class User implements java.io.Serializable {

    // Fields

    private Integer id;
    private String username;
    private String password;
    private String gender;
    private String imgPath;
    private Timestamp createTime;
    private Integer status;
    
    public static final Integer STATUS_PUBLIC=0;
    public static final Integer STATUS_PRIVATE=1;

    
    //我的分享
    private Set<Share> shares = new HashSet(0);

    //我的点赞
    private Set<Like> likes = new HashSet(0);

    //我的评论
    private Set<Comment> comments = new HashSet(0);

    //我的收藏
    private Set<Collect> collects = new HashSet(0);

    //关注我的人，userId为自己，则fansId为他人
    private Set<Follow> fanses = new HashSet(0);

    //我关注的人，fansId为自己，则自己是粉丝
    private Set<Follow> users = new HashSet(0);

    // Constructors


    public User() {
    }



    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(String username, String password, String gender,
                String imgPath, Timestamp createTime, Integer status, Set likes,
                Set comments, Set collects, Set fanses,
                Set users, Set shares) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.imgPath = imgPath;
        this.createTime = createTime;
        this.status = status;
        this.likes = likes;
        this.comments = comments;
        this.collects = collects;
        this.fanses = fanses;
        this.users = users;
        this.shares = shares;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Share> getShares() {
        return shares;
    }

    public void setShares(Set<Share> shares) {
        this.shares = shares;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Collect> getCollects() {
        return collects;
    }

    public void setCollects(Set<Collect> collects) {
        this.collects = collects;
    }

    public Set<Follow> getFanses() {
        return fanses;
    }

    public void setFanses(Set<Follow> fanses) {
        this.fanses = fanses;
    }

    public Set<Follow> getUsers() {
        return users;
    }

    public void setUsers(Set<Follow> users) {
        this.users = users;
    }
    
    @Override
    public int hashCode() {
    	return id;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj==null) {
			return false;
		}
    	if(!(obj instanceof User)){
    		return false;
    	}
    	User user=(User)obj;
    	if (user.getId().equals(getId())) {
			return true;
		}else {
			return false;
		}
    }
}