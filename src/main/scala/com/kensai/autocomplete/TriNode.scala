package com.kensai.autocomplete


class TriNode(var nodeValue: String, var isEndWord: Boolean, var children: Set[TriNode]) {

  def this() = this("", false, Set())
  def this(nodeValue: String) = this(nodeValue, true, Set())
  def this(nodeValue: String, isEndWord: Boolean) = this(nodeValue, isEndWord, Set())

  def insert(toInsert: String): Unit = {
    if (nodeValue == toInsert || toInsert.isEmpty) {
      isEndWord = true

    } else if (toInsert.startsWith(nodeValue)) {
      val newValueToInsert = toInsert.replaceFirst(nodeValue, "")
      children.foreach(child => {
        val prefix = intersec(child.nodeValue, newValueToInsert)
        if (!prefix.isEmpty) {
          child.insert(newValueToInsert)
          return
        }
      })

      children = children + new TriNode(newValueToInsert, true)

    } else {
      val prefix = intersec(nodeValue, toInsert)
      val oldSubstring = nodeValue.replaceFirst(prefix, "")
      val suffixToInsert = toInsert.replaceFirst(prefix, "")
      nodeValue = prefix
      children = Set(new TriNode(oldSubstring, isEndWord, children), new TriNode(suffixToInsert, true))
      isEndWord = false
    }
  }

  def intersec(left: String, right: String) : String = {
    left.zip(right)
        .takeWhile(tupple => tupple._1 == tupple._2)
        .foldLeft("") ((acc, pair) => acc + pair._1)
  }

  override def toString: String = {
    val childrenToString: String = children.toList
                                           .sortBy((node: TriNode) => node.nodeValue)
                                           .foldLeft("") { (s: String, node: TriNode) => s + ", " + node }
    "[" + nodeValue + "(" + isEndWord + ") -> {" + childrenToString + "}]"
  }

  override def equals(that: Any): Boolean =
    that match {
      case that: TriNode => that.nodeValue == nodeValue && that.isEndWord == isEndWord && that.children == children
      case _ => false
    }
}