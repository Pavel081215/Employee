package models




case class Employee(id: Long, name: String, surname: String)

object Employee {

  import anorm.SQL
  import anorm.SqlQuery
  import play.api.Play.current
  import play.api.db.DB


  val sql: SqlQuery = SQL("select * from employee order by name asc")

  var employees1: List[Employee] = DB.withConnection {
    implicit connection =>
      sql().map(row =>
        Employee(row[Long]("id"), row[String]("name"),
          row[String]("surname"))
      ).toList
  }

  def findAll = employees1.sortBy(_.id)

  def findByEan(name: String) = employees1.find(_.name == name)




  def add(employee: Employee) = ???



}
