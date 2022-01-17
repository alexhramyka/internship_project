# internship_project

## Use Cases: 

### Case 1: get all employees.
#### Characters:
* Admin
* System
#### Successful script:
1. The system provides list of employees.
#### Result:
List of employees.

### Case 2: get employee by id.
#### Characters:
* Admin 
* System
#### Successful script: 
1. The Admin enteres employee id.
2. The system validates the provided id.
3. The System provides information about specific employee to the Admin.
#### Result: 
Information about specific employee.
#### Extensions:
* 2.1: Error of validating id. 
    #### Result:
    Exception
    
    
* 2.2: Employee with such id doesn't exist
    #### Result:
    Exception
   
### Case 3: add employee
#### Characters:
* Admin
* System.
#### Successful script:
1. The admin provides information about the employee.
2. The system validates provided data
3. The system adds an employee to the database
#### Result:
The system adds employee to the database
#### Extensions:
 * 2.1: Error validating provided data
    #### Result: 
    Exception
    
    
### Case 4: update employee by id
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides employee id and information for update data of employee.
2. The system validates provided id.
3. The system validates the data provided for updating.
4. The system updates employee.
#### Result:
The system updates employee
#### Extensions:
 * 2.1: Error validating provided id
    #### Result: 
    Exception
    
 * 3.1: Error validating provided data
    #### Result:
    Exception

### Case 5: delete employee by id
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides employee id.
2. The system validates provided id.
3. The system deletes employee.
#### Result:
The system deletes employee
#### Extensions:
 * 2.1: Error validating provided id
    #### Result: 
    Exception
    
### Case 6: upload employees from file
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides csv file with list of employees.
2. The system validates employees.
3. The system adds employees to the database
#### Result:
The system deletes employee
#### Extensions:
 * 2.1: Error validating employees
    #### Result: 
    Exception
    
#### Case 7.1: get currently available employees
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The system provides list of all currently available employees.
#### Result:
The system provides list of all currently available employees.


#### Case 7.2: get employees available within a month
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The system validates specified date
2. The system provides list of all available employees
#### Result:
The system provides list of all available employees
#### Extensions:
 * 2.1: Error validating date
    #### Result: 
    Exception

#### Case 8.1: get report on the monthly workload of employees by department.
#### Characters: 
* Admin
* System
#### Successful script:
1. The system collecting information about monthly workload of employees by department
2. The system provides report
#### Result:
The system provides report

#### Case 8.2: get report about employees who will be available within the next 30 days.
#### Characters: 
* Admin
* System
#### Successful script:
1. The system collecting information about employees who will be available within the next 30 days
2. The system provides report
#### Result:
The system provides report

### Case 9: get all departments.
#### Characters:
* Admin
* System
#### Successful script:
1. The system provides list of departments.
#### Result:
List of departments.

### Case 10: get department by id.
#### Characters:
* Admin 
* System
#### Successful script: 
1. The Admin enteres department id.
2. The system validates the provided id.
3. The System provides information about specific department.
#### Result: 
Information about specific department.
#### Extensions:
* 2.1: Error of validating id. 
    #### Result:
    Exception
    
    
* 2.2: Department with such id doesn't exist
    #### Result:
    Exception
   
### Case 11: add department
#### Characters:
* Admin
* System.
#### Successful script:
1. The admin provides information about the department.
2. The system validates provided data
3. The system adds a department to the database
#### Result:
The system adds department to the database
#### Extensions:
 * 2.1: Error validating provided data
    #### Result: 
    Exception
    
    
### Case 12: update department by id
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides department id and information for update data of department.
2. The system validates provided id.
3. The system validates the data provided for updating.
4. The system updates department.
#### Result:
The system updates department
#### Extensions:
 * 2.1: Error validating provided id
    #### Result: 
    Exception
    
 * 3.1: Error validating provided data
    #### Result:
    Exception

### Case 13: delete department by id
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides department id.
2. The system validates provided id.
3. The system deletes department.
#### Result:
The system deletes department
#### Extensions:
 * 2.1: Error validating provided id
    #### Result: 
    Exception

### Case 14: get all projects.
#### Characters:
* Admin
* System
#### Successful script:
1. The system provides list of projects.
#### Result:
List of projects.

### Case 15: get project by id.
#### Characters:
* Admin 
* System
#### Successful script: 
1. The Admin enteres project id.
2. The system validates the provided id.
3. The System provides information about specific project.
#### Result: 
Information about specific project.
#### Extensions:
* 2.1: Error of validating id. 
    #### Result:
    Exception
    
    
* 2.2: Project with such id doesn't exist
    #### Result:
    Exception
   
### Case 16: add project
#### Characters:
* Admin
* System.
#### Successful script:
1. The admin provides information about the project.
2. The system validates provided data
3. The system adds a project to the database
#### Result:
The system adds project to the database
#### Extensions:
 * 2.1: Error validating provided data
    #### Result: 
    Exception
    
    
### Case 17: update project by id
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides project id and information for update data of department.
2. The system validates provided id.
3. The system validates the data provided for updating.
4. The system updates project.
#### Result:
The system updates project
#### Extensions:
 * 2.1: Error validating provided id
    #### Result: 
    Exception
    
 * 3.1: Error validating provided data
    #### Result:
    Exception

### Case 18: delete project by id
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides project id.
2. The system validates provided id.
3. The system deletes project.
#### Result:
The system deletes project
#### Extensions:
 * 2.1: Error validating provided id
    #### Result: 
    Exception
    
    
### Case 19: add employee to the project
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides project id.
2. The admin provides employee id.
3. The system validates provided employee id.
4. The system validates provided project id.
5. The system adds employee to the project.
#### Result:
The system adds employee to the project
#### Extensions:
 * 3.1: Error validating provided id
    #### Result: 
    Exception
 * 4.1: Error validating provided id
    #### Result: 
    Exception   
 * 5.1: Such employee is already on the project
    #### Result:
    Exception
    
### Case 20: add employee in the department
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides department id.
2. The admin provides employee id.
3. The system validates provided employee id.
4. The system validates provided department id.
5. The system adds employee in the department.
#### Result:
The system adds employee to the department
#### Extensions:
 * 3.1: Error validating provided id
    #### Result: 
    Exception
 * 4.1: Error validating provided id
    #### Result: 
    Exception   
 * 5.1: Such employee is already in the department
    #### Result:
    Exception
    
### Case 21: remove employee from the department
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides department id.
2. The admin provides employee id.
3. The system validates provided employee id.
4. The system validates provided department id.
5. The system removes employee from the department.
#### Result:
The system adds employee to the department
#### Extensions:
 * 3.1: Error validating provided id
    #### Result: 
    Exception
 * 4.1: Error validating provided id
    #### Result: 
    Exception   
 * 5.1: Such employee isn't in the department
    #### Result:
    Exception
    
### Case 22: remove employee from the project
#### Characters: 
* Admin
* System
#### Seccessful script:
1. The admin provides project id.
2. The admin provides employee id.
3. The system validates provided employee id.
4. The system validates provided project id.
5. The system removes employee from the project.
#### Result:
The system removes employee from the project
#### Extensions:
 * 3.1: Error validating provided id
    #### Result: 
    Exception
 * 4.1: Error validating provided id
    #### Result: 
    Exception   
 * 5.1: Such employee isn't on the project
    #### Result:
    Exception
