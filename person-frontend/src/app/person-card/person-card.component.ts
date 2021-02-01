import { Component, OnInit, Input } from '@angular/core';
import { Person, RequestService } from '../service/request.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { PersonDialogComponent } from '../person-dialog/person-dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-person-card',
  templateUrl: './person-card.component.html',
  styleUrls: ['./person-card.component.scss']
})
export class PersonCardComponent implements OnInit {

  @Input() person: Person

  constructor(
    private dialog: MatDialog,
    private request: RequestService,
    private router: Router
  ) {
    this.person = {
      firstName: "",
      lastName: "",
      address: []
    }
  }

  ngOnInit(): void {
  }

  editPerson() {
    const dialogConfig = new MatDialogConfig()
    dialogConfig.data = this.person
    const dialogRef = this.dialog.open(PersonDialogComponent, dialogConfig)
    dialogRef.afterClosed().subscribe(value => {
      if (value) {
        this.person.firstName = value.firstName
        this.person.lastName = value.lastName
        this.request.updatePerson(this.person).subscribe(response => {
        }, err => alert(err))
      }

    })
  }

  addPerson() {
    const dialogConfig = new MatDialogConfig()
    dialogConfig.data = this.person
    const dialogRef = this.dialog.open(PersonDialogComponent, dialogConfig)
    dialogRef.afterClosed().subscribe(value => {
      if (value) {
        this.person.firstName = value.firstName
        this.person.lastName = value.lastName
        this.request.updatePerson(this.person).subscribe(response => {
          this.person = response;
          this.router.navigate([`/person/${response.id}`])
        }, err => alert(err))
      }

    })
  }

  deletePerson(){
      if(confirm(`Are sure to delete ${this.person.firstName}?`))
      this.request.deletePerson(this.person.id).subscribe( response =>{
        this.router.navigate([`/persons`])
      },err => alert(err))
  }

}
