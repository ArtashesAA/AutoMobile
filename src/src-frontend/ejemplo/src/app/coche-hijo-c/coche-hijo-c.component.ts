import { Component, Input } from '@angular/core';
import { Coche } from '../coche.model';
import { LoginService } from '../login/login.service';

@Component({
  selector: 'app-coche-hijo-c',
  templateUrl: './coche-hijo-c.component.html',
  styleUrls: ['./coche-hijo-c.component.css'],
})
export class CocheHijoCComponent {
  @Input() cocheDeLista: Coche;
  @Input() indice: number;

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {}

  estaLogueado() {
    return this.loginService.estaLogueado();
  }
}
