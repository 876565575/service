package com.xc.model.course;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;

/**
 * Created by admin on 2018/2/7.
 */
@Data
@ToString
public class TeachplanMedia implements Serializable {
    private static final long serialVersionUID = -916357110051689485L;
    private String teachplanId;

    private String mediaId;

    private String mediaFileOriginalName;

    private String mediaUrl;
    private String courseId;

}
