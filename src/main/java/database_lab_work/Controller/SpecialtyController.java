package database_lab_work.Controller;

import database_lab_work.App;
import database_lab_work.Interface.EntityControllerInterface;
import database_lab_work.Model.Specialty;
import database_lab_work.Repository.DatabaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gman0_000 on 27.04.2017.
 */
@RestController(value= SpecialtyController.BASE_PATH)
@Api(value = "specialty")
public class SpecialtyController implements EntityControllerInterface<Specialty> {
    public final static String BASE_PATH = "/" + App.ROOT_PATH + "/" + "specialty";

    private DatabaseController db = DatabaseController.getInstance();

    @Override
    @RequestMapping(method = RequestMethod.GET, path=BASE_PATH + "/all")
    @ApiOperation(value = "Get data about all specialties", responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Specialty.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<Specialty>> getAllData() {
        try{
            return new ResponseEntity(db.<Specialty>getAllData("Specialty"), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @RequestMapping(method = RequestMethod.GET, path=BASE_PATH+"/getById/{id}")
    @ApiOperation(value = "Get single instance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Specialty.class),
            @ApiResponse(code = 500, message = "Error"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<Specialty> getById(@PathVariable("id") Long id) {
        try{
            Specialty specialty = db.find(Specialty.class, id);
            if(specialty == null){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(specialty, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, path=BASE_PATH + "/create")
    @ApiOperation(value = "Create new instance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 503, message = "Error"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<?> createNewInstance(@RequestBody Specialty entity){
        try{
            db.insert(Specialty.class, entity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, path=BASE_PATH + "/update/{id}")
    @ApiOperation(value = "Update instance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 503, message = "Error"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<?> updateInstance(@PathVariable("id") Long id, @RequestBody Specialty updatedEntity) {
        try{
            db.update(Specialty.class, id, updatedEntity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, path=BASE_PATH + "/remove/{id}")
    @ApiOperation(value = "Remove instance from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<?> removeInstance(@PathVariable("id") Long id) {
        try{
            db.remove(Specialty.class, id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
