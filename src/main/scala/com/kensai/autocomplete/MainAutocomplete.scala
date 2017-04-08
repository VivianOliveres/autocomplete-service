package com.kensai.autocomplete

object MainAutocomplete {

  def main(args: Array[String]) {
    val root = new TriNode()
    root.insert("romulus")
    root.insert("romus")
    println(root)

    // [ HashMap([romu HashMap(lus, s)])]

  }
}
