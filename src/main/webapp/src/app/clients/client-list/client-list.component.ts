import {Component, OnInit} from '@angular/core';
import {Client} from '../shared/client.model';
import {ClientService} from '../shared/client.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css', '../../app.component.css']
})
export class ClientListComponent implements OnInit {
  clients: Client[];
  selectedClient: Client;

  constructor(private clientService: ClientService,
              private router: Router) {
  }

  ngOnInit() {
    this.getClients();
  }

  getClients() {
    this.clientService.getClients()
      .subscribe(clients => {
        this.clients = clients;
      });

    // const client: Client = <Client>{};
    // this.clients.push(client);
    // ///spread operator
    // this.clients = [...this.clients, client];
  }

  onSelect(client: Client) {
    this.selectedClient = client;
  }

  delete(client: Client) {
    this.clientService.delete(client.id)
      .subscribe(_ => {
        console.log('client deleted');
        this.clients = this.clients.filter(clientAux => clientAux.id !== client.id);
      });
  }

  goToDetails(): void {
    this.router.navigate(['/client/detail', this.selectedClient.id]);
  }
}
