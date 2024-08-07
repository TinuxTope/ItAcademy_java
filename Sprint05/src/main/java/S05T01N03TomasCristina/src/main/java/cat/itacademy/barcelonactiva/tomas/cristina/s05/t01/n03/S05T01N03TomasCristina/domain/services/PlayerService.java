package cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n03.S05T01N03TomasCristina.domain.services;

import cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n03.S05T01N03TomasCristina.domain.model.Player;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n03.S05T01N03TomasCristina.api.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n03.S05T01N03TomasCristina.infrastructure.unified.UnifiedPlayerService;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n03.S05T01N03TomasCristina.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/players")
public class PlayerService {
    @Autowired
    private UnifiedPlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO, @RequestParam boolean useMongo) {
        Player player = playerService.savePlayer(playerDTO, useMongo);
        return ResponseEntity.ok(DTOUtils.toPlayerDTO(player));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable int id, @RequestBody PlayerDTO playerDTO, @RequestParam boolean useMongo) {
        Player existingPlayer = playerService.findPlayerById(id, useMongo);
        existingPlayer.setName(playerDTO.getName());
        existingPlayer.setEmail(playerDTO.getEmail());
        existingPlayer.setPassword(playerDTO.getPassword());
        Player updatedPlayer = playerService.savePlayer(DTOUtils.toPlayerDTO(existingPlayer), useMongo);
        return ResponseEntity.ok(DTOUtils.toPlayerDTO(updatedPlayer));
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers(@RequestParam boolean useMongo) {
        List<Player> players = playerService.getAllPlayers(useMongo);
        List<PlayerDTO> responseDTOs = players.stream().map(DTOUtils::toPlayerDTO).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable int id, @RequestParam boolean useMongo) {
        Player player = playerService.findPlayerById(id, useMongo);
        return ResponseEntity.ok(DTOUtils.toPlayerDTO(player));
    }

    @GetMapping("/ranking")
    public ResponseEntity<Double> getRanking(@RequestParam boolean useMongo) {
        List<Player> players = playerService.getAllPlayers(useMongo);
        double averageSuccessRate = players.stream().mapToDouble(Player::getSuccessRate).average().orElse(0.0);
        return ResponseEntity.ok(averageSuccessRate);
    }

    @GetMapping("/ranking/loser")
    public ResponseEntity<PlayerDTO> getLoser(@RequestParam boolean useMongo) {
        List<Player> players = playerService.getAllPlayers(useMongo);
        Player loser = players.stream().min((p1, p2) -> Double.compare(p1.getSuccessRate(), p2.getSuccessRate())).orElse(null);
        return (loser != null) ? ResponseEntity.ok(DTOUtils.toPlayerDTO(loser)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/ranking/winner")
    public ResponseEntity<PlayerDTO> getWinner(@RequestParam boolean useMongo) {
        List<Player> players = playerService.getAllPlayers(useMongo);
        Player winner = players.stream().max((p1, p2) -> Double.compare(p1.getSuccessRate(), p2.getSuccessRate())).orElse(null);
        return (winner != null) ? ResponseEntity.ok(DTOUtils.toPlayerDTO(winner)) : ResponseEntity.notFound().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Implementa la lógica de logout, como invalidar el token JWT
        return ResponseEntity.ok("Logout successful");
    }
}
