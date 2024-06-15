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
			favoritoRepositorio.deleteAll();
			imagenRepositorio.deleteAll();
			noticiaRepositorio.deleteAll();
			cocheRepositorio.deleteAll();
			usuarioRepositorio.deleteAll();

			// Usuarios
			List<Usuario> usuarios = List.of(
					new Usuario(1, "admin", "admin@gmail.com", "", passwordEncoder.encode("admin"), "ADMIN"),
					new Usuario(2, "john_doe", "john.doe@gmail.com", "", passwordEncoder.encode("password"), "ADMIN"),
					new Usuario(3, "emma_smith", "emma.smith@gmail.com", "", passwordEncoder.encode("password"),
							"ADMIN"),
					new Usuario(4, "michael_williams", "michael.williams@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(5, "MDS Motors", "mdsmotors@gmail.com", "", passwordEncoder.encode("password"), "USER"),
					new Usuario(6, "Burgo Car", "burgocar@gmail.com", "", passwordEncoder.encode("password"), "USER"),
					new Usuario(7, "DMA Automocion", "dmautomocion@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(8, "V8 Motors", "v8motors@gmail.com", "", passwordEncoder.encode("password"), "USER"),
					new Usuario(9, "Autos Madrid", "autosmadrid@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(10, "lily_garcia", "lily.garcia@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(11, "james_anderson", "james.anderson@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(12, "oliver_rodriguez", "oliver.rodriguez@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(13, "ava_martinez", "ava.martinez@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(14, "logan_jackson", "logan.jackson@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(15, "charlotte_hernandez", "charlotte.hernandez@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(16, "william_garcia", "william.garcia@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(17, "harper_lopez", "harper.lopez@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(18, "evelyn_yang", "evelyn.yang@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(19, "mason_lee", "mason.lee@gmail.com", "", passwordEncoder.encode("password"), "USER"),
					new Usuario(20, "amelia_walker", "amelia.walker@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(21, "oliver_thompson", "oliver.thompson@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(22, "mia_hall", "mia.hall@gmail.com", "", passwordEncoder.encode("password"), "USER"),
					new Usuario(23, "elijah_carter", "elijah.carter@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(24, "olivia_adams", "olivia.adams@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(25, "noah_rivera", "noah.rivera@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(26, "ava_torres", "ava.torres@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(27, "willow_ramirez", "willow.ramirez@gmail.com", "",
							passwordEncoder.encode("password"), "USER"),
					new Usuario(28, "ethan_james", "ethan.james@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(29, "emily_cruz", "emily.cruz@gmail.com", "", passwordEncoder.encode("password"),
							"USER"),
					new Usuario(30, "daniel_gonzalez", "daniel.gonzalez@gmail.com", "",
							passwordEncoder.encode("password"), "USER"));

			usuarioRepositorio.saveAll(usuarios);

			// Coches
			List<Coche> coches = List.of(new Coche(1L, "BMW", "330",
					"https://prod.pictures.autoscout24.net/listing-images/c142f22a-7c7c-4354-acee-9ff69da396f3_aab21175-1b08-4928-add9-85722f5a9399.jpg/1920x1080.webp",
					42000, 2020, 258, 75000, "Gasolina", "8 l/100 km", "Automatico", "Sedan", "Sedan", "Trasera", 5, 5,
					"1 Año", 1505, "Naranja", 8, 4, "Madrid", "\r\n" + "BMW SERIE 330i 258CV M SPORT AUT.", 919379134,
					"mdsmotors@gmail.com", usuarios.get(4), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(2L, "Toyota", "Corolla",
							"https://prod.pictures.autoscout24.net/listing-images/6307983c-ec56-44fb-8117-5a4ff78ace32_267e74de-b27d-4ddd-ba54-429e6229b3d2.jpg/720x540.webp",
							18490, 2020, 122, 32737, "Electro/Gasolina", "4 l/100 km", "Automatico", "Sedan", "Sedan",
							"Delantera", 5, 5, "1 Año", 1450, "Gris", 6, 4, "Leganes",
							"Coche compacto muy popular con bajo consumo de combustible.", 919379178,
							"autosmadrid@gmail.com", usuarios.get(8), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()),
					new Coche(3L, "BMW", "X5",
							"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_7a6707fe-4b54-48f7-93f0-0675ae42fb51.jpg/720x540.webp",
							35901, 2018, 265, 152500, "Diesel", "7 l/100 km", "Automatico", "SUV", "SUV", "Total", 5, 5,
							"2 Años", 2110, "Negro", 8, 6, "Burgos", "SUV de lujo con alto rendimiento y comodidad.",
							947851121, "burgocar@gmail.com", usuarios.get(5), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()),

					new Coche(4L, "Mercedes-Benz", "C 43 AMG",
							"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_c84719b0-9aea-4b3b-9098-1a303b81b383.jpg/720x540.webp",
							43900, 2017, 367, 56999, "Gasolina", "10 l/100 km", "Automatico", "Sedan", "Sedan", "Total",
							5, 5, "1 Año", 1705, "Blanco", 9, 6, "Barcelona", "Deportivo MERCEDES-BENZ C43 AMG",
							936163633, "dmautomocion@gmail.com", usuarios.get(0), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()),

					new Coche(5L, "Volkswagen", "Golf",
							"https://prod.pictures.autoscout24.net/listing-images/37549ba1-c7d8-4517-95da-e25dbe6f7510_614aa216-7201-4410-bf55-b73c129e186e.jpg/720x540.webp",
							33990, 2022, 245, 36500, "Gasolina", "8 l/100 km", "Automatico", "Compacto", "Compacto",
							"Delantera", 5, 5, "2 Años", 1460, "Gris", 7, 4, "Valencia",
							"Coche compacto versátil y confiable.", 864871318, "v8motors@gmail.com", usuarios.get(7),
							new ArrayList<Imagen>(), new ArrayList<Favorito>()),

					new Coche(6L, "Mercedes-Benz", "E 250",
							"https://assets.newatlas.com/dims4/default/6f51b09/2147483647/strip/true/crop/4032x2272+0+0/resize/2880x1623!/quality/90/?url=http%3A%2F%2Fnewatlas-brightspot.s3.amazonaws.com%2Fa2%2Fda%2F65700dd24d619365b4270fa796ce%2F2021-mercedes-benz-e450-1.jpg",
							40000, 2021, 200, 15000, "Gasolina", "8 l/100 km", "Automatico", "Sedan", "Sedan",
							"Trasera", 5, 4, "2 Años", 1800, "Rojo", 9, 6, "Madrid",
							"Sedan de lujo con un rendimiento excepcional y tecnología avanzada.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(9), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(7L, "Audi", "A4",
							"https://www.lovecars.es/comprar-vehiculo-seminuevo/media/audi-a4-30-tdi-s-tronic-advanced-136-1024-zEQjmP2X8N8AdpYMlnRr.jpg",
							30000, 2020, 180, 25000, "Diesel", "6 l/100 km", "Automatico", "Sedan", "Sedan",
							"Delantera", 5, 4, "2 Años", 1600, "Negro", 7, 4, "Madrid",
							"Sedan elegante con tecnología de vanguardia y excelente eficiencia de combustible.",
							324234432, "ejemplo1@gmail.com", usuarios.get(9), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()),
					new Coche(8L, "Hyundai", "Elantra",
							"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDxQSp6Q_uvFqf7-qxylVDMM98a4a9YBRCzg&usqp=CAU",
							21000, 2019, 140, 30000, "Gasolina", "6 l/100 km", "Automatico", "Compacto", "Sedan",
							"Delantera", 5, 4, "2 Años", 1300, "Plateado", 7, 4, "Madrid",
							"Sedan compacto con estilo moderno y rendimiento confiable.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(9), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(9L, "Toyota", "Rav4",
							"https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Toyota_Rav4_2020_Advance_Plus_4x4.jpg/1200px-Toyota_Rav4_2020_Advance_Plus_4x4.jpg",
							32000, 2020, 180, 18000, "Electro/Gasolina", "5 l/100 km", "Automatico", "SUV", "SUV",
							"Total", 5, 4, "3 Años", 1500, "Negro", 7, 4, "Madrid",
							"SUV espacioso y eficiente en combustible con tecnología híbrida avanzada.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(10), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(10L, "Nissan", "Sentra",
							"https://i.pinimg.com/originals/2a/64/a9/2a64a98b984a56e307df520e173ebc30.jpg", 23000, 2019,
							140, 25000, "Gasolina", "6 l/100 km", "Automatico", "Compacto", "Sedan", "Delantera", 5, 4,
							"Sin Garantia", 1250, "Blanco", 7, 4, "Madrid",
							"Sedan compacto con características avanzadas y excelente economía de combustible.",
							324234432, "ejemplo1@gmail.com", usuarios.get(10), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()),
					new Coche(11L, "Ford", "Focus",
							"https://prod.pictures.autoscout24.net/listing-images/26f98e37-18cb-4302-9918-542edab50c3b_62b7802c-6f80-412d-80a9-acea493f10d0.jpg/720x540.webp",
							26900, 2020, 280, 36000, "Gasolina", "8 l/100 km", "Automatico", "Compacto", "Compacto",
							"Delantera", 5, 4, "2 Años", 1200, "Blanco", 6, 4, "Murcia",
							"Coche compacto ágil y divertido con tecnología innovadora.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(11), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(12L, "Kia", "Sportage",
							"https://prod.pictures.autoscout24.net/listing-images/f5e665ce-02e6-4911-837d-b7439b4159f6_61ffaa55-a544-4d91-aded-6db39c115e40.jpg/720x540.webp",
							30000, 2021, 180, 15000, "Electro/Gasolina", "5 l/100 km", "Automatico", "SUV", "SUV",
							"Total", 5, 4, "3 Años", 1600, "Blanco", 8, 6, "Madrid",
							"SUV híbrido con excelente economía de combustible y características de seguridad avanzadas.",
							324234432, "ejemplo1@gmail.com", usuarios.get(13), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()),
					new Coche(13L, "Mazda", "CX-5",
							"https://prod.pictures.autoscout24.net/listing-images/af49ce57-4a19-4528-9d17-04d799c6e37f_dac9d02b-494b-4037-89c6-8b70dc430448.jpg/720x540.webp",
							35000, 2022, 200, 10000, "Gasolina", "7 l/100 km", "Automatico", "SUV", "SUV", "Total", 5,
							4, "3 Años", 1700, "Blanco", 8, 6, "Madrid",
							"SUV elegante con excelente rendimiento y tecnología de punta.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(14), new ArrayList<Imagen>(), new ArrayList<Favorito>()),
					new Coche(14L, "Tesla", "Model 3",
							"https://prod.pictures.autoscout24.net/listing-images/d4b49743-54f3-42dd-bb3d-b421e86bbc8c_84699dc6-d2e6-4f14-99e3-ffa79789db31.jpg/720x540.webp",
							45000, 2021, 250, 15000, "Electrico", "0 l/100 km", "Automatico", "Compacto", "Sedan",
							"Total", 5, 4, "3 Años", 1600, "Negro", 10, 6, "Madrid",
							"Sedan eléctrico con tecnología avanzada y cero emisiones.", 324234432,
							"ejemplo1@gmail.com", usuarios.get(15), new ArrayList<Imagen>(),
							new ArrayList<Favorito>()));

			cocheRepositorio.saveAll(coches);

			// imágenes
			Long cocheId1 = 1L;
			Long cocheId2 = 2L;
			Long cocheId3 = 3L;
			Long cocheId4 = 4L;
			Long cocheId5 = 5L;

			// Recuperar los coches de la base de datos usando el repositorio de coches
			Optional<Coche> cocheOptional1 = cocheRepositorio.findById(cocheId1);
			Optional<Coche> cocheOptional2 = cocheRepositorio.findById(cocheId2);
			Optional<Coche> cocheOptional3 = cocheRepositorio.findById(cocheId3);
			Optional<Coche> cocheOptional4 = cocheRepositorio.findById(cocheId4);
			Optional<Coche> cocheOptional5 = cocheRepositorio.findById(cocheId5);

			if (cocheOptional1.isPresent() && cocheOptional2.isPresent() && cocheOptional3.isPresent()
					&& cocheOptional4.isPresent() && cocheOptional5.isPresent()) {
				Coche coche1 = cocheOptional1.get();
				Coche coche2 = cocheOptional2.get();
				Coche coche3 = cocheOptional3.get();
				Coche coche4 = cocheOptional4.get();
				Coche coche5 = cocheOptional5.get();

				// Crear las imágenes y asociarlas a los coches correspondientes
				Imagen imagenCoche1 = new Imagen(1L,
						"https://prod.pictures.autoscout24.net/listing-images/c142f22a-7c7c-4354-acee-9ff69da396f3_b3a90a65-cecb-440e-9b1d-cdb7b6661025.jpg/720x540.webp",
						coche1);
				Imagen imagenCoche2 = new Imagen(2L,
						"https://prod.pictures.autoscout24.net/listing-images/c142f22a-7c7c-4354-acee-9ff69da396f3_34c5f7b9-6265-4a31-8d53-3a200a883b10.jpg/720x540.webp",
						coche1);
				Imagen imagenCoche3 = new Imagen(3L,
						"https://prod.pictures.autoscout24.net/listing-images/c142f22a-7c7c-4354-acee-9ff69da396f3_70619894-6f05-455d-9a4d-1f99d114647f.jpg/720x540.webp",
						coche1);
				Imagen imagenCoche4 = new Imagen(4L,
						"https://prod.pictures.autoscout24.net/listing-images/c142f22a-7c7c-4354-acee-9ff69da396f3_8239edd9-177b-46d3-9b28-6a62e2fbab4e.jpg/720x540.webp",
						coche1);
				Imagen imagenCoche5 = new Imagen(5L,
						"https://prod.pictures.autoscout24.net/listing-images/c142f22a-7c7c-4354-acee-9ff69da396f3_39703069-a958-47c6-91e7-4235162497dd.jpg/720x540.webp",
						coche1);
				Imagen imagenCoche6 = new Imagen(6L,
						"https://prod.pictures.autoscout24.net/listing-images/c142f22a-7c7c-4354-acee-9ff69da396f3_74586b66-1719-4b02-861d-f448f21efc91.jpg/720x540.webp",
						coche1);
				Imagen imagenCoche7 = new Imagen(6L,
						"https://prod.pictures.autoscout24.net/listing-images/c142f22a-7c7c-4354-acee-9ff69da396f3_2f5e6d82-3285-4d1e-a08c-7c8ad1d7b9a8.jpg/720x540.webp",
						coche1);

				Imagen imagenCoche8 = new Imagen(7L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_7a6707fe-4b54-48f7-93f0-0675ae42fb51.jpg/720x540.webp",
						coche3);
				Imagen imagenCoche9 = new Imagen(8L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_c87f894a-1983-43d8-a464-5dd18cf8f609.jpg/720x540.webp",
						coche3);
				Imagen imagenCoche10 = new Imagen(9L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_8597f699-e3f9-4374-ad02-e63b8023bdff.jpg/720x540.webp",
						coche3);
				Imagen imagenCoche11 = new Imagen(10L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_b1763d85-0f79-486c-885e-306de2a358b9.jpg/720x540.webp",
						coche3);
				Imagen imagenCoche12 = new Imagen(11L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_9c51ce5d-cd8e-40dd-82c3-7d83b468e4c5.jpg/720x540.webp",
						coche3);
				Imagen imagenCoche13 = new Imagen(12L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_d84527a9-6e1e-44df-94a6-760044bf66af.jpg/720x540.webp",
						coche3);
				Imagen imagenCoche14 = new Imagen(13L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_d6be6f34-ee87-4fb9-b7b1-ca665d324a5b.jpg/720x540.webp",
						coche3);

				Imagen imagenCoche15 = new Imagen(14L,
						"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_c84719b0-9aea-4b3b-9098-1a303b81b383.jpg/1920x1080.webp",
						coche4);
				Imagen imagenCoche16 = new Imagen(15L,
						"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_d7da9843-b76d-4e82-b97e-68ab676b0825.jpg/720x540.webp",
						coche4);
				Imagen imagenCoche17 = new Imagen(16L,
						"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_755bc9c1-c912-4dd1-8ae0-c82e6bd5a16c.jpg/720x540.webp",
						coche4);
				Imagen imagenCoche18 = new Imagen(17L,
						"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_bd438958-9e89-4f1b-93c7-877da6472369.jpg/720x540.webp",
						coche4);
				Imagen imagenCoche19 = new Imagen(18L,
						"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_dc67ad55-fc07-4d6d-bdce-d2c5f7757966.jpg/720x540.webp",
						coche4);
				Imagen imagenCoche20 = new Imagen(19L,
						"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_4cbcb19d-b7b1-4f28-ba30-1f6f3bf85e26.jpg/720x540.webp",
						coche4);
				Imagen imagenCoche21 = new Imagen(20L,
						"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_53aa853f-decc-4469-ab6c-220b95e00b25.jpg/720x540.webp",
						coche4);
				Imagen imagenCoche22 = new Imagen(21L,
						"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_de82acf2-9b0a-4e80-9ec0-83904d3991c6.jpg/720x540.webp",
						coche4);
				Imagen imagenCoche23 = new Imagen(22L,
						"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_000bd132-f2e8-42ff-bb09-8851185d1fbc.jpg/720x540.webp",
						coche4);
				Imagen imagenCoche24 = new Imagen(23L,
						"https://prod.pictures.autoscout24.net/listing-images/5bc415f1-9bd0-4ea5-8673-73882b1d07dd_27c502b3-7906-4b87-929d-0ba000509d09.jpg/720x540.webp",
						coche4);

				Imagen imagenCoche25 = new Imagen(24L,
						"https://prod.pictures.autoscout24.net/listing-images/37549ba1-c7d8-4517-95da-e25dbe6f7510_614aa216-7201-4410-bf55-b73c129e186e.jpg/720x540.webp",
						coche5);
				Imagen imagenCoche26 = new Imagen(25L,
						"https://prod.pictures.autoscout24.net/listing-images/37549ba1-c7d8-4517-95da-e25dbe6f7510_86ef198c-d2ba-4a20-a494-80be8b655b14.jpg/720x540.webp",
						coche5);
				Imagen imagenCoche27 = new Imagen(26L,
						"https://prod.pictures.autoscout24.net/listing-images/37549ba1-c7d8-4517-95da-e25dbe6f7510_d0430fe5-d9fe-4ef9-b855-8712211212ac.jpg/720x540.webp",
						coche5);
				Imagen imagenCoche28 = new Imagen(27L,
						"https://prod.pictures.autoscout24.net/listing-images/37549ba1-c7d8-4517-95da-e25dbe6f7510_30095f19-d87f-40c1-8e23-8f8d58e07a55.jpg/720x540.webp",
						coche5);
				Imagen imagenCoche29 = new Imagen(28L,
						"https://prod.pictures.autoscout24.net/listing-images/37549ba1-c7d8-4517-95da-e25dbe6f7510_92fb1582-3a1f-4b8b-968b-27aa06cf3d9b.jpg/720x540.webp",
						coche5);
				Imagen imagenCoche30 = new Imagen(29L,
						"https://prod.pictures.autoscout24.net/listing-images/37549ba1-c7d8-4517-95da-e25dbe6f7510_c4328d70-a432-4ec7-8767-835da3cb3d56.jpg/720x540.webp",
						coche5);

				Imagen imagenCoche31 = new Imagen(9L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_8597f699-e3f9-4374-ad02-e63b8023bdff.jpg/720x540.webp",
						coche3);
				Imagen imagenCoche32 = new Imagen(11L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_9c51ce5d-cd8e-40dd-82c3-7d83b468e4c5.jpg/720x540.webp",
						coche3);
				Imagen imagenCoche33 = new Imagen(12L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_d84527a9-6e1e-44df-94a6-760044bf66af.jpg/720x540.webp",
						coche3);
				Imagen imagenCoche34 = new Imagen(13L,
						"https://prod.pictures.autoscout24.net/listing-images/1c286ddc-844e-4309-86b8-7f9f7a9d7f1a_d6be6f34-ee87-4fb9-b7b1-ca665d324a5b.jpg/720x540.webp",
						coche3);

				Imagen imagenCoche35 = new Imagen(24L,
						"https://prod.pictures.autoscout24.net/listing-images/6307983c-ec56-44fb-8117-5a4ff78ace32_664ffeea-56ab-4d03-8be8-e2f7eace3d0d.jpg/720x540.webp",
						coche2);
				Imagen imagenCoche36 = new Imagen(25L,
						"https://prod.pictures.autoscout24.net/listing-images/6307983c-ec56-44fb-8117-5a4ff78ace32_7244afc7-b98a-49dd-9c83-880b2a688ddf.jpg/720x540.webp",
						coche2);
				Imagen imagenCoche37 = new Imagen(26L,
						"https://prod.pictures.autoscout24.net/listing-images/6307983c-ec56-44fb-8117-5a4ff78ace32_678291a1-aea2-4e64-9b57-b9a05cf46e2d.jpg/720x540.webp",
						coche2);
				Imagen imagenCoche38 = new Imagen(27L,
						"https://prod.pictures.autoscout24.net/listing-images/6307983c-ec56-44fb-8117-5a4ff78ace32_7e92f1dd-c3a4-4d21-bbcd-2ad65232c362.jpg/720x540.webp",
						coche2);
				Imagen imagenCoche39 = new Imagen(28L,
						"https://prod.pictures.autoscout24.net/listing-images/6307983c-ec56-44fb-8117-5a4ff78ace32_c2e3abd6-6e6b-4c16-95c8-8d5d8675b5e2.jpg/720x540.webp",
						coche2);

				// Guardar las imágenes en la base de datos
				imagenRepositorio.save(imagenCoche1);
				imagenRepositorio.save(imagenCoche2);
				imagenRepositorio.save(imagenCoche3);
				imagenRepositorio.save(imagenCoche4);
				imagenRepositorio.save(imagenCoche5);
				imagenRepositorio.save(imagenCoche6);
				imagenRepositorio.save(imagenCoche7);

				imagenRepositorio.save(imagenCoche8);
				imagenRepositorio.save(imagenCoche9);
				imagenRepositorio.save(imagenCoche10);
				imagenRepositorio.save(imagenCoche11);
				imagenRepositorio.save(imagenCoche12);
				imagenRepositorio.save(imagenCoche13);
				imagenRepositorio.save(imagenCoche14);

				imagenRepositorio.save(imagenCoche15);
				imagenRepositorio.save(imagenCoche16);
				imagenRepositorio.save(imagenCoche17);
				imagenRepositorio.save(imagenCoche18);
				imagenRepositorio.save(imagenCoche19);
				imagenRepositorio.save(imagenCoche20);
				imagenRepositorio.save(imagenCoche21);
				imagenRepositorio.save(imagenCoche22);
				imagenRepositorio.save(imagenCoche23);
				imagenRepositorio.save(imagenCoche24);

				imagenRepositorio.save(imagenCoche25);
				imagenRepositorio.save(imagenCoche26);
				imagenRepositorio.save(imagenCoche27);
				imagenRepositorio.save(imagenCoche28);
				imagenRepositorio.save(imagenCoche29);
				imagenRepositorio.save(imagenCoche30);

				imagenRepositorio.save(imagenCoche31);
				imagenRepositorio.save(imagenCoche32);
				imagenRepositorio.save(imagenCoche33);
				imagenRepositorio.save(imagenCoche34);
				imagenRepositorio.save(imagenCoche35);
				imagenRepositorio.save(imagenCoche36);
				imagenRepositorio.save(imagenCoche37);
				imagenRepositorio.save(imagenCoche38);
				imagenRepositorio.save(imagenCoche39);
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