import org.mongodb.scala._
import org.mongodb.scala.model.Filters._

object Delete {

  def main(args: Array[String]): Unit = {

    val mongoClient: MongoClient = MongoClient("mongodb+srv://luis:isc.luisgomez95@cluster0.jpg5ftg.mongodb.net/?retryWrites=true&w=majority")
    val database: MongoDatabase = mongoClient.getDatabase("Scala")
    val collection: MongoCollection[Document] = database.getCollection("Datos")

    val namesToDelete = Seq("pedro", "miqueas")

    namesToDelete.foreach { name =>
      val deleteObservable = collection.deleteMany(equal("nombre", name))
      deleteObservable.subscribe(new Observer[DeleteResult] {
        override def onNext(result: DeleteResult): Unit = println(s"Eliminados: ${result.getDeletedCount} documentos con nombre $name")
        override def onError(e: Throwable): Unit = println(s"Error: $e")
        override def onComplete(): Unit = println("Completado")
      })
    }

    mongoClient.close()
  }
}
