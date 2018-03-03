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
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UpdateCoursePostViewCountDto{" +
                "id=" + id +
                '}';
    }
}
