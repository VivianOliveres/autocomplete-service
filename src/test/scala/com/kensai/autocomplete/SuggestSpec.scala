package com.kensai.autocomplete

import org.scalatest._

class SuggestSpec extends FunSuite with Matchers with BeforeAndAfter {

  val root = new TrieNode()

  before {
    root.insert("romulus")
    root.insert("romus")
    root.insert("toto")
    root.insert("rome")
    root.insert("totoro")
    root.insert("romuald")
  }

  test("Suggest for 'rom' should return 4 values") {
    val words: List[String] = root.suggest("rom")

    words should contain ("romulus")
    words should contain ("romus")
    words should contain ("romuald")
    words should contain ("rome")

    words should have size 4
  }

  test("Suggest for 't' should return 2 values") {
    val words: List[String] = root.suggest("t")

    words should contain ("toto")
    words should contain ("totoro")

    words should have size 2
  }

  test("Suggest for 'totoro' should return 1 value") {
    val words: List[String] = root.suggest("totoro")

    words should contain ("totoro")

    words should have size 1
  }

  test("Suggest for 'blabla' should return 0 value") {
    val words: List[String] = root.suggest("blabla")

    words should have size 0
  }

  test("Suggest for 'romu' should return 3 values") {
    val words: List[String] = root.suggest("romu")

    words should contain ("romulus")
    words should contain ("romus")
    words should contain ("romuald")

    words should have size 3
  }
}
