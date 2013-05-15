/**
 * Copyright (C) 2013  Christian Autermann, Jan Alexander Wirwahn,
 *                     Arne De Wall, Dustin Demuth, Saqib Rasheed
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.car.server.core.entities;


import org.joda.time.DateTime;

import com.vividsolutions.jts.geom.Geometry;

import io.car.server.core.BaseEntity;
import io.car.server.core.MeasurementValue;
import io.car.server.core.MeasurementValues;

/**
 * 
 * @author Arne de Wall <a.dewall@52north.org>
 *
 */
public interface Measurement extends BaseEntity, Comparable<Measurement>  {
    MeasurementValues getValues();
    Measurement addValue(MeasurementValue value);
    Measurement removeValue(MeasurementValue value);
    Geometry getGeometry();
    Measurement setGeometry(Geometry geometry);
    Measurement setUser(User user);
    Sensor getSensor();
    Measurement setSensor(Sensor sensor);
    User getUser();
    String getIdentifier();
    DateTime getTime();
    Measurement setTime(DateTime time);
    Measurement setIdentifier(String identifier);
}