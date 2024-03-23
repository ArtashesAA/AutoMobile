import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Coche } from '../coche.model';
import { ServicioCochesService } from '../servicio-coches.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-crear-coche-component',
  templateUrl: './crear-coche-component.component.html',
  styleUrls: ['./crear-coche-component.component.css'],
})
export class CrearCocheComponentComponent implements OnInit {
  miFormulario: FormGroup;

  constructor(
    private router: Router,
    private miServicio: ServicioCochesService,
    private formBuilder: FormBuilder
  ) {
    this.miFormulario = this.formBuilder.group({
      marca: ['', Validators.required],
      modelo: ['', Validators.required],
      anyo: [null, [Validators.required, Validators.min(0)]],
      caballos: [null, [Validators.required, Validators.min(0)]],
    });
  }

  coches: Coche[] = [];

  ngOnInit(): void {
    this.miServicio.obtenerCoches().subscribe((misCoches) => {
      console.log(misCoches);
      this.coches = Object.values(misCoches);

      this.miServicio.setCoches(this.coches);
    });
  }

  volverHome() {
    this.router.navigate(['']);
  }

  agregarCoche() {
    if (this.miFormulario.valid) {
      let miCoche = new Coche(
        this.miFormulario.value.marca,
        this.miFormulario.value.modelo,
        this.miFormulario.value.anyo,
        this.miFormulario.value.caballos
      );

      this.miServicio.agregarCocheServicio(miCoche);

      this.router.navigate(['/catalogo']);
    } else {
      console.log('Formulario no válido');
    }
  }

  validarFormulario(): boolean {
    return (
      this.cuadroMarca.trim() !== '' &&
      this.cuadroModelo.trim() !== '' &&
      this.cuadroAnyo > 0 &&
      this.cuadroCaballos > 0
    );
  }

  cuadroMarca: string = '';
  cuadroModelo: string = '';
  cuadroAnyo: number = 0;
  cuadroCaballos: number = 0;
}
