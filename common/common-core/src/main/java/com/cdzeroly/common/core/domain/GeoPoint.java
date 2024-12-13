package com.cdzeroly.common.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author MGARY
 */
@Setter
@Getter
public class GeoPoint implements Serializable {
    /**
     * 纬度
     */
    private double lat;
    /**
     * 经度
     */
    private double lng;

    public GeoPoint() {
    }

    /**
     * 创建经维度坐标
     *
     * @param lat 纬度
     * @param lng 经度
     */
    public GeoPoint(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "GeoPoint{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeoPoint geoPoint = (GeoPoint) o;
        return Double.compare(lat, geoPoint.lat) == 0 && Double.compare(lng, geoPoint.lng) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lng);
    }
}
