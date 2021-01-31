import { Component, OnInit, Input } from '@angular/core';
import { Person } from '../service/request.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { PersonDialogComponent } from '../person-dialog/person-dialog.component';

@Component({
  selector: 'app-person-card',
  templateUrl: './person-card.component.html',
  styleUrls: ['./person-card.component.scss']
})
export class PersonCardComponent implements OnInit {

  @Input() person: Person

  constructor(private dialog: MatDialog) {
    this.person = {
      firstName: "",
      lastName: ""
    }
  }

  ngOnInit(): void {
  }

  editPerson(){
    const dialogConfig = new MatDialogConfig()
    dialogConfig.data = this.person
    const dialogRef = this.dialog.open(PersonDialogComponent,dialogConfig)
  }

}
