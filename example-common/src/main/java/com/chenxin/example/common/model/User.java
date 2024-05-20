package com.chenxin.example.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description 用户
 * @author fangchenxin
 * @date 2024/3/21 00:21
 * @modify
 */
@Data
public class User implements Serializable {

    private String name;

}
