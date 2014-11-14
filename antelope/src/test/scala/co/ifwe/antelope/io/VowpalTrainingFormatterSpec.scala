package co.ifwe.antelope.io

import co.ifwe.antelope.TrainingExample
import org.scalatest.FlatSpec

class VowpalTrainingFormatterSpec extends FlatSpec {
  "A VowpalTrainingFormatter" should "format simple content" in {
    val f = new VowpalTrainingFormatter()
    assert(f.header() === None)

    val e1 = new TrainingExample(true,Array(("column_a",1D),("column_b",2D)))
    assert(f.format(e1) === "+1 |f column_a:1.0 column_b:2.0")

    val e2 = new TrainingExample(false,Array(("column_a",6D),("column_b",3D)))
    assert(f.format(e2) === "-1 |f column_a:6.0 column_b:3.0")
  }
}
