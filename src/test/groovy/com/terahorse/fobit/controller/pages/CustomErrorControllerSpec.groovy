package com.terahorse.fobit.controller.pages

import com.terahorse.fobit.IntegrationSpec
import org.junit.Test

class CustomErrorControllerSpec extends IntegrationSpec {

    @Test
    def "cards check"() {
        when: "read cards"
        def html = readPage("/error")

        then:
        html.contains('Unexpected Error')
    }

}
