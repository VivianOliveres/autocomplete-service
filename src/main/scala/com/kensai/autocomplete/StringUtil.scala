package com.kensai.autocomplete

trait StringUtil {

  def intersection(left: String, right: String) : String = {
    left.zip(right)
      .takeWhile(tupple => tupple._1 == tupple._2)
      .foldLeft("") ((acc, pair) => acc + pair._1)
  }

}
