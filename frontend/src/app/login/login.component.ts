import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  imports: [ MatInputModule, MatFormFieldModule, FormsModule ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent
{
  nomusu:string   = '';
  claveusu:string = '';

  constructor(private httpClient: HttpClient){}

  doLogin()
  {
    //alert(this.nomusu + '/' + this.claveusu);

    this.httpClient.get('http://localhost:8080/login').subscribe((res: any) => {
      alert(res);
    });

  }
}
