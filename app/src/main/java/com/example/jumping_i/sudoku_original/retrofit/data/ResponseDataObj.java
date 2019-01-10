package com.example.jumping_i.sudoku_original.retrofit.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseDataObj {

    /**
     * 공통 데이터
     */
    public class Common {
        String alertYn;
        String stormYn;

        public String getAlertYn() {
            return alertYn;
        }

        public String getStormYn() {
            return stormYn;
        }
    }

    /**
     * 서버 연동 결과 데이터
     */
    public class Result {
        @SerializedName("message")
        String message;
        @SerializedName("code")
        String code;

        public String getMessage() {
            return message;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * https://api2.sktelecom.com/weather/current/hourly 결과 데이터.
     */
    public class HourlyData {
        @SerializedName("common")
        Common common;
        @SerializedName("result")
        Result result;
        @SerializedName("weather")
        Weather weather;

        public Result getResult() {
            return result;
        }

        public Weather getWeather() {
            return weather;
        }

        public Common getCommon() {
            return common;
        }

        /**
         * 날씨 정보 데이터.
         */
        public class Weather {
            @SerializedName("hourly")
            public List<Weather.Hourly> hourly = new ArrayList<>();

            public List<Weather.Hourly> getHourly() {
                return hourly;
            }

            /**
             * 시간별 날씨 데이터.
             */
            public class Hourly {
                @SerializedName("grid")
                Grid grid;
                @SerializedName("wind")
                Wind wind;
                @SerializedName("precipitation")
                Precipitation precipitation;
                @SerializedName("sky")
                Sky sky;
                @SerializedName("temperature")
                Temperature temperature;

                public Weather.Hourly.Grid getGrid() {
                    return grid;
                }

                public Weather.Hourly.Sky getSky() {
                    return sky;
                }

                public Weather.Hourly.Precipitation getPrecipitation() {
                    return precipitation;
                }

                public Weather.Hourly.Temperature getTemperature() {
                    return temperature;
                }

                public Weather.Hourly.Wind getWind() {
                    return wind;
                }

                /**
                 * Grid Inner Class
                 */
                public class Grid {
                    String longitude;
                    String latitude;
                    String city;
                    String county;
                    String village;

                    public String getLongitude() {
                        return longitude;
                    }

                    public String getLatitude() {
                        return latitude;
                    }

                    public String getCity() {
                        return city;
                    }

                    public String getCounty() {
                        return county;
                    }

                    public String getVillage() {
                        return village;
                    }
                }

                /**
                 * Wind Inner Class
                 */
                public class Wind { // 바람
                    @SerializedName("wdir")
                    String wdir;
                    @SerializedName("wspd")
                    String wspd;

                    public String getWdir() {
                        return wdir;
                    }

                    public String getWspd() {
                        return wspd;
                    }
                }

                /**
                 * Precipitation Inner Class
                 */
                public class Precipitation { // 강수 정보
                    @SerializedName("sinceOntime")
                    String sinceOntime; // 강우
                    @SerializedName("type")
                    String type; //0 :없음 1:비 2: 비/눈 3: 눈

                    public String getSinceOntime() {
                        return sinceOntime;
                    }

                    public String getType() {
                        return type;
                    }
                }

                /**
                 * Sky Inner Class
                 */
                public class Sky {
                    @SerializedName("name")
                    String name;
                    @SerializedName("code")
                    String code;

                    public String getName() {
                        return name;
                    }

                    public String getCode() {
                        return code;
                    }
                }

                /**
                 * Temperature Inner Class
                 */
                public class Temperature {
                    @SerializedName("tc")
                    String tc; // 현재 기온
                    @SerializedName("tmax")
                    String tmax;
                    @SerializedName("tmin")
                    String tmin;
                    @SerializedName("humidity")
                    String humidity;
                    @SerializedName("lightning")
                    String lightning;
                    @SerializedName("sunRiseTime")
                    String sunRiseTime;
                    @SerializedName("sunSetTime")
                    String sunSetTime;
                    @SerializedName("timeRelease")
                    String timeRelease;

                    public String getTc() {
                        return tc;
                    }

                    public String getTmax() {
                        return tmax;
                    }

                    public String getTmin() {
                        return tmin;
                    }

                    public String getHumidity() {
                        return humidity;
                    }

                    public String getLightning() {
                        return lightning;
                    }

                    public String getSunRiseTime() {
                        return sunRiseTime;
                    }

                    public String getSunSetTime() {
                        return sunSetTime;
                    }

                    public String getTimeRelease() {
                        return timeRelease;
                    }
                }
            }
        }
    }
}
