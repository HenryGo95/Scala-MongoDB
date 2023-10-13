import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._

object Update {

  def main(args: Array[String]): Unit = {

    val mongoClient: MongoClient = MongoClient("mongodb+srv://luis:isc.luisgomez95@cluster0.jpg5ftg.mongodb.net/?retryWrites=true&w=majority")
    val database: MongoDatabase = mongoClient.getDatabase("Scala")
    val collection: MongoCollection[Document] = database.getCollection("Datos")

    // Filtro para seleccionar el documento a actualizar
    val filter = equal("nombre", "Pedro") // Cambia al nombre que deseas actualizar

    // Nuevos valores para nombre y edad
    val newName = "Pancrasio"
    val newAge = 87

    val updateObservable = collection.updateOne(
      filter,
      set("nombre", newName) ++ set("edad", newAge)
    )

    updateObservable.subscribe(new Observer[UpdateResult] {
      override def onNext(result: UpdateResult): Unit = {
        println(s"Documentos actualizados: ${result.getModifiedCount}")
      }
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Completado")
    })

    mongoClient.close()
  }
}
