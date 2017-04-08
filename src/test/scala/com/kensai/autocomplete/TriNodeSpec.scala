package com.kensai.autocomplete

import org.scalatest._

class TriNodeSpec extends FlatSpec with Matchers {

  "Insertion" should "create correct model" in {
    val root = new TriNode()

    // Insert Romulus
    root.insert("romulus")

    val expectedRomulus = new TriNode("romulus", true)
    val expectedFirstRoot = new TriNode("", false, Set(expectedRomulus))
    root should be (expectedFirstRoot)

    // Insert Romus
    root.insert("romus")

    val expectedS = new TriNode("s", true)
    val expectedLus = new TriNode("lus", true)
    val expectedRomu = new TriNode("romu", false, Set(expectedS, expectedLus))
    val expectedSecondRoot = new TriNode("", false, Set(expectedRomu))
    root should be (expectedSecondRoot)

    // Insert toto
    root.insert("toto")

    val expectedToto = new TriNode("toto", true)
    val expectedThirdRoot = new TriNode("", false, Set(expectedRomu, expectedToto))
    root should be (expectedThirdRoot)

    // Insert rome
    root.insert("rome")

    val expectedU = new TriNode("u", false, Set(expectedS, expectedLus))
    val expectedE = new TriNode("e", true)
    val expectedRom = new TriNode("rom", false, Set(expectedE, expectedU))
    val expectedFourthRoot = new TriNode("", false, Set(expectedRom, expectedToto))
    root should be (expectedFourthRoot)

    // Insert totoro
    root.insert("totoro")

    val expectedRo = new TriNode("ro", true)
    expectedToto.children = expectedToto.children + expectedRo
    root should be (expectedFourthRoot)

    // Insert romuald
    root.insert("romuald")
    val expectedAld = new TriNode("ald", true)
    expectedU.children = expectedU.children + expectedAld
    root should be (expectedFourthRoot)
    println(root)
  }

}