package com.cdzeroly.wvp.domain.bean;

import lombok.Data;

import java.util.Set;

/**
 * @author MGARY
 */
@Data
public class BatchRemoveParam {
    private Set<Long> ids;
}
