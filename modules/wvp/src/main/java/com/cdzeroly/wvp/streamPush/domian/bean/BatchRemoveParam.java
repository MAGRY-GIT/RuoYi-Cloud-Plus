package com.cdzeroly.wvp.streamPush.domian.bean;

import lombok.Data;

import java.util.Set;

/**
 * @author MGARY
 */
@Data
public class BatchRemoveParam {
    private Set<Integer> ids;
}
