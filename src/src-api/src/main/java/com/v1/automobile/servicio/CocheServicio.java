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
import com.v1.automobile.entidad.dto.UsuarioDTO;
import com.v1.automobile.repositorio.CocheRepositorio;
import com.v1.automobile.repositorio.FavoritoRepositorio;
import com.v1.automobile.repositorio.ImagenRepositorio;

@Service
public class CocheServicio {

	@Autowired
	private CocheRepositorio cocheRepositorio;

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

	public ResponseEntity<String> addCoche(CocheDTO cocheDTO) {
        try {
            Coche coche = convertToEntity(cocheDTO);
            cocheRepositorio.save(coche);
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
		
		if (coche.getUsuario() != null) {
	        Usuario usuario = coche.getUsuario();
	        UsuarioDTO usuarioDTO = new UsuarioDTO();
	        usuarioDTO.setNombre_usuario(usuario.getNombre_usuario());
	        usuarioDTO.setImagen_usuario(usuario.getImagen_usuario());
	        dto.setUsuario(usuarioDTO);
	    }
		
        return dto;
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
	   
	    if (coche.getUsuario() != null) {
	        Usuario usuario = new Usuario();
	        usuario.setNombre_usuario(coche.getUsuario().getNombre_usuario());
	        usuario.setImagen_usuario(coche.getUsuario().getImagen_usuario());
	        coche.setUsuario(usuario);
	    }
	    
	    return coche;
	}


}
