package co.ifwe.antelope.bestbuy.exec.explore

import java.io.File

object Config {
  private def getEnv(envVar: String): String = {
    getEnvOption(envVar) match {
      case Some(res) =>
        res
      case None =>
        throw new IllegalArgumentException(s"must set $$$envVar environment variable")
    }
  }

  private def getEnvOption(envVar: String): Option[String] = {
    val res = System.getenv(envVar)
    if (res == null || res.isEmpty) {
      None
    } else {
      Some(res)
    }
  }

  val dataDir = getEnv("ANTELOPE_DATA")
  val trainingDir = getEnv("ANTELOPE_TRAINING")
  val cacheDir = getEnv("ANTELOPE_CACHE")
  val trainingStart = getEnv("ANTELOPE_TRAINING_START").toLong
  val trainingLimit = getEnv("ANTELOPE_TRAINING_LIMIT").toLong
  val scoringLimit = getEnv("ANTELOPE_SCORING_LIMIT").toLong
  val scoringTiming = getEnvOption("ANTELOPE_SCORING_TIMING") match {
    case Some(x) => x.toBoolean
    case None => false
  }
  val rCommand = getEnv("ANTELOPE_R_COMMAND")

  val viewsFn = dataDir + File.separator + "train_sorted.csv"
  val viewsFnBin = viewsFn + ".bin"
  val viewsFnBinCprog = viewsFn + ".bin-cp"
  val productsDirectory = dataDir + File.separator + "product_data/products"
  val mergedProducts = productsDirectory + File.separatorChar + "products_merged.bin"
  val allEvents = dataDir + File.separator + "all_events.bin"
  val weightsFn = trainingDir + File.separator + "r_logit_coef.txt"
}
