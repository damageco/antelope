package co.ifwe.antelope.datingdemo.gen

import org.apache.commons.math3.distribution.GammaDistribution
import org.apache.commons.math3.random.RandomGenerator

class BetaRandom(rnd: RandomGenerator, a: Double, b: Double) {
  val gammaX = new GammaDistribution(rnd, a, 1)
  val gammaY = new GammaDistribution(rnd, b, 1)
  def next(): Double = {
    val x = gammaX.sample()
    val y = gammaY.sample()
    x / (x + y)
  }
}
