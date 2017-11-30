package com.wangzhihao.blackmarket.dto;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class AddCoursePostDto {
    private String studentId;
    private Long mobileSwitch;
    private String mobile;
    private String wechat;
    private String message;
    private Long supply;
    private Long demand;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getMobileSwitch() {
        return mobileSwitch;
    }

    public void setMobileSwitch(Long mobileSwitch) {
        this.mobileSwitch = mobileSwitch;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSupply() {
        return supply;
    }

    public void setSupply(Long supply) {
        this.supply = supply;
    }

    public Long getDemand() {
        return demand;
    }

    public void setDemand(Long demand) {
        this.demand = demand;
    }

    @Override
    public String toString() {
        return "AddCoursePostDto{" +
                "studentId='" + studentId + '\'' +
                ", mobileSwitch=" + mobileSwitch +
                ", mobile='" + mobile + '\'' +
                ", wechat='" + wechat + '\'' +
                ", message='" + message + '\'' +
                ", supply=" + supply +
                ", demand=" + demand +
                '}';
    }
}
