package com.bw.net.bean;

public class ClassifLeftRecyBean {
    private  String title;
    private  boolean flag;

    public ClassifLeftRecyBean(String title, boolean flag) {
        this.title = title;
        this.flag = flag;
    }

    public ClassifLeftRecyBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "ClassifLeftRecyBean{" +
                "title='" + title + '\'' +
                ", flag=" + flag +
                '}';
    }
}
