import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { endpoint } from '../app.contant'

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private http: HttpClient) {

  }

  getPersons() {
    return this.http.get<Person[]>(endpoint + 'persons').pipe(
      catchError(this.handleError)
    );

  }

  getPerson(id: number){
    return this.http.get<Person>(endpoint +`person/${id}`).pipe(
      catchError(this.handleError)
    );
  }


  deleteAddress(personId:any,address:Address){
    return this.http.delete<Person>(`${endpoint}person/${personId}/address/${address.id}`).pipe(
      catchError(this.handleError)
    );
  }

  deletePerson(personId:any){
    return this.http.delete(`${endpoint}person/${personId}`).pipe(
      catchError(this.handleError)
    );
  }

  updatePerson(person:Person){
    return this.http.put<Person>(`${endpoint}person`,person).pipe(
      catchError(this.handleError)
    );
  }



  addAddress(personId:any,address:Address){
    return this.http.put<Person>(`${endpoint}person/${personId}/address`,address).pipe(
      catchError(this.handleError)
    );
  }

  createPerson(person:Person){
    return this.http.put<Person>(`${endpoint}person`,person).pipe(
      catchError(this.handleError)
    );
  }



  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }

}

export interface Person {
  id?:Number,
  firstName: string,
  lastName: string,
  address?: Address[]
}

export interface Address {
  id?:Number
  street: string,
  city: string,
  state: string,
  postalcode: string,
}



