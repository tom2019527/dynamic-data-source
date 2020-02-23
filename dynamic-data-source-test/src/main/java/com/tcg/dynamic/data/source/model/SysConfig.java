package com.tcg.dynamic.data.source.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysConfig implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7506324901020935428L;
    private String variable;
    private String value;

}
