package com.mendor;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PathFinderCharacterJSONDeserializer extends StdDeserializer<PathfinderCharacter> {

    public PathFinderCharacterJSONDeserializer() {
        this(null);
    }

    protected PathFinderCharacterJSONDeserializer(Class<PathfinderCharacter> vc) {
        super(vc);
    }

    @Override
    public PathfinderCharacter deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return null;
    }
}
