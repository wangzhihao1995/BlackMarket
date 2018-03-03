package com.wangzhihao.blackmarket.dto;

import javax.validation.constraints.NotNull;

/**
 * Description
 * <p>
 * </p>
 * DATE 2017/11/28.
 *
 * @author Wang Zhihao.
 */
public class UpdateCoursePostViewCountDto {

    @NotNull
    private Long postId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "UpdateCoursePostViewCountDto{" +
                "postId=" + postId +
                '}';
    }
}
