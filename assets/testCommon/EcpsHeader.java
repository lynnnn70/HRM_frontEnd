package com.thsrc.meig.ecp.api.util.common;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EcpsHeader {
    @NotNull
    @Schema(description = "請求來源主機IP")
    private String host;
    @NotNull
    @Pattern(regexp = "\\d{2}", message = "請求來源站點為兩碼數字字串")
    @Schema(description = "請求來源站點")
    private String station;
    @NotNull
    @Min(value = 0, message = "請求來源設備類型最小值為0")
    @Max(value = 255 , message = "請求來源設備類最大值為255")
    @Schema(description = "請求來源設備類型")
    private int equipType;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{1,10}$", message = "設備編號為1-10碼的英數字字串")
    @Schema(description = "請求來源設備編號")
    private String equipId;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public int getEquipType() {
        return equipType;
    }

    public void setEquipType(int equipType) {
        this.equipType = equipType;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public EcpsHeader() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{host:'").append(host).append('\'');
        sb.append(", station:'").append(station).append('\'');
        sb.append(", equipType:").append(equipType);
        sb.append(", equipId:'").append(equipId).append('\'');
        sb.append("}");
        return sb.toString();
    }
}
