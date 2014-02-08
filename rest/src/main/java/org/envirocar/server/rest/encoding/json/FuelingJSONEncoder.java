package org.envirocar.server.rest.encoding.json;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.envirocar.server.core.entities.DimensionedNumber;
import org.envirocar.server.core.entities.Fueling;
import org.envirocar.server.core.entities.Sensor;
import org.envirocar.server.core.entities.User;
import org.envirocar.server.rest.JSONConstants;
import org.envirocar.server.rest.encoding.JSONEntityEncoder;
import org.envirocar.server.rest.rights.AccessRights;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;

/**
 * JSON encoder for {@link Fueling}s.
 *
 * @author Christian Autermann
 */
@Provider
public class FuelingJSONEncoder extends AbstractJSONEntityEncoder<Fueling> {
    private final JSONEntityEncoder<User> userEncoder;
    private final JSONEntityEncoder<Sensor> sensorEncoder;

    /**
     * Creates a new {@code FuelingJSONEncoder} using the specified delegates.
     *
     * @param userEncoder   the encoder to encode {@link User}s
     * @param sensorEncoder the encoder to encode {@link Sensor}s
     */
    @Inject
    public FuelingJSONEncoder(JSONEntityEncoder<User> userEncoder,
                              JSONEntityEncoder<Sensor> sensorEncoder) {
        super(Fueling.class);
        this.userEncoder = checkNotNull(userEncoder);
        this.sensorEncoder = checkNotNull(sensorEncoder);
    }

    @Override
    public ObjectNode encodeJSON(Fueling t, AccessRights rights, MediaType mt) {
        ObjectNode fueling = getJsonFactory().objectNode();
        if (t.hasFuelType()) {
            fueling.put(JSONConstants.FUEL_TYPE, t.getFuelType());
        }
        if (t.hasCost()) {
            fueling.put(JSONConstants.COST, encodeJSON(t.getCost()));
        }
        if (t.hasMileage()) {
            fueling.put(JSONConstants.MILEAGE, encodeJSON(t.getMileage()));
        }
        if (t.hasVolume()) {
            fueling.put(JSONConstants.VOLUME, encodeJSON(t.getVolume()));
        }
        if (t.hasTime()) {
            fueling.put(JSONConstants.TIME_KEY, getDateTimeFormat().print(t.getTime()));
        }
        if (t.hasCar()) {
            fueling.put(JSONConstants.CAR_KEY, sensorEncoder.encodeJSON(t.getCar(), rights, mt));
        }
        if (t.hasUser()) {
            fueling.put(JSONConstants.USER_KEY, userEncoder.encodeJSON(t.getUser(), rights, mt));
        }
        if (t.hasCreationTime()) {
            fueling.put(JSONConstants.CREATED_KEY, getDateTimeFormat().print(t.getCreationTime()));
        }
        if (t.hasModificationTime()) {
            fueling.put(JSONConstants.MODIFIED_KEY, getDateTimeFormat().print(t.getModificationTime()));
        }
        if (t.hasComment()) {
            fueling.put(JSONConstants.COMMENT, t.getComment());
        }
        if (t.hasIdentifier()) {
            fueling.put(JSONConstants.IDENTIFIER_KEY, t.getIdentifier());
        }
        fueling.put(JSONConstants.MISSED_FUEL_STOP, t.isMissedFuelStop());
        return fueling;
    }

    private ObjectNode encodeJSON(DimensionedNumber dm) {
        ObjectNode node = getJsonFactory().objectNode();
        node.put(JSONConstants.VALUE_KEY, dm.value());
        node.put(JSONConstants.UNIT_KEY, dm.unit());
        return node;
    }
}