package mailapi.testmailapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mailapi.testmailapi.forms.TrackingUpdateForm;
import mailapi.testmailapi.models.PackageTracking;
import mailapi.testmailapi.projections.PackageTrackingView;
import mailapi.testmailapi.services.PackageTrackingService;
import mailapi.testmailapi.swagger_support_schemas.PackageTrackingSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Package tracking", description = "The package tracking API")
@RestController
@RequestMapping("/main/tracks")
public class PackageTrackingController implements BasicControllerOptions<PackageTrackingView, PackageTracking>{

    private PackageTrackingService trackingService;

    @Autowired
    public PackageTrackingController(PackageTrackingService trackingService){
        this.trackingService = trackingService;
    }

    @Operation(summary = "Gets all track records")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found all track records",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            )
    })
    @GetMapping
    @Override
    public ResponseEntity<Iterable<PackageTrackingView>> findAll() {
        return new ResponseEntity<>(trackingService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Gets the track record by id")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the track record by id",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "The track record with this id does not exist"
            )
    })
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PackageTrackingView> findById(@PathVariable Long id) {

        if(trackingService.findById(id).isPresent()){
            return new ResponseEntity<>(trackingService.findById(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Adds the track record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PackageTrackingSchema.class)
                    )
            )
    )
    @PostMapping
    @Override
    public void add(@RequestBody PackageTracking track){
        trackingService.add(track);
    }

    @Operation(
            summary = "Updates the track record",
            description = "Lead to empty service update method, can be used in future",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PackageTrackingSchema.class)
                    )
            )
    )
    // Lead to empty service update method
    @PostMapping("/update")
    @Override
    public void update(@RequestBody PackageTracking patchEntity) {
        trackingService.update(patchEntity);
    }

    /**
     * Method for updating postage's status, reassign it's post-office, change status, add shippingInfo
     * @param trackForm the form for updating postage's status
     */
    @Operation(
            summary = "Reassigns the track record",
            description = """
                    It's the method for updating postage's status, reassign it's post-office, change status, add shippingInfo\n
                    'trackId' - Long field - the unique package track\n
                    'officeId' - Long field - the unique post office id, an employee of the post office enters the number of the received parcel, sends a request, and new information about the intermediate point is added to the track of the parcel.\n
                    'received' - Boolean field - if the recipient has picked up the parcel, the employee of the post office sets the desired option, as a result of which the parcel is considered delivered 
                    """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TrackingUpdateForm.class)
                    )
            )
    )
    @PostMapping("/reassign")
    public void reassign(@RequestBody TrackingUpdateForm trackForm){
        trackingService.reassign(trackForm);
    }

    @Operation(summary = "Deletes the track record by id")
    @PostMapping("/delete/{id}")
    @Override
    public void deleteById(@PathVariable Long id) {
        trackingService.deleteById(id);
    }

    @Operation(
            summary = "Deletes the track record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PackageTrackingSchema.class)
                    )
            )
    )
    @PostMapping("/delete")
    @Override
    public void delete(@RequestBody PackageTracking entity) {
        trackingService.delete(entity);
    }
}
