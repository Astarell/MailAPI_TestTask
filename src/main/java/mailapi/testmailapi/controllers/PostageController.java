package mailapi.testmailapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mailapi.testmailapi.models.Postage;
import mailapi.testmailapi.projections.PostageView;
import mailapi.testmailapi.services.PostageService;
import mailapi.testmailapi.swagger_support_schemas.PostageSchemaMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Postage", description = "The postage API")
@RestController
@RequestMapping("/main/postages")
public class PostageController implements BasicControllerOptions<PostageView, Postage> {

    private PostageService postageService;
    @Autowired
    public PostageController(PostageService postageService){
        this.postageService = postageService;
    }

    @Operation(summary = "Gets all postage records")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found all postage records",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping
    @Override
    public ResponseEntity<Iterable<PostageView>> findAll() {
        return new ResponseEntity<>(postageService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the postage record by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the postage record by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "The postage record with this id does not exist"
            )
    })
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PostageView> findById(@PathVariable Long id) {

        if(postageService.findById(id).isPresent()){
            return new ResponseEntity<>(postageService.findById(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Adds the postage record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PostageSchemaMain.class)
                    )
            )
    )
    @PostMapping
    @Override
    public void add(@RequestBody Postage entity) {
        postageService.add(entity);
    }

    @Operation(
            summary = "Updates the postage record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PostageSchemaMain.class)
                    )
            )
    )
    @PostMapping("/update")
    @Override
    public void update(@RequestBody Postage patchEntity) {
        postageService.update(patchEntity);
    }

    @Operation(summary = "Deletes the postage record by id")
    @PostMapping("/delete/{id}")
    @Override
    public void deleteById(@PathVariable Long id) {
        postageService.deleteById(id);
    }

    @Operation(
            summary = "Deletes the postage record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PostageSchemaMain.class)
                    )
            )
    )
    @PostMapping("/delete")
    @Override
    public void delete(@RequestBody Postage entity) {
        postageService.delete(entity);
    }
}
