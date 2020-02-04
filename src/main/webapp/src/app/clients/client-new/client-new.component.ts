import {Component, OnInit} from '@angular/core';
import {ClientService} from '../shared/client.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-client-new',
  templateUrl: './client-new.component.html',
  styleUrls: ['./client-new.component.css']
})
export class ClientNewComponent implements OnInit {

  constructor(private clientService: ClientService,
              private location: Location) {
  }

  ngOnInit() {
  }

  save(firstName, lastName, dateOfBirth, cnp) {
    console.log('save works: ', firstName, ';', lastName, ';', dateOfBirth, ';', cnp, ';');

    this.clientService.save(
      {
        firstName,
        lastName,
        dateOfBirth,
        cnp
      })
      .subscribe(client => this.location.back(),
        err => console.log('Error in saving client', err),
        () => console.log('save client complete'));
  }

  goBack() {
    console.log('client-new component go BACK');
    this.location.back();
  }
}
