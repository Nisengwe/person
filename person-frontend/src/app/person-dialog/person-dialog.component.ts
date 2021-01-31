import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Person } from '../service/request.service';

@Component({
  selector: 'app-person-dialog',
  templateUrl: './person-dialog.component.html',
  styleUrls: ['./person-dialog.component.scss']
})
export class PersonDialogComponent implements OnInit {

  form: FormGroup
  person: Person

  constructor(
    @Inject(MAT_DIALOG_DATA) data: Person,
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<PersonDialogComponent>
  ) {
    this.person = data
    this.form = fb.group({
      firstName: [this.person.firstName, Validators.required],
      lastName: [this.person.lastName, Validators.required]
    })
  }

  ngOnInit(): void {
  }

}
