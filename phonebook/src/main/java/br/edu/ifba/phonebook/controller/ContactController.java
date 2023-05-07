package br.edu.ifba.phonebook.controller;

import br.edu.ifba.phonebook.domain.dto.request.ContactDtoRequest;
import br.edu.ifba.phonebook.domain.dto.response.ContactDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface ContactController {
    @Operation(summary = "Save only one contact")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Saved with success",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ContactDtoResponse.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "406",
                            description = "Not Acceptable",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ContactDtoResponse.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<ContactDtoResponse> save(
            @Parameter(description = "New contact body content to be created")
            ContactDtoRequest data,
            UriComponentsBuilder builder
    );

    @Operation(summary = "Retrieve all contacts with or without filter")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Retrieval of successful contacts",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ContactDtoResponse.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ContactDtoResponse.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<List<ContactDtoResponse>> find(
            @Parameter(description = "Name for contact to be found (optional)")
            String name,
            Pageable pageable
    );

    @Operation(summary = "Retrieve tip by id")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Retrieval of successful",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ContactDtoResponse.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ContactDtoResponse.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<ContactDtoResponse> findById(
            @Parameter(description = "Contact Id to be searched")
            Long id
    );

    @Operation(summary = "Update only one contact")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated with successful",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ContactDtoResponse.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ContactDtoResponse.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<ContactDtoResponse> update(
            @Parameter(description = "Contact Id to be updated")
            Long id,
            @Parameter(description = "Contact Elements/Body Content to be updated")
            ContactDtoRequest data
    );

    @Operation(summary = "Delete only one contact")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleted with successful",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ContactDtoResponse.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ContactDtoResponse.class)
                                    )
                            }
                    )
            }
    )
    ResponseEntity<ContactDtoResponse> deleteById(
            @Parameter(description = "Contact Id to be deleted")
            Long id
    );
}
