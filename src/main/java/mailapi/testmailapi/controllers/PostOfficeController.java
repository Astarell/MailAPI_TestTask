package mailapi.testmailapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mailapi.testmailapi.models.PostOffice;
import mailapi.testmailapi.projections.PostOfficeView;
import mailapi.testmailapi.services.PostOfficeService;
import mailapi.testmailapi.swagger_support_schemas.PostOfficeSchemaMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Post office", description = "The post office API")
@RestController
@RequestMapping("/main/offices")
public class PostOfficeController implements BasicControllerOptions<PostOfficeView, PostOffice>{

    private PostOfficeService postOfficeService;
    @Autowired
    public PostOfficeController(PostOfficeService postOfficeService){
        this.postOfficeService = postOfficeService;
    }

    @Operation(summary = "Gets all post offices")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found all post office records",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping
    @Override
    public ResponseEntity<Iterable<PostOfficeView>> findAll() {
        return new ResponseEntity<>(postOfficeService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the post office record by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the post office record by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "The post office record with this id does not exist"
            )
    })
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PostOfficeView> findById(@PathVariable Long id) {

        if(postOfficeService.findById(id).isPresent()){
            return new ResponseEntity<>(postOfficeService.findById(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Adds the post office record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PostOfficeSchemaMain.class)
                    )
            )
    )
    @PostMapping
    @Override
    public void add(@RequestBody PostOffice entity) {
        postOfficeService.add(entity);
    }

    @Operation(
            summary = "Updates the post office record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PostOfficeSchemaMain.class)
                    )
            )
    )
    @PostMapping("/update")
    @Override
    public void update(@RequestBody PostOffice patchEntity) {
        postOfficeService.update(patchEntity);
    }

    @Operation(summary = "Deletes the post office record by id")
    @PostMapping("/delete/{id}")
    @Override
    public void deleteById(@PathVariable Long id) {
        postOfficeService.deleteById(id);
    }

    @Operation(
            summary = "Deletes the post office record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PostOfficeSchemaMain.class)
                    )
            )
    )
    @PostMapping("/delete")
    @Override
    public void delete(@RequestBody PostOffice entity) {
        postOfficeService.delete(entity);
    }
}
