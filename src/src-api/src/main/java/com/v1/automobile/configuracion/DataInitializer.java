package com.v1.automobile.configuracion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.entidad.Favorito;
import com.v1.automobile.entidad.Imagen;
import com.v1.automobile.entidad.Noticia;
import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.repositorio.CocheRepositorio;
import com.v1.automobile.repositorio.FavoritoRepositorio;
import com.v1.automobile.repositorio.ImagenRepositorio;
import com.v1.automobile.repositorio.NoticiaRepositorio;
import com.v1.automobile.repositorio.UsuarioRepositorio;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private final CocheRepositorio cocheRepositorio;

	@Autowired
	private final UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Autowired
	private final NoticiaRepositorio noticiaRepositorio;

	@Autowired
	private final ImagenRepositorio imagenRepositorio;

	@Autowired
	private final FavoritoRepositorio favoritoRepositorio;

	public DataInitializer(CocheRepositorio cocheRepositorio, UsuarioRepositorio usuarioRepositorio,
			PasswordEncoder passwordEncoder, NoticiaRepositorio noticiaRepositorio, ImagenRepositorio imagenRepositorio,
			FavoritoRepositorio favoritoRepositorio) {
		this.cocheRepositorio = cocheRepositorio;
		this.usuarioRepositorio = usuarioRepositorio;
		this.passwordEncoder = passwordEncoder;
		this.noticiaRepositorio = noticiaRepositorio;
		this.imagenRepositorio = imagenRepositorio;
		this.favoritoRepositorio = favoritoRepositorio;
	}

	@Override
	public void run(String... args) throws Exception {

		try {
			cocheRepositorio.deleteAll();
			usuarioRepositorio.deleteAll();
			noticiaRepositorio.deleteAll();
			imagenRepositorio.deleteAll();
			favoritoRepositorio.deleteAll();

			// Usuarios
			List<Usuario> usuarios = List.of(
					new Usuario(1, "admin", "admin@gmail.com", "", passwordEncoder.encode("admin"), "ADMIN"),
					new Usuario(2, "john_doe", "john.doe@gmail.com", "", passwordEncoder.encode("contra"), "USER"),
					new Usuario(3, "emma_smith", "emma.smith@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(4, "michael_williams", "michael.williams@gmail.com", "",
							passwordEncoder.encode("contra"), "USER"),
					new Usuario(5, "sarah_jones", "sarah.jones@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(6, "alice_walker", "alice.walker@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(7, "david_martin", "david.martin@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(8, "olivia_smith", "olivia.smith@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(9, "mason_johnson", "mason.johnson@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(10, "lily_garcia", "lily.garcia@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(11, "james_anderson", "james.anderson@gmail.com", "",
							passwordEncoder.encode("contra"), "USER"),
					new Usuario(12, "oliver_rodriguez", "oliver.rodriguez@gmail.com", "",
							passwordEncoder.encode("contra"), "USER"),
					new Usuario(13, "ava_martinez", "ava.martinez@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(14, "logan_jackson", "logan.jackson@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(15, "charlotte_hernandez", "charlotte.hernandez@gmail.com", "",
							passwordEncoder.encode("contra"), "USER"),
					new Usuario(16, "william_garcia", "william.garcia@gmail.com", "",
							passwordEncoder.encode("contra"), "USER"),
					new Usuario(17, "harper_lopez", "harper.lopez@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(18, "evelyn_yang", "evelyn.yang@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(19, "mason_lee", "mason.lee@gmail.com", "", passwordEncoder.encode("contra"), "USER"),
					new Usuario(20, "amelia_walker", "amelia.walker@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(21, "oliver_thompson", "oliver.thompson@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(22, "mia_hall", "mia.hall@gmail.com", "", passwordEncoder.encode("contra"), "USER"),
					new Usuario(23, "elijah_carter", "elijah.carter@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(24, "olivia_adams", "olivia.adams@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(25, "noah_rivera", "noah.rivera@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(26, "ava_torres", "ava.torres@gmail.com", "", passwordEncoder.encode("contra"),
							"USER"),
					new Usuario(27, "willow_ramirez", "willow.ramirez@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(28, "ethan_james", "ethan.james@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(29, "emily_cruz", "emily.cruz@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(30, "daniel_gonzalez", "daniel.gonzalez@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(31, "madison_carter", "madison.carter@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(32, "liam_lopez", "liam.lopez@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(33, "ava_green", "ava.green@gmail.com", "", passwordEncoder.encode("password"), "USER"),
					new Usuario(34, "henry_walker", "henry.walker@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(35, "amelia_adams", "amelia.adams@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(36, "oliver_harris", "oliver.harris@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(37, "emma_smith2", "emma.smith2@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(38, "alexander_rodriguez", "alexander.rodriguez@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(39, "mia_garcia", "mia.garcia@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(40, "james_martin", "james.martin@gmail.com", "", passwordEncoder.encode("password"),
							"USER"));
			usuarioRepositorio.saveAll(usuarios);

			// Coches
			List<Coche> coches = List.of(
					new Coche(1L, "Toyota", "Corolla", "https://cdn.motor1.com/images/mgl/P3gQyK/s1/corolla-grs-1.jpg",
							20000, 2018, 120, 50000, "Gasolina", "7 l/100 km", "Manual", "Compacto", "Sedan",
							"Delantera", 5, 4, "1 Año", 1200, "Blanco", 6, 4, "Madrid",
							"Coche compacto muy popular con bajo consumo de combustible.", 324234432, "admin@gmail.com",
							usuarios.get(1), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(2L, "Ford", "F-150",
							"https://www.cnet.com/a/img/resize/565adfebca21852aec95e86c233bf09173ddf60a/hub/2019/08/11/6f859b6b-6442-42a8-883e-8641c437f54d/2019-ford-f-150-ogi2.jpg?auto=webp&fit=crop&height=675&width=1200",
							35000, 2019, 300, 30000, "Gasolina", "10 l/100 km", "Automatico", "Furgoneta", "Pickup",
							"Trasera", 5, 4, "2 Años", 2500, "Gris", 8, 6, "Madrid",
							"Furgoneta robusta y potente, ideal para trabajos pesados.", 231432432,
							"john_doe@gmail.com", usuarios.get(2), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(3L, "Honda", "Civic", "https://photos.prnewswire.com/prnfull/20160929/413671", 18000,
							2017, 140, 40000, "Gasolina", "6 l/100 km", "Manual", "Compacto", "Compacto", "Delantera",
							5, 4, "1 Año", 1100, "Gris", 6, 4, "Madrid",
							"Coche compacto con diseño deportivo y excelente economía de combustible.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(3), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(4L, "BMW", "X5",
							"https://a.ccdn.es/cnet/vehicles/14323874/6b04d7d5-b340-4a84-8e76-5dbc37d7ab59.jpg/400x300cut/",
							50000, 2020, 250, 20000, "Diesel", "8 l/100 km", "Automatico", "SUV", "SUV", "Total", 5, 4,
							"3 Años", 2200, "Blanco", 8, 6, "Sevilla", "SUV de lujo con alto rendimiento y comodidad.",
							324234432, "ejemplo1@gmail.com", usuarios.get(4), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()),
					new Coche(5L, "Volkswagen", "Golf",
							"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6p-Z7rvpCHxZwdsKYDBZWkG4oWw1GYVaZ_Q&usqp=CAU",
							22000, 2019, 150, 35000, "Gasolina", "7 l/100 km", "Automatico", "Compacto", "Compacto",
							"Delantera", 5, 4, "2 Años", 1200, "Blanco", 7, 4, "Sevilla",
							"Coche compacto versátil y confiable.", 324234432, "ejemplo1@gmail.com", usuarios.get(5),
							new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(6L, "Mercedes-Benz", "E-Class",
							"https://assets.newatlas.com/dims4/default/6f51b09/2147483647/strip/true/crop/4032x2272+0+0/resize/2880x1623!/quality/90/?url=http%3A%2F%2Fnewatlas-brightspot.s3.amazonaws.com%2Fa2%2Fda%2F65700dd24d619365b4270fa796ce%2F2021-mercedes-benz-e450-1.jpg",
							40000, 2021, 200, 15000, "Gasolina", "8 l/100 km", "Automatico", "Sedan", "Sedan",
							"Trasera", 5, 4, "2 Años", 1800, "Rojo", 9, 6, "Madrid",
							"Sedan de lujo con un rendimiento excepcional y tecnología avanzada.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(6), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(7L, "Audi", "A4",
							"https://www.lovecars.es/comprar-vehiculo-seminuevo/media/audi-a4-30-tdi-s-tronic-advanced-136-1024-zEQjmP2X8N8AdpYMlnRr.jpg",
							30000, 2020, 180, 25000, "Diesel", "6 l/100 km", "Automatico", "Sedan", "Sedan",
							"Delantera", 5, 4, "2 Años", 1600, "Negro", 7, 4, "Madrid",
							"Sedan elegante con tecnología de vanguardia y excelente eficiencia de combustible.",
							324234432, "ejemplo1@gmail.com", usuarios.get(7), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()),
					new Coche(8L, "Hyundai", "Elantra",
							"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDxQSp6Q_uvFqf7-qxylVDMM98a4a9YBRCzg&usqp=CAU",
							21000, 2019, 140, 30000, "Gasolina", "6 l/100 km", "Automatico", "Compacto", "Sedan",
							"Delantera", 5, 4, "2 Años", 1300, "Plateado", 7, 4, "Madrid",
							"Sedan compacto con estilo moderno y rendimiento confiable.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(8), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(9L, "Toyota", "Rav4",
							"https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Toyota_Rav4_2020_Advance_Plus_4x4.jpg/1200px-Toyota_Rav4_2020_Advance_Plus_4x4.jpg",
							32000, 2020, 180, 18000, "Electro/Gasolina", "5 l/100 km", "Automatico", "SUV", "SUV",
							"Total", 5, 4, "3 Años", 1500, "Negro", 7, 4, "Madrid",
							"SUV espacioso y eficiente en combustible con tecnología híbrida avanzada.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(9), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(10L, "Nissan", "Sentra",
							"https://i.pinimg.com/originals/2a/64/a9/2a64a98b984a56e307df520e173ebc30.jpg", 23000, 2019,
							140, 25000, "Gasolina", "6 l/100 km", "Automatico", "Compacto", "Sedan", "Delantera", 5, 4,
							"Sin Garantia", 1250, "Blanco", 7, 4, "Madrid",
							"Sedan compacto con características avanzadas y excelente economía de combustible.",
							324234432, "ejemplo1@gmail.com", usuarios.get(10), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()),
					new Coche(11L, "Ford", "Focus",
							"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKdD5aP5pcS_hcMXl2zaM5aeryJQK6Cv82US7g2v920w&s",
							25000, 2020, 160, 20000, "Gasolina", "6 l/100 km", "Automatico", "Compacto", "Compacto",
							"Delantera", 5, 4, "2 Años", 1200, "Rojo", 6, 4, "Madrid",
							"Coche compacto ágil y divertido con tecnología innovadora.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(11), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(12L, "Chevrolet", "Malibu",
							"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmORxqjQBbuK5NCtoJEFJ36C7fpugwrXKc8OK5QqJXBw&s",
							28000, 2021, 160, 15000, "Gasolina", "7 l/100 km", "Automatico", "Sedan", "Sedan",
							"Delantera", 5, 4, "2 Años", 1450, "Azul", 8, 6, "Madrid",
							"Sedan elegante y eficiente con tecnología avanzada.", 324234432, "ejemplo1@gmail.com",
							usuarios.get(12), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(13L, "Kia", "Sportage",
							"https://upload.wikimedia.org/wikipedia/commons/2/21/Kia_Sportage_DCT_2.0_2018_%28V%29_2.jpg",
							30000, 2021, 180, 15000, "Electro/Gasolina", "5 l/100 km", "Automatico", "SUV", "SUV",
							"Total", 5, 4, "3 Años", 1600, "Rojo", 8, 6, "Madrid",
							"SUV híbrido con excelente economía de combustible y características de seguridad avanzadas.",
							324234432, "ejemplo1@gmail.com", usuarios.get(13), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()),
					new Coche(14L, "Mazda", "CX-5",
							"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRxKHqzF5b7M5Q3D5PqaCnR7fD0O1m7OmZ5jTzBIxNR&s",
							35000, 2022, 200, 10000, "Gasolina", "7 l/100 km", "Automatico", "SUV", "SUV", "Total", 5,
							4, "3 Años", 1700, "Blanco", 8, 6, "Madrid",
							"SUV elegante con excelente rendimiento y tecnología de punta.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(14), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(15L, "Tesla", "Model 3",
							"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwrU4mcW7V2ErU4YOhVtvF1SLe0PbKwL-C9kIoEMq6tA&s",
							45000, 2021, 250, 15000, "Electrico", "0 l/100 km", "Automatico", "Compacto", "Sedan",
							"Total", 5, 4, "3 Años", 1600, "Negro", 10, 6, "Madrid",
							"Sedan eléctrico con tecnología avanzada y cero emisiones.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(15), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()));

			cocheRepositorio.saveAll(coches);

			// Supongamos que tienes los IDs de los coches a los que quieres agregar
			// imágenes
			Long cocheId1 = 1L;
			Long cocheId2 = 3L;

			// Recuperar los coches de la base de datos usando el repositorio de coches
			Optional<Coche> cocheOptional1 = cocheRepositorio.findById(cocheId1);
			Optional<Coche> cocheOptional2 = cocheRepositorio.findById(cocheId2);

			if (cocheOptional1.isPresent() && cocheOptional2.isPresent()) {
				Coche coche1 = cocheOptional1.get();
				Coche coche2 = cocheOptional2.get();

				// Crear las imágenes y asociarlas a los coches correspondientes
				Imagen imagenCoche1 = new Imagen(1L,
						"https://prod.pictures.autoscout24.net/listing-images/8ff579a9-b17f-4a74-a370-22acbf3fe24b_3fbf8620-b687-4d06-ba00-8c0210a7ad9d.jpg/720x540.webp",
						coche1);
				Imagen imagenCoche2 = new Imagen(2L,
						"https://imagenes.km77.com/fotos/bbtcontent/clippingnew/KM7KPH20180618_0016/full-original.jpg",
						coche2);

				// Guardar las imágenes en la base de datos
				imagenRepositorio.save(imagenCoche1);
				imagenRepositorio.save(imagenCoche2);
			} else {
				System.out.println("No se encontró algún coche con alguno de los IDs proporcionados.");
			}

			List<Noticia> noticias = List.of(new Noticia(1L, null, "Presentación del nuevo modelo de la marca BMW",
					"La marca BMW ha presentado su último modelo de coche en un evento exclusivo para prensa y aficionados.",
					"https://images.prismic.io/carwow/9acac9a7-30cc-4bbf-bc1f-490a179ead62_LHD+BMW+i5+2024+Exterior-10.jpg",
					"https://www.youtube.com/watch?v=2DgMcDu3Hco", usuarios.get(1)),
					new Noticia(2L, null, "Top 10 de coches más vendidos del año",
							"Un análisis revela los 10 coches más vendidos del año hasta la fecha, con sorpresas en el ranking.",
							"https://cdn.autobild.es/sites/navi.axelspringer.es/public/bdc/dc/fotos/hl-34fr-hv-20-2.jpg?tf=3840x",
							null, usuarios.get(1)),
					new Noticia(3L, null, "Consejos para mantener tu coche en buen estado",
							"Expertos en mecánica automotriz comparten consejos útiles para mantener tu coche en óptimas condiciones.",
							"https://cdn.topgear.es/sites/navi.axelspringer.es/public/media/image/2022/10/imagen-motor-diesel-2848179.jpg?tf=3840x",
							null, usuarios.get(1)),
					new Noticia(4L, null, "Prueba de manejo del nuevo coche eléctrico",
							"Un equipo de periodistas ha tenido la oportunidad de probar en exclusiva el nuevo coche eléctrico de una marca líder en el mercado.",
							"https://cdn.autobild.es/sites/navi.axelspringer.es/public/media/image/2022/03/ioniq-5-2642493.jpg?tf=3840x",
							"https://www.youtube.com/watch?v=video2", usuarios.get(1)),
					new Noticia(5L, null, "Tendencias en diseño de coches para el próximo año",
							"Diseñadores de coches revelan las tendencias más importantes en el diseño de vehículos para el próximo año.",
							"https://img.remediosdigitales.com/f6cc36/mercedes-benz-vision-avtr-concept-03/1366_2000.jpg",
							null, usuarios.get(1))

			);

			noticiaRepositorio.saveAll(noticias);

			int cocheIdd1 = 1;
			int cocheIdd2 = 2;
			int cocheIdd3 = 3;
			int cocheIdd4 = 4;
			int cocheIdd5 = 5;
			int cocheIdd6 = 6;
			int usuarioId1 = 1;
			int usuarioId3 = 3;

			// Crear la lista de favoritos
			List<Favorito> favoritos = List.of(new Favorito(1L, usuarios.get(usuarioId1), coches.get(cocheIdd1), null),
					new Favorito(2L, usuarios.get(usuarioId1), coches.get(cocheIdd2), null),
					new Favorito(3L, usuarios.get(usuarioId3), coches.get(cocheIdd2), null),
					new Favorito(4L, usuarios.get(usuarioId1), coches.get(cocheIdd3), null),
					new Favorito(5L, usuarios.get(usuarioId1), coches.get(cocheIdd4), null),
					new Favorito(6L, usuarios.get(usuarioId1), coches.get(cocheIdd5), null),
					new Favorito(7L, usuarios.get(usuarioId3), coches.get(cocheIdd6), null));

			favoritoRepositorio.saveAll(favoritos);

			System.out.println("Base de datos de coches inicializada con más registros.");
		} catch (Exception e) {
			// Manejo de excepciones
			System.err.println("Error durante la inicialización de datos: " + e.getMessage());
			e.printStackTrace();
		}
	}
}