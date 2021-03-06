package org.elastic4play.services

import javax.inject.{ Inject, Singleton }

import scala.concurrent.{ ExecutionContext, Future }

import org.elastic4play.database.DBGet
import org.elastic4play.models.{ AbstractModelDef, EntityDef }

@Singleton
class GetSrv @Inject() (
    dbGet: DBGet,
    implicit val ec: ExecutionContext) {

  def apply[M <: AbstractModelDef[M, E], E <: EntityDef[M, E]](model: M, id: String): Future[E] = {
    dbGet(model.modelName, id).map(attrs ⇒ model(attrs))
  }
}