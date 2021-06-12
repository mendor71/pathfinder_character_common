package com.mendor71.pathfinder.common.races;

import com.mendor71.pathfinder.common.exceptions.RaceNotExiststException;

import java.util.HashMap;
import java.util.Map;

public class RaceManager {
    private final static Map<Races, IRace> racesMap = new HashMap<>();

    static {
        racesMap.put(Races.ELF, ElfRace.getInstance());
        racesMap.put(Races.HUMAN, HumanRace.getInstance());
    }

    public static IRace get(Races race) throws RaceNotExiststException {
        if (!racesMap.containsKey(race))
            throw new RaceNotExiststException(race + " not exists or race map init incorrect");
        return racesMap.get(race);
    }

    public static Races getNameByRace(IRace race) throws RaceNotExiststException {
        return racesMap.entrySet()
            .stream()
            .filter( entry -> entry.getValue().equals(race) )
            .findFirst()
            .orElseThrow( () -> new RaceNotExiststException(race + " not exists or race map init incorrect") ).getKey();
    }
}
