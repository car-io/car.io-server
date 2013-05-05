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
package io.car.server.rest.guice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

import io.car.server.rest.provider.GeoJSONProvider;
import io.car.server.rest.provider.GroupProvider;
import io.car.server.rest.provider.GroupsProvider;
import io.car.server.rest.provider.TrackProvider;
import io.car.server.rest.provider.TracksProvider;
import io.car.server.rest.provider.UserProvider;
import io.car.server.rest.provider.UsersProvider;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class JerseyProviderModule extends AbstractModule {
    private static final Logger log = LoggerFactory.getLogger(JerseyProviderModule.class);

    @Override
    protected void configure() {
        log.debug("Installing JerseyProviderModule");
        bind(GeoJSONProvider.class).in(Scopes.SINGLETON);
        bind(GroupProvider.class).in(Scopes.SINGLETON);
        bind(GroupsProvider.class).in(Scopes.SINGLETON);
        bind(UserProvider.class).in(Scopes.SINGLETON);
        bind(UsersProvider.class).in(Scopes.SINGLETON);
        bind(TrackProvider.class).in(Scopes.SINGLETON);
        bind(TracksProvider.class).in(Scopes.SINGLETON);
    }

}
