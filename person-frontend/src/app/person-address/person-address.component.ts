import { Component, OnInit, Input } from '@angular/core';
import { Address, Person, RequestService } from '../service/request.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddressDialogComponent } from '../address-dialog/address-dialog.component';

@Component({
  selector: 'app-person-address',
  templateUrl: './person-address.component.html',
  styleUrls: ['./person-address.component.scss']
})
export class PersonAddressComponent implements OnInit {


  @Input() person: Person

  constructor(private dialog: MatDialog, private request: RequestService) {
    this.person = {
      id: -1,
      firstName: "",
      lastName: "",
      address: []
    }
  }

  ngOnInit(): void {
  }

  //update address
  editAddress(address: Address) {
    const dialogConfig = new MatDialogConfig()
    dialogConfig.data = address;
    const dialogRef = this.dialog.open(AddressDialogComponent, dialogConfig)
    dialogRef.afterClosed().subscribe(value => {

      if (value) {
        this.person.address = this.person.address?.map(x => {
          if (value.id === x.id) {
            return value
          }
          return x
        })

        this.request.addAddress(this.person.id, value).subscribe(response => {
          if (response.id) {
            this.person = response;
          }
        }, err => alert(err))

      }

    })

  }

  //Add new address
  addAddress() {
    const dialogConfig = new MatDialogConfig()
    dialogConfig.data = {
      street: '',
      city: '',
      state: '',
      postalcode: ''
    };
    const dialogRef = this.dialog.open(AddressDialogComponent, dialogConfig)
    dialogRef.afterClosed().subscribe(value => {
      if (value) {
        this.request.addAddress(this.person.id, value).subscribe(response => {
          if (response.id) {
            this.person = response;
          }
        }, err => alert(err))

      }

    })

  }

  deleteAddress(address: Address) {
    if (confirm(`Are you sure to delete address (${address.id}) ?`)) {
      this.request.deleteAddress(this.person.id,address).subscribe(response => {
        this.person.address = this.person.address?.filter(x => x.id !== address.id)
      }, err => alert(err))
    }
  }

}
