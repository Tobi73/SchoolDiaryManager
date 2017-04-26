package database_lab_work.Controller;

import database_lab_work.App;
import database_lab_work.Interface.EntityControllerInterface;
import database_lab_work.Model.Discipline;
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
 * Created by gman0_000 on 26.04.2017.
 */

@RestController(value= DisciplineController.BASE_PATH)
@Api(value = "discipline")
public class DisciplineController implements EntityControllerInterface<Discipline> {
    public final static String BASE_PATH = "/" + App.ROOT_PATH + "/" + "discipline";

    private DatabaseController db = DatabaseController.getInstance();

    @Override
    @RequestMapping(method = RequestMethod.GET, path=BASE_PATH + "/all")
    @ApiOperation(value = "Gell data about all disciplines in school", responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Discipline.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<Discipline>> getAllData() {
        try{
            return new ResponseEntity(db.<Discipline>getAllData("Discipline"), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @RequestMapping(method = RequestMethod.GET, path=BASE_PATH+"/getById/{id}")
    @ApiOperation(value = "Get single instance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Discipline.class),
            @ApiResponse(code = 500, message = "Error"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<Discipline> getById(@PathVariable("id") Long id) {
        try{
            Discipline discipline = db.find(Discipline.class, id);
            if(discipline == null){
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity(discipline, HttpStatus.OK);
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
    public ResponseEntity<?> createNewInstance(@RequestBody Discipline entity){
        try{
            db.insert(Discipline.class, entity);
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
    public ResponseEntity<?> updateInstance(@PathVariable("id") Long id, @RequestBody Discipline updatedEntity) {
        try{
            db.update(Discipline.class, id, updatedEntity);
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
            db.remove(Discipline.class, id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
