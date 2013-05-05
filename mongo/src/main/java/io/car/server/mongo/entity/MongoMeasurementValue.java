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
package io.car.server.mongo.entity;

import com.github.jmkgreen.morphia.annotations.Embedded;
import com.github.jmkgreen.morphia.annotations.Property;

import io.car.server.core.MeasurementValue;

@Embedded("measurementvalue")
public class MongoMeasurementValue extends MongoBaseEntity<MongoMeasurementValue> implements MeasurementValue<Object> {
	public static final String VALUE = "value";
	
	@Property(VALUE)
	Object value;
	
	@Override
	public Object getValue() {
		return this.value;
	}

	@Override
	public MeasurementValue<Object> setValue(Object value) {
		this.value = value;
		return this;
	}
	
}
