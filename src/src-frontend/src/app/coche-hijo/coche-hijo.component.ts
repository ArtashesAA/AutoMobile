import { Component, Input } from '@angular/core';
import { Coche } from '../coche.model';
import { LoginService } from '../login/login.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-coche-hijo',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './coche-hijo.component.html',
  styleUrl: './coche-hijo.component.css',
})
export class CocheHijoComponent {
  @Input() cocheDeLista!: Coche;
  @Input() indice!: number;

  constructor(private loginService: LoginService) {}

  ngOnInit(): void {}

  estaLogueado() {
    return this.loginService.estaLogueado();
  }
}
