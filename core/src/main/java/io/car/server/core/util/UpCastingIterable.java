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
package io.car.server.core.util;

import java.util.Iterator;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

/**
 * @author Christian Autermann <c.autermann@52north.org>
 */
public class UpCastingIterable<T> implements Iterable<T> {
    private final Iterable<? extends T> delegate;

    public UpCastingIterable(Iterable<? extends T> delegate) {
        Preconditions.checkNotNull(delegate);
        this.delegate = delegate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return (Iterator<T>) delegate.iterator();
    }

    @Override
    public String toString() {
        return Joiner.on(", ").appendTo(new StringBuilder()
                .append(getClass().getSimpleName())
                .append('['), iterator()).append(']').toString();
    }
}
