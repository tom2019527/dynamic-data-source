package com.tcg.dynamic.data.source.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Customer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4623503280418092551L;
    private Integer customerId;
    private String customerName;
}
