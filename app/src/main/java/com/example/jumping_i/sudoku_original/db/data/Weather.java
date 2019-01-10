package com.example.jumping_i.sudoku_original.db.data;

import com.example.jumping_i.sudoku_original.db.utils.DatabaseConstants;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Weather")
public class Weather {
    /*******************************************************************************
     * Variable.
     *******************************************************************************/
    // Index
    @DatabaseField(columnName = DatabaseConstants.WEATHER_IDX_FIELD, generatedId = true)
    // For Autoincrement
    private int mIdx;
    // 경도
    @DatabaseField(columnName = DatabaseConstants.WEATHER_LONGITUDE_FIELD)
    private String mLongitude;
    // 위도
    @DatabaseField(columnName = DatabaseConstants.WEATHER_LATITUDE_FIELD)
    private String mLatitude;
    // 도
    @DatabaseField(columnName = DatabaseConstants.WEATHER_CITY_FIELD)
    private String mCity;
    // 시
    @DatabaseField(columnName = DatabaseConstants.WEATHER_COUNTY_FIELD)
    private String mCounty;
    // 군/면
    @DatabaseField(columnName = DatabaseConstants.WEATHER_VILLAGE_FIELD)
    private String mVillage;

    /*******************************************************************************
     * Getter / Setter.
     *******************************************************************************/
    public int getIdx() {
        return mIdx;
    }

    public void setIdx(int idx) {
        this.mIdx = idx;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String strLongitude) {
        this.mLongitude = strLongitude;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String strLatitude) {
        this.mLatitude = strLatitude;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String strCity) {
        this.mCity = strCity;
    }

    public String getCounty() {
        return mCounty;
    }

    public void setCounty(String strCounty) {
        this.mCounty = strCounty;
    }

    public String getVillage() {
        return mVillage;
    }

    public void setVillage(String strVillage) {
        this.mVillage = strVillage;
    }
}