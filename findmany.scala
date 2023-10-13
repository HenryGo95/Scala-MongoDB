import org.mongodb.scala._
import org.mongodb.scala.model.Filters._

object FindMany {

  def main(args: Array[String]): Unit = {

    val mongoClient: MongoClient = MongoClient("mongodb+srv://luis:isc.luisgomez95@cluster0.jpg5ftg.mongodb.net/?retryWrites=true&w=majority")
    val database: MongoDatabase = mongoClient.getDatabase("Scala")
    val collection: MongoCollection[Document] = database.getCollection("Datos")

    // Filtro para seleccionar los documentos a buscar
    val filter = or(equal("nombre", "Enrique"), equal("nombre", "Pedro"), equal("nombre", "Pablo"))

    val findObservable = collection.find(filter)

    findObservable.subscribe(new Observer[Document] {
      override def onNext(result: Document): Unit = println(s"Encontrado: $result")
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Completado")
    })

    mongoClient.close()
  }
}
