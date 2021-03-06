package com.xc.model.cms.response;

import lombok.Data;

import java.util.List;

/**
 * <Description> <br>
 *
 * @author Wu<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/09/23 23:34 <br>
 * @see com.xc.cms.model.vo <br>
 */
@Data
public class QueryResult {

    private Long total;

    private List list;
}
