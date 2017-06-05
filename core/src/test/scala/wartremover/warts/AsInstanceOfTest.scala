package org.wartremover
package test

import org.scalatest.FunSuite

import org.wartremover.warts.AsInstanceOf

import scala.io.Source

class AsInstanceOfTest extends FunSuite with ResultAssertions {
  test("asInstanceOf is disabled") {
    val result = WartTestTraverser(AsInstanceOf) {
      "abc".asInstanceOf[String]
    }
    assertError(result)("asInstanceOf is disabled")
  }
  test("asInstanceOf wart works with GADTs") {
    val result = WartTestTraverser.applyToFiles(AsInstanceOf)(
      getClass.getResource("/GADT.scala").getPath
    )
    assertEmpty(result)
  }
  test("asInstanceOf wart obeys SuppressWarnings") {
    val result = WartTestTraverser(AsInstanceOf) {
      @SuppressWarnings(Array("org.wartremover.warts.AsInstanceOf"))
      val foo = "abc".asInstanceOf[String]
    }
    assertEmpty(result)
  }
}
