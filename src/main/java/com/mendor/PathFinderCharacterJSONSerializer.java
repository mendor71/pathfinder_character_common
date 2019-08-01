package com.mendor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Set;

public class PathFinderCharacterJSONSerializer extends StdSerializer<PathfinderCharacter> {

    public PathFinderCharacterJSONSerializer() {
        this(null);
    }

    public PathFinderCharacterJSONSerializer(Class<PathfinderCharacter> t) {
        super(t);
    }

    @Override
    public void serialize(PathfinderCharacter character, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        appendCharacterProperties(character, jsonGenerator);
        appendAttributesBlock(character, jsonGenerator);
        appendClassesBlock(character, jsonGenerator);
        appendSkillsBlock(character, jsonGenerator);

        jsonGenerator.writeEndObject();
    }

    protected void appendCharacterProperties(PathfinderCharacter character, JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStringField("uuid", character.getUUID());
        jsonGenerator.writeStringField("name", character.getName());
        jsonGenerator.writeNumberField("level", character.getLevel());
        jsonGenerator.writeNumberField("age", character.getAge());
        jsonGenerator.writeNumberField("armorClass", character.getArmorClass());
        jsonGenerator.writeNumberField("height", character.getHeight());
        jsonGenerator.writeNumberField("height", character.getHeight());
        jsonGenerator.writeStringField("eyeColor", character.getEyeColor() != null ? character.getEyeColor().toString() : null);
        jsonGenerator.writeStringField("hairColor", character.getHairColor() != null ? character.getHairColor().toString() : null);
    }

    protected void appendAttributesBlock(PathfinderCharacter character, JsonGenerator jsonGenerator) throws IOException {
        Set<CharacterAttributeDetails> attributes = character.getAttributes();

        jsonGenerator.writeArrayFieldStart("attributes");

        for (CharacterAttributeDetails d: attributes) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField("type", d.getType().toString());
            jsonGenerator.writeNumberField("value", d.getValueNormal());
            jsonGenerator.writeNumberField("tempValueBonus", d.getTempValueBonus());
            jsonGenerator.writeNumberField("modifier", d.getModifier());
            jsonGenerator.writeNumberField("tempModifierBonus", d.getTempModifierBonus());
            jsonGenerator.writeNumberField("id", d.getId());

            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
    }

    protected void appendClassesBlock(PathfinderCharacter character, JsonGenerator jsonGenerator) throws IOException {
        Set<CharacterClassDetails> characterClasses = character.getCharacterClasses();

        jsonGenerator.writeArrayFieldStart("classes");

        for (CharacterClassDetails d: characterClasses) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField("name", d.getClassName());
            jsonGenerator.writeNumberField("level", d.getLevel());

            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
    }

    protected void appendSkillsBlock(PathfinderCharacter character, JsonGenerator jsonGenerator) throws IOException {
        Set<CharacterSkillDetails> characterSkills = character.getSkillSet();

        jsonGenerator.writeArrayFieldStart("skills");

        for (CharacterSkillDetails d: characterSkills) {
            jsonGenerator.writeStartObject();

            jsonGenerator.writeStringField("type", d.getSkillType().toString());
            jsonGenerator.writeNumberField("value", d.getValue());
            jsonGenerator.writeNumberField("modifier", d.getModifier());
            jsonGenerator.writeNumberField("bonus", d.getBonus());

            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();
    }
}
