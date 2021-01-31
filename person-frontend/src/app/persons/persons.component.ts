import { Component, OnInit,AfterViewInit, ViewChild } from '@angular/core'
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import {MatPaginator} from '@angular/material/paginator';
import { RequestService, Person } from '../service/request.service'


@Component({
  selector: 'app-persons',
  templateUrl: './persons.component.html',
  styleUrls: ['./persons.component.scss']
})
export class PersonsComponent implements OnInit {

  persons: Person[]
  dataSource = new MatTableDataSource<Person>([]);
  message: string
  columnsToDisplay = ['firstName', 'lastName','actions'];
  

  constructor(private request: RequestService,private router:Router) {
    this.persons = [];
    this.message = "";
  }

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  ngOnInit(): void {
    this.request.getPersons()
      .subscribe(data => this.dataSource.data = data, error => this.message = error)
  }

  showRecord(person:Person){
    this.router.navigate([`/person/${person.id}`])
  }

  addRecord(){
    this.router.navigate(['/person'])
  }

}
