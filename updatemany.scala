import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._

object UpdateMany {

  def main(args: Array[String]): Unit = {

    val mongoClient: MongoClient = MongoClient("mongodb+srv://luis:isc.luisgomez95@cluster0.jpg5ftg.mongodb.net/?retryWrites=true&w=majority")
    val database: MongoDatabase = mongoClient.getDatabase("Scala")
    val collection: MongoCollection[Document] = database.getCollection("Datos")

    // Filtro para seleccionar los documentos a actualizar
    val filter = or(equal("nombre", "Ejemplo1"), equal("nombre", "Ejemplo2")) // Cambia los nombres según tus necesidades

    // Nuevos valores para nombre y edad
    val newName = "Nuevo Nombre"
    val newAge = 35

    val updateObservable = collection.updateMany(
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
