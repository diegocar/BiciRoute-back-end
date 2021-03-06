package edu.eci.BiciRoute.Controllers;

import java.util.ArrayList;
import java.util.List;

import edu.eci.BiciRoute.services.IPointService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.eci.BiciRoute.Models.Point;
import edu.eci.BiciRoute.Repositories.IPointRepository;

import javax.xml.ws.Response;

@RestController
@RequestMapping("v1/point")
@CrossOrigin
public class PointController{

    @Autowired
    private IPointService iPointService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Point> getAllPoints() {
        return iPointService.getPointList();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Point createPoint(@RequestBody Point point) {
        point.set_id(ObjectId.get());
        iPointService.savePoint(point);
        return point;
    }

    @GetMapping(value = "/commonRoute")
    public ResponseEntity<?> getCommonRoute() {
        try{
            return new ResponseEntity<>(iPointService.getAllCommonRoutes(),HttpStatus.OK);
        } catch (Exception ex) {

            return new ResponseEntity<>("Could not get all common points", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/pointsWithoutCommonRoute")
    public ResponseEntity<?> deletePoint(){
        try{
            List<Point> points = getAllPoints();
            List<Point> pointsWithoutAddress = new ArrayList<>();
            for (Point p: points) {
                if(p.getAddress()==null){
                    pointsWithoutAddress.add(p);
                }
            }
            return new ResponseEntity<>(pointsWithoutAddress, HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>("Could not delete the point", HttpStatus.FORBIDDEN);
        }
    }

}