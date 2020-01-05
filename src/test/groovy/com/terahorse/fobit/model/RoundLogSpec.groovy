package com.terahorse.fobit.model

import com.terahorse.fobit.UnitSpec

class RoundLogSpec extends UnitSpec {

    def "Test of getters/setters"() {
        when:
        def roundLog = new RoundLog(new Player("human", true), new Player("computer", false))

        then:
        roundLog.humanPlayer.name == "human"
        roundLog.computerPlayer.name == "computer"
    }

}
