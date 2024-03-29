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

	@Transactional
	public ResponseEntity<String> addCoche(CocheDTO cocheDTO, Integer usuarioId) {
		try {
			// Crea un nuevo coche
			Coche coche = new Coche();
			coche.setMarca(cocheDTO.getMarca());
			coche.setModelo(cocheDTO.getModelo());
			coche.setAnyo(cocheDTO.getAnyo());
			coche.setPotencia(cocheDTO.getPotencia());
			coche.setKilometraje(cocheDTO.getKilometraje());
			coche.setPeso(cocheDTO.getPeso());
			coche.setCombustible(cocheDTO.getCombustible());
			coche.setColor(cocheDTO.getColor());
			coche.setPrecio(cocheDTO.getPrecio());
			coche.setDescripcion(cocheDTO.getDescripcion());

			Usuario usuario = new Usuario();
	        usuario.setId(usuarioId);
	        coche.setUsuario(usuario);
			
			// Guarda el coche
			Coche savedCoche = cocheRepositorio.save(coche);

			// Guarda las imagenes asociadas
			List<ImagenDTO> imagenes = cocheDTO.getImagenes();
			for (ImagenDTO imagenDTO : imagenes) {
				Imagen imagen = new Imagen();
				imagen.setCoche(savedCoche);
				imagen.setImagen_url(imagenDTO.getImagen_url());
				imagenRepositorio.save(imagen);
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

			// Guardar el coche actualizado
			cocheRepositorio.save(cocheExistente);

			return ResponseEntity.ok().body("Coche con ID " + id + " actualizado correctamente");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<String> deleteCoche(Long id) {
		try {
			favoritoRepositorio.deleteByCocheId(id);
			imagenRepositorio.deleteByCocheId(id);
			cocheRepositorio.deleteById(id);

			return new ResponseEntity<>("Coche con ID " + id + " borrado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error al borrar el coche con ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
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

		// Set the UsuarioDTO without including CocheDTO to avoid recursion
		if (coche.getUsuario() != null) {
			Usuario usuario = coche.getUsuario();
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setNombre_usuario(usuario.getNombre_usuario());
			dto.setUsuario(usuarioDTO);
		}

		// Set the ImagenDTO list without including CocheDTO to avoid recursion
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
