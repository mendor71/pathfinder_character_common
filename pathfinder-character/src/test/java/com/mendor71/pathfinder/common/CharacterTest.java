package com.mendor71.pathfinder.common;

import com.mendor71.pathfinder.common.pathfinderclasses.IClassManager;
import com.mendor71.pathfinder.common.pathfinderclasses.PersonifiedClassManager;
import com.mendor71.pathfinder.common.races.ElfRace;
import org.junit.Before;

public class CharacterTest {

    private Character character;

    @Before
    public void before() {
        CharacterBase characterBase = CharacterBase.newBuilder().setRace(new ElfRace()).build();



        IClassManager classManager = new PersonifiedClassManager(characterBase.getUuid());
    }

}
