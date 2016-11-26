package models

case class Employee (id: Int, name: String, surname: String)

object Employee {

  var employees = Set(
    Employee(1, "Ivan",
      "Susanin"),
    Employee(2, "Koly",
      "Khopkin"),
    Employee(3, "Olena",
      "Klushuna"),
    Employee(4, "Veniamin",
      "Pusturiv"),
    Employee(5, "Casanldra",
      "Zebrova")
  )

  //def find(): employee = employees.groupBy()
  def findAll = employees.toList.sortBy(_.id)
}
