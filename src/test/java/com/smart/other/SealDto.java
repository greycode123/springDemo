package com.smart.other;

import java.io.Serializable;

public class SealDto implements Serializable {

    private String sealName;

    private int version;

    private String classPath;

    private int valid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SealDto sealDto = (SealDto) o;

        if (sealName != null ? !sealName.equals(sealDto.sealName) : sealDto.sealName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return sealName != null ? sealName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SealDto{" +
                "sealName='" + sealName + '\'' +
                ", version=" + version +
                ", classPath='" + classPath + '\'' +
                ", valid=" + valid +
                '}';
    }

    public String getSealName() {
        return sealName;
    }

    public void setSealName(String sealName) {
        this.sealName = sealName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
