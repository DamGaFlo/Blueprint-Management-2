/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */
@RestController
public class BlueprintAPIController {
    @Autowired
    BlueprintsServices bps;

    @RequestMapping(method = RequestMethod.GET, value = "/blueprints")
    public ResponseEntity<?> getAllBlueprints() {
        try {

            return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar la lista de Blueprints", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/blueprints/{author}")
    public ResponseEntity<?> getBlueprintByAuthor(@PathVariable("author") String author) {
        try {

            return new ResponseEntity<>(bps.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar la lista de Blueprints", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/blueprints/{author}/{bpname}")
    public ResponseEntity<?> getBlueprint(@PathVariable("author") String author,
            @PathVariable("bpname") String bpname) {
        try {

            return new ResponseEntity<>(bps.getBlueprint(author, bpname), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar el Blueprint", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/blueprints")
    public ResponseEntity<?> addNewBlueprint(@RequestBody Blueprint bp) {
        try {
            bps.addNewBlueprint(bp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error en la creacion", HttpStatus.FORBIDDEN);
        }

    }


    @RequestMapping(method = RequestMethod.PUT, value = "/blueprints/{author}/{name}")
    public ResponseEntity<?> addNewBlueprint(@RequestBody Blueprint bp, @PathVariable String author, @PathVariable String name) {
        try {
            if(!bp.getAuthor().equals(author) || !bp.getName().equals(name)) return new ResponseEntity<>("Datos inconsistentes", HttpStatus.FORBIDDEN);
            Blueprint consultbp = bps.getBlueprint(author, name);
            consultbp.setPoints(bp.getPoints());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Blueprint no encontrado", HttpStatus.NOT_FOUND);
        }

    }


}
