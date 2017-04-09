package com.kensai.autocomplete

class TrieSuggester {

  val root: TrieNode = new TrieNode()

  def insert(word: String) = root.insert(word)

  def suggest(input: String): List[String] = root.suggest(input)
}
