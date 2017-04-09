package com.kensai.autocomplete


class TrieNode(var nodeValue: String, var isEndWord: Boolean, var children: Set[TrieNode]) extends StringUtil {

  def this() = this("", false, Set())
  def this(nodeValue: String) = this(nodeValue, true, Set())
  def this(nodeValue: String, isEndWord: Boolean) = this(nodeValue, isEndWord, Set())

  def insert(toInsert: String): Unit = {
    if (nodeValue == toInsert || toInsert.isEmpty) {
      isEndWord = true

    } else if (toInsert.startsWith(nodeValue)) {
      val newValueToInsert = toInsert.replaceFirst(nodeValue, "")
      children.foreach(child => {
        val prefix = intersection(child.nodeValue, newValueToInsert)
        if (!prefix.isEmpty) {
          child.insert(newValueToInsert)
          return
        }
      })

      children = children + new TrieNode(newValueToInsert, true)

    } else {
      val prefix = intersection(nodeValue, toInsert)
      val oldSubstring = nodeValue.replaceFirst(prefix, "")
      val suffixToInsert = toInsert.replaceFirst(prefix, "")
      nodeValue = prefix
      children = Set(new TrieNode(oldSubstring, isEndWord, children), new TrieNode(suffixToInsert, true))
      isEndWord = false
    }
  }

  def list(): List[String] = {
    if (children.isEmpty) {
      return List(nodeValue)
    }

    children.flatMap(node => node.list())
            .map(word => nodeValue + word)
            .toList
  }

  override def toString: String = {
    val childrenToString: String = children.toList
                                           .sortBy((node: TrieNode) => node.nodeValue)
                                           .foldLeft("") { (s: String, node: TrieNode) => s + ", " + node }
    "[" + nodeValue + "(" + isEndWord + ") -> {" + childrenToString + "}]"
  }

  override def equals(that: Any): Boolean =
    that match {
      case that: TrieNode => that.nodeValue == nodeValue && that.isEndWord == isEndWord && that.children == children
      case _ => false
    }
}
