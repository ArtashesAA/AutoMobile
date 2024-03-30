package com.v1.automobile.servicio;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.v1.automobile.entidad.Coche;
import com.v1.automobile.entidad.CocheDTO;
import com.v1.automobile.entidad.CocheRequest;
import com.v1.automobile.entidad.Favorito;
import com.v1.automobile.entidad.Imagen;
import com.v1.automobile.entidad.ImagenDTO;
import com.v1.automobile.entidad.Usuario;
import com.v1.automobile.entidad.UsuarioDTO;
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

			// Obtiene el usuario asociado al coche
			Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(cocheRequest.getUsuario_id());
			if (optionalUsuario.isPresent()) {
				coche.setUsuario(optionalUsuario.get());
			} else {
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
			cocheExistente.setAnyo(nuevoCoche.getAnyo());
			cocheExistente.setKilometraje(nuevoCoche.getKilometraje());
			cocheExistente.setPeso(nuevoCoche.getPeso());
			cocheExistente.setColor(nuevoCoche.getColor());
			cocheExistente.setPrecio(nuevoCoche.getPrecio());
			cocheExistente.setDescripcion(nuevoCoche.getDescripcion());
			cocheExistente.setPotencia(nuevoCoche.getPotencia());
			cocheExistente.setCombustible(nuevoCoche.getCombustible());
			cocheExistente.setConsumo(nuevoCoche.getConsumo());
			cocheExistente.setTipoCambio(nuevoCoche.getTipoCambio());
			cocheExistente.setCategoria(nuevoCoche.getCategoria());
			cocheExistente.setTipoVehiculo(nuevoCoche.getTipoVehiculo());
			cocheExistente.setTraccion(nuevoCoche.getTraccion());
			cocheExistente.setPlazas(nuevoCoche.getPlazas());
			cocheExistente.setPuertas(nuevoCoche.getPuertas());
			cocheExistente.setGarantia(nuevoCoche.getGarantia());
			cocheExistente.setNumeroMarchas(nuevoCoche.getNumeroMarchas());
			cocheExistente.setNumeroCilindros(nuevoCoche.getNumeroCilindros());
			cocheExistente.setCiudad(nuevoCoche.getCiudad());

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

	@Transactional
	public Usuario addCocheToUsuario(Coche coche, Integer usuarioId) {
		Usuario usuario = usuarioRepositorio.findById(usuarioId)
				.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + usuarioId));
		usuario.getCoches().add(coche);
		return usuarioRepositorio.save(usuario);
	}

	private CocheDTO convertToDto(Coche coche) {
		CocheDTO dto = new CocheDTO();

		dto.setMarca(coche.getMarca());
		dto.setModelo(coche.getModelo());
		dto.setAnyo(coche.getAnyo());
		dto.setPotencia(coche.getPotencia());
		dto.setKilometraje(coche.getKilometraje());
		dto.setPeso(coche.getPeso());
		dto.setCombustible(coche.getCombustible());
		dto.setColor(coche.getColor());
		dto.setPrecio(coche.getPrecio());
		dto.setDescripcion(coche.getDescripcion());
		dto.setConsumo(coche.getConsumo());
		dto.setTipoCambio(coche.getTipoCambio());
		dto.setCategoria(coche.getCategoria());
		dto.setTipoVehiculo(coche.getTipoVehiculo());
		dto.setTraccion(coche.getTraccion());
		dto.setPlazas(coche.getPlazas());
		dto.setPuertas(coche.getPuertas());
		dto.setGarantia(coche.getGarantia());
		dto.setNumeroMarchas(coche.getNumeroMarchas());
		dto.setNumeroCilindros(coche.getNumeroCilindros());
		dto.setCiudad(coche.getCiudad());

		if (coche.getUsuario() != null) {
			Usuario usuario = coche.getUsuario();
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setNombre_usuario(usuario.getNombre_usuario());
			usuarioDTO.setImagen_usuario(usuario.getImagen_usuario());
			dto.setUsuario(usuarioDTO);
		}

		List<Imagen> imagenes = coche.getImagenes();
		if (imagenes != null) {
			List<ImagenDTO> imagenesDTO = imagenes.stream().map(this::convertImagenToDto).collect(Collectors.toList());
			dto.setImagenes(imagenesDTO);
		}

		return dto;
	}

	private ImagenDTO convertImagenToDto(Imagen imagen) {
		ImagenDTO imagenDTO = new ImagenDTO();
		imagenDTO.setImagen_url(imagen.getImagen_url());
		return imagenDTO;
	}

}
