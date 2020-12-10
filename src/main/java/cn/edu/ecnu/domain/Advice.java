package cn.edu.ecnu.domain;

import java.io.Serializable;
import java.util.Date;

public class Advice implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advice.aid
     *
     * @mbggenerated
     */
    private String aid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advice.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advice.pid
     *
     * @mbggenerated
     */
    private String pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advice.advicetime
     *
     * @mbggenerated
     */
    private Date advicetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table advice
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advice.aid
     *
     * @return the value of advice.aid
     *
     * @mbggenerated
     */
    public String getAid() {
        return aid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advice.aid
     *
     * @param aid the value for advice.aid
     *
     * @mbggenerated
     */
    public void setAid(String aid) {
        this.aid = aid == null ? null : aid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advice.content
     *
     * @return the value of advice.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advice.content
     *
     * @param content the value for advice.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advice.pid
     *
     * @return the value of advice.pid
     *
     * @mbggenerated
     */
    public String getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advice.pid
     *
     * @param pid the value for advice.pid
     *
     * @mbggenerated
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advice.advicetime
     *
     * @return the value of advice.advicetime
     *
     * @mbggenerated
     */
    public Date getAdvicetime() {
        return advicetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advice.advicetime
     *
     * @param advicetime the value for advice.advicetime
     *
     * @mbggenerated
     */
    public void setAdvicetime(Date advicetime) {
        this.advicetime = advicetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advice
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aid=").append(aid);
        sb.append(", content=").append(content);
        sb.append(", pid=").append(pid);
        sb.append(", advicetime=").append(advicetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}