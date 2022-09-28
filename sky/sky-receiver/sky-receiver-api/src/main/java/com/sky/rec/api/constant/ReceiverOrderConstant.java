package com.sky.rec.api.constant;

public class ReceiverOrderConstant {

    public static final String RECEIVER_ORDER_NO = "RECEIVER_ORDER_NO";

    /**
     * 已保存
     * 审核中
     * 已审批
     * 已驳回
     * 已关闭
     */
    public enum State {
        DRAFT,
        IN_PROCESS,
        APPROVED,
        REJECTED,
        CLOSED
    }
}
