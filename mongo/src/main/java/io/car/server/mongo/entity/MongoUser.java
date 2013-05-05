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

import static io.car.server.mongo.entity.MongoBaseEntity.ID;

import java.util.Set;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Indexed;
import com.github.jmkgreen.morphia.annotations.Property;
import com.github.jmkgreen.morphia.annotations.Reference;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;

import io.car.server.core.Track;
import io.car.server.core.Tracks;
import io.car.server.core.User;
import io.car.server.core.Users;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
@Entity("users")
public class MongoUser extends MongoBaseEntity<MongoUser> implements User {
    public static final String NAME = "name";
    public static final String MAIL = "mail";
    public static final String TOKEN = "token";
    public static final String IS_ADMIN = "isAdmin";
    public static final String FRIENDS = "friends";
    public static final String TRACKS = "tracks";
    @Indexed(unique = true)
    @Property(NAME)
    private String name;
    @Indexed(unique = true)
    @Property(MAIL)
    private String mail;
    @Property(TOKEN)
    private String token;
    @Property(IS_ADMIN)
    private boolean isAdmin = false;
    @Reference(value = FRIENDS, lazy = true)
    private Set<MongoUser> friends = Sets.newHashSet();
    @Reference(value = TRACKS, lazy = true)
    private Set<MongoTrack> tracks = Sets.newHashSet();

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public MongoUser setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public MongoUser setMail(String mail) {
        this.mail = mail;
        return this;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public MongoUser setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public MongoUser setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    @Override
    public Users getFriends() {
        return new Users(this.friends);
    }

    @Override
    public MongoUser addFriend(User user) {
        this.friends.add((MongoUser) user);
        return this;
    }

    @Override
    public MongoUser removeFriend(User user) {
        this.friends.remove((MongoUser) user);
        return this;
    }

    public MongoUser setFriends(Users friends) {
        this.friends.clear();
        for (User u : friends) {
            this.friends.add((MongoUser) u);
        }
        return this;
    }
    
	@Override
	public User addTrack(Track track) {
		this.tracks.add((MongoTrack) track);
		return this;
	}

	@Override
	public User removeTrack(Track track) {
		this.tracks.remove((MongoTrack) track);
		return this;
	}

	@Override
	public Tracks getTracks() {
		return new Tracks(this.tracks);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .omitNullValues()
                .add(ID, getId())
                .add(NAME, getName())
                .add(MAIL, getMail())
                .add(TOKEN, getToken())
                .add(IS_ADMIN, isAdmin())
                .add(FRIENDS, getFriends())
                .add(TRACKS, getTracks())
                .toString();
    }
}
