package com.jiayuan.xiaozhi.model;

/**
 * Created by xiaoxian on 15/12/29.
 */
public class JySocketHost {

    private Long id;

    private String hostname;

    private String hostoutip;

    private String hostinip;

    private String hostqueryip;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getHostoutip() {
        return hostoutip;
    }

    public void setHostoutip(String hostoutip) {
        this.hostoutip = hostoutip;
    }

    public String getHostinip() {
        return hostinip;
    }

    public void setHostinip(String hostinip) {
        this.hostinip = hostinip;
    }

    public String getHostqueryip() {
        return hostqueryip;
    }

    public void setHostqueryip(String hostqueryip) {
        this.hostqueryip = hostqueryip;
    }
}
