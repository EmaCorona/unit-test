package it.corona.unitest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.corona.unitest.model.dto.PokemonDto;
import it.corona.unitest.service.PokemonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PokemonController.class)
public class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PokemonService pokemonService;
    private PokemonDto pokemonDto;

    @BeforeEach
    public void setUp() {
        pokemonDto = PokemonDto.builder()
                .pokemonId(1L)
                .name("Pikachu")
                .type("Electro")
                .build();
    }

    @Test
    public void PokemonController_CreatePokemon_ReturnCreatedPokemon() throws Exception {
        when(pokemonService.createPokemon(any(PokemonDto.class))).thenReturn(pokemonDto);

        mockMvc.perform(post("/pokemon/create-pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pokemonDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(pokemonDto)));
    }

    @Test
    public void PokemonController_UpdatePokemon_ReturnUpdatedPokemon() throws Exception {
        PokemonDto raichu = PokemonDto.builder().name("Raichu").type("Electro").build();

        when(pokemonService.updatePokemon(any(PokemonDto.class), any(Long.class))).thenReturn(raichu);

        mockMvc.perform(put("/pokemon/update-pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pokemonDto))
                        .param("pokemonId", String.valueOf(pokemonDto.getPokemonId()))
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(raichu)));
    }

    @Test
    public void PokemonController_FindById_ReturnFoundPokemon() throws Exception {
        when(pokemonService.findById(any(Long.class))).thenReturn(pokemonDto);

        Long pokemonId = 1L;

        mockMvc.perform(get("/pokemon/find-by-id/{pokemonId}", pokemonId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pokemonDto)));
    }

    @Test
    public void PokemonController_FindAll_ReturnFoundedPokemons() throws Exception {
        when(pokemonService.findAllPokemon()).thenReturn(List.of(pokemonDto));

        mockMvc.perform(get("/pokemon/find-all-pokemons")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void PokemonController_DeletePokemon_ReturnDeletedPokemon() throws Exception {
        doNothing().when(pokemonService).deletePokemonById(pokemonDto.getPokemonId());

        mockMvc.perform(delete("/pokemon/delete-pokemon")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("pokemonId", String.valueOf(pokemonDto.getPokemonId())))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pokemonDto)));
    }
}