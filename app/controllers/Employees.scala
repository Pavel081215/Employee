package controllers
import play.api.mvc.{Action, Controller}
import models._


object Employees extends Controller{

  def list = Action { implicit request =>
    val employees = Employee.findAll
    Ok(views.html.employee.list(employees))
  }

}
