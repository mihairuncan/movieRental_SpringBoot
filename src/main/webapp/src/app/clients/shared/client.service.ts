import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Client} from './client.model';
import {catchError, map} from 'rxjs/operators';


@Injectable()
export class ClientService {
  private clientsUrl = 'http://localhost:8080/clients';

  constructor(private httpClient: HttpClient) {
  }

  getClients(): Observable<Client[]> {
    return this.httpClient
      .get<Array<Client>>(this.clientsUrl)
      // .pipe(
      //   catchError(this.handleError<Client[]>('getClients', []))
      // )
    ;
  }

  getClient(id: number): Observable<Client> {
    return this.getClients()
      .pipe(map(clients => clients.find(client => client.id === id)));
  }

  save(client): Observable<Client> {
    console.log('client service: ', client);

    return this.httpClient
      .post<Client>(this.clientsUrl, client);
  }

  delete(clientid: number): Observable<any> {
    const url = `${this.clientsUrl}/${clientid}`;
    return this.httpClient
      .delete(url);
  }

  update(client: Client): Observable<Client> {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.httpClient
      .put<Client>(url, client);

  }

  // private handleError<T>(operation = 'operation', result?: T) {
  //   return (error: any): Observable<T> => {
  //     alert('Error fetching clients');
  //     return of(result as T);
  //   };
  // }


  // /**
  //  * Handle Http operation that failed.
  //  * Let the app continue.
  //  * @param operation - name of the operation that failed
  //  * @param result - optional value to return as the observable result
  //  */
  // private handleError<T> (operation = 'operation', result?: T) {
  //   return (error: any): Observable<T> => {
  //
  //     // TODO: send the error to remote logging infrastructure
  //     console.error(error); // log to console instead
  //
  //     // TODO: better job of transforming error for user consumption
  //     this.log(`${operation} failed: ${error.message}`);
  //
  //     // Let the app keep running by returning an empty result.
  //     return of(result as T);
  //   };
  // }

}
