package edu.project2

import edu.project2.generators.chaoticMaze.ChaoticMazeGenerator
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll

class ChaoticMazeGeneratorTest : ShouldSpec({

    var generator = ChaoticMazeGenerator()

    should("generate maze for positive integer sizes") {
        checkAll(Arb.int(1..50), Arb.int(1..50)) { a, b ->
            generator.getMaze(a, b, 50)
        }
    }

    should("throw exception if height or width not positive") {
        checkAll(Arb.int(-100..-1), Arb.int(-100..-1)) { a, b ->
            shouldThrowAny {
                generator.getMaze(a, b, 50)
            }
        }
    }
})
