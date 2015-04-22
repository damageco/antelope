package co.ifwe.antelope.bestbuy.model

import co.ifwe.antelope.Text._
import co.ifwe.antelope.UpdateDefinition._
import co.ifwe.antelope._
import co.ifwe.antelope.feature._
import co.ifwe.antelope.bestbuy.event._

/**
 * A simple model implemented for the
 * [[https://www.kaggle.com/c/acm-sf-chapter-hackathon-small SF Bay Area ACM Data Mining Kaggle Competition]].
 *
 * Features are:
 *   - popularity overall
 *   - popularity for terms in query
 *   - popularity for bigrams in query
 *   - Tf-Idf for terms in product name
 *   - Tf-Idf for bigrams in product name
 *
 * This remains a very simple implementation and is readily extended.
 */
class BestBuyModel extends Model[ProductSearchScoringContext] {
  import s._
  type SC = ProductSearchScoringContext

  val skuSelected = defUpdate {
    case pv: ProductView => pv.skuSelected
  }

  val catalogSku = defUpdate {
    case pu: ProductUpdate => pu.sku
  }

  feature(new OverallPopularityFeature(skuSelected))
  for (te <- List(terms, bigrams)) {
    feature(new TermPopularityFeature(skuSelected, new TermsFromText(te)))
    feature(new TfIdfFeature(catalogSku, new TermsProductUpdate(te)))
  }
}
