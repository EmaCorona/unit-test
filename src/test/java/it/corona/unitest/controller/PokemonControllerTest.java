package it.corona.unitest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.corona.unitest.enums.PokemonEnum;
import it.corona.unitest.model.dto.*;
import it.corona.unitest.service.PokemonService;
import it.corona.unitest.utils.PokemonMockUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PokemonController.class)
public class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String CREATE_POKEMON_REQUEST = "/pokemon/create-pokemon";

    private static final String DELETE_POKEMON_REQUEST = "/pokemon/delete-pokemon";

    private static final String FIND_BY_ID_POKEMON_REQUEST = "/pokemon/find-pokemon-by-id/";

    private static final String UPDATE_POKEMON_REQUEST = "/pokemon/update-pokemon";

    private static final String WRONG_URL = "/WRONG_URL";

    @Test
    public void GivenValidRequest_CreatePokemon_ReturnCreatedPokemon() throws Exception {
        /* ***************** ARRANGE ***************** */
        PokemonDTO pokemon = PokemonMockUtils.getMockedPikachuDto(false);

        CreatePokemonRequestDTO request = new CreatePokemonRequestDTO();
        request.setPokedexId(pokemon.getPokedexId());
        request.setPokemonName(pokemon.getName());
        request.setPokemonType(pokemon.getType());

        ResponseDTO response = new ResponseDTO();
        response.setPokemonDTO(pokemon);
        response.setHttpStatusCode(HttpStatus.CREATED.value());

        when(pokemonService.createPokemon(request)).thenReturn(response);

        /* ***************** ACT ***************** */
        mockMvc.perform(post(CREATE_POKEMON_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        /* ***************** ASSERT ***************** */
        verify(pokemonService).createPokemon(request);
    }

    @Test
    public void GivenInvalidRequest_CreatePokemon_ReturnBadRequest() throws Exception {
        /* ***************** ARRANGE ***************** */
        ResponseDTO response = new ResponseDTO();
        response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

        when(pokemonService.createPokemon(any())).thenReturn(response);

        /* ***************** ACT ***************** */
        mockMvc.perform(post(CREATE_POKEMON_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(response))
        ).andExpect(status().isBadRequest());

        /* ***************** ASSERT ***************** */
        verify(pokemonService).createPokemon(any());
    }

    @Test
    public void GivenWrongUrl_CreatePokemon_ReturnNotFound() throws Exception {
        /* ***************** ACT ***************** */
        mockMvc.perform(post(CREATE_POKEMON_REQUEST + WRONG_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void GivenValidRequest_UpdatePokemon_ReturnUpdatedPokemon() throws Exception {
        /* ***************** ARRANGE ***************** */
        PokemonDTO pokemonDto = PokemonMockUtils.getMockedPikachuDto(true);
        PokemonDTO updatedPokemon = PokemonMockUtils.getMockedRaichuDto(false);

        UpdatePokemonRequestDTO request = new UpdatePokemonRequestDTO();
        request.setPokemonId(pokemonDto.getPokemonId());
        request.setPokedexId(updatedPokemon.getPokedexId());
        request.setPokemonName(updatedPokemon.getName());
        request.setPokemonType(updatedPokemon.getType());

        ResponseDTO response = new ResponseDTO();
        response.setPokemonDTO(updatedPokemon);
        response.setHttpStatusCode(HttpStatus.OK.value());

        when(pokemonService.updatePokemon(request)).thenReturn(response);

        /* ***************** ACT ***************** */
        mockMvc.perform(put(UPDATE_POKEMON_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        /* ***************** ASSERT ***************** */
        verify(pokemonService).updatePokemon(request);
    }

    @Test
    public void GivenInvalidRequest_UpdatePokemon_ReturnBadRequest() throws Exception {
        /* ***************** ARRANGE ***************** */
        UpdatePokemonRequestDTO request = new UpdatePokemonRequestDTO();

        ResponseDTO response = new ResponseDTO();
        response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

        when(pokemonService.updatePokemon(request)).thenReturn(response);

        /* ***************** ACT ***************** */
        mockMvc.perform(put(UPDATE_POKEMON_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        /* ***************** ASSERT ***************** */
        verify(pokemonService).updatePokemon(request);
    }

    @Test
    public void GivenWrongUrl_UpdatePokemon_ReturnNotFound() throws Exception {
        /* ***************** ACT ***************** */
        mockMvc.perform(post(UPDATE_POKEMON_REQUEST + WRONG_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
//
//    @Test
//    public void PokemonController_FindById_ReturnFoundPokemon() throws Exception {
//        when(pokemonService.findById(any(Long.class))).thenReturn(pokemonDto);
//
//        Long pokemonId = 1L;
//
//        mockMvc.perform(get("/pokemon/find-by-id/{pokemonId}", pokemonId)
//                        .accept(MediaType.APPLICATION_JSON)
//                ).andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(pokemonDto)));
//    }
//
//    @Test
//    public void PokemonController_FindAll_ReturnFoundedPokemons() throws Exception {
//        when(pokemonService.findAllPokemon()).thenReturn(List.of(pokemonDto));
//
//        mockMvc.perform(get("/pokemon/find-all-pokemons")
//                .accept(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk());
//    }

    @Test
    public void GivenNonExistingPokemon_FindById_ReturnNotFound() throws Exception {
        /* ***************** ACT ***************** */
        mockMvc.perform(get(FIND_BY_ID_POKEMON_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("pokemonId", "999")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void GivenValidRequest_DeletePokemon_ReturnDeletedPokemon() throws Exception {
        /* ***************** ARRANGE ***************** */
        DeletePokemonRequestDTO request = new DeletePokemonRequestDTO();
        request.setPokemonId(1L);
        request.setPokemonName(PokemonEnum.CHARMANDER.getPokemonName());

        ResponseDTO response = new ResponseDTO();
        response.setHttpStatusCode(HttpStatus.OK.value());

        when(pokemonService.deletePokemon(request)).thenReturn(response);

        /* ***************** ACT ***************** */
        mockMvc.perform(delete(DELETE_POKEMON_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        /* ***************** ASSERT ***************** */
        verify(pokemonService).deletePokemon(request);
    }

    @Test
    public void GivenInvalidRequest_DeletePokemon_ReturnBadRequest() throws Exception {
        /* ***************** ARRANGE ***************** */
        DeletePokemonRequestDTO request = new DeletePokemonRequestDTO();

        ResponseDTO response = new ResponseDTO();
        response.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());

        when(pokemonService.deletePokemon(request)).thenReturn(response);

        /* ***************** ACT ***************** */
        mockMvc.perform(delete(DELETE_POKEMON_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

        /* ***************** ASSERT ***************** */
        verify(pokemonService).deletePokemon(request);
    }

    @Test
    public void GivenWrongUrl_DeletePokemon_ReturnNotFound() throws Exception {
        /* ***************** ACT ***************** */
        mockMvc.perform(post(DELETE_POKEMON_REQUEST + WRONG_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
}