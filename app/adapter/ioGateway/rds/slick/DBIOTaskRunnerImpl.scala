package adapter.ioGateway.rds.slick

import slick.dbio._
import util.dddSupport.adapter.DBIOTaskRunner
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ ExecutionContext, Future }

class DBIOTaskRunnerImpl
  extends DBIOTaskRunner[DBIO]
  with DBProfile {

  import driver.api.jdbcActionExtensionMethods

  override def run[R](a: DBIO[R])(implicit ctx: ExecutionContext): Future[R] = masterDB.run(a)

  override def runSeq[R](as: DBIO[Seq[R]])(implicit ctx: ExecutionContext): Future[Seq[R]] = run(as)

  override def runTransaction[R](a: DBIO[R])(implicit ctx: ExecutionContext): Future[R] =  run(a.transactionally)
}
