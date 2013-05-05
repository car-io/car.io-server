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
package io.car.server.mongo.guice;

import com.github.jmkgreen.morphia.converters.TypeConverter;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

import io.car.server.mongo.convert.DateTimeConverter;
import io.car.server.mongo.convert.DurationConverter;
import io.car.server.mongo.convert.FileConverter;
import io.car.server.mongo.convert.GeometryConverter;
import io.car.server.mongo.convert.URLConverter;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class MongoConverterModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<TypeConverter> mb = Multibinder.newSetBinder(binder(), TypeConverter.class);
        mb.addBinding().to(DateTimeConverter.class);
        mb.addBinding().to(DurationConverter.class);
        mb.addBinding().to(FileConverter.class);
        mb.addBinding().to(GeometryConverter.class);
        mb.addBinding().to(URLConverter.class);
    }
}
