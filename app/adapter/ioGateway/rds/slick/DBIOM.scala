package adapter.ioGateway.rds.slick

import slick.dbio.DBIO

import scala.concurrent.ExecutionContext.Implicits.global

import scalaz.Monad

class DBIOM extends Monad[DBIO] {

  def point[A](a : => A): DBIO[A] = DBIO.successful(a)

  def bind[A, B](fa: DBIO[A])(f: A => DBIO[B]): DBIO[B] = fa.flatMap(f)
}
