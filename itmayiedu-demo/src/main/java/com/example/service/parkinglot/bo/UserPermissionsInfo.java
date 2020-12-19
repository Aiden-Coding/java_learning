package com.example.service.parkinglot.bo;


public class UserPermissionsInfo {

    //单位ID
    private String unitId = "";

    //品牌ID
    private String brandId = "";

    //单位类别(0品牌平台；1代理商；2物业；3停车场；4大型商场；5普通商家...）
    private int unitType = 0;

    private int isSysAdmin = 0;

    private int isBrandAdmin = 0;





    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public int getIsSysAdmin() {
        return isSysAdmin;
    }

    public void setIsSysAdmin(int isSysAdmin) {
        this.isSysAdmin = isSysAdmin;
    }

    @Override
    public String toString(){

        return  "";
    }
}
