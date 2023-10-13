import org.mongodb.scala._
import org.mongodb.scala.model.Indexes._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._

object InsertMany {

  def main(args: Array[String]): Unit = {

    val mongoClient: MongoClient = MongoClient("mongodb+srv://luis:isc.luisgomez95@cluster0.jpg5ftg.mongodb.net/?retryWrites=true&w=majority")
    val database: MongoDatabase = mongoClient.getDatabase("Scala")
    val collection: MongoCollection[Document] = database.getCollection("Datos")

    val documents = List(
      Document("_id" -> 1, "nombre" -> "Paquito", "edad" -> 30),
      Document("_id" -> 2, "nombre" -> "Juanito", "edad" -> 25),
      Document("_id" -> 3, "nombre" -> "Pedro", "edad" -> 35),
      Document("_id" -> 4, "nombre" -> "Miqueas", "edad" -> 40),
      Document("_id" -> 5, "nombre" -> "Francisco", "edad" -> 28),
      Document("_id" -> 6, "nombre" -> "Enrique", "edad" -> 32),
      Document("_id" -> 7, "nombre" -> "Pablo", "edad" -> 27),
      Document("_id" -> 8, "nombre" -> "Jesus", "edad" -> 33),
      Document("_id" -> 9, "nombre" -> "Maria", "edad" -> 29),
      Document("_id" -> 10, "nombre" -> "Mercedes", "edad" -> 31)
    )

    val insertObservable = collection.insertMany(documents)
    insertObservable.subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println(s"Insertado: $result")
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Completado")
    })

    mongoClient.close()
  }
}
