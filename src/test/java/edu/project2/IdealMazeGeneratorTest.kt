package edu.project2

import edu.project2.generators.idealMaze.IdealMazeGenerator
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll

class IdealMazeGeneratorTest : StringSpec({

    val generator = IdealMazeGenerator()


    "Ideal maze generator should generate maze for positive integer sizes" {
        checkAll(Arb.int(1..50), Arb.int(1..50)) { a, b ->
            shouldNotThrowAny {
                generator.getMaze(a, b)
            }
        }
    }

    "Ideal maze generator should throw exception if height or width not positive" {
        checkAll(Arb.int(-100..-1), Arb.int(-100..-1)) { a, b ->
            shouldThrowAny {
                generator.getMaze(a, b)
            }
        }
    }

})
