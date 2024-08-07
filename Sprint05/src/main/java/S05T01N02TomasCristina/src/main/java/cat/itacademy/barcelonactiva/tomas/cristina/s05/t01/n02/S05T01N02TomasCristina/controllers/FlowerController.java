package cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n02.S05T01N02TomasCristina.controllers;

import cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n02.S05T01N02TomasCristina.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n02.S05T01N02TomasCristina.model.service.imp.FlowerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flower")
public class FlowerController {
    @Autowired
    private FlowerServiceImp flowerServiceImp;

    @PostMapping("/add")
    public ResponseEntity<FlowerDTO> addFlower(@RequestBody FlowerDTO flowerDTO) {
        FlowerDTO createdFlower = flowerServiceImp.addFlower(flowerDTO);
        return ResponseEntity.ok(createdFlower);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FlowerDTO> updateFlower(@PathVariable int id, @RequestBody FlowerDTO flowerDTO) {
        FlowerDTO updatedFlower = flowerServiceImp.updateFlower(id, flowerDTO);
        return ResponseEntity.ok(updatedFlower);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFlower(@PathVariable int id) {
        flowerServiceImp.deleteFlower(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<FlowerDTO> getOneFlower(@PathVariable int id) {
        FlowerDTO flowerDTO = flowerServiceImp.getOneFlower(id);
        return ResponseEntity.ok(flowerDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FlowerDTO>> getAllFlowers() {
        List<FlowerDTO> flowerList = flowerServiceImp.getAllFlowers();
        return ResponseEntity.ok(flowerList);
    }
}
