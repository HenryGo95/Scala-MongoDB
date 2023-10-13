import org.mongodb.scala._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.model.Updates._

object InsertOne {

  def main(args: Array[String]): Unit = {

    val mongoClient: MongoClient = MongoClient("mongodb+srv://luis:isc.luisgomez95@cluster0.jpg5ftg.mongodb.net/?retryWrites=true&w=majority")
    val database: MongoDatabase = mongoClient.getDatabase("Scala")
    val collection: MongoCollection[Document] = database.getCollection("Datos")

    val document: Document = Document("nombre" -> "jorge", "edad" -> 30)

    val insertObservable = collection.insertOne(document)
    insertObservable.subscribe(new Observer[Completed] {
      override def onNext(result: Completed): Unit = println("Documento insertado correctamente")
      override def onError(e: Throwable): Unit = println(s"Error: $e")
      override def onComplete(): Unit = println("Completado")
    })

    mongoClient.close()
  }
}
