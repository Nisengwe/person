import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Address } from '../service/request.service';

@Component({
  selector: 'app-address-dialog',
  templateUrl: './address-dialog.component.html',
  styleUrls: ['./address-dialog.component.scss']
})
export class AddressDialogComponent implements OnInit {

  address: Address
  form: FormGroup

  constructor(@Inject(MAT_DIALOG_DATA) data: Address,
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AddressDialogComponent>
  ) {
    this.address = data
    this.form = fb.group({
      id: [this.address.id],
      street: [this.address.street, Validators.required],
      city: [this.address.city, Validators.required],
      state: [this.address.state, Validators.required],
      postalcode: [this.address.postalcode, Validators.required]
    })
  }

  ngOnInit(): void {
  }

  submit() {
    this.dialogRef.close(this.form.value)
  }

}
