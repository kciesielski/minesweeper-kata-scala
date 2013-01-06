package org.minesweeperkata.common

/**
 * Based on SafeLong by SoftwareMill
 */
object SafeInt {
  val IntPattern = "-?([0-9]+)"

  def apply(o: Option[String]): Option[Int] = apply(o.getOrElse(""))

  def apply(o: String): Option[Int] = if (o.matches(IntPattern)) Some(o.toInt) else None

  def unapply(o: String): Option[Int] = if (o.matches(IntPattern)) Some(o.toInt) else None

  def orNone(i: Any): Option[Int] = i match {
    case i: Int => Some(i)
    case _ => None
  }
}