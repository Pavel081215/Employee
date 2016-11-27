package controllers


import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import models._
import play.api.mvc.Flash


object Employees extends Controller{

  def list = Action { implicit request =>
    val employees = Employee.findAll
    Ok(views.html.employee.list(employees))
  }


  private val productForm: Form[Employee] = Form(
    mapping(
      "in" -> longNumber,
      "name" -> nonEmptyText.verifying(
        "validation.name.duplicate", Employee.findByEan(_).isEmpty),
      "surname" -> nonEmptyText
    )(Employee.apply)(Employee.unapply)
  )

  def save = Action { implicit request =>
    val newProductForm = productForm.bindFromRequest()
    newProductForm.fold(
      hasErrors = { form =>
        Redirect(routes.Employees.newProduct())
      },
      success = { newProduct =>
        Employee.add(newProduct)
        Redirect(routes.Employees.show(newProduct.name))
      }
    )
  }

  def show(name: String) = Action { implicit request =>
    Employee.findByEan(name).map { employee =>
      Ok(views.html.employee.details(employee))
    }.getOrElse(NotFound)
  }




  def newProduct = Action { implicit request =>

    val form = if (request.flash.get("error").isDefined)
      productForm.bind(request.flash.data)
    else

      productForm
    Ok(views.html.employee.editEmployee(form))
  }


  def update = Action { implicit request =>

    Ok(views.html.employee.editEmployee(productForm))
  }

}
