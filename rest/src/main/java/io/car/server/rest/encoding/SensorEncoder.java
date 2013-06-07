/*
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
package io.car.server.rest.encoding;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Maps;

import io.car.server.core.entities.Sensor;
import io.car.server.rest.JSONConstants;
import io.car.server.rest.MediaTypes;
import io.car.server.rest.resources.RootResource;
import io.car.server.rest.resources.SensorsResource;

/**
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class SensorEncoder extends AbstractEntityEncoder<Sensor> {
    @Override
    public ObjectNode encode(Sensor t, MediaType mediaType) {
        ObjectNode sensor = getJsonFactory().objectNode();
        if (t.hasType()) {
            sensor.put(JSONConstants.TYPE_KEY, t.getType());
        }
        Map<String, Object> properties = Maps.newHashMap();

        if (t.hasProperties()) {
            properties.putAll(t.getProperties());
        }
        properties.put(JSONConstants.IDENTIFIER_KEY, t.getIdentifier());

        if (mediaType.equals(MediaTypes.SENSOR_TYPE)) {
            if (t.hasCreationTime()) {
                properties.put(JSONConstants.CREATED_KEY,
                               getDateTimeFormat().print(t.getCreationTime()));
            }
            if (t.hasModificationTime()) {
                properties.put(JSONConstants.MODIFIED_KEY,
                               getDateTimeFormat()
                        .print(t.getModificationTime()));
            }
        } else {
            URI href = getUriInfo().getBaseUriBuilder()
                    .path(RootResource.class)
                    .path(RootResource.SENSORS)
                    .path(SensorsResource.SENSOR)
                    .build(t.getIdentifier());
            sensor.put(JSONConstants.HREF_KEY, href.toString());
        }

        sensor.putPOJO(JSONConstants.PROPERTIES_KEY, properties);
        return sensor;
    }
}
