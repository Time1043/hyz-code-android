package com.example.viewdemo.bean;

import java.util.List;

public class ExpandableBean {
    private String groupId;
    private String groupName;
    private List<ChildData> childs;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<ChildData> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildData> childs) {
        this.childs = childs;
    }

    public static class ChildData {
        private String childId;
        private String childName;

        public String getChildId() {
            return childId;
        }

        public void setChildId(String childId) {
            this.childId = childId;
        }

        public String getChildName() {
            return childName;
        }

        public void setChildName(String childName) {
            this.childName = childName;
        }
    }
}
