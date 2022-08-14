package nextstep.station.ui;

import nextstep.station.application.StationService;
import nextstep.station.application.dto.StationRequest;
import nextstep.station.application.dto.StationResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nextstep.auth.authorization.secured.Secured;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/stations")
    public ResponseEntity<StationResponse> createStation(@RequestBody @Valid StationRequest stationRequest) {
        StationResponse station = stationService.saveStation(stationRequest);
        return ResponseEntity.created(URI.create("/stations/" + station.getId())).body(station);
    }

    @GetMapping(value = "/stations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StationResponse> showStations() {
        return stationService.findAllStations();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/stations/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        stationService.deleteStationById(id);
        return ResponseEntity.noContent().build();
    }
}