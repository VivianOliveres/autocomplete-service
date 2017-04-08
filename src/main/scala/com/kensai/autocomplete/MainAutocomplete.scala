package com.kensai.autocomplete

object MainAutocomplete {

  def main(args: Array[String]) {
    val root = new TrieNode()
    root.insert("romulus")
    root.insert("romus")
    root.insert("toto")
    root.insert("rome")
    root.insert("totoro")
    root.insert("romuald")
    println(root)
  }
}
