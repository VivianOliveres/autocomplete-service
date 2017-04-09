package com.kensai.autocomplete

import org.scalatest._

class TrieNodeSpec extends FlatSpec with Matchers {

  "Insertion" should "create correct model" in {
    val root = new TrieNode()

    // Insert Romulus
    root.insert("romulus")

    val expectedRomulus = new TrieNode("romulus", true)
    val expectedFirstRoot = new TrieNode("", false, Set(expectedRomulus))
    root should be (expectedFirstRoot)

    // Insert Romus
    root.insert("romus")

    val expectedS = new TrieNode("s", true)
    val expectedLus = new TrieNode("lus", true)
    val expectedRomu = new TrieNode("romu", false, Set(expectedS, expectedLus))
    val expectedSecondRoot = new TrieNode("", false, Set(expectedRomu))
    root should be (expectedSecondRoot)

    // Insert toto
    root.insert("toto")

    val expectedToto = new TrieNode("toto", true)
    val expectedThirdRoot = new TrieNode("", false, Set(expectedRomu, expectedToto))
    root should be (expectedThirdRoot)

    // Insert rome
    root.insert("rome")

    val expectedU = new TrieNode("u", false, Set(expectedS, expectedLus))
    val expectedE = new TrieNode("e", true)
    val expectedRom = new TrieNode("rom", false, Set(expectedE, expectedU))
    val expectedFourthRoot = new TrieNode("", false, Set(expectedRom, expectedToto))
    root should be (expectedFourthRoot)

    // Insert totoro
    root.insert("totoro")

    val expectedRo = new TrieNode("ro", true)
    expectedToto.children = expectedToto.children + expectedRo
    root should be (expectedFourthRoot)

    // Insert romuald
    root.insert("romuald")
    val expectedAld = new TrieNode("ald", true)
    expectedU.children = expectedU.children + expectedAld
    root should be (expectedFourthRoot)
  }

  "List" should "return a list of all values inserted" in {
    val root = new TrieNode()
    root.insert("romulus")
    root.insert("romus")
    root.insert("toto")
    root.insert("rome")
    root.insert("totoro")
    root.insert("romuald")

    val words: List[String] = root.list()

    words should contain ("romulus")
    words should contain ("romus")
    words should contain ("toto")
    words should contain ("rome")
    words should contain ("totoro")
    words should contain ("romuald")
    words should have size 6
  }

}