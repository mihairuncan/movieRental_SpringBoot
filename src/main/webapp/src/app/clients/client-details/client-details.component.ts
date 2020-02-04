import {Component, OnInit, Input} from '@angular/core';
import {Client} from '../shared/client.model';
import {ClientService} from '../shared/client.service';
import {ActivatedRoute, Params} from '@angular/router';
import {switchMap} from 'rxjs/operators';
import {DatePipe, Location} from '@angular/common';


@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {
  @Input() client: Client;

  constructor(private clientService: ClientService,
              private route: ActivatedRoute,
              private location: Location,
              private datePipe: DatePipe) {

  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.clientService.getClient(+params['id'])))
      .subscribe(client => {
        this.client = client;
        let newDate = this.datePipe.transform(client.dateOfBirth, 'dd.MM.yyyy');
        console.log(newDate);
        // this.client.dateOfBirth= newDate;
        this.client = {...this.client, dateOfBirth: newDate};
      });
  }

  goBack() {
    this.location.back();
  }

  update() {
    this.clientService.update(this.client)
      .subscribe(_ => this.goBack());
  }
}
