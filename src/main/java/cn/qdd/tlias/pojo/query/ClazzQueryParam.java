package cn.qdd.tlias.pojo.query;

import lombok.Data;

@Data
public class ClazzQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private String room;
    private Integer masterId;
    private Integer subject;
}
