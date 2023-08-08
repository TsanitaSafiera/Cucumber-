Feature: Syntax API HRMS Flow

  Background:
    Given a JWT is generated

  @api
  Scenario: Creating the employee using API
    Given a request is prepared for creating an employee
    When  a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable

  @api
  Scenario: Get the created Employee
    Given a request is prepared for retrieving an employee
    When a GET call is made to retrieve the employee
    Then the status code for this employee is 200
    And the employee id "employee.employee_id" must match with globally stored employee id
    And this employee data at "employee" object matches with the data used to create the employee
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |nina         |Sara         |ms            |Female    |1995-07-23  |married   |QA           |


  @json
  Scenario: Creating an employee using json body
    Given a request is prepared for creating an employee using json payload
    When  a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as a global variable


   @dynamic
  Scenario: Creating an employee using highly dynamic scenario
    Given a request is prepared for creating an employee with data "justin" , "azzuri" , "ms" , "M" , "2000-07-21" , "confirmed" , "QA"
     When  a POST call is made to create an employee
     Then the status code for creating an employee is 201
     And the employee created contains key "Message" and value "Employee Created"
     And the employee id "Employee.employee_id" is stored as a global variable

  @hw @t @api
  Scenario:partially update the created employee
    Given the request is prepared to update the employee firstname to "joan"
    When a PATCH call is made to update the employee
    Then the status code is 201
    And the employee updated has the updated firstname "joan"


  @test2 @t @api
  Scenario:Get the created Employee
    Given a request is prepared for retrieving an employee
    When a GET call is made to retrieve the employee
    Then the status code for this employee is 200
    And the employee id "employee.employee_id" must match with globally stored employee id
    And this employee data at "employee" object matches with the data used to create the employee
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |joan         |Sara         |ms            |Female    |1995-07-23  |married   |QA           |
