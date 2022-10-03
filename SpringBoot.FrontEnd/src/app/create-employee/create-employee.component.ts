import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css'],
})
export class CreateEmployeeComponent implements OnInit {
  employee: Employee = new Employee();

  constructor(private employeeService: EmployeeService,private router:Router) {}

  saveEmployee()
  {
    this.employeeService.CreateEmployee(this.employee).subscribe(data=>{
      console.log(data);
      this.goToEmployeeList();
    },
    error=>console.error());
    
     
  }
  goToEmployeeList()
  {
    this.router.navigate(['/employees']);


  }

  ngOnInit(): void {}

  onSubmit() {
    
    this.saveEmployee();

  }
}
