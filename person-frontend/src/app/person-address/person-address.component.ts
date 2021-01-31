import { Component, OnInit, Input } from '@angular/core';
import { Address } from '../service/request.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddressDialogComponent } from '../address-dialog/address-dialog.component';

@Component({
  selector: 'app-person-address',
  templateUrl: './person-address.component.html',
  styleUrls: ['./person-address.component.scss']
})
export class PersonAddressComponent implements OnInit {


  @Input() addresses: Address[]

  constructor(private dialog: MatDialog) {
    this.addresses = []
  }

  ngOnInit(): void {
  }

  editAddress(address: Address) {
    const dialogConfig = new MatDialogConfig()
    dialogConfig.data = address;
    const dialogRef = this.dialog.open(AddressDialogComponent,dialogConfig)
  }

}
