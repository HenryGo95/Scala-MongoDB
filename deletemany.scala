import org.mongodb.scala._
import org.mongodb.scala.model.Filters._

object DeleteMany {

  def main(args: Array[String]): Unit = {

    val mongoClient: MongoClient = MongoClient("mongodb+srv://luis:isc.luisgomez95@cluster0.jpg5ftg.mongodb.net/?retryWrites=true&w=majority")
    val database: MongoDatabase = mongoClient.getDatabase("Scala")
    val collection: MongoCollection[Document] = database.getCollection("Datos")

    // Aquí se define el filtro para seleccionar los documentos que se eliminarán
    val filter = equal("campo", "valor") // Reemplaza "campo" y "valor" con tus criterios de selección

    val deleteObservable = collection.deleteMany(filter)
    deleteObservable.subscribe(new Observer[DeleteResult] {
      override def onNext(result: DeleteResult): Unit = println(s"Eliminados: ${result.getDeletedCount} documentos")
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Completado")
    })

    mongoClient.close()
  }
}
