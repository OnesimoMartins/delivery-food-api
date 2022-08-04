package com.deliveryFood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryFood.api.DTO.input.CidadeInput;
import com.deliveryFood.api.DTO.response.CidadeResponse;
import com.deliveryFood.domain.exception.NegocioException;
import com.deliveryFood.domain.exception.ProvinciaNaoEncontradaException;
import com.deliveryFood.domain.repository.CidadeRepository;
import com.deliveryFood.domain.service.CidadeService;
import com.deliveryFood.util.ResponseUtils;

@RestController
@RequestMapping("/cidades")
class CidadeController {

	@Autowired
	private CidadeRepository cidades;

	@Autowired
	private CidadeService cidadeService;

	@GetMapping
	public List<CidadeResponse> mostrarTodas() {
		return ResponseUtils.toCidadeResponseList(
				cidades.findAll()
				);
	}

	@GetMapping("/{cidadeId}")
	public CidadeResponse consultar(@PathVariable("cidadeId") long id) {
		return new CidadeResponse(
				cidadeService.buscarPorIdOuFalhar(id)
				);
	}

	@PostMapping
	public CidadeResponse cadastrar(@RequestBody @Valid CidadeInput cidade) {
			var cidadeSalva=cidadeService.cadatrar(cidade.toCidade());
			return new CidadeResponse(cidadeSalva);
	}

	@PutMapping("/{cidadeId}")
	public CidadeResponse actualizar(@RequestBody @Valid CidadeInput cidadeNova,
			@PathVariable("cidadeId") long id) {
	
		var cidadeActual=cidadeService.buscarPorIdOuFalhar(id);
 
	   try {
 	    BeanUtils.copyProperties(cidadeNova.toCidade(), cidadeActual, "id");
 	   
		return  new CidadeResponse(
				cidadeService.actualizar(cidadeActual, id)
				);
		}catch(ProvinciaNaoEncontradaException e) {
				throw new NegocioException("A provincia digitada não existe");
		}
	}

//	@PatchMapping("/{cidadeId}")
//	public Cidade alterarParcial(@RequestBody Map<String, Object> novaCidade,
//			@PathVariable("cidadeId") long id) {
//		Cidade cidadeActual = cidadeService.buscarPorIdOuFalhar(id);
//        mesclar(novaCidade, cidadeActual);
//		return this.actualizar(cidadeActual, id);
//	}
//
//	private void mesclar(Map<String, Object> camposOrigem, Cidade cidadeDestino) {
//       
//        Cidade cidadeOrigem=new ObjectMapper().convertValue(camposOrigem, Cidade.class);
//		
//        camposOrigem.forEach((campo, valor) -> {
//			Field field = ReflectionUtils.findRequiredField(Cidade.class, campo);
//            field.setAccessible(true);
//        	
//			try {
//			
//				Object	novoValor = field.get(cidadeOrigem);
//	      		ReflectionUtils.setField(field, cidadeDestino, novoValor);
//			} catch (IllegalArgumentException | IllegalAccessException e) {
//				//e.printStackTrace();
//			}
//        	
//        });
//	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable("cidadeId") long id) {
		cidadeService.remover(id);
     }
	
}