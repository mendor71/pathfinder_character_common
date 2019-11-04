package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.races.IRace;

import java.awt.*;
import java.util.UUID;

public class CharacterBase {
    private String uuid;
    private IRace race;
    private long armorClass;
    private long level;
    private String name;
    private long hitPointsMax;
    private long hitPointsCurrent;
    private long age;
    private long height;
    private long weight;
    private Color hairColor;
    private Color eyeColor;

    private CharacterBase() {
        this.uuid = UUID.randomUUID().toString();
    }

    public static Builder newBuilder() {
        return new CharacterBase().new Builder();
    }

    public String getUuid() {
        return uuid;
    }

    public IRace getRace() {
        return race;
    }

    public long getArmorClass() {
        return armorClass;
    }

    public long getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public long getHitPointsMax() {
        return hitPointsMax;
    }

    public long getHitPointsCurrent() {
        return hitPointsCurrent;
    }

    public long getAge() {
        return age;
    }

    public long getHeight() {
        return height;
    }

    public long getWeight() {
        return weight;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public class Builder {
        private Builder() {}

        public Builder setRace(IRace race) {
            CharacterBase.this.race = race;
            return this;
        }

        public Builder setArmorClass(long armorClass) {
            CharacterBase.this.armorClass = armorClass;
            return this;
        }

        public Builder setLevel(long level) {
            CharacterBase.this.level = level;
            return this;
        }

        public Builder setName(String name) {
            CharacterBase.this.name = name;
            return this;
        }

        public Builder setHitPointsMax(long hitPointsMax) {
            CharacterBase.this.hitPointsMax = hitPointsMax;
            return this;
        }

        public Builder setHitPointsCurrent(long hitPointsCurrent) {
            CharacterBase.this.hitPointsCurrent = hitPointsCurrent;
            return this;
        }

        public Builder setAge(long age) {
            CharacterBase.this.age = age;
            return this;
        }

        public Builder setHeight(long height) {
            CharacterBase.this.height = height;
            return this;
        }

        public Builder setWeight(long weight) {
            CharacterBase.this.weight = weight;
            return this;
        }

        public Builder setHairColor(Color hairColor) {
            CharacterBase.this.hairColor = hairColor;
            return this;
        }

        public Builder setEyeColor(Color eyeColor) {
            CharacterBase.this.eyeColor = eyeColor;
            return this;
        }

        public CharacterBase build() {
            return CharacterBase.this;
        }
    }
}
