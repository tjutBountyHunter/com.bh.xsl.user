package pojo;

import vo.tagVo;

import java.util.List;

/**
 * @author 梁俊伟
 * @version 1.0
 * @date 2019/5/15 14:28
 */
public class UserAccReqVo {
    private String userid;

    private String sno;

    private String major;

    private String college;

    private String school;

    private String sex;

    private List<tagVo> tagVos;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<tagVo> getTagVos() {
        return tagVos;
    }

    public void setTagVos(List<tagVo> tagVos) {
        this.tagVos = tagVos;
    }
}
