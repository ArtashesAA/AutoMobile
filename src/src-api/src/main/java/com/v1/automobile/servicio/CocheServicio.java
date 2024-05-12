package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.entidad.Favorito;
import com.v1.automobile.entidad.Imagen;
import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.entidad.dto.CocheDTO;
import com.v1.automobile.entidad.dto.ImagenDTO;
import com.v1.automobile.entidad.dto.UsuarioDTO;
import com.v1.automobile.entidad.request.CocheRequest;
import com.v1.automobile.repositorio.CocheRepositorio;
import com.v1.automobile.repositorio.FavoritoRepositorio;
import com.v1.automobile.repositorio.ImagenRepositorio;
import com.v1.automobile.repositorio.UsuarioRepositorio;

@Service
public class CocheServicio {

	@Autowired
	private CocheRepositorio cocheRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private ImagenRepositorio imagenRepositorio;

	@Autowired
	private ImagenServicio imagenServicio;
	
	@Autowired
	private FavoritoRepositorio favoritoRepositorio;

	public List<CocheDTO> getAllCoches() {
		List<Coche> coches = cocheRepositorio.findAll();
		return coches.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public ResponseEntity<CocheDTO> getCocheById(Long id) {
		Optional<Coche> cocheOptional = cocheRepositorio.findById(id);
		if (cocheOptional.isPresent()) {
			Coche coche = cocheOptional.get();
			CocheDTO cocheDTO = convertToDto(coche);
			return ResponseEntity.ok(cocheDTO);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<String> addCoche(CocheRequest cocheRequest) {
		try {
			// Crea un nuevo coche
			Coche coche = new Coche();
			coche.setId(cocheRequest.getId());
			coche.setMarca(cocheRequest.getMarca());
			coche.setModelo(cocheRequest.getModelo());
			coche.setAnyo(cocheRequest.getAnyo());
			coche.setPotencia(cocheRequest.getPotencia());
			coche.setKilometraje(cocheRequest.getKilometraje());
			coche.setPeso(cocheRequest.getPeso());
			coche.setCombustible(cocheRequest.getCombustible());
			coche.setColor(cocheRequest.getColor());
			coche.setPrecio(cocheRequest.getPrecio());
			coche.setDescripcion(cocheRequest.getDescripcion());
			coche.setTipoCambio(cocheRequest.getTipoCambio());
			coche.setConsumo(cocheRequest.getConsumo());
			coche.setCategoria(cocheRequest.getCategoria());
			coche.setTipoVehiculo(cocheRequest.getTipoVehiculo());
			coche.setTraccion(cocheRequest.getTraccion());
			coche.setPlazas(cocheRequest.getPlazas());
			coche.setPuertas(cocheRequest.getPuertas());
			coche.setGarantia(cocheRequest.getGarantia());
			coche.setNumeroMarchas(cocheRequest.getNumeroMarchas());
			coche.setNumeroCilindros(cocheRequest.getNumeroCilindros());
			coche.setCiudad(cocheRequest.getCiudad());
			coche.setDescripcion(cocheRequest.getDescripcion());
			

			// Obtiene el usuario asociado al coche
			Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(cocheRequest.getUsuarioId());
			if (optionalUsuario.isPresent()) {
				coche.setUsuario(optionalUsuario.get());
			} else {
				System.out.println("Error");
				return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
			}
			

			// Guarda el coche
			Coche savedCoche = cocheRepositorio.save(coche);

			// Guarda las imágenes asociadas
			List<ImagenDTO> imagenes = cocheRequest.getImagenes();
			for (ImagenDTO imagenDTO : imagenes) {
				ResponseEntity<String> response = imagenServicio.addImagen(savedCoche.getId(),
						imagenDTO.getImagen_url());
				if (response.getStatusCode() != HttpStatus.OK) {
					// Mensaje de error
					return new ResponseEntity<>(response.getBody(), response.getStatusCode());
				}
			}

			return new ResponseEntity<>("Coche creado correctamente", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al crear el coche", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> updateCoche(Long id, Coche nuevoCoche) {
		Coche cocheExistente = cocheRepositorio.findById(id).orElse(null);
		if (cocheExistente != null) {
			// Actualizar los campos del coche existente con los datos del nuevo coche
			cocheExistente.setMarca(nuevoCoche.getMarca());
			cocheExistente.setModelo(nuevoCoche.getModelo());
			cocheExistente.setImagen_principal(nuevoCoche.getImagen_principal());
			cocheExistente.setPrecio(nuevoCoche.getPrecio());
			cocheExistente.setAnyo(nuevoCoche.getAnyo());
			cocheExistente.setPotencia(nuevoCoche.getPotencia());
			cocheExistente.setKilometraje(nuevoCoche.getKilometraje());
			cocheExistente.setCombustible(nuevoCoche.getCombustible());
			cocheExistente.setConsumo(nuevoCoche.getConsumo());
			cocheExistente.setTipoCambio(nuevoCoche.getTipoCambio());
			cocheExistente.setCategoria(nuevoCoche.getCategoria());
			cocheExistente.setTipoVehiculo(nuevoCoche.getTipoVehiculo());
			cocheExistente.setTraccion(nuevoCoche.getTraccion());
			cocheExistente.setPlazas(nuevoCoche.getPlazas());
			cocheExistente.setPuertas(nuevoCoche.getPuertas());
			cocheExistente.setGarantia(nuevoCoche.getGarantia());
			cocheExistente.setPeso(nuevoCoche.getPeso());
			cocheExistente.setColor(nuevoCoche.getColor());
			cocheExistente.setNumeroMarchas(nuevoCoche.getNumeroMarchas());
			cocheExistente.setNumeroCilindros(nuevoCoche.getNumeroCilindros());
			cocheExistente.setCiudad(nuevoCoche.getCiudad());
			cocheExistente.setDescripcion(nuevoCoche.getDescripcion());
			cocheExistente.setTelefonoAdjunto(nuevoCoche.getTelefonoAdjunto());
			cocheExistente.setEmailAdjunto(nuevoCoche.getEmailAdjunto());

			// Guardar el coche actualizado
			cocheRepositorio.save(cocheExistente);

			return ResponseEntity.ok().body("Coche con ID " + id + " actualizado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<String> deleteCoche(Long cocheId) {
		try {
			// Verificar si el coche con el ID proporcionado existe
			Optional<Coche> optionalCoche = cocheRepositorio.findById(cocheId);
			if (!optionalCoche.isPresent()) {
				return new ResponseEntity<>("Coche no encontrado", HttpStatus.NOT_FOUND);
			}

			// Eliminar las imágenes asociadas al coche
			List<Imagen> imagenes = imagenRepositorio.findByCocheId(cocheId);
			for (Imagen imagen : imagenes) {
				imagenRepositorio.delete(imagen);
			}

			// Eliminar los favoritos asociados al coche
			List<Favorito> favoritos = favoritoRepositorio.findByCocheId(cocheId);
			for (Favorito favorito : favoritos) {
				favoritoRepositorio.delete(favorito);
			}

			// Eliminar el coche
			cocheRepositorio.deleteById(cocheId);

			return new ResponseEntity<>("Coche eliminado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al eliminar el coche", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private CocheDTO convertToDto(Coche coche) {
		CocheDTO dto = new CocheDTO();
		dto.setId(coche.getId());
		dto.setMarca(coche.getMarca());
		dto.setModelo(coche.getModelo());
		dto.setImagen_principal(coche.getImagen_principal());
		dto.setPrecio(coche.getPrecio());
		dto.setAnyo(coche.getAnyo());
		dto.setPotencia(coche.getPotencia());
		dto.setKilometraje(coche.getKilometraje());
		dto.setCombustible(coche.getCombustible());
		dto.setConsumo(coche.getConsumo());
		dto.setTipoCambio(coche.getTipoCambio());
		dto.setCategoria(coche.getCategoria());
		dto.setTipoVehiculo(coche.getTipoVehiculo());
		dto.setTraccion(coche.getTraccion());
		dto.setPlazas(coche.getPlazas());
		dto.setPuertas(coche.getPuertas());
		dto.setGarantia(coche.getGarantia());
		dto.setPeso(coche.getPeso());
		dto.setColor(coche.getColor());
		dto.setNumeroMarchas(coche.getNumeroMarchas());
		dto.setNumeroCilindros(coche.getNumeroCilindros());
		dto.setCiudad(coche.getCiudad());
		dto.setDescripcion(coche.getDescripcion());
		dto.setTelefonoAdjunto(coche.getTelefonoAdjunto());
		dto.setEmailAdjunto(coche.getEmailAdjunto());

		Usuario usuario = coche.getUsuario();
		if (usuario != null) {
			dto.setUsuario(convertUsuarioToDto(usuario));
		}

		List<ImagenDTO> imagenDTOs = coche.getImagenes().stream().map(imagen -> new ImagenDTO(imagen.getImagen_url()))
				.collect(Collectors.toList());
		dto.setImagenes(imagenDTOs);

		return dto;
	}

	private UsuarioDTO convertUsuarioToDto(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNombre_usuario(usuario.getNombre_usuario());
		usuarioDTO.setImagen_usuario(usuario.getImagen_usuario());
		return usuarioDTO;
	}

	private Coche convertToEntity(CocheDTO cocheDTO) {
		Coche coche = new Coche();
		coche.setId(cocheDTO.getId());
		coche.setMarca(cocheDTO.getMarca());
		coche.setModelo(cocheDTO.getModelo());
		coche.setImagen_principal(cocheDTO.getImagen_principal());
		coche.setPrecio(cocheDTO.getPrecio());
		coche.setAnyo(cocheDTO.getAnyo());
		coche.setPotencia(cocheDTO.getPotencia());
		coche.setKilometraje(cocheDTO.getKilometraje());
		coche.setCombustible(cocheDTO.getCombustible());
		coche.setConsumo(cocheDTO.getConsumo());
		coche.setTipoCambio(cocheDTO.getTipoCambio());
		coche.setCategoria(cocheDTO.getCategoria());
		coche.setTipoVehiculo(cocheDTO.getTipoVehiculo());
		coche.setTraccion(cocheDTO.getTraccion());
		coche.setPlazas(cocheDTO.getPlazas());
		coche.setPuertas(cocheDTO.getPuertas());
		coche.setGarantia(cocheDTO.getGarantia());
		coche.setPeso(cocheDTO.getPeso());
		coche.setColor(cocheDTO.getColor());
		coche.setNumeroMarchas(cocheDTO.getNumeroMarchas());
		coche.setNumeroCilindros(cocheDTO.getNumeroCilindros());
		coche.setCiudad(cocheDTO.getCiudad());
		coche.setDescripcion(cocheDTO.getDescripcion());
		coche.setTelefonoAdjunto(cocheDTO.getTelefonoAdjunto());
		coche.setEmailAdjunto(cocheDTO.getEmailAdjunto());

		UsuarioDTO usuarioDTO = cocheDTO.getUsuario();
		if (usuarioDTO != null) {
			coche.setUsuario(convertDtoToUsuario(usuarioDTO));
		}

		List<Imagen> imagenes = cocheDTO.getImagenes().stream()
				.map(imagenDTO -> new Imagen(null, imagenDTO.getImagen_url(), coche)).collect(Collectors.toList());
		coche.setImagenes(imagenes);

		return coche;
	}

	private Usuario convertDtoToUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setNombre_usuario(usuarioDTO.getNombre_usuario());
		usuario.setImagen_usuario(usuarioDTO.getImagen_usuario());
		return usuario;
	}

	public List<CocheDTO> cochesFiltrados(String marca, String modelo, Integer anyo, Integer precio) {
		List<Coche> coches = cocheRepositorio.findByMarcaAndModeloAndAnyoAndPrecio(marca, modelo, anyo, precio);
		return coches.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<CocheDTO> cochesPorMarca(String marca) {
		List<Coche> coches = cocheRepositorio.findByMarca(marca);
		return coches.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<CocheDTO> cochesPorMarcaModelo(String marca, String modelo) {
		List<Coche> coches = cocheRepositorio.findByMarcaAndModelo(marca, modelo);
		return coches.stream().map(this::convertToDto).collect(Collectors.toList());
	}
}
