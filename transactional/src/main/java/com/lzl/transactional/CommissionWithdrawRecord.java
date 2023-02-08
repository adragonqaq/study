package com.lzl.transactional;

import java.math.BigDecimal;
import java.util.Date;

public class CommissionWithdrawRecord {
    /**
     * 提现记录id
     */
    private String recordId;

    /**
     * 店铺id
     */
    private String sid;

    /**
     * 分销员id
     */
    private String memberId;

    /**
     * 小程序openId
     */
    private String openId;

    /**
     * 分销分组所属渠道id
     */
    private String channelId;

    /**
     * 提现方唯一标识，例如：用户银行卡id
     */
    private String withdrawTypeId;

    /**
     * 第三方流水号
     */
    private String outNo;

    /**
     * 提现方式：1微信，2余额，3支付宝，4银行卡
     */
    private String withdrawType;

    /**
     * 可提现金额
     */
    private BigDecimal allowAmount;

    /**
     * 本次提现金额
     */
    private BigDecimal withdrawAmount;

    /**
     * 审核状态：0无需审核, 1待审核，2已审核，3已驳回
     */
    private String checkStatus;

    /**
     * 提现状态  1提现申请中，2提现成功，3提现失败（审核失败），4提现失败（资金入账失败）
     */
    private String withdrawStatus;

    /**
     * 备注，驳回处理意见
     */
    private String remark;

    /**
     * 行删除标识：1是，0否（默认）
     */
    private String delFlag;

    /**
     * 创建人
     */
    private String createUserId;

    /**
     * 修改人
     */
    private String updateUserId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    /**
     * 版本（如：0、1、2、3...，程序维护，顺序增长，初始为0）
     */
    private Integer version;

    /**
     * 获取提现记录id
     * @return record_id - 提现记录id
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * 设置提现记录id
     * @param recordId 提现记录id
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取店铺id
     * @return sid - 店铺id
     */
    public String getSid() {
        return sid;
    }

    /**
     * 设置店铺id
     * @param sid 店铺id
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * 获取分销员id
     * @return member_id - 分销员id
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 设置分销员id
     * @param memberId 分销员id
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取分销分组所属渠道id
     * @return channel_id - 分销分组所属渠道id
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 设置分销分组所属渠道id
     * @param channelId 分销分组所属渠道id
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 获取提现方式：1微信，2余额，3支付宝
     * @return withdraw_type - 提现方式：1微信，2余额，3支付宝
     */
    public String getWithdrawType() {
        return withdrawType;
    }

    /**
     * 设置提现方式：1微信，2余额，3支付宝
     * @param withdrawType 提现方式：1微信，2余额，3支付宝
     */
    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

    /**
     * 获取可提现金额
     * @return allow_amount - 可提现金额
     */
    public BigDecimal getAllowAmount() {
        return allowAmount;
    }

    /**
     * 设置可提现金额
     * @param allowAmount 可提现金额
     */
    public void setAllowAmount(BigDecimal allowAmount) {
        this.allowAmount = allowAmount;
    }

    /**
     * 获取本次提现金额
     * @return withdraw_amount - 本次提现金额
     */
    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    /**
     * 设置本次提现金额
     * @param withdrawAmount 本次提现金额
     */
    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    /**
     * 获取审核状态：0 无需审核, 1待审核，2已审核，3已驳回
     * @return check_status - 审核状态：10 无需审核, 1待审核，2已审核，3已驳回
     */
    public String getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置审核状态：0 无需审核, 1待审核，2已审核，3已驳回
     * @param checkStatus 审核状态：0 无需审核, 1待审核，2已审核，3已驳回
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }


    /**
     * 获取提现状态  1提现申请中，2提现成功，3提现失败（审核失败），4提现失败（资金入账失败）
     * @return withdraw_status - 审核状态：10 无需审核, 1待审核，2已审核，3已驳回
     */
    public String getWithdrawStatus() {
        return withdrawStatus;
    }

    /**
     * 设置提现状态  1提现申请中，2提现成功，3提现失败（审核失败），4提现失败（资金入账失败）
     * @param withdrawStatus 提现状态  1提现申请中，2提现成功，3提现失败（审核失败），4提现失败（资金入账失败）
     */
    public void setWithdrawStatus(String withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }

    /**
     * 获取备注，驳回处理意见
     * @return remark - 备注，驳回处理意见
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注，驳回处理意见
     * @param remark 备注，驳回处理意见
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取行删除标识：1是，0否（默认）
     * @return del_flag - 行删除标识：1是，0否（默认）
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置行删除标识：1是，0否（默认）
     * @param delFlag 行删除标识：1是，0否（默认）
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取创建人
     * @return create_user_id - 创建人
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人
     * @param createUserId 创建人
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取修改人
     * @return update_user_id - 修改人
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置修改人
     * @param updateUserId 修改人
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取创建时间
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改时间
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取版本（如：0、1、2、3...，程序维护，顺序增长，初始为0）
     * @return version - 版本（如：0、1、2、3...，程序维护，顺序增长，初始为0）
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本（如：0、1、2、3...，程序维护，顺序增长，初始为0）
     * @param version 版本（如：0、1、2、3...，程序维护，顺序增长，初始为0）
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getWithdrawTypeId() {
        return withdrawTypeId;
    }

    public void setWithdrawTypeId(String withdrawTypeId) {
        this.withdrawTypeId = withdrawTypeId;
    }

    public String getOutNo() {
        return outNo;
    }

    public void setOutNo(String outNo) {
        this.outNo = outNo;
    }

    @Override
    public String toString() {
        return "CommissionWithdrawRecord{" +
                "recordId='" + recordId + '\'' +
                ", sid='" + sid + '\'' +
                ", memberId='" + memberId + '\'' +
                ", openId='" + openId + '\'' +
                ", channelId='" + channelId + '\'' +
                ", withdrawTypeId='" + withdrawTypeId + '\'' +
                ", outNo='" + outNo + '\'' +
                ", withdrawType='" + withdrawType + '\'' +
                ", allowAmount=" + allowAmount +
                ", withdrawAmount=" + withdrawAmount +
                ", checkStatus='" + checkStatus + '\'' +
                ", withdrawStatus='" + withdrawStatus + '\'' +
                ", remark='" + remark + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", createUserId='" + createUserId + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", version=" + version +
                '}';
    }
}